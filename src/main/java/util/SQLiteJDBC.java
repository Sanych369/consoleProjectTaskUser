package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class SQLiteJDBC {
    private static final Logger logger = Logger.getLogger(SQLiteJDBC.class.getName());
    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed())
                Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:memory:SQLite");
            return connection;
        } catch (ClassNotFoundException | SQLException ignored) {
            logger.warning("Getting connection is not possible!");
            return null;
        }
    }

    public static void closeStatement(Statement stm) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                logger.warning("Connection statement not closed!");
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.warning("Connection resultSet not closed!");
            }
        }
    }
}