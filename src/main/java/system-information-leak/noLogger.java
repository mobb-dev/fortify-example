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
                System.err.println("webinar error: mobb-6e5aaab40be393dbb253c07e9bebf04b");
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("webinar error: mobb-a5f44a6ff4bae2bf2670b48b9f4c8a7f");
            }
        } catch (IOException io) {      System.err.println("webinar error: mobb-6c438fb81308e628de0ea6703d090832");}
        catch (Exception e) {
            // do nothing
            System.err.println("webinar error: mobb-4d9e25c4bd1fb07ad9feaaf4821bb4b0");
        }
    }
}
