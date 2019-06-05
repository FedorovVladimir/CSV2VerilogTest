import model.AllTests;
import model.Module;
import model.TestModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateAllTests {

    @Test
    void createAllTestsModule() {
        TestModule testModule = new TestModule("NAMETWO", 1);
        testModule.addOutput("c", 0);
        testModule.addOutput("d", 0);
        AllTests allTests = new AllTests();
        allTests.add(testModule);
        assertEquals(allTests.getText(),
                "module allTests();\n" +
                "\treg res_1;\n" +
                "\tNAMETWO_test_1 N1(res_1);\n" +
                "\tinitial begin\n" +
                "\t\t#1\n" +
                "\t\tif (res_1 == 0)\n" +
                "\t\t\t$display(\"Fail test NAMETWO_test_1\");\n" +
                "\t\telse\n" +
                "\t\t\t$display(\"Success!\");\n" +
                "\tend\n" +
                "endmodule\n");
    }

    @Test
    void createAllTestsModule2() {
        TestModule testModule1 = new TestModule("NAMETWO", 1);
        testModule1.addOutput("c", 0);
        testModule1.addOutput("d", 0);
        TestModule testModule2 = new TestModule("NAMETWO", 2);
        testModule2.addOutput("c", 0);
        testModule2.addOutput("d", 0);
        AllTests allTests = new AllTests();
        allTests.add(testModule1);
        allTests.add(testModule2);
        assertEquals(allTests.getText(),
                "module allTests();\n" +
                        "\treg res_1;\n" +
                        "\tNAMETWO_test_1 N1(res_1);\n" +
                        "\treg res_2;\n" +
                        "\tNAMETWO_test_2 N2(res_2);\n" +
                        "\tinitial begin\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_1 == 0)\n" +
                        "\t\t\t$display(\"Fail test NAMETWO_test_1\");\n" +
                        "\t\telse if (res_2 == 0)\n" +
                        "\t\t\t$display(\"Fail test NAMETWO_test_2\");\n" +
                        "\t\telse\n" +
                        "\t\t\t$display(\"Success!\");\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
