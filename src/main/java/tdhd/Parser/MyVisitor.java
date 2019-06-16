package tdhd.Parser;

import gramm.Verilog2001BaseVisitor;
import gramm.Verilog2001Parser;

import java.util.LinkedList;
import java.util.List;

class MyVisitor extends Verilog2001BaseVisitor {

    private List<String> testsNames = new LinkedList<String>();

    @Override
    public Object visitModule_declaration(Verilog2001Parser.Module_declarationContext ctx) {
        testsNames.add(ctx.getChild(1).getText());
        return super.visitModule_declaration(ctx);
    }

    List<String> getAllModulesNames() {
        return testsNames;
    }
}
