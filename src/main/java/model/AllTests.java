package model;

import java.util.LinkedList;
import java.util.List;

public class AllTests {

    private List<TestModule> testModules = new LinkedList<>();

    public void add(TestModule module) {
        testModules.add(module);
    }

    public String getText() {
        if (testModules.size() < 1) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        str.append("module allTests();\n");
        for (int i = 0; i < testModules.size(); i++) {
            str.append("\twire res_").append(i+1).append(";\n");
            str.append("\t").append(testModules.get(i).getName()).append("_test_").append(testModules.get(i).getNumber());
            str.append(" ").append(testModules.get(i).getName().charAt(0)).append(testModules.get(i).getNumber());
            str.append("(res_").append(i+1).append(");\n");
        }
        str.append("\tinitial begin\n");
        str.append("\t\t#1\n");

        str.append("\t\tif (res_1 == 0)\n");
        str.append("\t\t\t$display(\"Fail test ").append(testModules.get(0).getName()).append("_test_").append(testModules.get(0).getNumber()).append("\");\n");

        for (int i = 1; i < testModules.size(); i++) {
            str.append("\t\telse if (res_").append(i+1).append(" == 0)\n");
            str.append("\t\t\t$display(\"Fail test ").append(testModules.get(i).getName()).append("_test_").append(testModules.get(i).getNumber()).append("\");\n");
        }

        str.append("\t\telse\n");
        str.append("\t\t\t$display(\"Success!\");\n");

        str.append("\tend\n");
        str.append("endmodule\n");
        return String.valueOf(str);
    }
}
