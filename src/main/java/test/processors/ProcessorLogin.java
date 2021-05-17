package test.processors;

import test.dao.DAOPostgreSQL;
import test.entities.ProcessorResult;
import test.entities.Question;
import test.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ProcessorLogin extends Processor {

    public ProcessorLogin() {
        actionToPerform = "loginProcess";
    }

    @Override
    public ProcessorResult process(HttpServletRequest request) {


        try {
            String username = request.getParameter("username");
            DAOPostgreSQL.getInstance().connect();
            User user  = DAOPostgreSQL.getInstance().selectByNameFromUsers(username);
            if (user == null) {
                DAOPostgreSQL.getInstance().addUser(new User(DAOPostgreSQL.getInstance().getMaxId(), username, 0));
            }
            List<Question> questionList = DAOPostgreSQL.getInstance().selectFromQuestions(5);
            request.getSession().setAttribute("questions", questionList);
            request.getSession().setAttribute("username", username);
            return new ProcessorResult("pages/template.jsp", null, true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;//!!!
        }
    }

}
