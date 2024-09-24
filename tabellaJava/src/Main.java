import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("persone.csv"));
            BufferedReader reader2 = new BufferedReader(new FileReader("persone2.csv"));
            String csv1 = "", csv2 = "";

            String s = reader.readLine();
            while (s != null) {
                s += "\n";
                csv1 += s;
                s = reader.readLine(); 
            }
            System.out.println("Rel1: ");
            Relation rel = new Relation(csv1);
            System.out.println(rel.toString());
            System.out.println();

            String s2 = reader2.readLine();
            while (s2 != null) {
                s2 += "\n";
                csv2 += s2;
                s2 = reader2.readLine(); 
            }
            System.out.println("Rel2: ");
            Relation rel2 = new Relation(csv2);
            System.out.println(rel2.toString());
            System.out.println();

            //Selection
            System.out.println("Selection: ");
            System.out.println(Selection.apply(rel, "nome", "Mario"));
            System.out.println();

            //Projection
            System.out.println("Projection: ");
            ArrayList<String> params = new ArrayList<>();
            params.add("nome");
            params.add("id");
            System.out.println(Projection.apply(rel, params));
            System.out.println();

            //Union
            System.out.println("Union: ");
            System.out.println(Union.apply(rel, rel2));
            System.out.println();

            //Difference
            System.out.println("Difference: ");
            System.out.println(Difference.apply(rel, rel2));
            System.out.println();

            reader.close();
            reader2.close();

        } catch (Exception e) {e.printStackTrace();}
        
        
    }
}
