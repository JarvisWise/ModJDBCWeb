package test.dao;

import test.entities.Question;
import test.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface {
    boolean connect();
    boolean disconnect();
    User selectByNameFromUsers(String name) throws SQLException;
    List<Question> selectFromQuestions(int rowCount) throws SQLException;
    List<User> selectAllFromUsers() throws SQLException;
    boolean addUser(User user);

}
