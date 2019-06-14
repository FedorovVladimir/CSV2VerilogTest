package tdhd.project;

import tdhd.file_system.WindowsFileSystem;

import java.util.Map;

public class Project {

    private FileSystem fileSystem;

    private GitVersionControlSystem gitVersionControlSystem;

    private SimulationEnvironment simulationEnvironment;

    public Project(String url, String absoluteFolderPath) {
        selectFileSystem();
    }

    public Project(String absoluteFolderPath) {
        selectFileSystem();
    }

    private void selectFileSystem() {
        fileSystem = new WindowsFileSystem();
    }

    public void setSimulationEnvironment(SimulationEnvironment simulationEnvironment) {
        this.simulationEnvironment = simulationEnvironment;
    }

    public void setGitVersionControlSystem(GitVersionControlSystem gitVersionControlSystem) {
        this.gitVersionControlSystem = gitVersionControlSystem;
    }

    public Map<String, Boolean> runAllTests() {
        return null;
    }
}
