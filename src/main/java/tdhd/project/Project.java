package tdhd.project;

import tdhd.simulation_environment.IcarusVerilog;

import java.io.File;
import java.util.*;

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

    public String compile() {
        String[] files = findAllFiles();
        return icarusVerilog.compile(absoluteFolderPath, files);
    }

    public String compile(File testFile) {
        List<String> paths = new LinkedList<>();
        File[] srcFiles = getAllSrcFiles();
        for (File f : srcFiles) {
            paths.add(f.getAbsolutePath());
        }
        paths.add(testFile.getAbsolutePath());
        paths.add(absoluteFolderPath + "\\test\\allTests.v");
        return icarusVerilog.compile(absoluteFolderPath, paths.toArray(new String[0]));
    }

    public List<Map<String, String>> run() {
        String runStr = icarusVerilog.run(absoluteFolderPath);
        Scanner scanner = new Scanner(runStr);

        List<Map<String, String>> tests = new LinkedList<>();

        while (scanner.hasNext()) {
            Map<String, String> a = new HashMap<>();
            a.put("name", scanner.nextLine());
            a.put("result", scanner.nextLine());
            tests.add(a);
        }

        return tests;
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

    public void saveSrcFile(String text, String nameFile) {
        save(text, absoluteFolderPath + "\\src\\" + nameFile);
    }

    public void saveTestFile(String text, String nameFile) {
        save(text, absoluteFolderPath + "\\test\\" + nameFile);
    }

    private void save(String text, String absoluteFilePath) {
        fileSystem.writeFile(text, absoluteFilePath);
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
