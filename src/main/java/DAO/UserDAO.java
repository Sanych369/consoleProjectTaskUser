package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void createUser(String firstName, String lastName);

    void deleteUser(String firstName, String lastName);

    boolean existUser(String firstName, String lastName);

    void assignUserOnTask(String userFirstName, String userLastName, String taskName);
}
