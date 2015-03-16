import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class AsciiToBinary{
    public static void main(String[] args){

        // validate arguments
        if(args.length < 2){
            System.out.println("Usage: AsciiToBinary inputfile outputfile");
            System.exit(1);
        }
        File in = new File(args[0]);
        File out = new File(args[1]);

        String inText = "";

        System.out.print("Attempting to read file... ");
        try{
            // reads complete file, including line separators, into a string
            inText = new String(Files.readAllBytes(Paths.get(in.getPath())));
        }
        catch(IOException e){
            System.out.println("Failed. Printing stack trace.");
            e.printStackTrace();
            System.out.println("This is a critical failure. Exiting program.");
            System.exit(1);
        }
        System.out.println("Success.");

        System.out.print("Attempting to convert... ");
        String outText = null;
        try {
            outText = Factory.getAsciiToBinaryConverter().toBinary(inText);
        } catch (InterruptedException e) {
            System.out.println("Failed. Printing stack trace.");
            e.printStackTrace();
            System.out.println("This is a critical failure. Exiting program.");
            System.exit(1);
        }
        System.out.println("Success.");

        System.out.print("Attempting to write file... ");
        try{
            Factory.getFileWriter().writeFile(out, outText);
        } catch(IOException e){
            System.out.println("Failed. Printing stack trace.");
            e.printStackTrace();
            System.out.println("This is a critical failure. Exiting program.");
            System.exit(1);
        }
        System.out.println("Success. ");
        System.out.println("Cleaning up. Bye bye.");
    }
}
