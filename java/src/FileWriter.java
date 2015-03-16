import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Responsible for writing files.
 * Created by benja_000 on 3/15/2015.
 */
public class FileWriter {
    /**
     * Writes a string to a file.
     * @param file The file to which the string will be written.
     * @param text The string which will be written.
     * @throws IOException
     */
    public void writeFile(File file, String text) throws IOException{
        PrintWriter out = new PrintWriter(file);
        out.print(text);
        out.close();
    }
}
