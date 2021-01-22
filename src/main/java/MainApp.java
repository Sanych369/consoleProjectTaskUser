import util.CreateDB;
import util.Menu;

public class MainApp {

    public static void main(String[] args) {
        CreateDB createDB = new CreateDB();
        createDB.createDB();
        Menu menu = new Menu();
        menu.startMenu();
    }
}
