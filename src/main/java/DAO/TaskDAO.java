package DAO;

import model.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();

    void createTask(String taskName);

    void deleteTask(String taskName);

    boolean existTask(String taskName);

    void assignTaskOnProject(String projectName, String taskName);
}
