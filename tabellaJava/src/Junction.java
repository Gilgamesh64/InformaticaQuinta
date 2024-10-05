import java.util.ArrayList;

public class Junction {
    @SuppressWarnings("unchecked")
    public static Relation apply(Relation one, Relation two, ArrayList<String> junctionField){
        String csv = "";
        for (String string : junctionField) {
            if(!one.getHeader().contains(string) || !two.getHeader().contains(string)) return new Relation(csv);
        }

        csv += one.getHeader().toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + ",";
        for (String string : two.getHeader()) {
            if(junctionField.contains(string)) continue;
            csv += string + ",";
        }
        csv = csv.substring(0, csv.length()-1) + "\n";

        ArrayList<Integer> positionsOne = new ArrayList<>();
        ArrayList<Integer> positionsTwo = new ArrayList<>();
        for (String string : junctionField) {
            positionsOne.add(one.getHeader().indexOf(string));
            positionsTwo.add(two.getHeader().indexOf(string));
        }

        String csv2 = csv;
        for (Row row : one.getRows()) {
            for (Row row2 : two.getRows()) {
                for (Integer pos : positionsOne) {
                    for (Integer pos2 : positionsTwo) {
                        if(row.getValues().get(pos).equals(row2.getValues().get(pos2))){
                            ArrayList<String> clone = (ArrayList<String>) row2.getValues().clone();
                            clone.remove(pos2.intValue());
                            csv += row.getValues().toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + "," + clone.toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "");
                        } 
                    }
                }
                if(!csv.equals(csv2)){
                    csv = csv.substring(0, csv.length()) + "\n";
                    csv2 = csv;
    
                }
            }
            
        }
        return new Relation(csv);
    }
}
