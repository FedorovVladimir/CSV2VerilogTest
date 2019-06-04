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

        button.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            if (!textField.getText().equals("")) {
                label.setText(textField.getText());
            }
            newWindow.close();
        });

        label.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            Label secondLabel = new Label("Enter the new name of the module.");
            textField.setText(label.getText());

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(secondLabel);
            anchorPane.getChildren().add(textField);
            anchorPane.getChildren().add(button);

            secondLabel.setLayoutX(20);
            secondLabel.setLayoutY(20);

            textField.setLayoutX(20);
            textField.setLayoutY(55);

            button.setLayoutX(20);
            button.setLayoutY(100);

            Scene secondScene = new Scene(anchorPane, 250, 140);

            newWindow = new Stage();
            newWindow.setTitle("settings");
            newWindow.setScene(secondScene);

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();

            newWindow.setX((double) screenSize.width/2 - secondScene.getWidth()/2);
            newWindow.setY((double) screenSize.height/2 - secondScene.getHeight()/2);

            newWindow.show();
        });
        label.setOnMouseMoved(event -> label.setCursor(Cursor.HAND));
    }

    Label getLabel() {
        return label;
    }

    Line getLine() {
        return line;
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
