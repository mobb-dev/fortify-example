import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Test extends HttpServlet {
    private static String OS_NAME = System.getProperty("os.name").toLowerCase();
    private static String OS_VERSION = System.getProperty("os.version");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String foo = request.getParameter("foo");

        response.setContentType("text/html");

        if (foo.equals("foo")) {
            response.getWriter().print("foo " + OS_NAME + OS_VERSION);
        } else if (request.getParameter("bar").equals("bar")) {
            response.getWriter().print("bar " + OS_NAME + OS_VERSION);
        } else {
            response.getWriter().print(OS_NAME + OS_VERSION);
        }
    }
}
