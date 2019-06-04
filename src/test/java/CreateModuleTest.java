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
                        "\tNAMETWO N(a, b, c, d);\n" +
                        "\treg test_c, test_d;\n" +
                        "\tassertEquals t1(c, test_c);\n" +
                        "\tassertEquals t2(d, test_d);\n" +
                        "\tinitial begin\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
