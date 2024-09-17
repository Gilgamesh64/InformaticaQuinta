public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.createIndexFile();
        System.out.print(dbManager.getData("GIGI"));

        dbManager.dispose();
    }
}