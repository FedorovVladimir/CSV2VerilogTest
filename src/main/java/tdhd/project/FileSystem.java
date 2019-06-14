package tdhd.project;

public interface FileSystem {
    Boolean createFile(String absolutePath);

    String readFile(String absolutePath);

    Void writeFile(String absolutePath, String text);

    Void createFolder(String absolutePath);

    String[] getAllFilesPaths(String absolutePathFolder);

    String[] getAllFilesNames(String absoluteFolderPath);
}
