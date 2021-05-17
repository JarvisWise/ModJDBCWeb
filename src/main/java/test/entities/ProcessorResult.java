package test.entities;

public class ProcessorResult {
    private String url;
    private String includePage;
    private boolean isForward;

    public ProcessorResult(String url, String includePage, boolean isForward) {
        this.url = url;
        this.includePage = includePage;
        this.isForward = isForward;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIncludePage() {
        return includePage;
    }

    public void setIncludePage(String includePage) {
        this.includePage = includePage;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }
}

