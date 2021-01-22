package DAO;

import model.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> getAllProjects();

    void createProject(String projectName);

    void deleteProject(String projectName);

    boolean existProject(String projectName);
}
