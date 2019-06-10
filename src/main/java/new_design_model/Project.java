package new_design_model;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Project {

    private Path path;
    private boolean isOpen;

    public Project(String name) {
        this.path = new Path(name);
        File dir = new File(path.getPath());
        File dirSrc = new File(path.getPath() + "\\src");
        File dirTest = new File(path.getPath() + "\\test");
        if (dir.mkdir() && dirSrc.mkdir() && dirTest.mkdir()) {
            System.out.println("Создан проект " + dir.getName());
        }
        isOpen = true;
    }

    public Project(Path path) {
        this.path = new Path(path.getName());
        isOpen = true;
    }

    public Project(Url url) {
        String[] subStr = url.getUrl().split("/");
        this.path = new Path(subStr[subStr.length - 1]);
        try {
            Git.cloneRepository()
                    .setURI(url.getUrl())
                    .setDirectory(new File(path.getPath()))
                    .call();
            this.isOpen = true;
        } catch (GitAPIException e) {
            this.isOpen = false;
        }
    }

    public String getName() {
        return path.getName();
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
}
