import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class noLogger {

    public void doSomething() {
        try {
            FileOutputStream fos = new FileOutputStream("fileout.txt");  
            PrintStream ps = new PrintStream(fos);
            FileWriter fw = new FileWriter("output.txt");
            PrintWriter pw = new PrintWriter(fw, true);
            try {
                int a = 30, b = 0;
                int c = a / b; // cannot divide by zero
                /* Using of System.out.println() or System.err.println() may lead to information leakage. Instead, you should write the message to your logger like so: LOGGER.error("Result = " + c); */

                int[] myNumbers = {1, 2, 3};
                /* Using of System.out.println() or System.err.println() may lead to information leakage. Instead, you should write the message to your logger like so: LOGGER.error(myNumbers[10]); */

            } catch (ArithmeticException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        } catch (IOException io) {      io.printStackTrace();}
        catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }
}
