import java.util.ArrayList;

public class Selection {

    /**
     * Applies selection
     * @param input
     * @param key
     * @param value
     * @return Arraylist of all the matching rows
     */
    public static Relation apply(Relation input, String key, String value){
        int keyPos;
        String csv = "";
        for (String keyString : input.getHeader()) {
            csv += keyString + ",";
        }
        csv = csv.substring(0, csv.length()-1);
        csv += "\n";

        keyPos = input.getHeader().indexOf(key);

        for (Row row : input.getRows()) {
            if(row.get(keyPos).equals(value)){
                csv += row.getValues().toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + "\n";
            }
        }
        csv = csv.substring(0, csv.length()-1);
        return new Relation(csv);
    }

    /**
     * Applies selection
     * @param input
     * @param key
     * @param value
     * @return Arraylist of all the matching rows
     */
    public static Relation apply(Relation input, String key, float min, float max){
        int keyPos;
        String csv = "";
        for (String keyString : input.getHeader()) {
            csv += keyString + ",";
        }
        csv = csv.substring(0, csv.length()-1);
        csv += "\n";

        keyPos = input.getHeader().indexOf(key);

        for (Row row : input.getRows()) {
            String s = row.get(keyPos).replaceAll("[^0-9]", "");
            if(s.equals("")){
                break;
            } 
            float value = Float.parseFloat(s);
            if(value < max && value > min){
                csv += row.getValues().toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + "\n";
            }
        }
        csv = csv.substring(0, csv.length()-1);
        return new Relation(csv);
    }
}
