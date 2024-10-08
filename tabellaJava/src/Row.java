import java.util.ArrayList;
import java.util.Collections;

public class Row {

    //contains all the values in the Row
    private ArrayList<String> values;

    /**
     * Creates a new raw using valued
     * @param values MUST be fomatted like so:
     * value1,value2,value3,...
     */
    Row(String values){
        this.values = new ArrayList<>();
        Collections.addAll(this.values, values.split(","));
    }

    @Override
    public String toString() {
        String result = "";
        for (String string : values) {
            result += string + ",";
        }
        result = result.substring(0, result.length()-1); 
        return result;
    }

    public String get(int n){
        return values.get(n);
    }

    public ArrayList<String> getValues() {
        return values;
    }
}
