import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {

    @Test
    void createEmptyModuleTest1() {
        Module module = new Module("NAMETWO");
        module.addInput("a");
        assertEquals(module.getTestText(1),
                "module NAMETWO_test_1(output out);\n" +
                        "reg a;" +
                        "endmodule\n");
    }
}
