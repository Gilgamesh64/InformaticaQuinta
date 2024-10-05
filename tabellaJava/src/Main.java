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
            Relation relPersone = new Relation(csv1);
            System.out.println(relPersone.toString());
            System.out.println();

            String s2 = reader2.readLine();
            while (s2 != null) {
                s2 += "\n";
                csv2 += s2;
                s2 = reader2.readLine(); 
            }
            System.out.println("relOrdini: ");
            Relation relOrdini = new Relation(csv2);
            System.out.println(relOrdini.toString());
            System.out.println();

            //Selection
            System.out.println("Selection: ");
            System.out.println(Selection.apply(relPersone, "nome", "Mario"));
            System.out.println();

            //Projection
            System.out.println("Projection: ");
            ArrayList<String> params = new ArrayList<>();
            params.add("nome");
            params.add("id");
            System.out.println(Projection.apply(relPersone, params));
            System.out.println();

            //Union
            System.out.println("Union: ");
            System.out.println(Union.apply(relPersone, relOrdini));
            System.out.println();

            //Difference
            System.out.println("Difference: ");
            System.out.println(Difference.apply(relPersone, relOrdini));
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
            Relation relPersone = new Relation(csv1);
            System.out.println(relPersone.toString());
            System.out.println();

            String s2 = reader2.readLine();
            while (s2 != null) {
                s2 += "\n";
                csv2 += s2;
                s2 = reader2.readLine(); 
            }
            System.out.println("relOrdini: ");
            Relation relOrdini = new Relation(csv2);
            System.out.println(relOrdini.toString());
            System.out.println();

            String s3 = reader3.readLine();
            while (s3 != null) {
                s3 += "\n";
                csv3 += s3;
                s3 = reader3.readLine(); 
            }
            System.out.println("relProdotti: ");
            Relation relProdotti = new Relation(csv3);
            System.out.println(relProdotti.toString());
            System.out.println();

            System.out.println("Product: ");
            System.out.println(CartesianProduct.apply(relPersone, relOrdini));
            System.out.println();

            System.out.println("Junction: ");
            ArrayList<String> id_utente = new ArrayList<>();
            id_utente.add("id_utente");

            ArrayList<String> id_prodotto = new ArrayList<>();
            id_prodotto.add("id_prodotto");

            System.out.println(Junction.apply(relPersone, Junction.apply(relOrdini, relProdotti, id_prodotto), id_utente));
            

            System.out.println("\n\nQUERY1: ");
            ArrayList<String> keysQueryOne = new ArrayList<>();
            keysQueryOne.add("qty");
            keysQueryOne.add("prezzo_unitario");
            Relation queryOne = Projection.apply(Junction.apply(relOrdini, relProdotti, id_prodotto), keysQueryOne);
            System.out.println(queryOne);
            float sum = 0f;
            for (Row row : queryOne.getRows()) {
                float element = 1f;
                for (String value : row.getValues()) {
                    element *= Float.parseFloat(value);
                }
                sum += element;
            }
            System.out.println(sum);

            System.out.println("\n\nQUERY2");
            ArrayList<String> keysQueryTwo = new ArrayList<>();
            keysQueryTwo.add("qty");
            keysQueryTwo.add("prezzo_unitario");
            Relation queryTwo = Projection.apply(Junction.apply(relOrdini, relProdotti, id_prodotto), keysQueryTwo);
            System.out.println(queryTwo);
            for (Row row : queryTwo.getRows()) {
                float element = 1f;
                for (String value : row.getValues()) {
                    element *= Float.parseFloat(value);
                }
                System.out.println(element);
            }

            System.out.println("\n\nQUERY3");
            ArrayList<String> keysQuery3 = new ArrayList<>();
            keysQuery3.add("nome");
            keysQuery3.add("prezzo_unitario");
            Relation query3 = Projection.apply(Junction.apply(relPersone, Junction.apply(relOrdini, relProdotti, id_prodotto), id_utente), keysQuery3);
            System.out.println(query3);
            ArrayList<String> nameUserMaxPrex = new ArrayList<>();
            float maxPrex = 0f;
            for (Row row : query3.getRows()) {
                if(Float.parseFloat(row.getValues().get(1)) > maxPrex){
                    nameUserMaxPrex.clear();
                    maxPrex = Float.parseFloat(row.getValues().get(1));
                    nameUserMaxPrex.add(row.getValues().get(0));
                    continue;
                }
                if(Float.parseFloat(row.getValues().get(1)) == maxPrex){
                    nameUserMaxPrex.add(row.getValues().get(0));
                }
            }
            System.out.println(nameUserMaxPrex);

            reader.close();
            reader2.close();
            reader3.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
