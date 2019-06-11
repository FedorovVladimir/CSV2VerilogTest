package new_design_model;

public class Path {

    private static String projectsPath;

    private String path;

    public Path(String path) {
        this.path = path;
    }

    String getPath() {
        return projectsPath + "\\" + path;
    }

    String getName() {
        return path;
    }

    public static void setProjectsPath(String projectsPath) {
        Path.projectsPath = projectsPath;
    }

    public static String getProjectsPath() {
        return projectsPath;
    }

    @Override
    public String toString() {
        return "Path{" +
                "path='" + path + '\'' +
                '}';
    }
}
