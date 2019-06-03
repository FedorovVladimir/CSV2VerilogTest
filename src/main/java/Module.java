import java.util.LinkedList;
import java.util.List;

class Module {

    private String name = "";
    private List<String> inputs = new LinkedList<String>();
    private List<String> outputs = new LinkedList<String>();

    Module(String name) {
        this.name = name;
    }

    String getText() {
        StringBuilder str = new StringBuilder();
        str.append("module ").append(name).append("(");
        if (inputs.size() > 0) {
            str.append("input");
            for (int i = 0; i < inputs.size(); i++) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(" ").append(inputs.get(i));
            }
        }
        if (outputs.size() > 0) {
            str.append("output");
            for (int i = 0; i < outputs.size(); i++) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(" ").append(outputs.get(i));
            }
        }
        str.append(");\n");
        str.append("endmodule\n");
        return str.toString();
    }

    void addInput(String name) {
        inputs.add(name);
    }

    void addOutput(String name) {
        outputs.add(name);
    }
}
