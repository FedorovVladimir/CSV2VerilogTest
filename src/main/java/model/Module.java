package model;

import java.util.LinkedList;
import java.util.List;

public class Module {

    private String name;
    private List<String> inputs = new LinkedList<>();
    private List<String> outputs = new LinkedList<>();

    public Module(String name) {
        this.name = name;
    }

    public static String getTestAssertEquals() {
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

    String getName() {
        return name;
    }

    public String getModuleText() {
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
}
