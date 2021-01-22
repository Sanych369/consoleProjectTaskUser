package Service;

import DAO.ProjectDAOImp;
import model.Project;

import java.util.List;

public class ProjectServiceImp implements ProjectService {
    private final ProjectDAOImp projectDAOImp = new ProjectDAOImp();

    @Override
    public List<Project> getAllProjects() {
        for (int i = 0; i < projectDAOImp.getAllProjects().size(); i++) {
            System.out.println(projectDAOImp.getAllProjects().get(i));
        }
        return projectDAOImp.getAllProjects();
    }

    @Override
    public void createProject(String projectName) {
        projectDAOImp.createProject(projectName);
    }

    @Override
    public void deleteProject(String projectName) {
        projectDAOImp.deleteProject(projectName);
    }

}
