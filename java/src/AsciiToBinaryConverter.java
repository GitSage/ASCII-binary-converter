/**
 * Converts ordinary ascii text to its psuedo-binary equivalent.
 * "Psuedo-binary" refers to a series of ascii 0's and 1's.
 * Created by lajinnsolo on 3/15/2015.
 */
public class AsciiToBinaryConverter {

    /**
     * Converts an ascii String to a Binary string.
     * @return
     */
    public String toBinary(String in){
        String out = Integer.toString(Integer.parseInt(in), 2);
        return out;
    }
}
