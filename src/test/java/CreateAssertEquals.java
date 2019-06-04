import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAssertEquals {

    @Test
    void createAssertEquals() {
        assertEquals(Module.getTestAssertEquals(),
                "module assertEquals(input a, b, output reg out);\n" +
                        "\tinitial begin \n" +
                        "\t\t#1\n" +
                        "\t\tif (a == b)\n" +
                        "\t\t\tassign out = 1'b1;\n" +
                        "\t\telse\n" +
                        "\t\t\tassign out = 1'b0;\n" +
                        "\tend\n" +
                        "endmodule\n");
    }
}
