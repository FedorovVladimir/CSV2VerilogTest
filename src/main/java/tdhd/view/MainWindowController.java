package tdhd.view;

import javafx.scene.control.TextInputDialog;
import tdhd.project.Project;
import tdhd.tools.CreateModuleTool;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainWindowController {

    private Project project;

    private List<Tool> tools = new LinkedList<>();

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

    void gitCommit() {
        String message = getString("", "git commit", "Enter commit text", "");
        project.gitCommit(message);
    }

    private String getString(String defaultValue, String title, String headerText, String contentText) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        return String.valueOf(result);
    }

    void gitPush() {
        String login = "";
        String password = "";

        // TODO login password

        project.gitPush(login, password);
    }

    void newFile() {
        String absoluteFilePath = "";
        // TODO get module name
        boolean result = project.createFile(absoluteFilePath);

        // TODO message result
    }

    void saveFile() {
        String absoluteFilePath = "";
        String text = "";

        // TODO get absoluteFilePath and text

        project.save(absoluteFilePath, text);
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }
}
