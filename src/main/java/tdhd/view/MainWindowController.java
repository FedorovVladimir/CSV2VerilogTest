package tdhd.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import tdhd.project.Project;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MainWindowController implements Initializable {

    @FXML
    private TreeView<Label> FilesTree;

    private String login = "";

    private String password = "";

    private Project project;

    private List<Tool> tools = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilesTree.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {
                TreeItem<Label> item = FilesTree.getSelectionModel().getSelectedItem();
                String folder = item.getParent().getValue().getText();
                String nameFile = item.getValue().getText();
                if (folder.equals("src")) {
                    System.out.println(project.readSrcFile(nameFile));
                } else if (folder.equals("test")) {
                    System.out.println(project.readTestFile(nameFile));
                }
            }
        });
    }

    public MainWindowController() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProject(Project project) {
        this.project = project;
        update();
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

    @FXML
    void gitCommit(ActionEvent event) {
        String message = getString("", "git commit", "Enter commit text", "");
        project.gitCommit(message);
    }

    @FXML
    void gitPush(ActionEvent event) {
        if (login.equals("")) {
            login = getString("", "git push", "Enter login", "");
        }
        if (password.equals("")) {
            password = getString("", "git push", "Enter password", "");
        }
        project.gitPush(login, password);
    }

    void runAllTests() {
        Map<String, Boolean> results = project.runAllTests();

        // TODO output results
    }

    private String getString(String defaultValue, String title, String headerText, String contentText) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        return result.get();
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
        TreeItem<Label> srcItem = new TreeItem<>(new Label("src"));
        TreeItem<Label> testItem = new TreeItem<>(new Label("test"));
        rootItem.getChildren().add(srcItem);
        rootItem.getChildren().add(testItem);


        for (File f : srcFiles) {
            Label label = new Label(f.getName());
            TreeItem<Label> item = new TreeItem<>(label);
            srcItem.getChildren().add(item);
        }
        for (File f : testFiles) {
            Label label = new Label(f.getName());
            TreeItem<Label> item = new TreeItem<>(label);
            testItem.getChildren().add(item);
        }

        rootItem.setExpanded(true);
        srcItem.setExpanded(true);
        testItem.setExpanded(true);
        FilesTree.setRoot(rootItem);
    }

    private void writeLog(String text) {
        System.out.println("Controller message: " + text);
    }
}
