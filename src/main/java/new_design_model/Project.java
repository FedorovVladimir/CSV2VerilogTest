package new_design_model;

import model.TestModule;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.*;

public class Project {

    private PathFile path;
    private boolean isOpen;

    private MyGit git = new MyGit();
    private IcarusVerilog icarusVerilog;

    public Project(String name) {
        this.path = new PathFile(PathProject.getProjectsPath() + "\\" + name);
        File dir = new File(path.getPath());
        File dirSrc = new File(path.getPath() + "\\src");
        File dirTest = new File(path.getPath() + "\\test");
        if (dir.mkdir() && dirSrc.mkdir() && dirTest.mkdir()) {
            System.out.println("Создан проект " + dir.getName());
        }
        isOpen = true;
        icarusVerilog = new IcarusVerilog(getName());
        icarusVerilog.setProjectPath(PathProject.getProjectsPath());
    }

    public Project(PathFile path) {
        this.path = new PathFile(path.getPath());
        isOpen = true;
        icarusVerilog = new IcarusVerilog(getName());
        icarusVerilog.setProjectPath(PathProject.getProjectsPath());
    }

    public Project(Url url) {
        String[] subStr = url.getUrl().split("/");
        this.path = new PathFile(PathProject.getProjectsPath() + "\\" + subStr[subStr.length - 1]);
        try {
            Git.cloneRepository()
                    .setURI(url.getUrl())
                    .setDirectory(new File(path.getPath()))
                    .call();
            this.isOpen = true;
        } catch (GitAPIException e) {
            this.isOpen = false;
        }
        icarusVerilog = new IcarusVerilog(getName());
        icarusVerilog.setProjectPath(PathProject.getProjectsPath());
    }

    public String getPath() {
        return path.getPath();
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public File[] getListFiles() {
        return new File(getPath() + "\\src").listFiles(file -> getFileExtension(file).equals("v") || getFileExtension(file).equals("sv"));
    }

    public File[] getListTests() {
        return new File(getPath() + "\\test").listFiles(file -> getFileExtension(file).equals("v"));
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    public void newModule(String name) {
        try {
            File file = new File(getPath() + "\\src\\" + name + ".v");
            FileWriter fileWriter = new FileWriter(file);
            Module module = new Module(name);
            fileWriter.write(module.getText());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeModule(String name) {
        System.out.println("remove " + name);
        File file = new File(getPath() + "\\src\\" + name);
        if (file.delete()) {
            System.out.println("File " + name + " remove");
        } else {
            System.out.println("Error remove");
        }
    }

    public String getModuleText(String name) {
        return getText(getPath() + "\\src\\" + name);
    }

    public String getTestText(String name) {
        return getText(getPath() + "\\test\\" + name);
    }

    private String getText(String path) {
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            char [] a = new char[1000];
            fileReader.read(a);
            fileReader.close();
            return String.valueOf(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error read file " + path;
    }

    public void saveModule(String text, String path) {
        try {
            File file = new File(getPath() + "\\src\\" + path);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String run() {
        File[] f1 = getListFiles();
        File[] f2 = getListTests();
        icarusVerilog.setListFiles(f1, f2);

        String compileText = icarusVerilog.compile();
        if (!compileText.equals("")) {
            return compileText;
        }
        return icarusVerilog.run();
    }

    public String getName() {
        String[] strings = path.getPath().replace("\\", "&").split("&");
        return strings[strings.length - 1];
    }

    public void commit(String message) {
        git.commit(message);
    }

    public void push() {
        git.push();
    }

    public void newTest(String name) {
        try {
            File file = new File(getPath() + "\\test\\" + name + ".v");
            FileWriter fileWriter = new FileWriter(file);
            TestModule test = new TestModule(name);
            fileWriter.write(test.getText());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTest(String text, String path) {
        try {
            File file = new File(getPath() + "\\test\\" + path);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeTest(String name) {
        System.out.println("remove " + name);
        File file = new File(getPath() + "\\test\\" + name);
        if (file.delete()) {
            System.out.println("File " + name + " remove");
        } else {
            System.out.println("Error remove");
        }
    }
}
