import java.util.LinkedList;
import java.util.List;

class Module {

    private String name;
    private int number;
    private List<String> inputs = new LinkedList<>();
    private List<String> inputsValues = new LinkedList<>();
    private List<String> outputs = new LinkedList<>();
    private List<String> outputsValues = new LinkedList<>();

    Module(String name) {
        this.name = name;
    }

    Module(String name, int number) {
        this.name = name;
        this.number = number;
    }

    static String getTestAssertEquals() {
        return "module assertEquals(input a, b, output reg out);\n" +
                "\tinitial begin \n" +
                "\t\t#1\n" +
                "\t\tif (a == b)\n" +
                "\t\t\tassign out = 1'b1;\n" +
                "\t\telse\n" +
                "\t\t\tassign out = 1'b0;\n" +
                "\tend\n" +
                "endmodule\n";
    }

    int getNumber() {
        return number;
    }

    String getName() {
        return name;
    }

    String getModuleText() {
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

    String getTestText() {
        if (number == 0) {
            return "Error " + name;
        }

        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("_test_").append(number).append("(output reg out);\n");
        if (inputs.size() + outputs.size() > 0) {
            str.append("\treg ");
            listAllRegs(str);
            str.append(";\n");
        }

        str.append("\t").append(name).append(" ").append(name.charAt(0)).append("(");
        if (inputs.size() + outputs.size() > 0) {
            listAllRegs(str);
        }
        str.append(");\n");

        if (outputs.size() > 0) {
            str.append("\treg ");
            str.append("test_").append(String.join(", test_", outputs));
            str.append(";\n");
        }

        for (int i = 0; i < outputs.size(); i++) {
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

        str.append("\t\tif (res_").append(outputs.get(0)).append(" == 1\n");
        for (int i = 1; i < outputsValues.size(); i++) {
            str.append("\t\t\t&& res_").append(outputs.get(i)).append(" == 1");
            if (i < outputsValues.size() - 1) {
                str.append("\n");
            }
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

    void addInput(String name, int value) {
        inputs.add(name);
        inputsValues.add(String.valueOf(value));
    }
}
