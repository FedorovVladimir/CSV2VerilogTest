package view;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

class ViewLine{

    private Stage newWindow;
    private Button button = new Button("save");
    private TextField textField = new TextField();

    private Line line;
    private Label label;

    ViewLine(String name) {
        label = new Label(name);
        label.setFont(new Font("System", 14));
        line = new Line();
        setPosition(-100, -100, -100, -100);

        label.setOnMouseMoved(event -> label.setCursor(Cursor.HAND));
    }

    Label getLabel() {
        return label;
    }

    Line getLine() {
        return line;
    }

    TextField getTextField() {
        return textField;
    }

    public Stage getNewWindow() {
        return newWindow;
    }

    public void setNewWindow(Stage newWindow) {
        this.newWindow = newWindow;
    }

    Button getButton() {
        return button;
    }

    private void setPosition(double x, double y, double x2, double y2) {
        label.setLayoutY(y - 22);
        line.setStartX(x);
        line.setEndX(x2);
        line.setStartY(y);
        line.setEndY(y2);
    }

    void setPositionInput(double x, double y, double x2, double y2) {
        label.setLayoutX(x);
        setPosition(x, y, x2, y2);
    }

    void setPositionOutput(double x, double y, double x2, double y2) {
        label.setLayoutX(x2 - label.getWidth());
        setPosition(x, y, x2, y2);
    }
}
