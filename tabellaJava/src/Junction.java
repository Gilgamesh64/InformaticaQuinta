import java.util.ArrayList;

public class Junction {
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


        return new Relation(csv);
    }
}
