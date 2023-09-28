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
                LOGGER.info(String.valueOf("Result = " + c));

                int[] myNumbers = {1, 2, 3};
                LOGGER.info(String.valueOf(myNumbers[10]));

            } catch (ArithmeticException e) {
                LOGGER.error("webinar error: mobb-72f7979db3ca3a91a6ce078520bf5855");
            } catch (ArrayIndexOutOfBoundsException ex) {
                LOGGER.error("webinar error: mobb-e686d450cc8cadf8e75b5a180c1e759e");
            }
        } catch (IOException io) {      io.printStackTrace();}
        catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }
}
