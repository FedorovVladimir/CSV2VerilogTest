package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import new_design.Module;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGitClone implements Initializable {

    @FXML
    private TextField TextFieldURL;

    @FXML
    private TextField TextFieldDirectory;

    @FXML
    private Button ButtonClone;

    private Module module;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonClone.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
             try {
                Git.cloneRepository()
                    .setURI(TextFieldURL.getText())
                    .setDirectory(new File(TextFieldDirectory.getText()))
                    .call();
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Git clone");
                 alert.setHeaderText("Git clone");
                 alert.setContentText("Success!");
                 alert.showAndWait();
            } catch (GitAPIException e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Git clone");
                 alert.setHeaderText("Git clone");
                 alert.setContentText("Fail");
                 alert.showAndWait();
            }
        });
    }

    public void setModule(Module module) {
        this.module = module;
        if (module != null) {
            TextFieldDirectory.setText("C:\\Users\\vladimir\\TDHDProjects\\" + module.getName());
        }
    }

    Button getButtonClone() {
        return ButtonClone;
    }
}
