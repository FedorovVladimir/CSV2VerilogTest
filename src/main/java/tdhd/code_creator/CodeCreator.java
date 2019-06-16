package tdhd.code_creator;

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
        return "\t\t$display(\"" + s + "\");\n" +
                "\t\t$display(out_" + s + ");\n";
    }

    private String a(String s) {
        return "\twire out_" + s + ";\n" +
                "\t" + s + " " + s + "(out_" + s + ");\n";
    }
}
