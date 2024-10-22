import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static Connection connection;
    public static Statement stmt;

    public static void menuFullQuery(){
        System.out.println("\nSeleziona l'operazione da eseguire: \nSELECT\nINSERT\nUPDATE\nDELETE\nEXIT");
        switch (System.console().readLine("-> ")) {
            case "SELECT" -> {
                System.out.println("SELECT statement: ");
                applySelect(System.console().readLine("-> "));
            }
            case "INSERT" -> {
                System.out.println("INSERT statement:");
                applyInsert((System.console().readLine("-> ")));
            }
            case "UPDATE" -> {
                System.out.println("UPDATE statement:");
                applyUpdate(System.console().readLine("-> "));
            }
            case "DELETE" -> {
                System.out.println("DELETE statement:");
                applyDelete((System.console().readLine("-> ")));
            }
            case "EXIT" -> {
                System.out.println("Thank you!");
                return;
            }
            default -> {
                System.out.println("Errore\n\n");
            }
        }
        menuFullQuery();
    }

    public static void menuPartialQuery(){
        System.out.println(
            """

            Seleziona l'operazione da eseguire: 
            1 -> SELEZIONE
            2 -> INSERIMENTO
            3 -> MODIFICA
            4 -> CANCELLAZIONE
            5 -> SELEZIONA VOCI CON LO STESSO COGNOME
            6 -> VOCI CON NOME CHE INIZIA CON UNA CERTA LETTERA
            7 -> RIGA CON ETA MEDIA
            8 -> RIGA CON ETA MINIMA
            9 -> RIGA CON ETA MASSIMA
            99 -> ESCI
            """);
        switch (System.console().readLine("-> ")) {
            case "1" -> {
                applySelect("SELECT * FROM dati");
            }
            case "2" -> {
                String nome = System.console().readLine("Nome: ");
                String cognome = System.console().readLine("Cognome: ");
                int età = Integer.parseInt(System.console().readLine("Età: "));
                applyInsert("INSERT INTO dati (Cognome, Nome, Età) VALUES (\"" + cognome + "\", \"" + nome + "\", \"" + età + "\")");
            }
            case "3" -> {
                String campo = System.console().readLine("Campo da aggiornare: ");
                String dato = System.console().readLine("Nuovo dato: ");
                String condizione = System.console().readLine("Condizione: ");
                applyUpdate("UPDATE dati SET " + campo + " = \"" + dato + "\" WHERE " + condizione);
            }
            case "4" -> {
                String condizione = System.console().readLine("Condizione: ");
                applyDelete("DELETE FROM dati WHERE " + condizione);
            }
              
            case "5" -> {
                String cognome = System.console().readLine("Cognome: ");
                applySelect("SELECT * FROM dati" + " WHERE Cognome = \"" + cognome + "\"");
            }
            case "6" -> {
                String letteraNome = System.console().readLine("Lettera: ");
                applySelect("SELECT * FROM dati" + " WHERE Nome LIKE \"" + letteraNome + "%\"");
            }
            case "7" -> {
                applySelectAvg("SELECT AVG(Età) FROM dati;");
            }
            case "8" -> {
                applySelectMin("SELECT MIN(Età) FROM dati");
            }

            case "9" -> {
                applySelectMax("SELECT MAX(Età) FROM dati");
            }
            case "99" -> {
                System.out.println("Thank you!");
                return;
            }
            default -> {
                System.out.println("Errore\n\n");
            }
        }
        menuPartialQuery();
    }
    public static void main(String[] args) {
        
        try {

            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/persone?user=root&password=");
            stmt = connection.createStatement();
            //menuFullQuery();
            menuPartialQuery();

        } catch (SQLException e) {e.printStackTrace();}
    }



    public static void applySelect(String query){
        try {
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.print(rs.getString("ID") + " | ");                              
                System.out.print(rs.getString("Cognome") + " | ");
                System.out.print(rs.getString("Nome") + " | ");
                System.out.print(rs.getString("Età"));
                System.out.println();
            }
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            e.printStackTrace();
            return;
        }
    }


    public static void applySelectAvg(String query){
        try {
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.print(rs.getString("Avg(Età)"));
            }
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            e.printStackTrace();
            return;
        }
    }


    public static void applySelectMin(String query){
        try {
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.print(rs.getString("Min(Età)"));
            }
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            e.printStackTrace();
            return;
        }
    }


    public static void applySelectMax(String query){
        try {
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.print(rs.getString("Max(Età)"));
            }
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            e.printStackTrace();
            return;
        }
    }


    public static void applyInsert(String query){
        try {
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            return;
        }
    }


    public static void applyUpdate(String query){
        try {
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            return;
        }
    }


    public static void applyDelete(String query){
        try {
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            System.out.println("Errore nella query!");
            return;
        }
    }

    
}
