import model.TestModule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {

    @Disabled
    @Test
    void createEmptyModuleTest3() {
        TestModule testModule = new TestModule("name");
        testModule.addInput("a", "0");
        testModule.addOutput("b", "0");
        assertEquals(testModule.getText(),
                "module name_test_1(output reg out);\n" +
                        "\twire b, res_b;\n" +
                        "\tname n(1'b0, b);\n" +
                        "\tassertEquals t1(b, 1'b0, res_b);\n" +
                        "\tinitial begin\n" +
                        "\t\t#1\n" +
                        "\t\tif (res_b == 1)\n" +
                        "\t\t\tassign out = 1'b1;\n" +
                        "\t\telse\n" +
                        "\t\t\tassign out = 1'b0;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
