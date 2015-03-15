import sun.misc.ASCIICaseInsensitiveComparator;

import java.io.File;

/**
 * Factory class. Handles the construction of everything.
 * Created by lajinnsolo on 3/15/2015.
 */
public class Factory {
    public static AsciiToBinaryConverter getAsciiToBinaryConverter(){
        return new AsciiToBinaryConverter();
    }
}
