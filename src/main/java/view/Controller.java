package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelNameModule.setText("");

        TextFieldNameModule.textProperty().addListener((ov, oldV, newV) -> LabelNameModule.setText(newV));

        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            double x = RectangleModule.getLayoutX() + RectangleModule.getWidth() / 2;
            double y = RectangleModule.getLayoutY() + RectangleModule.getHeight() / 2;
            Line line = new Line(100,y, x, y);
            ModulePane.getChildren().add(0, line);
        });
    }
}
