package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.AllTests;
import model.AssertModule;
import model.Module;
import model.TestModule;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCreateTests implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private TextArea Code;

    @FXML
    private Button ButtonAddTest;

    private List<List<Button>> table = new LinkedList<>();

    private List<List<String>> rows = new LinkedList<>();

    private Module module;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButtonAddTest.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> addRow());
        ButtonAddTest.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> update(module));
    }

    void update(Module module) {
        this.module = module;

        for (List<Button> buttons : table) {
            for (Button button : buttons) {
                MainPane.getChildren().remove(button);
            }
        }
        table.clear();

        int x = 20;
        int y = 20;

        List<Button> header = new LinkedList<>();
        for (int i = 0; i < module.getInputs().size(); i++) {
            Button button = new Button(module.getInputs().get(i));
            button.setTranslateX(x);
            button.setTranslateY(y);
            button.setMinWidth(40);
            x += 50;
            header.add(button);
            MainPane.getChildren().add(button);
        }
        for (int i = 0; i < module.getOutputs().size(); i++) {
            Button button = new Button(module.getOutputs().get(i));
            button.setTranslateX(x);
            button.setTranslateY(y);
            button.setMinWidth(40);
            x += 50;
            header.add(button);
            MainPane.getChildren().add(button);
        }
        table.add(header);

        if (rows.size() == 0) {
            addRow();
        }

        for (int i = 0; i < rows.size(); i++) {
            x = 20;
            y += 40;
            for (int j = 0; j < rows.get(i).size(); j++) {
                Button button = new Button(rows.get(i).get(j));

                int finalI = i;
                int finalJ = j;
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (rows.get(finalI).get(finalJ).equals("0")) {
                        rows.get(finalI).set(finalJ, "1");
                    } else {
                        rows.get(finalI).set(finalJ, "0");
                    }
                    update(module);
                });

                button.setTranslateX(x);
                button.setTranslateY(y);
                button.setMinWidth(40);
                x += 50;
                header.add(button);
                MainPane.getChildren().add(button);
            }

            Button delete = new Button("remove test");
            int finalI = i;
            delete.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> rows.remove(finalI));
            delete.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> update(module));

            delete.setTranslateX(x);
            delete.setTranslateY(y);
            delete.setMinWidth(40);
            header.add(delete);
            MainPane.getChildren().add(delete);
        }


        Code.setText("");
        AllTests allTests = new AllTests();
        for (int i = 0; i < rows.size(); i++) {
            if (!module.getName().equals("")) {
                TestModule testModule = new TestModule(module.getName(), i + 1);

                for (int j = 0; j < module.getInputs().size(); j++) {
                    testModule.addInput(module.getInputs().get(j), rows.get(i).get(j));
                }

                for (int j = 0; j < module.getOutputs().size(); j++) {
                    testModule.addOutput(module.getOutputs().get(j), rows.get(i).get(module.getInputs().size() + j));
                }
                Code.setText(testModule.getText() + "\n" + Code.getText());
                allTests.add(testModule);
            }
        }
        if (!Code.getText().equals("") && !Code.getText().trim().equals("The module has no outputs.")) {
            Code.setText(Code.getText() + "\n" + allTests.getText());
            Code.setText(Code.getText() + "\n" + new AssertModule().getText());
        }
    }

    private void addRow() {
        List<String> row = new LinkedList<>();
        for (int i = 0; i < module.getInputs().size() + module.getOutputs().size(); i++) {
            row.add(String.valueOf(0));
        }
        rows.add(row);
    }

}
