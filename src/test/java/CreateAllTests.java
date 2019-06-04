import model.AllTests;
import model.Module;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateAllTests {

    @Test
    void createAllTestsModule() {
        Module module = new Module("NAMETWO", 1);
        module.addOutput("c", 0);
        module.addOutput("d", 0);
        AllTests allTests = new AllTests();
        allTests.add(module);
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
        Module module = new Module("NAMETWO", 1);
        module.addOutput("c", 0);
        module.addOutput("d", 0);
        Module module2 = new Module("NAMETWO", 2);
        module2.addOutput("c", 0);
        module2.addOutput("d", 0);
        AllTests allTests = new AllTests();
        allTests.add(module);
        allTests.add(module2);
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
