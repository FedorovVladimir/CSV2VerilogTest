package tdhd.project;

public interface FileSystem {
    boolean createFile(String absolutePath);

    String readFile(String absolutePath);

    void writeFile(String absolutePath, String text);

    void createFolder(String absolutePath);

    String[] getAllFilesPaths(String absolutePathFolder);

    String[] getAllFilesNames(String absoluteFolderPath);
}
