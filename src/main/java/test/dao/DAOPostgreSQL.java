package test.dao;

import test.entities.Question;
import test.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
CREATE TABLE QUESTIONS(
   ID INT NOT NULL,
   QUESTION VARCHAR(800),
   ANSWER VARCHAR(20),
   POINTS INT,
   PRIMARY KEY (ID)
);

INSERT INTO QUESTIONS VALUES(1, '1+1', '2', 10);
INSERT INTO QUESTIONS VALUES(2, '1+2', '3', 10);
INSERT INTO QUESTIONS VALUES(3, '1+3', '4', 10);
INSERT INTO QUESTIONS VALUES(4, '1+4', '5', 10);
INSERT INTO QUESTIONS VALUES(5, '1+5', '6', 10);
INSERT INTO QUESTIONS VALUES(6, '1+6', '7', 10);
INSERT INTO QUESTIONS VALUES(7, '1+7', '8', 10);
INSERT INTO QUESTIONS VALUES(8, '1+8', '9', 10);
INSERT INTO QUESTIONS VALUES(9, '1+9', '10', 10);
INSERT INTO QUESTIONS VALUES(10, '1+10', '11', 10);


CREATE TABLE USERS(
   ID INT NOT NULL,
   NAME VARCHAR(20),
   MAX_TOTAL_POINTS INT,
   PRIMARY KEY (ID)
);

*/


public class DAOPostgreSQL implements DAOInterface {

    private static final DAOPostgreSQL instance = new DAOPostgreSQL();
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    private DAOPostgreSQL(){super();}

    public static DAOPostgreSQL getInstance(){
        return instance;
    }


    @Override
    public boolean connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "ama_user","1234");
            return !connection.isClosed();
        } catch (SQLException | ClassNotFoundException throwables) {
            return false;
        }
    }


    @Override
    public boolean disconnect() {
        try {
            if(connection!=null){
                connection.close();
            }
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }


    private Question parseQuestion(ResultSet result) throws SQLException {
        return new Question(
                result.getInt("id"),
                result.getString("question"),
                result.getString("answer"),
                result.getInt("points")
        );
    }


    private User parseUser(ResultSet result) throws SQLException {
        return new User(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("max_total_points")
        );
    }

    public int getMaxId() throws SQLException {
        try {
            statement = connection.prepareStatement(
                    "SELECT MAX(id) AS max FROM users"
            );

            result = statement.executeQuery();

            if(result.next()){
                return result.getInt("max");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to select from emp", e);
        }
    }

    @Override
    public User selectByNameFromUsers(String name) throws SQLException {
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE name = ?"
            );
            statement.setString(1, name);
            result = statement.executeQuery();

            if(result.next()){
                return parseUser(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to select from emp", e);
        }
    }



    @Override
    public List<Question> selectFromQuestions(int rowCount) throws SQLException {
        List<Question> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM questions WHERE rownum <= ?");
            statement.setInt(1, rowCount);
            result = statement.executeQuery();
            while(result.next()){
                list.add(parseQuestion(result));
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to select from emp", e);
        }
        return list;
    }


    @Override
    public List<User> selectAllFromUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM users");
            result = statement.executeQuery();
            while(result.next()){
                list.add(parseUser(result));
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to select from emp", e);
        }
        return list;
    }


    @Override
    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO user(id, name, max_total_points)" +
                            "VALUES (?, ?, ?)"
            );

            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getMaxTotalPoints());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
