public class DatabaseManager {

    private RandomAccessFileManager dbFile;
    private RandomAccessFileManager indexFile;
    
    DatabaseManager(){
        try {
            dbFile = new RandomAccessFileManager("database.dat", "rw");
            indexFile = new RandomAccessFileManager("indexFile.dat", "rw");

        } catch (Exception e) {e.printStackTrace();}
    }

    public void createIndexFile(){
        for (int i = 0; i < 20; i++) {
            dbFile.index(i * 75);
            String str = new String(dbFile.read(25));
            String codice = String.format("%05d", (Integer.parseInt(str.substring(0, 5))-1)*75);
            String nome = str.substring(15, 25);
            String cognome = str.substring(5, 15);

            indexFile.write(cognome + nome + codice);
            if (i < 19)
                indexFile.write("\n");
        }

        dbFile.index(0);
        indexFile.index(0);
    }

    public String getData(String name){
        String line = indexFile.findLine(name, 26);
        if(line.equals("")) return "DATA NOT FOUND";
        
        long j = Integer.parseInt(line.substring(20, 25));
        dbFile.index(j);
        String dbData = new String(dbFile.read(75));
        return dbData;
    }

    public void dispose(){
        dbFile.close();
        indexFile.close();
    }
}
