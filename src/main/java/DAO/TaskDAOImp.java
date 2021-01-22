package DAO;

import model.Task;
import util.SQLiteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TaskDAOImp implements TaskDAO {
    private final Connection connection = SQLiteJDBC.getConnection();
    private final Logger logger = Logger.getLogger(TaskDAOImp.class.getName());

    @Override
    public List<Task> getAllTasks() {
        Statement stm = null;
        ResultSet rs = null;

        try {
            List<Task> listOfTaskNames = new ArrayList<>();
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM TASKS");

            while (rs.next()) {
                listOfTaskNames.add(new Task(rs.getString("TaskName")));
            }
            return listOfTaskNames;
        } catch (SQLException e) {
            logger.warning("Getting task is not possible!");
            return null;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }
    }

    @Override
    public void createTask(String taskName) {
        PreparedStatement pstm = null;

        if (!existTask(taskName)) {
            try {
                assert connection != null;
                pstm = connection.prepareStatement("INSERT INTO TASKS (TaskName) VALUES (?)");
                pstm.setString(1, taskName);
                logger.info("Task " + taskName + " successfully created!");
                pstm.executeUpdate();
            } catch (SQLException e) {
                logger.warning("Can't create new task");
            } finally {
                SQLiteJDBC.closeStatement(pstm);
            }
        } else {
            logger.info("Task " + taskName + " was created!");
        }
    }

    @Override
    public void deleteTask(String taskName) {
        Statement stm = null;

        if (existTask(taskName)) {
            try {
                assert connection != null;
                stm = connection.createStatement();
                stm.executeUpdate("DELETE FROM TASKS WHERE TaskName = '" + taskName + "'");
                logger.info("Task " + taskName + " successfully deleted!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLiteJDBC.closeStatement(stm);
            }
        } else {
            logger.warning("Task " + taskName + " not found!");
        }
    }

    @Override
    public boolean existTask(String taskName) {
        Statement stm = null;
        ResultSet rs = null;

        try {
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM TASKS WHERE TaskName = '" + taskName + "'");
            return rs.next();
        } catch (SQLException e) {
            logger.warning("Task " + taskName + " does not exist");
            return true;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }
    }

    @Override
    public void assignTaskOnProject(String projectName, String taskName) {
        ProjectDAOImp projectDAOImp = new ProjectDAOImp();
        Statement stm = null;

        if (projectDAOImp.existProject(projectName)) {
            try {
                assert connection != null;
                {
                    stm = connection.createStatement();
                    stm.executeUpdate("UPDATE TASKS SET Project_ID = "
                            + "(SELECT ID FROM PROJECTS WHERE ProjectName = '" + projectName + "')" +
                            "WHERE TASKS.TaskName = '" + taskName + "'");
                    logger.info("Task " + taskName + " successfully assign on project " + projectName + "!");
                }
            } catch (SQLException e) {
                logger.warning("Can't assign task on the project!");
            } finally {
                SQLiteJDBC.closeStatement(stm);
            }
        } else {
            logger.warning("Project " + projectName + " not found!");
        }
    }
}
