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
                System.out.println("Result = " + c);

                int[] myNumbers = {1, 2, 3};
                System.out.println(myNumbers[10]);

            } catch (ArithmeticException e) {
                /* Logging errors in the System.err may lead to information leakage. Instead, you should write the error to your logger like so: LOGGER.error("Unexpected error mine: mobb-6e5aaab40be393dbb253c07e9bebf04b"); */
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        } catch (IOException io) {      /* Logging errors in the System.err may lead to information leakage. Instead, you should write the error to your logger like so: LOGGER.error("Unexpected error mine: mobb-6c438fb81308e628de0ea6703d090832"); */}
        catch (Exception e) {
            // do nothing
            /* Logging errors in the System.err may lead to information leakage. Instead, you should write the error to your logger like so: LOGGER.error("Unexpected error mine: mobb-4d9e25c4bd1fb07ad9feaaf4821bb4b0"); */
        }
    }
}
