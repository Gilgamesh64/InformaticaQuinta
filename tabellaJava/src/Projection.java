import java.util.ArrayList;

public class Projection {

    /**
     * applies Projection
     * @param input
     * @param keys
     * @return new Relation with given keys
     */
    public static Relation apply(Relation input, ArrayList<String> keys){
        int[] keyPos = new int[keys.size()];
        for (int i = 0; i < keyPos.length; i++) {
            keyPos[i] = input.getHeader().indexOf(keys.get(i));
        }
        String csv = "";

        for (String keyString : keys) {
            csv += keyString + ",";
        }
        csv = csv.substring(0, csv.length()-1);
        csv += "\n";
        
        for (Row row : input.getRows()) {
            for (int i = 0; i < keyPos.length; i++) {
                csv += row.get(keyPos[i]) + ",";
            }
            csv += "\n";
        }
        return new Relation(csv);
    }
}
