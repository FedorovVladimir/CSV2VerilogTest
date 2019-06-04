import java.util.LinkedList;
import java.util.List;

class Module {

    private String name;
    private List<String> inputs = new LinkedList<String>();
    private List<String> inputsValues = new LinkedList<String>();
    private List<String> outputs = new LinkedList<String>();
    private List<String> outputsValues = new LinkedList<String>();

    Module(String name) {
        this.name = name;
    }

    String getModuleText() {
        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("(");
        if (inputs.size() > 0) {
            str.append("input");
            for (int i = 0; i < inputs.size(); i++) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(" ").append(inputs.get(i));
            }
        }
        if (outputs.size() > 0) {
            if (inputs.size() > 0) {
                str.append(", ");
            }
            str.append("output");
            for (int i = 0; i < outputs.size(); i++) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(" ").append(outputs.get(i));
            }
        }
        str.append(");\n");
        str.append("endmodule\n");
        return str.toString();
    }

    void addInput(String name) {
        inputs.add(name);
    }

    void addOutput(String name) {
        outputs.add(name);
    }

    void addOutput(String name, int value) {
        outputs.add(name);
        outputsValues.add(String.valueOf(value));
    }

    String getTestText(int number) {
        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("_test_").append(number).append("(output out);\n");
        if (inputs.size() > 0) {
            str.append("\treg");
            for (int i = 0; i < inputs.size(); i++) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(" ").append(inputs.get(i));
            }
            for (int i = 0; i < outputs.size(); i++) {
                if (i > 0 || inputs.size() > 0) {
                    str.append(",");
                }
                str.append(" ").append(outputs.get(i));
            }
        }
        str.append(";\n");

        str.append("\t").append(name).append(" ").append(name.charAt(0)).append("(");
        if (inputs.size() > 0) {
            for (int i = 0; i < inputs.size(); i++) {
                if (i > 0) {
                    str.append(", ");
                }
                str.append(inputs.get(i));
            }
            for (int i = 0; i < outputs.size(); i++) {
                if (i > 0 || inputs.size() > 0) {
                    str.append(",");
                }
                str.append(" ").append(outputs.get(i));
            }
        }
        str.append(");\n");

        str.append("\treg ");
        for (int i = 0; i < outputs.size(); i++) {
            if (i > 0) {
                str.append(", ");
            }
            str.append("test_").append(outputs.get(i));
        }
        str.append(";\n");

        for (int i = 0; i < outputs.size(); i++) {
            str.append("\tassertEquals t").append(i + 1).append("(");
            str.append(outputs.get(i)).append(", test_").append(outputs.get(i));
            str.append(");\n");
        }

        str.append("\tinitial begin\n");
        str.append("\tend\n");
        str.append("endmodule\n");
        return str.toString();
    }

    void addInput(String name, int value) {
        inputs.add(name);
        inputsValues.add(String.valueOf(value));
    }
}
