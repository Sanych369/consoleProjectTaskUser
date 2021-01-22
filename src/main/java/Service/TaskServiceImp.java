package Service;

import DAO.TaskDAOImp;
import model.Task;

import java.util.List;

public class TaskServiceImp implements TaskService {
    private final TaskDAOImp taskDAOImp = new TaskDAOImp();

    @Override
    public List<Task> getAllTasks() {
        return taskDAOImp.getAllTasks();
    }

    @Override
    public void createTask(String taskName) {
        taskDAOImp.createTask(taskName);
    }

    @Override
    public void deleteTask(String taskName) {
        taskDAOImp.deleteTask(taskName);
    }

    @Override
    public void assignTaskOnProject(String projectName, String taskName) {
        taskDAOImp.assignTaskOnProject(projectName, taskName);
    }

    @Override
    public void printAllTasks() {
        if (taskDAOImp.getAllTasks().size() != 0) {
            for (int i = 0; i < taskDAOImp.getAllTasks().size(); i++) {
                System.out.println(taskDAOImp.getAllTasks().get(i));
            }
        } else {
            System.out.println("Список задач пуст!");
        }
    }
}
