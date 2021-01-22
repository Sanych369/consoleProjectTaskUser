package DAO;

import model.Project;
import util.SQLiteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProjectDAOImp implements ProjectDAO {
    private final Connection connection = SQLiteJDBC.getConnection();
    private final Logger logger = Logger.getLogger(ProjectDAOImp.class.getName());

    @Override
    public List<Project> getAllProjects() {
        Statement stm = null;
        ResultSet rs = null;

        try {
            List<Project> listOfProjectNames = new ArrayList<>();
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM PROJECTS");

            while (rs.next()) {
                String projectName = rs.getString("ProjectName");
                listOfProjectNames.add(new Project(projectName));
            }

            return listOfProjectNames;
        } catch (SQLException e) {
            logger.warning("Getting projects is not possible!");
            return null;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }

    }

    @Override
    public void createProject(String projectName) {
        PreparedStatement pstm = null;

        if (!existProject(projectName)) {
            try {
                assert connection != null;
                pstm = connection.prepareStatement("INSERT INTO PROJECTS (ProjectName) VALUES (?)");
                pstm.setString(1, projectName);
                logger.info("Project " + projectName + " successfully created!");
                pstm.executeUpdate();
            } catch (SQLException e) {
                logger.warning("Can't create new project");
            } finally {
                SQLiteJDBC.closeStatement(pstm);
            }
        } else {
            logger.warning("Project " + projectName + " was created!");
        }
    }


    @Override
    public void deleteProject(String projectName) {
        Statement stm = null;

        if (existProject(projectName)) {
            try {
                assert connection != null;
                stm = connection.createStatement();
                stm.executeUpdate("DELETE FROM PROJECTS WHERE ProjectName = '" + projectName + "'");
                logger.info("Project " + projectName + " successfully deleted!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLiteJDBC.closeStatement(stm);
            }
        } else {
            logger.warning("Project " + projectName + " not found!");
        }
    }

    public boolean existProject(String projectName) {
        Statement stm = null;
        ResultSet rs = null;

        try {
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM PROJECTS WHERE ProjectName = '" + projectName + "'");
            return rs.next();
        } catch (SQLException e) {
            logger.warning("Project " + projectName + " does not exist");
            return true;
        } finally {
            SQLiteJDBC.closeStatement(stm);
            SQLiteJDBC.closeResultSet(rs);
        }
    }
}
