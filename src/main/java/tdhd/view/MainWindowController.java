package tdhd.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import tdhd.project.Project;
import tdhd.tools.CreateModuleTool;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainWindowController {

    @FXML
    private TreeView<Label> FilesTree;

    private String login = "";

    private String password = "";

    private Project project;

    private List<Tool> tools = new LinkedList<>();

    public MainWindowController() {
    }

    public MainWindowController(Project project) {
        this.project = project;
    }

    @FXML
    void cloneProject(ActionEvent event) {
        String url = getString("", "git clone", "Enter url", "");
        writeLog("enter url " + url);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(null);
        writeLog("select project folder " + dir.getAbsolutePath());
        project = new Project(url, dir.getAbsolutePath());
        update();
    }

    @FXML
    void openProject(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(null);
        writeLog("open project " + dir.getAbsolutePath());
        project = new Project(dir);
        update();
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
        return result.get();
    }

    void gitPush() {
        if (login.equals("")) {
            login = getString("", "git push", "Enter login", "");
        }
        if (password.equals("")) {
            password = getString("", "git push", "Enter password", "");
        }
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

    private void update() {
        File[] srcFiles = project.getAllSrcFiles();
        File[] testFiles = project.getAllTestFiles();

        Label projectLabel = new Label(project.getNameProject());
        TreeItem<Label> rootItem = new TreeItem<>(projectLabel);

        for (File f : srcFiles) {
            Label label = new Label(f.getName());
            TreeItem<Label> item = new TreeItem<>(label);
            rootItem.getChildren().add(item);
        }
        for (File f : testFiles) {
            Label label = new Label(f.getName());
            TreeItem<Label> item = new TreeItem<>(label);
            rootItem.getChildren().add(item);
        }

        rootItem.setExpanded(true);
        FilesTree.setRoot(rootItem);
    }

    private void writeLog(String text) {
        System.out.println("Controller message: " + text);
    }
}
