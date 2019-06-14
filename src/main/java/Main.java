import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tdhd.project.Project;
import tdhd.view.MainWindowController;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        VBox root = loader.load();
        primaryStage.setTitle("Test Driven Hardware Development");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        Project project = new Project(new File("C:\\Users\\vladimir\\TDHDProjects\\first"));
        // TODO read last project
        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setLogin("FedorovVladimir");
        mainWindowController.setPassword("Mitogfvo08011997");
        mainWindowController.setProject(project);
    }

    public static void main(String[] args) {
        launch(args);
    }
}