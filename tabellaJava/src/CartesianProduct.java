public class CartesianProduct {
    public static Relation apply(Relation relationOne, Relation relationTwo){
        String csv = "";
        for (String keyString : relationOne.getHeader()) {
            csv += keyString + ",";
        }

        for (String keyString : relationTwo.getHeader()) {
            csv += keyString + ",";
        }
        csv = csv.substring(0, csv.length()-1);
        csv += "\n";

        for (Row row : relationOne.getRows()) {
            for (Row row2 : relationTwo.getRows()) {
                csv += row.toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + "," + row2.toString().replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "") + "\n";
            }
        }

        return new Relation(csv);
    }
}
