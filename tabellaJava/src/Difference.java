public class Difference {

    /**
     * Applies difference
     * @param relationOne
     * @param relationTwo
     * @return new Relation containing a difference of the two
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
            if(isPresentInRel(relationTwo, row)) continue;
            
            for (String s : row.getValues()) {
                csv += s + ",";
            }
            csv = csv.substring(0, csv.length()-1);
            csv += "\n";
        }

        return new Relation(csv);
    }

    private static boolean isPresentInRel(Relation relation, Row row){
        for (Row relRow : relation.getRows()) {
            if(relRow.toString().equals(row.toString())) return true;
        }
        return false;
    }
}
