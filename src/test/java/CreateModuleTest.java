import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {

    @Test
    void createEmptyModuleTest1() {
        Module module = new Module("NAMETWO");
        module.addInput("a");
        module.addInput("b");
        module.addOutput("c");
        module.addOutput("d");
        assertEquals(module.getTestText(1),
                "module NAMETWO_test_1(output out);\n" +
                        "\treg a, b, c, d;\n" +
                        "NAMETWO N(a, b, c, d);" +
                        "\tinitial begin\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
