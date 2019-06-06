package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import model.Module;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGitCommit implements Initializable {

    @FXML
    private TextArea TextAreaCommit;

    @FXML
    private Button ButtonCommit;

    private Module module;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonCommit.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                File gitFile = new File("C:\\Users\\vladimir\\TDHDProjects\\" + module.getName() + "\\.git");
                Git git = Git.open(gitFile);
                Repository repository = git.getRepository();
                git.add().addFilepattern(".").call();
                git.commit().setMessage(TextAreaCommit.getText()).call();
            } catch (IOException | GitAPIException e) {
                e.printStackTrace();
            }
        });
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
