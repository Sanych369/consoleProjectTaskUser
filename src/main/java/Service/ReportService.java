package Service;

import DAO.ReportDAO;

public class ReportService {
    private final ReportDAO reportDAO = new ReportDAO();

    public void getReport() {
        for (int i = 0; i < reportDAO.getReport().size(); i++) {
            System.out.println(reportDAO.getReport().get(i));
        }
    }
}
