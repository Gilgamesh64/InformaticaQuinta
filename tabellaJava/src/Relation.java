import java.util.ArrayList;
import java.util.Collections;

public class Relation {

    //contains all the headers of the table
    private ArrayList<String> header;

    //constains all the Rows of the table
	private ArrayList<Row> rows;

    /**
     * Creates a new Relation passing a CSV Sting
     * @param csvfile MUST be formatted like so:
     * header1,header2,header3,...
     * field1,field2,field3,...
     * ...
     */
	Relation(String csvfile){
        header = new ArrayList<>();
        rows = new ArrayList<>();

        Collections.addAll(header, csvfile.split("\n")[0].split(","));
        
        for (int i = 1; i < csvfile.split("\n").length; i++) {
            rows.add(new Row(csvfile.split("\n")[i]));
        }
    }

    public ArrayList<String> getHeader() {
        return header;
    }
    
    public ArrayList<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return header + "\n" + rows.toString();
    }
}
