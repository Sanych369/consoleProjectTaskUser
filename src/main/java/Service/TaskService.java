package Service;

import model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    void createTask(String taskName);

    void deleteTask(String taskName);

    void assignTaskOnProject(String projectName, String taskName);
}
