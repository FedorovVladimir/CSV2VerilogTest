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
    }

    Label getLabel() {
        return label;
    }

    Line getLine() {
        return line;
    }

    void setPosition(double x, double y, double x2, double y2) {
        label.setLayoutX(x);
        label.setLayoutY(y - 22);
        line.setStartX(x);
        line.setEndX(x2);
        line.setStartY(y);
        line.setEndY(y2);
    }
}
