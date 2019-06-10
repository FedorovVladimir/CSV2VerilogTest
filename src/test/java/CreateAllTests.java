import model.AllTests;
import model.TestModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateAllTests {

    @Test
    void createAllTestsModule1() {
        TestModule testModule = new TestModule("name", 1);
        testModule.addInput("a", "0");
        testModule.addOutput("b", "0");
        AllTests allTests = new AllTests();
        allTests.add(testModule);
        assertEquals(allTests.getText(),
                "module allTests();\n" +
                        "\twire res_1;\n" +
                        "\tname_test_1 n1(res_1);\n" +
                        "\tinitial begin\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_1 == 0)\n" +
                        "\t\t\t$display(\"Fail test name_test_1\");\n" +
                        "\t\telse\n" +
                        "\t\t\t$display(\"Success!\");\n" +
                        "\tend\n" +
                        "endmodule\n");
    }

    @Test
    void createAllTestsModule() {
        TestModule testModule = new TestModule("NAMETWO", 1);
        testModule.addOutput("c", "0");
        testModule.addOutput("d", "0");
        AllTests allTests = new AllTests();
        allTests.add(testModule);
        assertEquals(allTests.getText(),
                "module allTests();\n" +
                "\twire res_1;\n" +
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
}
