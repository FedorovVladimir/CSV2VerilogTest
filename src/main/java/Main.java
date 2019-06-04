public class Main {
    public static void main(String[] args) {
        System.out.println(Module.getTestAssertEquals());
        Module module = new Module("name");
        module.addInput("a", 1);
        module.addInput("b", 0);
        module.addOutput("c", 1);
        module.addOutput("d", 0);
        System.out.println(module.getModuleText());
        System.out.println(module.getTestText(1));
    }
}
