package tdhd.project;

import tdhd.simulation_environment.IcarusVerilog;

import java.io.File;
import java.util.Map;

public class Project {

    private FileSystem fileSystem = new FileSystem();

    private GitVersionControlSystem gitVersionControlSystem = new GitVersionControlSystem();

    private SimulationEnvironment simulationEnvironment = new IcarusVerilog();

    private String nameProject;
    private String absoluteFolderPath;

    public Project(String url, String absoluteFolderPath) {
        String[] s = url.split("/");
        this.nameProject = s[s.length - 1];
        this.absoluteFolderPath = absoluteFolderPath + "\\" + this.nameProject;
        gitVersionControlSystem.gitClone(url, this.absoluteFolderPath);
        createStructProject();
    }

    public Project(File dirProject) {
        this.nameProject = dirProject.getName();
        this.absoluteFolderPath = dirProject.getAbsolutePath();
        createStructProject();
    }

    private void createStructProject() {
        fileSystem.createFolder(absoluteFolderPath);
        fileSystem.createFolder(absoluteFolderPath + "\\src");
        fileSystem.createFolder(absoluteFolderPath + "\\test");
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

    public File[] getAllSrcFiles() {
        return fileSystem.getAllFiles(absoluteFolderPath + "\\src");
    }

    public File[] getAllTestFiles() {
        return fileSystem.getAllFiles(absoluteFolderPath + "\\test");
    }

    public String getNameProject() {
        return nameProject;
    }
}
