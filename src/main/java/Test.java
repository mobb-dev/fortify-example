import java.util.*;
import java.io.*;

class Test {
    public static void main(String[] args) throws Exception {
        String arg = args[0];
        var command = "ls -laxo " + arg;
        Process proc = Runtime.getRuntime().exec(command);
        proc.waitFor();

        var stdio = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        var stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String line = null;
        while ((line = stdio.readLine()) != null) {
            System.out.println(line);
        }
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
    }
}
