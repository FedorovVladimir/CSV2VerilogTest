package tdhd.project;

import java.io.*;

class FileSystem {
    void createFile(String absolutePath) {
        createFile(absolutePath, "");
    }

    void createFile(String absolutePath, String text) {
        try {
            FileWriter fileWriter = new FileWriter(absolutePath);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
            writeLog(absolutePath + " file created");
        } catch (IOException e) {
            writeLog(absolutePath + " file created fail");
        }
    }

    String readFile(String absolutePath) {
        try {
            StringBuilder stringBuffer = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text).append("\n");
            }
            writeLog(absolutePath + " file read");
            return String.valueOf(stringBuffer);
        } catch (IOException e) {
            writeLog(absolutePath + " file read fail");
        }
        return "";
    }

    void writeFile(String text, String absolutePath) {
        try {
            FileWriter fileWriter = new FileWriter(absolutePath);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
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
