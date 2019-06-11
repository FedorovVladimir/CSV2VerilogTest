package new_design_model;

public class PathProject {

    private static String projectsPath;

    public static void setProjectsPath(String projectsPath) {
        PathProject.projectsPath = projectsPath;
    }

    public static String getProjectsPath() {
        return projectsPath;
    }
}
