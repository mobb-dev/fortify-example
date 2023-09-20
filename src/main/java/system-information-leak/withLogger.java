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
                System.out.println(myNumbers[10]);


            } catch (ArithmeticException e) {
                LOGGER.error("Unexpected error mine: mobb-5c70d185dadd3a3ec7e4550a629daf17");
            } catch (ArrayIndexOutOfBoundsException ex) {
                LOGGER.error("Unexpected error mine: mobb-2f3f926abf925c155032b1c2ff8dbcef");
            }
        } catch (IOException io) {      LOGGER.error("Unexpected error mine: mobb-1e8df467d70837a5b42c3cf8bcda80f4");}
        catch (Exception e) {
            if (e instanceof RuntimeException) throw e;
            // do nothing
            LOGGER.error("Unexpected error mine: mobb-7cb11431e0d47bdf3eeb8fb234b62e29");
        }
    }
}
