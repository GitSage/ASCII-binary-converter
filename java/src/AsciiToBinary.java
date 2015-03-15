import java.io.File;

class AsciiToBinary{
    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("Usage: AsciiToBinary inputfile outputfile");
            System.exit(1);
        }
        File in = new File(args[0]);
        File out = new File(args[1]);

        AsciiToBinaryConverter conv = Factory.getAsciiToBinaryConverter();
    }
}
