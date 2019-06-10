package model;

public class Main {
    public static void main(String[] args) {

        String nameModule = "fulladder";

        String[] addrInputsNames = {
                "a",
                "b",
                "cin",
        };

        String[] addrOutputsNames = {
                "s",
                "cout",
        };

        int[][] addrTests = {
                {0,0,0,0,0},
                {0,1,0,1,0},
                {1,0,0,1,0},
                {1,1,0,0,1},

                {0,0,1,1,0},
                {0,1,1,0,1},
                {1,0,1,0,1},
                {1,1,1,1,1},
        };

        Module module = new Module(nameModule);
        for (String name: addrInputsNames) {
            module.addInput(name);
        }
        for (String name: addrOutputsNames) {
            module.addOutput(name);
        }
        // получаем текст модуля
        System.out.println(module.getText());


        AllTests allTests = new AllTests();
        for (int i = 0; i < addrTests.length; i++) {
            TestModule m = new TestModule(nameModule, i+1);

            for (int j = 0; j < addrInputsNames.length; j++) {
                m.addInput(addrInputsNames[j], String.valueOf(addrTests[i][j]));
            }

            for (int j = 0; j < addrOutputsNames.length; j++) {
                m.addOutput(addrOutputsNames[j], String.valueOf(addrTests[i][j + addrInputsNames.length]));
            }

            // получаем текст каждого теста
            System.out.println(m.getText());
            allTests.add(m);
        }

        // получаем текст assertEquals
        System.out.println(new AssertModule().getText());

        // получаем текст alltests
        System.out.println(allTests.getText());
    }
}
