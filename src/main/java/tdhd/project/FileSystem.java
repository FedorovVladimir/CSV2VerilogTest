package tdhd.project;

import java.io.File;

class FileSystem {
    boolean createFile(String absolutePath) {
        return false;
    }

    String readFile(String absolutePath) {
        return null;
    }

    void writeFile(String absolutePath, String text) {

    }

    void createFolder(String absolutePath) {
        if (new File(absolutePath).mkdir()) {
            writeLog(absolutePath + " folder created");
        } else {
            writeLog(absolutePath + " folder not created");
        }
    }

    File[] getAllFiles(String absolutePath) {
        return new File(absolutePath).listFiles();
    }

    private void writeLog(String text) {
        System.out.println("FileSystem message: " + text);
    }
}
