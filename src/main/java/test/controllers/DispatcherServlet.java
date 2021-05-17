package test.controllers;

import test.entities.ProcessorResult;
import test.processors.Processor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet  extends HttpServlet {
    private List<Processor> processorList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        //processorList.add(new ProcessorMainPage());
        //processorList.add(new ProcessorLogOut());
        //processorList.add(new ProcessorShowStudents());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        for (Processor processor: processorList) {
            if (processor.canProcess(action)) {
                ProcessorResult result = processor.process(request);
                if (result.getIncludePage() != null) {
                    request.getSession().setAttribute("includedJSPPage", result.getIncludePage());
                }
                if (result.isForward()) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(result.getUrl());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(result.getUrl());
                }
            }
        }
        /*PrintWriter writer = response.getWriter();
        writer.println("Browser");*/
    }
}
