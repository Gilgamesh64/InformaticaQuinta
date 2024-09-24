import java.util.ArrayList;

public class Selection {

    /**
     * Applies selection
     * @param input
     * @param key
     * @param value
     * @return Arraylist of all the matching rows
     */
    public static ArrayList<Row> apply(Relation input, String key, String value){
        int keyPos;
        keyPos = input.getHeader().indexOf(key);
        ArrayList<Row> result = new ArrayList<>();

        for (Row row : input.getRows()) {
            if(row.get(keyPos).equals(value)){
                result.add(row);
            }
        }
        return result;
    }
}
