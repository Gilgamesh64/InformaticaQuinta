import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static Connection connection;
    public static Statement stmt;
    public static void main(String[] args) {
        
        try {

            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/customers?user=root&password=");
            stmt = connection.createStatement();
            applyInsert();
            applySelect();

        } catch (SQLException e) {e.printStackTrace();}
    }
    public static void applySelect(){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * from clients");
            while(rs.next()){
                                                  
                System.out.print(rs.getString("customer_id") + " | ");
                System.out.print(rs.getString("first_name") + " | ");
                System.out.print(rs.getString("last_name") + " | ");
                System.out.print(rs.getString("phone") + " | ");
                System.out.print(rs.getString("email") + " | ");
                System.out.print(rs.getString("street") + " | ");
                System.out.print(rs.getString("city") + " | ");
                System.out.print(rs.getString("state") + " | ");
                System.out.print(rs.getString("zip_code") + " | ");
                System.out.println();
            }
            
        } catch (Exception e) {e.printStackTrace();}
    }
    public static void applyInsert(){
        try {
            //stmt.executeUpdate("DELETE FROM clients WHERE customer_id = 11;");
            stmt.executeUpdate("INSERT into clients VALUES(11 ,'Gigi', 'Verdi', NULL, 'gigi.verdi@gmail.com', 'Stato del Veneto st.', 'Marghera City', 'VE', 1205);");
            
        } catch (Exception e) {e.printStackTrace();}
    }
}
