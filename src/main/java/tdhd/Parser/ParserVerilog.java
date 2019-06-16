package tdhd.Parser;

import gramm.Verilog2001Lexer;
import gramm.Verilog2001Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParserVerilog {

    public static void main(String[] args) {
        List<String> testsNames = getAllModulesNames("C:\\Users\\vladimir\\TDHDProjects\\first\\test\\test.v");
        for (String s: testsNames) {
            System.out.println(s);
        }
    }

    public static List<String> getAllModulesNames(String absoluteFilePath) {
        String code = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(absoluteFilePath));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }
            code = String.valueOf(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Verilog2001Lexer lexer = new Verilog2001Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser parser = new Verilog2001Parser(tokens);
        ParseTree tree = parser.source_text();
        MyVisitor myVisitor = new MyVisitor();
        myVisitor.visit(tree);
        return myVisitor.getAllModulesNames();
    }
}
