package new_design;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import new_design_model.Path;
import new_design_model.Project;
import new_design_model.Url;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public class Controller {

    @FXML
    private TreeView<Label> TreeView;

    private Project project;
    private Stage root = App.getPrimaryStage();

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
        directoryChooser.setInitialDirectory(new File(Path.getProjectsPath()));
        File selectedDirectory = directoryChooser.showDialog(root);

        if (selectedDirectory != null) {
            String pathStr = selectedDirectory.getName();
            Path path = new Path(pathStr);
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

    private void updateTitle() {
        root.setTitle(project.getName() + " [" + project.getPath() + "] Test Driven Hardware Development");
    }

    private void updateListFiles() {
        File[] filesSrc = project.getListFiles();
        File[] filesTest = project.getListTests();

        Label srcLabel = new Label("src");
        srcLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/src_icon_5.png"))));
        TreeItem<Label> srcItem = loadFiles(filesSrc, srcLabel);

        Label testLabel = new Label("test");
        testLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/test_icon_5.png"))));
        TreeItem<Label> testItem = loadFiles(filesTest, testLabel);

        Label projectLabel = new Label(project.getName());
        projectLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/folder_icon.png"))));
        TreeItem<Label> rootItem = new TreeItem<>(projectLabel);
        rootItem.getChildren().add(srcItem);
        rootItem.getChildren().add(testItem);

        TreeView.setRoot(rootItem);
    }

    private TreeItem<Label> loadFiles(File[] filesSrc, Label srcLabel) {
        TreeItem<Label> item = new TreeItem<>(srcLabel);
        for (File file : filesSrc) {
            Label label = new Label(file.getName());
            label.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/text_icon.png"))));
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
//        project.newModule();
    }

    @FXML
    void editModule(ActionEvent event) {
//        project.editModule();
    }

    @FXML
    void removeModule(ActionEvent event) {
//        project.removeModule();
    }



    @FXML
    void newTest(ActionEvent event) {
//        project.newTest();
    }

    @FXML
    void editTest(ActionEvent event) {
//        project.editTest();
    }

    @FXML
    void removeTest(ActionEvent event) {
//        project.removeTest();
    }



    @FXML
    void run(ActionEvent event) {
//        project.run();
    }

    @FXML
    void commit(ActionEvent event) {
//        project.commit();
    }

    @FXML
    void push(ActionEvent event) {
//        project.push();
    }
}
