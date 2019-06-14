package tdhd;

import tdhd.project.Project;
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
    }
}
