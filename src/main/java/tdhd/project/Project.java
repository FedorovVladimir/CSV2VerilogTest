package tdhd.project;

import tdhd.file_system.WindowsFileSystem;

import java.util.Map;

public class Project {

    private FileSystem fileSystem;

    private GitVersionControlSystem gitVersionControlSystem;

    private SimulationEnvironment simulationEnvironment;

    private String nameProject;
    private String absoluteFolderPath;

    public Project(String url, String absoluteFolderPath) {
        selectFileSystem();
        gitVersionControlSystem.gitClone(url, absoluteFolderPath);
        this.nameProject = "";
        this.absoluteFolderPath = absoluteFolderPath;
        // TODO get name project
        createStructProject();
    }

    public Project(String absoluteFolderPath) {
        selectFileSystem();
        this.nameProject = "";
        this.absoluteFolderPath = absoluteFolderPath;
        // TODO get name project
        createStructProject();
    }

    private void createStructProject() {
        fileSystem.createFolder(absoluteFolderPath + "\\" + nameProject + "\\src");
        fileSystem.createFolder(absoluteFolderPath + "\\" + nameProject + "\\test");
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

    public void gitCommit(String message) {
        gitVersionControlSystem.gitCommit(message);
    }

    public void gitPush(String login, String password) {
        gitVersionControlSystem.gitPush(login, password);
    }

    public boolean createFile(String absoluteFilePath) {
        return fileSystem.createFile(absoluteFilePath);
    }

    public void save(String absoluteFilePath, String text) {
        fileSystem.writeFile(absoluteFilePath, text);
    }
}
