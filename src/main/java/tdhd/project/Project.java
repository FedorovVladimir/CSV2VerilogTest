package tdhd.project;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import tdhd.simulation_environment.IcarusVerilog;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Project {

    private FileSystem fileSystem = new FileSystem();

    private GitVersionControlSystem gitVersionControlSystem = new GitVersionControlSystem();

    private IcarusVerilog icarusVerilog = new IcarusVerilog();

    private String nameProject;

    private String absoluteFolderPath;

    public String getNameProject() {
        return nameProject;
    }

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

    public Map<String, Boolean> runAllTests() {
        String[] files = findAllFiles();
        String compileString = icarusVerilog.run(absoluteFolderPath, files);
        System.out.println(compileString);
        return null;
    }

    private String[] findAllFiles() {
        File[] srcFiles = getAllSrcFiles();
        File[] testFiles = getAllTestFiles();
        List<String> paths = new LinkedList<>();
        for (File f : srcFiles) {
            paths.add(f.getAbsolutePath());
        }
        for (File f : testFiles) {
            paths.add(f.getAbsolutePath());
        }
        return paths.toArray(new String[0]);
    }

    public void gitCommit(String message) {
        gitVersionControlSystem.gitCommit(message, absoluteFolderPath);
    }

    public void gitPush(String login, String password) {
        gitVersionControlSystem.gitPush(login, password, absoluteFolderPath);
    }

    public void save(String absoluteFilePath, String text) {
        fileSystem.writeFile(absoluteFilePath, text);
    }


    public File[] getAllSrcFiles() {
        return getAllFiles(absoluteFolderPath + "\\src");
    }

    public File[] getAllTestFiles() {
        return getAllFiles(absoluteFolderPath + "\\test");
    }

    private File[] getAllFiles(String absoluteFilePath) {
        return fileSystem.getAllFiles(absoluteFilePath);
    }


    public String readSrcFile(String nameFile) {
        return readFile(absoluteFolderPath + "\\src\\" + nameFile);
    }

    public String readTestFile(String nameFile) {
        return readFile(absoluteFolderPath + "\\test\\" + nameFile);
    }

    private String readFile(String absoluteFilePath) {
        return fileSystem.readFile(absoluteFilePath);
    }


    public void createSrcFile(String nameFile) {
        createFile(absoluteFolderPath + "\\src\\" + nameFile + ".v");
    }

    public void createTestFile(String nameFile) {
        createFile(absoluteFolderPath + "\\test\\" + nameFile + ".v");
    }

    private void createFile(String absoluteFilePath) {
        fileSystem.createFile(absoluteFilePath);
    }
}
