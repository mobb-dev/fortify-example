import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class withLogger {
    private static Logger LOGGER = LogManager.getLogger();

    public void doSomething() {
        try {
            FileOutputStream fos = new FileOutputStream("fileout.txt");  
            PrintStream ps = new PrintStream(fos);
            FileWriter fw = new FileWriter("output.txt");
            PrintWriter pw = new PrintWriter(fw, true);
            try {
                int a = 30, b = 0;
                int c = a / b; // cannot divide by zero
                System.out.println("Result = " + c);

                int[] myNumbers = {1, 2, 3};
                System.out.println(myNumbers[10]);

            } catch (ArithmeticException e) {
                LOGGER.error("Unexpected error: mobb-72f7979db3ca3a91a6ce078520bf5855");
            } catch (ArrayIndexOutOfBoundsException ex) {
                LOGGER.error("Unexpected error: mobb-e686d450cc8cadf8e75b5a180c1e759e");
            }
        } catch (IOException io) {      LOGGER.error("Unexpected error: mobb-281e28adeb3fd7bee3160649195453ab");}
        catch (Exception e) {
            // do nothing
            LOGGER.error("Unexpected error: mobb-cc3540c85305b2930ba8fc1b25b57378");
        }
    }
}
