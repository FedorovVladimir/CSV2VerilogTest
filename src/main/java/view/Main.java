package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Test Driven Hardware Development");
        primaryStage.setScene(new Scene(root, 960, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
