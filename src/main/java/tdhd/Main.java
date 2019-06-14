package tdhd;

import tdhd.project.Project;
import tdhd.tools.CreateDynamicTestTool;
import tdhd.tools.CreateModuleTool;
import tdhd.tools.CreateStaticTestTool;
import tdhd.view.MainWindowController;

public class Main {
    public static void main(String[] args) {
        Project project = null;

        // TODO read last project

        MainWindowController mainWindowController;

        if (project != null) {
            mainWindowController = new MainWindowController(project);
        } else {
            mainWindowController = new MainWindowController();
        }

        mainWindowController.addTool(new CreateModuleTool());
        mainWindowController.addTool(new CreateStaticTestTool());
        mainWindowController.addTool(new CreateDynamicTestTool());
    }
}
