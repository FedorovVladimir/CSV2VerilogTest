package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
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

    private List<ViewLine> inputsLines = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelNameModule.setText("");

        TextFieldNameModule.textProperty().addListener((ov, oldV, newV) -> LabelNameModule.setText(newV));

        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!TextFieldNameInput.getText().equals("")) {
                addLineInput(new ViewLine(TextFieldNameInput.getText()));
                TextFieldNameInput.setText("");
            }
        });

        ButtonAddOutput.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!TextFieldNameOutput.getText().equals("")) {
                addLineInput(new ViewLine(TextFieldNameOutput.getText()));
                TextFieldNameOutput.setText("");
            }
        });
    }

    private void addLineInput(ViewLine viewLine) {
        inputsLines.add(viewLine);
        ModulePane.getChildren().add(0, viewLine.getLabel());
        ModulePane.getChildren().add(0, viewLine.getLine());
        updateInputsPositions();
    }

    private void updateInputsPositions() {
        double xStart = RectangleModule.getLayoutX();
        double yStart = RectangleModule.getLayoutY();
        double w = RectangleModule.getWidth();
        double h = RectangleModule.getHeight();

        double step = h / (inputsLines.size() + 1);
        while (step < 30) {
            RectangleModule.setHeight(h + 2);
            RectangleModule.setLayoutY(yStart - 1);
            xStart = RectangleModule.getLayoutX();
            yStart = RectangleModule.getLayoutY();
            w = RectangleModule.getWidth();
            h = RectangleModule.getHeight();
            step = h / (inputsLines.size() + 1);
        }

        for (int i = 0; i < inputsLines.size(); i++) {
            inputsLines.get(i).setPosition(100, yStart + step * (i+1), xStart + w/2, yStart + step * (i+1));
        }
    }
}
