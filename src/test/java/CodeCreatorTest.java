import code_creator.CodeCreator;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeCreatorTest {
    @Test
    void createAllTests() {
        List<String> testsNames = new LinkedList<>();
        testsNames.add("test1");
        testsNames.add("test2");
        CodeCreator codeCreator = new CodeCreator();
        String text = codeCreator.createAllTests(testsNames);
        assertEquals("module allTests();\n" +
                "\twire out_test1;\n" +
                "\ttest1 test1(out_test1);\n" +
                "\twire out_test2;\n" +
                "\ttest2 test2(out_test2);\n" +
                "\tinitial begin\n" +
                "\t\t#1\n" +
                "\t\t$display(\"test1\");\n" +
                "\t\t$display(out_test1);\n" +
                "\t\t$display(\"test2\");\n" +
                "\t\t$display(out_test2);\n" +
                "\tend\n" +
                "endmodule\n", text);
    }
}
