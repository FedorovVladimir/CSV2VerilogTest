import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {

    @Test
    void createEmptyModuleTest1() {
        Module module = new Module("NAMETWO");
        module.addInput("a", 1);
        module.addInput("b", 1);
        module.addOutput("c", 0);
        module.addOutput("d", 0);
        assertEquals(module.getTestText(1),
                "module NAMETWO_test_1(output out);\n" +
                        "\treg a, b, c, d;\n" +
                        "\tNAMETWO N(a, b, c, d);\n" +
                        "\treg test_c, test_d;\n" +
                        "\tassertEquals t1(c, test_c);\n" +
                        "\tassertEquals t2(d, test_d);\n" +
                        "\tinitial begin\n" +
                        "\t\ta = 1;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
