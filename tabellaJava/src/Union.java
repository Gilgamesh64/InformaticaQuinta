public class Union {

    /**
     * Applies union
     * @param relationOne
     * @param relationTwo
     * @return new Relation composed by the union of the two
     */
    public static Relation apply(Relation relationOne, Relation relationTwo){

        if(!(relationOne.getHeader().containsAll(relationTwo.getHeader()) && relationTwo.getHeader().containsAll(relationOne.getHeader()))) return null;

        String csv = "";

        for (String keyString : relationOne.getHeader()) {
            csv += keyString + ",";
        }
        csv = csv.substring(0, csv.length()-1);
        csv += "\n";

        for (Row row : relationOne.getRows()) {
            for (String s : row.getValues()) {
                csv += s + ",";
            }
            csv = csv.substring(0, csv.length()-1);
            csv += "\n";
        }

        for (Row row : relationTwo.getRows()) {
            if(csv.contains(row.toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", ""))) continue;
            for (String s : row.getValues()) {
                csv += s + ",";
            }
            csv = csv.substring(0, csv.length()-1);
            csv += "\n";
        }

        return new Relation(csv);
    }
}
