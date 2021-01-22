package DAO;

import model.User;
import util.SQLiteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAOImp implements UserDAO {
    private final Connection connection = SQLiteJDBC.getConnection();
    private final Logger logger = Logger.getLogger(TaskDAOImp.class.getName());

    @Override
    public List<User> getAllUsers() {
        Statement stm = null;
        ResultSet rs = null;

        try {
            List<User> listOfUserNames = new ArrayList<>();
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM USERS");

            while (rs.next()) {
                String userFirstName = rs.getString("FirstName");
                String userLastName = rs.getString("LastName");
                listOfUserNames.add(new User(userFirstName, userLastName));
            }
            return listOfUserNames;
        } catch (SQLException e) {
            logger.warning("Getting projects is not possible!");
            return null;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }
    }

    @Override
    public void createUser(String firstName, String lastName) {
        PreparedStatement pstm = null;

        if (!existUser(firstName, lastName)) {
            try {
                assert connection != null;
                pstm = connection.prepareStatement("INSERT INTO USERS (firstname, lastname) VALUES (?, ?)");
                pstm.setString(1, firstName);
                pstm.setString(2, lastName);
                logger.info("User " + firstName + " " + lastName + " successfully created!");
                pstm.executeUpdate();
            } catch (SQLException e) {
                logger.warning("Can't create new user");
            } finally {
                SQLiteJDBC.closeStatement(pstm);
            }
        } else {
            logger.warning("User " + firstName + " " + lastName + " was created!");
        }
    }

    @Override
    public void deleteUser(String firstName, String lastName) {
        Statement stm = null;

        if (existUser(firstName, lastName)) {
            try {
                assert connection != null;
                stm = connection.createStatement();
                stm.executeUpdate("DELETE FROM USERS WHERE " +
                        "FirstName = '" + firstName + "'" + "" +
                        "AND " +
                        "LastName = '" + lastName + "'");
                logger.info("User " + firstName + " " + lastName + " successfully deleted!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLiteJDBC.closeStatement(stm);
            }
        } else {
            logger.warning("User " + firstName + " " + lastName + " not found!");
        }
    }

    @Override
    public boolean existUser(String firstName, String lastName) {
        Statement stm = null;
        ResultSet rs = null;

        try {
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM USERS " +
                    "WHERE FirstName = '" + firstName + "'" +
                    "AND LastName = '" + lastName + "'");
            return rs.next();
        } catch (SQLException e) {
            logger.warning("User " + firstName + " " + lastName + " does not exist");
            return true;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }
    }

    @Override
    public void assignUserOnTask(String userFirstName, String userLastName, String taskName) {
        Statement stm = null;

        TaskDAO taskDAOImp = new TaskDAOImp();
        if (taskDAOImp.existTask(taskName)) {
            try {
                assert connection != null;
                {
                    stm = connection.createStatement();
                    stm.executeUpdate("UPDATE USERS SET Task_ID = "
                            + "(SELECT ID FROM TASKS WHERE TaskName = '" + taskName + "')" +
                            "WHERE USERS.FirstName = '" + userFirstName + "'" +
                            "AND USERS.LastName = '" + userLastName + "'");
                    logger.info("User " + userFirstName + " " + userLastName + " successfully assign on task " + taskName + "!");
                }
            } catch (SQLException e) {
                logger.warning("Can't assign user on the task!");
            } finally {
                SQLiteJDBC.closeStatement(stm);
            }
        } else {
            logger.warning("Task " + taskName + " not found!");
        }
    }
}
