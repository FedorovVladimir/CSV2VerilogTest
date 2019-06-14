package new_design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import new_design_model.PathFile;
import new_design_model.PathProject;
import new_design_model.Project;
import new_design_model.Url;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TreeView<Label> TreeView;

    @FXML
    private TextArea TextCode;

    @FXML
    private TextArea TextTest;

    @FXML
    private TextArea TextConsole;

    @FXML
    private Label LabelFileCode;

    @FXML
    private Label LabelTestCode;

    private Project project;
    private Stage root = App.getPrimaryStage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = new Project("untitled");
        updateTitle();
        updateListFiles();

        LabelFileCode.setText("");
        LabelTestCode.setText("");

        TextCode.textProperty().addListener((obs,old,niu)-> project.saveModule(TextCode.getText(), LabelFileCode.getText()));
        TextTest.textProperty().addListener((obs,old,niu)-> project.saveTest(TextTest.getText(), LabelTestCode.getText()));
    }

    @FXML
    void newProject(ActionEvent event) {
        final String[] projectName = {""};

        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("New project");
        dialog.setHeaderText("Enter project name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> projectName[0] = name);

        if (!projectName[0].equals("")) {
            project = new Project(projectName[0]);
            updateTitle();
            updateListFiles();
        }
    }

    @FXML
    void openProject(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(PathProject.getProjectsPath()));
        File selectedDirectory = directoryChooser.showDialog(root);

        if (selectedDirectory != null) {
            String pathStr = selectedDirectory.getPath();
            PathFile path = new PathFile(pathStr);
            project = new Project(path);
            updateTitle();
            updateListFiles();
        }
    }

    @FXML
    void cloneProject(ActionEvent event) {
        final String[] gitUrl = {""};

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Clone project");
        dialog.setHeaderText("Enter git url:");
        dialog.setContentText("URL:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> gitUrl[0] = name);

        Alert alert;
        String title = "Git clone";
        String header = "Git clone";
        if (!gitUrl[0].equals("")) {
            project = new Project(new Url(gitUrl[0]));
            if (project.isOpen()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Success!");
                updateTitle();
                updateListFiles();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Fail");
            }
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.showAndWait();
        }
    }

    private void updateTitle() {
        root.setTitle(project.getName() + " [" + project.getPath() + "] Test Driven Hardware Development");
    }

    private void updateListFiles() {
        File[] filesSrc = project.getListFiles();
        File[] filesTest = project.getListTests();

        Label srcLabel = new Label("src");
        srcLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/src_icon.png"))));
        TreeItem<Label> srcItem = loadFiles(filesSrc, srcLabel, (EventHandler<MouseEvent>) e -> {
            String nameFile = TreeView.getSelectionModel().getSelectedItems().get(0).getValue().getText();
            LabelFileCode.setText(nameFile);
            TextCode.setText(project.getModuleText(nameFile));
        });

        Label testLabel = new Label("test");
        testLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/test_icon.png"))));
        TreeItem<Label> testItem = loadFiles(filesTest, testLabel, (EventHandler<MouseEvent>) e -> {
            String nameFile = TreeView.getSelectionModel().getSelectedItems().get(0).getValue().getText();
            LabelTestCode.setText(nameFile);
            TextTest.setText(project.getTestText(TreeView.getSelectionModel().getSelectedItems().get(0).getValue().getText()));
        });

        Label projectLabel = new Label(project.getName());
        projectLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/folder_icon.png"))));
        TreeItem<Label> rootItem = new TreeItem<>(projectLabel);
        rootItem.getChildren().add(srcItem);
        rootItem.getChildren().add(testItem);

        TreeView.setRoot(rootItem);
        rootItem.setExpanded(true);
        srcItem.setExpanded(true);
        testItem.setExpanded(true);
    }

    private TreeItem<Label> loadFiles(File[] filesSrc, Label srcLabel, EventHandler<MouseEvent> eventHandler) {
        TreeItem<Label> item = new TreeItem<>(srcLabel);
        for (File file : filesSrc) {
            Label label = new Label(file.getName());
            label.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/text_icon.png"))));
            label.setOnMouseClicked(eventHandler);
            item.getChildren().add(new TreeItem<>(label));
        }
        return item;
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }



    @FXML
    void newModule(ActionEvent event) {
        final String[] projectName = {""};

        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("New module");
        dialog.setHeaderText("Enter module name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> projectName[0] = name);

        if (!projectName[0].equals("")) {
            project.newModule(projectName[0]);
            updateListFiles();
        }
    }

    @FXML
    void removeModule(ActionEvent event) {
        String name = TreeView.getSelectionModel().getSelectedItems().get(0).getValue().getText();
        project.removeModule(name);
        updateListFiles();
    }



    @FXML
    void newTest(ActionEvent event) {
        final String[] testName = {""};

        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("New test");
        dialog.setHeaderText("Enter test name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> testName[0] = name);

        if (!testName[0].equals("")) {
            project.newTest(testName[0]);
            updateListFiles();
        }
    }

    @FXML
    void removeTest(ActionEvent event) {
        String name = TreeView.getSelectionModel().getSelectedItems().get(0).getValue().getText();
        project.removeTest(name);
        updateListFiles();
    }



    @FXML
    void run(ActionEvent event) {
        TextConsole.setText(project.run());
    }

    @FXML
    void commit(ActionEvent event) {
        final String[] message = {""};

        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("New commit");
        dialog.setHeaderText("Enter commit text:");
        dialog.setContentText("Text:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> message[0] = name);

        project.commit(message[0]);
    }

    @FXML
    void push(ActionEvent event) {
        project.push();
    }

    @FXML
    void runImage(MouseEvent event) {
        TextConsole.setText(project.run());
    }
}
