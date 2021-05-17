<%@ page import="test.entities.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 17.05.2021
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<!--<-% request.getSession(true); %>-->
<div id="header">
    Welcome, <%= request.getSession().getAttribute("username")%><br/>
</div>

<div id="main">
    <!--<input type="button" name="deleteButton" value="DELETE"/>
    <input type="button" name="editButton" value="EDIT"/>-->
    <% List<Question> questions = (List<Question>) request.getAttribute("questions");%>
    <% for (int i =0; i<questions.size(); i++) {%>
    <label>ID: <%= questions.get(i).getId() %>: Question: <%= questions.get(i).getQuestion() %></label>
    //undone
    <% } %>
</div>
<div id="footer"><p>HO-HO-HO-HO</p></div>

    </body>
    </html>