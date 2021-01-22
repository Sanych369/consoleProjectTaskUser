package Service;

import DAO.UserDAOImp;
import model.User;

import java.util.List;

public class UserServiceImp implements UserService {
    private final UserDAOImp userDAOImp = new UserDAOImp();

    @Override
    public List<User> getAllUsers() {
        return userDAOImp.getAllUsers();
    }

    @Override
    public void createUser(String firstName, String lastName) {
        userDAOImp.createUser(firstName, lastName);
    }

    @Override
    public void deleteUser(String firstName, String lastName) {
        userDAOImp.deleteUser(firstName, lastName);
    }

    @Override
    public void assignUserOnTask(String userFirstName, String userLastName, String taskName) {
        userDAOImp.assignUserOnTask(userFirstName, userLastName, taskName);
    }

    @Override
    public void printAllUsers() {
        if(userDAOImp.getAllUsers().size() != 0) {
            for (int i = 0; i < userDAOImp.getAllUsers().size(); i++) {
                System.out.println(userDAOImp.getAllUsers().get(i));
            }
        }else {
            System.out.println("Список пользователей пуст!");
        }
    }
}
