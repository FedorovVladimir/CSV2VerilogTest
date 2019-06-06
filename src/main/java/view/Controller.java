package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Module;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane ModulePane;

    @FXML
    private Rectangle RectangleModule;

    @FXML
    private Label LabelNameModule;

    @FXML
    private TextField TextFieldNameModule;

    @FXML
    private TextField TextFieldNameInput;

    @FXML
    private Button ButtonAddInput;

    @FXML
    private TextField TextFieldNameOutput;

    @FXML
    private Button ButtonAddOutput;

    @FXML
    private TextArea Code;

    @FXML
    private Button ButtonReset;

    @FXML
    private Button ButtonCreateTests;

    @FXML
    private Button ButtonPush;

    @FXML
    private Button ButtonCommit;

    @FXML
    private Button ButtonClone;

    @FXML
    private Button ButtonSave;

    private AnchorPane testsWindow;
    private AnchorPane gitCloneWindow;
    private AnchorPane gitCommitWindow;
    private ControllerCreateTests controllerCreateTests;
    private ControllerGitClone controllerGitClone;
    private ControllerGitCommit controllerGitCommit;

    private String nameModule = "";
    private List<ViewLine> inputsLines = new LinkedList<>();
    private List<ViewLine> outputsLines = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelNameModule.setText("");

        TextFieldNameModule.textProperty().addListener((ov, oldV, newV) -> {
            nameModule = newV;
            update();
        });

        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!TextFieldNameInput.getText().equals("")) {
                addLineInput(new ViewLine(TextFieldNameInput.getText()));
                TextFieldNameInput.setText("");
            }
        });
        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        ButtonAddOutput.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!TextFieldNameOutput.getText().equals("")) {
                ViewLine viewLine = new ViewLine(TextFieldNameOutput.getText());
                addLineOutput(viewLine);
                TextFieldNameOutput.setText("");
            }
        });
        ButtonAddOutput.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        ButtonReset.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            nameModule = "";
            TextFieldNameModule.setText("");
            for (ViewLine inputsLine : inputsLines) {
                ModulePane.getChildren().remove(inputsLine.getLine());
                ModulePane.getChildren().remove(inputsLine.getLabel());
            }
            for (ViewLine outputsLine : outputsLines) {
                ModulePane.getChildren().remove(outputsLine.getLine());
                ModulePane.getChildren().remove(outputsLine.getLabel());
            }
            inputsLines.clear();
            outputsLines.clear();
        });
        ButtonReset.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        ButtonCreateTests.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/create_tests.fxml"));
                testsWindow = loader.load();
                controllerCreateTests = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Create tests");
            stage.setScene(new Scene(testsWindow, 960, 720));
            stage.show();
        });
        ButtonCreateTests.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> update());

        ButtonClone.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/git_clone.fxml"));
                gitCloneWindow = loader.load();
                controllerGitClone = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Git clone");
            stage.setScene(new Scene(gitCloneWindow, 500, 150));
            stage.show();

            controllerGitClone.setModule(update());
            controllerGitClone.getButtonClone().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent2 -> stage.close());
        });

        ButtonCommit.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/git_commit.fxml"));
                gitCommitWindow = loader.load();
                controllerGitCommit = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Git commit");
            stage.setScene(new Scene(gitCommitWindow, 300, 300));
            stage.show();

            controllerGitCommit.setModule(update());
            controllerGitCommit.getButtonCommit().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent2 -> stage.close());
        });

        ButtonPush.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                File gitFile = new File("C:\\Users\\vladimir\\TDHDProjects\\" + TextFieldNameModule.getText() + "\\.git");
                Git git = Git.open(gitFile);
                PushCommand pushCommand = git.push();
                pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("FedorovVladimir", "Mitogfvo08011997"));
                pushCommand.call();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Git push");
                alert.setHeaderText("Git push");
                alert.setContentText("Success!");
                alert.showAndWait();
            } catch (IOException | GitAPIException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Git push");
                alert.setHeaderText("Git push");
                alert.setContentText("Fail");
                alert.showAndWait();
            }
        });

        ButtonSave.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            File dir = new File("C:\\Users\\vladimir\\TDHDProjects\\" + TextFieldNameModule.getText() + "\\src");
            if (dir.mkdir()) {
                System.out.println("Создана директория " + dir.getName());
            }
            Module module = update();
            File codeModule = new File("C:\\Users\\vladimir\\TDHDProjects\\" + TextFieldNameModule.getText() + "\\src\\" + module.getName() + ".sv");
            try {
                FileWriter fileWriter = new FileWriter(codeModule, false);
                fileWriter.write(module.getText());
                fileWriter.flush();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Save");
                alert.setContentText("Success!");
                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Save");
                alert.setHeaderText("Save");
                alert.setContentText("Fail");
                alert.showAndWait();
            }
        });

        update();
    }

    private void updateLinesPositions() {
        double xStart = RectangleModule.getLayoutX();
        double yStart = RectangleModule.getLayoutY();
        double w = RectangleModule.getWidth();
        double h = RectangleModule.getHeight();

        double step = h / (inputsLines.size() + 1);
        double step2 = h / (outputsLines.size() + 1);
        while (step < 30 || step2 < 30) {
            RectangleModule.setHeight(h + 2);
            RectangleModule.setLayoutY(yStart - 1);
            xStart = RectangleModule.getLayoutX();
            yStart = RectangleModule.getLayoutY();
            w = RectangleModule.getWidth();
            h = RectangleModule.getHeight();
            step = h / (inputsLines.size() + 1);
            step2 = h / (outputsLines.size() + 1);
        }

        for (int i = 0; i < inputsLines.size(); i++) {
            inputsLines.get(i).setPositionInput(100, yStart + step * (i+1), xStart + w/2, yStart + step * (i+1));
        }

        for (int i = 0; i < outputsLines.size(); i++) {
            outputsLines.get(i).setPositionOutput(xStart + w/2, yStart + step2 * (i+1), ModulePane.getWidth() - 100, yStart + step2 * (i+1));
        }

        LabelNameModule.setLayoutY(RectangleModule.getLayoutY() - 20);
    }

    private Module update() {
        LabelNameModule.setText(nameModule);
        Module module = null;
        if (!nameModule.equals("")) {
            module = new Module(nameModule);
            for (ViewLine inputsLine : inputsLines) {
                module.addInput(inputsLine.getLabel().getText());
            }
            for (ViewLine outputsLine : outputsLines) {
                module.addOutput(outputsLine.getLabel().getText());
            }
            Code.setText(module.getText());
            if (controllerCreateTests != null) {
                controllerCreateTests.update(module);
            }
        } else {
            Code.setText("Enter the name of the module.");
        }
        return module;
    }

    private void SettingsNamesEvent(ViewLine viewLine) {
        viewLine.getLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Label secondLabel = new Label("Enter the new name of the module.");
            viewLine.getTextField().setText(viewLine.getLabel().getText());

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(secondLabel);
            anchorPane.getChildren().add(viewLine.getTextField());
            anchorPane.getChildren().add(viewLine.getButton());

            secondLabel.setLayoutX(20);
            secondLabel.setLayoutY(20);

            viewLine.getTextField().setLayoutX(20);
            viewLine.getTextField().setLayoutY(55);

            viewLine.getButton().setLayoutX(20);
            viewLine.getButton().setLayoutY(100);

            Scene secondScene = new Scene(anchorPane, 250, 140);

            viewLine.setNewWindow(new Stage());
            viewLine.getNewWindow().setTitle("settings");
            viewLine.getNewWindow().setScene(secondScene);

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();

            viewLine.getNewWindow().setX((double) screenSize.width/2 - secondScene.getWidth()/2);
            viewLine.getNewWindow().setY((double) screenSize.height/2 - secondScene.getHeight()/2);

            viewLine.getNewWindow().show();
        });

        viewLine.getButton().addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            if (!viewLine.getTextField().getText().equals("")) {
                viewLine.getLabel().setText(viewLine.getTextField().getText());
            }
            viewLine.getNewWindow().close();
            update();
        });
    }

    private void addLineInput(ViewLine viewLine) {
        inputsLines.add(viewLine);
        ModulePane.getChildren().add(0, viewLine.getLabel());
        ModulePane.getChildren().add(0, viewLine.getLine());
        SettingsNamesEvent(viewLine);
    }

    private void addLineOutput(ViewLine viewLine) {
        outputsLines.add(viewLine);
        ModulePane.getChildren().add(0, viewLine.getLabel());
        ModulePane.getChildren().add(0, viewLine.getLine());
        SettingsNamesEvent(viewLine);
    }
}
