import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModuleTest {
    @Test
    void createEmptyModuleTest() {
        Module module = new Module("NAMETWO");
        assertEquals(module.getTestText(1),
                "module NAMETWO_test_1(output out);\n" +
                        "endmodule\n");
    }
}
