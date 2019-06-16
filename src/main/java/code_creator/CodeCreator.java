package code_creator;

import java.util.List;

public class CodeCreator {
    public String createAllTests(List<String> testsNames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("module allTests();\n");
        for (String s: testsNames) {
            stringBuilder.append(a(s));
        }
        stringBuilder.append("\tinitial begin\n");
        stringBuilder.append("\t\t#1\n");
        for (String s: testsNames) {
            stringBuilder.append(b(s));
        }
        stringBuilder.append("\tend\n");
        stringBuilder.append("endmodule\n");
        return String.valueOf(stringBuilder);
    }

    private String b(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\t$display(\"").append(s).append("\");\n");
        stringBuilder.append("\t\t$display(out_").append(s).append(");\n");
        return String.valueOf(stringBuilder);
    }

    private String a(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\twire out_").append(s).append(";\n");
        stringBuilder.append("\t").append(s).append(" ").append(s).append("(out_").append(s).append(");\n");
        return String.valueOf(stringBuilder);
    }
}
