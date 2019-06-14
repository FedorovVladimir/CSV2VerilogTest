package tdhd.file_system;

import tdhd.project.FileSystem;

public class WindowsFileSystem implements FileSystem {
    @Override
    public boolean createFile(String absolutePath) {
        return false;
    }

    @Override
    public String readFile(String absolutePath) {
        return null;
    }

    @Override
    public void writeFile(String absolutePath, String text) {

    }

    @Override
    public void createFolder(String absolutePath) {

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
