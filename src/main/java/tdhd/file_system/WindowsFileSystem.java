package tdhd.file_system;

import tdhd.project.FileSystem;

public class WindowsFileSystem implements FileSystem {
    @Override
    public Boolean createFile(String absolutePath) {
        return null;
    }

    @Override
    public String readFile(String absolutePath) {
        return null;
    }

    @Override
    public Void writeFile(String absolutePath, String text) {
        return null;
    }

    @Override
    public Void createFolder(String absolutePath) {
        return null;
    }

    @Override
    public String[] getAllFilesPaths(String absolutePathFolder) {
        return new String[0];
    }

    @Override
    public String[] getAllFilesNames(String absoluteFolderPath) {
        return new String[0];
    }
}
