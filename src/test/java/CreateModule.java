import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateModule {

    @Test
    void createEmptyModuleName() {
        Module module = new Module("NAME");
        assertEquals(module.getText(),
                "module NAME();\n" +
                "endmodule\n");
    }

    @Test
    void createEmptyModuleName2() {
        Module module = new Module("NAMETWO");
        assertEquals(module.getText(),
                "module NAMETWO();\n" +
                        "endmodule\n");
    }

    @Test
    void createEmptyModuleAddInput() {
        Module module = new Module("NAMETWO");
        module.addInput("a");
        assertEquals(module.getText(),
                "module NAMETWO(input a);\n" +
                        "endmodule\n");
    }
}
