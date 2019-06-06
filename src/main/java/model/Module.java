package model;

import java.util.LinkedList;
import java.util.List;

public class Module implements TextMaker {

    private String name;
    private List<String> inputs = new LinkedList<>();
    private List<String> outputs = new LinkedList<>();

    public Module(String name) {
        this.name = name;
    }

    @Override
    public String getText() {
        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("(");
        if (inputs.size() > 0) {
            str.append("input ").append(String.join(", ", inputs));
        }
        if (outputs.size() > 0) {
            if (inputs.size() > 0) {
                str.append(", ");
            }
            str.append("output ").append(String.join(", ", outputs));
        }
        str.append(");\n");
        str.append("endmodule\n");
        return str.toString();
    }

    public void addInput(String name) {
        inputs.add(name);
    }

    public void addOutput(String name) {
        outputs.add(name);
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public String getName() {
        return name;
    }
}
