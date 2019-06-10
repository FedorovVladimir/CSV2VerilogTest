package model;

import java.util.LinkedList;
import java.util.List;

public class TestModule implements TextMaker {

    private String name;
    private int number;
    private List<String> inputs = new LinkedList<>();
    private List<String> inputsValues = new LinkedList<>();
    private List<String> outputs = new LinkedList<>();
    private List<String> outputsValues = new LinkedList<>();

    public TestModule(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void addOutput(String name, String value) {
        outputs.add(name);
        outputsValues.add(value);
    }

    public void addInput(String name, String value) {
        inputs.add(name);
        inputsValues.add(value);
    }

    @Override
    public String getText() {
        if (number == 0) {
            return "Error " + name;
        }
        if (outputs.size() == 0) {
            return "The module has no outputs.";
        }

        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("_test_").append(number).append("(output reg out);\n");
        if (inputs.size() + outputs.size() > 0) {
            str.append("\treg ");
            str.append(String.join(", ", inputs));
            if (inputs.size() > 0) {
                str.append(";\n\twire ");
            }
            str.append(String.join(", ", outputs));
            str.append(";\n");
        }

        str.append("\t").append(name).append(" ").append(name.charAt(0)).append("(");
        if (inputs.size() + outputs.size() > 0) {
            listAllRegs(str);
        }
        str.append(");\n");

        for (int i = 0; i < outputs.size(); i++) {
            str.append("\treg ");
            str.append("test_").append(outputs.get(i));
            str.append(";\n\twire res_").append(outputs.get(i));
            str.append(";\n");
            str.append("\tassertEquals t").append(i + 1).append("(");
            str.append(outputs.get(i)).append(", test_").append(outputs.get(i)).append(", res_").append(outputs.get(i));
            str.append(");\n");
        }

        str.append("\tinitial begin\n");

        for (int i = 0; i < inputs.size(); i++) {
            str.append("\t\t").append(inputs.get(i)).append(" = ").append(inputsValues.get(i)).append(";\n");
        }

        for (int i = 0; i < outputs.size(); i++) {
            str.append("\t\ttest_").append(outputs.get(i)).append(" = ").append(outputsValues.get(i)).append(";\n");
        }

        str.append("\t\t#1\n");

        str.append("\t\tif (res_").append(outputs.get(0)).append(" == 1");
        for (int i = 1; i < outputsValues.size(); i++) {
            str.append("\n\t\t\t&& res_").append(outputs.get(i)).append(" == 1");
        }
        str.append(")\n");
        str.append("\t\t\tassign out = 1'b1;\n");
        str.append("\t\telse\n");
        str.append("\t\t\tassign out = 1'b0;\n");

        str.append("\tend\n");
        str.append("endmodule\n");
        return str.toString();
    }

    private void listAllRegs(StringBuilder str) {
        str.append(String.join(", ", inputs));
        if (inputs.size() > 0) {
            str.append(", ");
        }
        str.append(String.join(", ", outputs));
    }

    String getName() {
        return name;
    }

    int getNumber() {
        return number;
    }
}
