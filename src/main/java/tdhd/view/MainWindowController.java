package tdhd.view;

import tdhd.project.Project;

import java.util.Map;

public class MainWindowController {

    private Project project;

    public MainWindowController() {
    }

    public MainWindowController(Project project) {
        this.project = project;
    }

    void cloneProject() {
        String url = "";
        String absoluteFolderPath = "";

        // TODO get url and absolute folder path

        project = new Project(url, absoluteFolderPath);
    }

    void openProject() {
        String absoluteFolderPath = "";

        // TODO get absolute folder path

        project = new Project(absoluteFolderPath);
    }

    void runAllTests() {
        Map<String, Boolean> results = project.runAllTests();

        // TODO output results
    }

}
