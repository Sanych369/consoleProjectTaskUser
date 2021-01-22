package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {
    private final JSONParser jsonParser = new JSONParser();

    private final String path = "src/main/resources/initialDB.json";

    private final String dropTables = jsonParser.parsingJSON(path).get("command dropTables");
    private final String createTablePROJECTS = jsonParser.parsingJSON(path).get("command createTable PROJECTS");
    private final String createTableTASKS = jsonParser.parsingJSON(path).get("command createTable TASKS");
    private final String createTableUSERS = jsonParser.parsingJSON(path).get("command createTable USERS");
    private final String addUser = jsonParser.parsingJSON(path).get("command addUser");

    public void createDB() {

        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:memory:SQLite");

            stmt = c.createStatement();

            stmt.executeUpdate(dropTables + createTablePROJECTS + createTableTASKS + createTableUSERS + addUser);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("DB has created!");
    }
}
