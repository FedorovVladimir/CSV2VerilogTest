package new_design;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import new_design_model.Path;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        App.primaryStage = primaryStage;
        VBox root = FXMLLoader.load(getClass().getResource("/fxml/Wrapper.fxml"));
        primaryStage.setTitle("Test Driven Hardware Development");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        Path.setProjectsPath("C:\\Users\\vladimir\\TDHDProjects");
    }

    static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
