package model;

public class AssertModule implements TextMaker {
    @Override
    public String getText() {
        return "module assertEquals(input a, b, output reg out);\n" +
                "\tinitial begin \n" +
                "\t\t#1\n" +
                "\t\tif (a == b)\n" +
                "\t\t\tassign out = 1'b1;\n" +
                "\t\telse\n" +
                "\t\t\tassign out = 1'b0;\n" +
                "\tend\n" +
                "endmodule\n";
    }
}
