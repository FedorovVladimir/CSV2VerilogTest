package new_design_model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IcarusVerilog {

    private String projectPath;

    private List<String> listFiles = new LinkedList<>();

    private String projectName;

    public IcarusVerilog(String projectName) {
        this.projectName = projectName;
    }

    public void setListFiles(File[] listFiles, File[] listFiles2) {
        this.listFiles.clear();
        for (File file : listFiles) {
            this.listFiles.add("src\\" + file.getName());
        }
        for (File file : listFiles2) {
            this.listFiles.add("test\\" + file.getName());
        }
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String compile() {
        List<String> commandCompile = new ArrayList<>();
        commandCompile.add("iverilog");
        commandCompile.add("-o");
        commandCompile.add(projectPath + "\\" + projectName + "\\run");

        for (String listFile : listFiles) {
            commandCompile.add(projectPath + "\\" + projectName + "\\" + listFile);
        }

        return exec(commandCompile);
    }

    public String run() {
        List<String> commandRun = new ArrayList<>();
        commandRun.add("vvp");
        commandRun.add(projectPath + "\\" + projectName + "\\run");
        return exec(commandRun);
    }

    private String exec(List<String> command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);

        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader r = null;
        if (p != null) {
            r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        }

        String line;
        StringBuilder text = new StringBuilder();
        if (r != null) {
            try {
                line = r.readLine();
                while (line != null && !line.equals("")) {
                    text.append(line).append("\n");
                    line = r.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    }
}
