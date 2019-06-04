package view;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

class ViewLine{

    private Line line;
    private Label label;

    ViewLine(String name) {
        label = new Label(name);
        label.setFont(new Font("System", 14));
        line = new Line();
        setPosition(-100, -100, -100, -100);
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
        System.out.println("update");
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
