import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Converts ordinary ascii text to its psuedo-binary equivalent.
 * "Psuedo-binary" refers to a series of ascii 0's and 1's.
 * Created by lajinnsolo on 3/15/2015.
 */
public class AsciiToBinaryConverter extends Converter {

    /**
     * Converts an ascii String to a Binary string.
     * @return String
     * @throws java.lang.InterruptedException
     */
    public String toBinary(String in) throws InterruptedException{
        byte[] bytes = in.getBytes();
        List<FormatThread> threads = new ArrayList<>();


        // spin up one thread for each available processor
        int from = 0;
        int processors = Runtime.getRuntime().availableProcessors();
        for(int i = 0; i < processors; i++){
            int to = Math.min(from + (bytes.length/processors), bytes.length);
            byte[] bytesRange = Arrays.copyOfRange(bytes, from, to);
            from = to;
            FormatThread t = new FormatThread(bytesRange);
            t.start();
            threads.add(t);
        }

        // join each thread in order
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < threads.size(); i++){
            while(!threads.get(i).isDoneFormatting()){
                Thread.sleep(10);
            }
            builder.append(threads.get(i).getOutput());
            threads.get(i).setCanTerminate();
            threads.get(i).join();
        }

        return builder.toString();
    }


    /**
     * The only function of this class is to format an array of bytes and store it in a variable.
     */
    private class FormatThread extends Thread{
        private byte[] unformatted;
        private String output;
        private boolean canTerminate;
        private boolean isDoneFormatting;

        /**
         * Constructor.
         * @param unformatted The byte[] to be formatted.
         */
        public FormatThread(byte[] unformatted) {
            this.unformatted = unformatted;
            this.output = "";
            this.canTerminate = false;
            this.isDoneFormatting = false;
        }

        /**
         * Calls format() to format this thread's particular piece of byte array.
         */
        public void run(){
            output = format(unformatted);
            this.isDoneFormatting = true;
            while(!canTerminate){
                try {
                    sleep(100); // wait 100 milliseconds, poll again
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }

        /**
         * Gets the output of this thread.
         * @return String
         */
        public String getOutput(){
            return output;
        }

        /**
         * @return true if format is complete, false otherwise.
         */
        public boolean isDoneFormatting(){
            return isDoneFormatting;
        }

        /**
         * Informs this thread that it may terminate.
         * It will terminate as soon as possible.
         */
        public void setCanTerminate(){
            this.canTerminate = true;
        }


        /**
         * Returns a formatted string.
         * The format is eight bytes, separated by spaces, followed by a line separator.
         * For example:
         * <code>
         *      00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000
         *      00000000 00000000 00000000
         * </code>
         * @param unformatted a String containing bytes. Each byte must be less than or equal to  11111111 (255).
         * @return A formatted string.
         */
        private String format(byte[] unformatted){
            StringBuilder formatted = new StringBuilder();

            for(int i = 0; i < unformatted.length; i++){
                // pad the left size with zeros, guaranteeing each byte is 8 digits long.
                // May not be an optimal solution but should be fast enough not to matter.
                String toAppend = String.format("%8s", Integer.toBinaryString(unformatted[i])).replace(" ", "0") + " ";
                formatted.append(toAppend);
            }
            return formatted.toString();
        }
    }
}
