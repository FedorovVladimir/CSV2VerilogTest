package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.Module;

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

    @FXML
    private TextArea Code;

    @FXML
    private Button ButtonReset;

    private String nameModule = "";
    private List<ViewLine> inputsLines = new LinkedList<>();
    private List<ViewLine> outputsLines = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelNameModule.setText("");

        TextFieldNameModule.textProperty().addListener((ov, oldV, newV) -> {
            nameModule = newV;
            update();
        });

        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!TextFieldNameInput.getText().equals("")) {
                addLineInput(new ViewLine(TextFieldNameInput.getText()));
                TextFieldNameInput.setText("");
            }
        });
        ButtonAddInput.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        ButtonAddOutput.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (!TextFieldNameOutput.getText().equals("")) {
                ViewLine viewLine = new ViewLine(TextFieldNameOutput.getText());
                addLineOutput(viewLine);
                TextFieldNameOutput.setText("");
            }
        });
        ButtonAddOutput.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        ButtonReset.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            nameModule = "";
            TextFieldNameModule.setText("");
            for (ViewLine inputsLine : inputsLines) {
                ModulePane.getChildren().remove(inputsLine.getLine());
                ModulePane.getChildren().remove(inputsLine.getLabel());
            }
            for (ViewLine outputsLine : outputsLines) {
                ModulePane.getChildren().remove(outputsLine.getLine());
                ModulePane.getChildren().remove(outputsLine.getLabel());
            }
            inputsLines.clear();
            outputsLines.clear();
        });
        ButtonReset.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            updateLinesPositions();
            update();
        });

        update();
    }

    private void update() {
        LabelNameModule.setText(nameModule);
        if (!nameModule.equals("")) {
            Module module = new Module(nameModule);
            for (ViewLine inputsLine : inputsLines) {
                module.addInput(inputsLine.getLabel().getText());
            }
            for (ViewLine outputsLine : outputsLines) {
                module.addOutput(outputsLine.getLabel().getText());
            }
            Code.setText(module.getModuleText());
        } else {
            Code.setText("Enter the name of the module.");
        }
    }

    private void addLineInput(ViewLine viewLine) {
        inputsLines.add(viewLine);
        ModulePane.getChildren().add(0, viewLine.getLabel());
        ModulePane.getChildren().add(0, viewLine.getLine());
    }

    private void updateLinesPositions() {
        double xStart = RectangleModule.getLayoutX();
        double yStart = RectangleModule.getLayoutY();
        double w = RectangleModule.getWidth();
        double h = RectangleModule.getHeight();

        double step = h / (inputsLines.size() + 1);
        double step2 = h / (outputsLines.size() + 1);
        while (step < 30 || step2 < 30) {
            RectangleModule.setHeight(h + 2);
            RectangleModule.setLayoutY(yStart - 1);
            xStart = RectangleModule.getLayoutX();
            yStart = RectangleModule.getLayoutY();
            w = RectangleModule.getWidth();
            h = RectangleModule.getHeight();
            step = h / (inputsLines.size() + 1);
            step2 = h / (outputsLines.size() + 1);
        }

        for (int i = 0; i < inputsLines.size(); i++) {
            inputsLines.get(i).setPositionInput(100, yStart + step * (i+1), xStart + w/2, yStart + step * (i+1));
        }

        for (int i = 0; i < outputsLines.size(); i++) {
            outputsLines.get(i).setPositionOutput(xStart + w/2, yStart + step2 * (i+1), ModulePane.getWidth() - 100, yStart + step2 * (i+1));
        }

        LabelNameModule.setLayoutY(RectangleModule.getLayoutY() - 20);
    }

    private void addLineOutput(ViewLine viewLine) {
        outputsLines.add(viewLine);
        ModulePane.getChildren().add(0, viewLine.getLabel());
        ModulePane.getChildren().add(0, viewLine.getLine());
    }
}