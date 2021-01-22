package DAO;

import model.Report;
import util.SQLiteJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReportDAO {
    private final Connection connection = SQLiteJDBC.getConnection();
    private final Logger logger = Logger.getLogger(ReportDAO.class.getName());

    public List<Report> getReport() {
        Statement stm = null;
        ResultSet rs = null;

        try {
            List<Report> reportList = new ArrayList<>();
            assert connection != null;
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT ProjectName, TaskName, FirstName, LastName " +
                    "FROM PROJECTS " +
                    "JOIN TASKS T on PROJECTS.ID = T.Project_ID " +
                    "JOIN USERS U on T.ID = U.Task_ID");

            while (rs.next()) {
                String projectName = rs.getString("ProjectName");
                String taskName = rs.getString("TaskName");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                reportList.add(new Report(projectName, taskName, firstName, lastName));
            }
            return reportList;
        } catch (SQLException e) {
            logger.warning("Getting report is not possible!");
            return null;
        } finally {
            SQLiteJDBC.closeResultSet(rs);
            SQLiteJDBC.closeStatement(stm);
        }
    }
}
