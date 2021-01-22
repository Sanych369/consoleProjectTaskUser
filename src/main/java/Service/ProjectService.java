package Service;

import model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();

    void createProject(String projectName);

    void deleteProject(String projectName);

}
