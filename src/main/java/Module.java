public class Module {

    private String name = "";

    public Module(String name) {
        this.name = name;
    }

    public String getText() {
        String str = "";
        str += "module " + name + "();\n";
        str += "endmodule\n";
        return str;
    }

    public void addInput(String name) {

    }
}
