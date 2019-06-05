import model.Module;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateEmptyModule {

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

    @Test
    void createEmptyModuleAddTwoInput() {
        Module module = new Module("NAMETWO");
        module.addInput("a");
        module.addInput("b");
        assertEquals(module.getText(),
                "module NAMETWO(input a, b);\n" +
                        "endmodule\n");
    }

    @Test
    void createEmptyModuleAddOutput() {
        Module module = new Module("NAMETWO");
        module.addOutput("a");
        assertEquals(module.getText(),
                "module NAMETWO(output a);\n" +
                        "endmodule\n");
    }

    @Test
    void createEmptyModuleAddTwoOutput() {
        Module module = new Module("NAMETWO");
        module.addOutput("a");
        module.addOutput("b");
        assertEquals(module.getText(),
                "module NAMETWO(output a, b);\n" +
                        "endmodule\n");
    }

    @Test
    void createEmptyModuleAddTwoInputAndTwoOutput() {
        Module module = new Module("NAMETWO");
        module.addInput("a");
        module.addInput("b");
        module.addOutput("c");
        module.addOutput("d");
        assertEquals(module.getText(),
                "module NAMETWO(input a, b, output c, d);\n" +
                        "endmodule\n");
    }
}
