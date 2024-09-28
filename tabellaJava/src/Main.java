import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Es1: \n");
        //applyEs1();
        System.out.println("Es2: \n");
        applyEs2();
    }

    private static void applyEs1(){
        try {

            BufferedReader reader = new BufferedReader(new FileReader("V1Data/persone.csv"));
            BufferedReader reader2 = new BufferedReader(new FileReader("V1Data/persone2.csv"));
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

    private static void applyEs2(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("V2Data/persone.csv"));
            BufferedReader reader2 = new BufferedReader(new FileReader("V2Data/ordini.csv"));
            BufferedReader reader3 = new BufferedReader(new FileReader("V2Data/prodotti.csv"));
            String csv1 = "", csv2 = "", csv3 = "";

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

            String s3 = reader3.readLine();
            while (s3 != null) {
                s3 += "\n";
                csv3 += s3;
                s3 = reader3.readLine(); 
            }
            System.out.println("Rel3: ");
            Relation rel3 = new Relation(csv3);
            System.out.println(rel3.toString());
            System.out.println();

            System.out.println("Product: ");
            System.out.println(CartesianProduct.apply(rel, rel2));
            System.out.println();

            System.out.println("Junction: ");
            ArrayList<String> params = new ArrayList<>();
            params.add("id_utente");
            System.out.println(Junction.apply(rel, rel2, params));
            
            reader.close();
            reader2.close();
            reader3.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
