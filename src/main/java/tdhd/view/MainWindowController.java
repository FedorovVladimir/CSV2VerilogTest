package tdhd.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import tdhd.project.Project;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class MainWindowController implements Initializable {

    @FXML
    private TreeView<Label> FilesTree;

    @FXML
    private Tab SrcFileName;

    @FXML
    private TextArea SrcCode;

    @FXML
    private Tab TestFileName;

    @FXML
    private TextArea TestCode;

    @FXML
    private TreeView<Label> TestsTree;

    @FXML
    private TextArea Console;

    private String login = "";

    private String password = "";

    private Project project;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilesTree.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {
                TreeItem<Label> item = FilesTree.getSelectionModel().getSelectedItem();
                String folder = item.getParent().getValue().getText();
                String nameFile = item.getValue().getText();
                if (folder.equals("src")) {
                    SrcFileName.setText(nameFile);
                    SrcCode.setText(project.readSrcFile(nameFile));
                } else if (folder.equals("test")) {
                    TestFileName.setText(nameFile);
                    TestCode.setText(project.readTestFile(nameFile));
                }
            }
        });

        SrcCode.textProperty().addListener((obs,old,niu) -> {
            saveSrcFile(niu);
        });

        TestCode.textProperty().addListener((obs,old,niu) -> {
            saveTestFile(niu);
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

    @FXML
    void runAllTests(ActionEvent event) {
        Label projectLabel = new Label("test");
        TreeItem<Label> rootItem = new TreeItem<>(projectLabel);
        for (File testFile: project.getAllTestFiles()) {
            if (testFile.getName().equals("allTests.v")) {
                continue;
            }
            Label label = new Label(testFile.getName());
            TreeItem<Label> item = new TreeItem<>(label);
            rootItem.getChildren().add(item);

            String compileStr = project.compile(testFile);
            Console.setText(compileStr);
            if (compileStr.equals("")) {
                List<Map<String, String>> tests = project.run();
                boolean isAllTestTrue = true;
                for (Map<String, String> test : tests) {
                    Label labelOneTest = new Label(test.get("name"));
                    TreeItem<Label> itemOneTest = new TreeItem<>(labelOneTest);
                    item.getChildren().add(itemOneTest);

                    InputStream input;
                    if (test.get("result").equals("1")) {
                        input = getClass().getResourceAsStream("/t_true.png");
                    } else {
                        input = getClass().getResourceAsStream("/t_false.png");
                        isAllTestTrue = false;
                    }
                    Image image = new Image(input);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    labelOneTest.setGraphic(imageView);
                }
                InputStream input;
                if (isAllTestTrue) {
                    input = getClass().getResourceAsStream("/t_true.png");
                } else {
                    input = getClass().getResourceAsStream("/t_false.png");
                }
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                label.setGraphic(imageView);
                if (!isAllTestTrue) {
                    item.setExpanded(true);
                }
            }

            rootItem.setExpanded(true);
            TestsTree.setRoot(rootItem);
        }
    }

    private String getString(String defaultValue, String title, String headerText, String contentText) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    @FXML
    void newSrcFile(ActionEvent event) {
        String nameFile = getString("", "new file", "Enter name file", "");
        project.createSrcFile(nameFile);
        update();
    }

    @FXML
    void newTestFile(ActionEvent event) {
        String nameFile = getString("", "new file", "Enter name file", "");
        project.createTestFile(nameFile);
        update();
    }

    private void saveSrcFile(String text) {
        String nameFile = SrcFileName.getText();
        project.saveSrcFile(text, nameFile);
    }

    private void saveTestFile(String text) {
        String nameFile = TestFileName.getText();
        project.saveTestFile(text, nameFile);
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
            if (f.getName().equals("allTests.v")) {
                continue;
            }
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
