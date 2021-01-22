package util;

import Service.ProjectServiceImp;
import Service.ReportService;
import Service.TaskServiceImp;
import Service.UserServiceImp;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private boolean stop = false;

    ProjectServiceImp projectService = new ProjectServiceImp();
    TaskServiceImp taskService = new TaskServiceImp();
    UserServiceImp userService = new UserServiceImp();

    public void startMenu() {
        while (!stop) {
            System.out.println("Выберите необходимое действие и введите цифру команды:");
            System.out.println("1. Проекты");
            System.out.println("2. Задачи");
            System.out.println("3. Пользователи");
            System.out.println("4. Отчёт");
            System.out.println("0. Выход");
            System.out.println();
            String choice = scanner.next();

            switch (choice) {
                case "1":
                    projectMenu();
                    break;
                case "2":
                    taskMenu();
                    break;
                case "3":
                    userMenu();
                    break;
                case "4":
                    reportMenu();
                    break;
                case "0":
                    stop = true;
                    System.out.println("Приложение закрыто.");
                default:
                    break;
            }
        }
    }

    private void projectMenu() {
        System.out.println("Меню проекты:\n");
        System.out.println("1. Создать проект");
        System.out.println("2. Посмотреть проекты");
        System.out.println("3. Удалить проект");
        System.out.println("Для возвращения в главное меню используйте любой символ или набор символов отличный от команд");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Создать проект: ");
                System.out.println("Введите название проекта :");
                String projectName = scanner.next();
                projectService.createProject(projectName);
                break;
            case "2":
                System.out.println("Посмотреть проекты: ");
                projectService.printAllProjects();
                mainMenuOrExit();
                break;
            case "3":
                System.out.println("Удалить проект: ");
                System.out.println("Введите название проекта: ");
                String projName = scanner.next();
                projectService.deleteProject(projName);
                break;
            default:
                break;
        }

    }

    private void taskMenu() {
        System.out.println("Меню задачи:");
        System.out.println("1. Создать задачу");
        System.out.println("2. Посмотреть задачи");
        System.out.println("3. Прекрепить задачу к проекту");
        System.out.println("4. Удалить задачу");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Создать задачу.");
                System.out.println("Введите название новой задачи:");
                String taskNameCreate = scanner.next();
                taskService.createTask(taskNameCreate);
                System.out.println("Прикрепить созданную задачу к проекту сразу?");
                System.out.println("1. Да");
                System.out.println("2. Нет");
                String choiceAssignTaskOnProject = scanner.next();

                if ("1".equals(choiceAssignTaskOnProject)) {
                    System.out.println("Введите имя проекта, к которому прикрепить задачу:");
                    String projectName = scanner.next();
                    taskService.assignTaskOnProject(projectName, taskNameCreate);
                }
                break;
            case "2":
                System.out.println("Посмотреть задачи.");
                taskService.printAllTasks();
                mainMenuOrExit();
                break;
            case "3":
                System.out.println("Прикрепить задачу к проекту.");
                System.out.println("Введите имя задачи:");
                String taskName = scanner.next();
                System.out.println("Введите имя проекта, к которому прикрепить задачу:");
                String projectNameAssign = scanner.next();
                taskService.assignTaskOnProject(projectNameAssign, taskName);
                break;
            case "4":
                System.out.println("Удалить задачу.");
                System.out.println("Введите название задачи:");
                String taskNameDelete = scanner.next();
                taskService.deleteTask(taskNameDelete);
                break;
            default:
                break;
        }
    }

    private void userMenu() {
        System.out.println("Меню пользователи:");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Посмотреть пользователей");
        System.out.println("3. Назначить пользователю задачу");
        System.out.println("4. Удалить пользователя");

        String choice = scanner.next();
        switch (choice) {
            case "1":
                System.out.println("Создать пользователя.");
                System.out.println("Введите имя пользователя:");
                String userFirstNameCreate = scanner.next();
                System.out.println("Введите фамилию пользователя:");
                String userLastNameCreate = scanner.next();
                userService.createUser(userFirstNameCreate, userLastNameCreate);
                System.out.println("Назначить пользователю задачу сразу?");
                System.out.println("1. Да");
                System.out.println("2. Нет");
                String choiceAssignUserOnTask = scanner.next();

                if ("1".equals(choiceAssignUserOnTask)) {
                    System.out.println("Прикрепить пользователя к задаче: ");
                    System.out.println("Введите название задачи: ");
                    String taskName = scanner.next();
                    userService.assignUserOnTask(userFirstNameCreate, userLastNameCreate, taskName);
                }
                break;
            case "2":
                System.out.println("Просмотреть пользователей.");
                userService.printAllUsers();
                mainMenuOrExit();
                break;
            case "3":
                System.out.println("Назначить пользователю задачу.");
                System.out.println("Введите имя пользователя:");
                String userFirstName = scanner.next();
                System.out.println("Введите фамилию пользователя:");
                String userLastName = scanner.next();
                System.out.println("Введите название задачи, к которой хотите прикрепить данного пользователя:");
                String taskName = scanner.next();
                userService.assignUserOnTask(userFirstName, userLastName, taskName);
                break;
            case "4":
                System.out.println("Удалить пользователя.");
                System.out.println("Введите имя пользователя:");
                String userFirstNameDelete = scanner.next();
                System.out.println("Введите фамилию пользователя:");
                String userLastNameDelete = scanner.next();
                userService.deleteUser(userFirstNameDelete, userLastNameDelete);
                break;
            default:
                break;
        }
    }

    private void reportMenu() {
        new ReportService().getReport();
        mainMenuOrExit();
    }

    private void mainMenuOrExit() {
        System.out.println("Вернуться в главное меню?");
        System.out.println("1. Главное меню");
        System.out.println("2. Завершить программу");
        String returnToMenu = scanner.next();
        switch (returnToMenu) {
            case "2":
                stop = true;
                System.out.println("Приложение закрыто.");
            default:
                break;
        }
    }
}
