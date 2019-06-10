import model.TestModule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {

    @Test
    void createEmptyModuleTest3() {
        TestModule testModule = new TestModule("name", 1);
        testModule.addInput("a", 0);
        testModule.addOutput("b", 0);
        assertEquals(testModule.getText(),
                "module name_test_1(output reg out);\n" +
                        "\treg a;\n" +
                        "\twire b;\n" +
                        "\tname n(a, b);\n" +
                        "\treg test_b;\n" +
                        "\twire res_b;\n" +
                        "\tassertEquals t1(b, test_b, res_b);\n" +
                        "\tinitial begin\n" +
                        "\t\ta = 0;\n" +
                        "\t\ttest_b = 0;\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_b == 1)\n" +
                        "\t\t\tassign out = 1'b1;\n" +
                        "\t\telse\n" +
                        "\t\t\tassign out = 1'b0;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }

    @Disabled
    @Test
    void createEmptyModuleTest1() {
        TestModule testModule = new TestModule("NAMETWO", 1);
        testModule.addInput("a", 1);
        testModule.addInput("b", 1);
        testModule.addOutput("c", 0);
        testModule.addOutput("d", 0);
        assertEquals(testModule.getText(),
                "module NAMETWO_test_1(output reg out);\n" +
                        "\treg a, b, c, d;\n" +
                        "\tNAMETWO N(a, b, c, d);\n" +
                        "\treg test_c, res_c;\n" +
                        "\tassertEquals t1(c, test_c, res_c);\n" +
                        "\treg test_d, res_d;\n" +
                        "\tassertEquals t2(d, test_d, res_d);\n" +
                        "\tinitial begin\n" +
                        "\t\ta = 1;\n" +
                        "\t\tb = 1;\n" +
                        "\t\ttest_c = 0;\n" +
                        "\t\ttest_d = 0;\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_c == 1\n" +
                        "\t\t\t&& res_d == 1)\n" +
                        "\t\t\tassign out = 1'b1;\n" +
                        "\t\telse\n" +
                        "\t\t\tassign out = 1'b0;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }

    @Disabled
    @Test
    void createEmptyModuleTest2() {
        TestModule testModule = new TestModule("NAMETWO", 1);
        testModule.addInput("a", 1);
        testModule.addOutput("c", 0);
        assertEquals(testModule.getText(),
                "module NAMETWO_test_1(output reg out);\n" +
                        "\treg a, c;\n" +
                        "\tNAMETWO N(a, c);\n" +
                        "\treg test_c, res_c;\n" +
                        "\tassertEquals t1(c, test_c, res_c);\n" +
                        "\tinitial begin\n" +
                        "\t\ta = 1;\n" +
                        "\t\ttest_c = 0;\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_c == 1)\n" +
                        "\t\t\tassign out = 1'b1;\n" +
                        "\t\telse\n" +
                        "\t\t\tassign out = 1'b0;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
