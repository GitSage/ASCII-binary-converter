/**
 * Factory class. Handles the construction of everything.
 * Created by lajinnsolo on 3/15/2015.
 */
public class Factory {

    /**
     * Generates a new <code>AsciiToBinaryConverter.</code>
     * @return
     */
    public static AsciiToBinaryConverter getAsciiToBinaryConverter(){
        return new AsciiToBinaryConverter();
    }

    public static FileWriter getFileWriter(){
        return new FileWriter();
    }
}
