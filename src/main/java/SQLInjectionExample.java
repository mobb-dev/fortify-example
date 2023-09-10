import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args, Connection con) throws SQLException {
        String userInputA = args[1];

        String query = "SELECT * FROM users WHERE username = '" + userInputA + "';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }
    }
}
