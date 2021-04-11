package endpoints;

public enum ApiEndpoints {
    LOGIN("/login"), MAIN("/");
    String path;

    ApiEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String addPath(String additionalPath) {
        return path + additionalPath;
    }
}
