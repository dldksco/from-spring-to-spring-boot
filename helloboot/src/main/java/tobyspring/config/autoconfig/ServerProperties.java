package tobyspring.config.autoconfig;

public class ServerProperties {
    private String contextPath;

    private int port;

    public ServerProperties() {

    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerProperties(String contextPath, int port) {
        this.contextPath = contextPath;
        this.port = port;
    }
}
