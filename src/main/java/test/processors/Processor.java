package test.processors;

import test.entities.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

public abstract class Processor {
    protected  String  actionToPerform = null;

    public boolean canProcess(String action) {
        return actionToPerform.equals(action);
    }

    public abstract ProcessorResult process(HttpServletRequest request);
}
