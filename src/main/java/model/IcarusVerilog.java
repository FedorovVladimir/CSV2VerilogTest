package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IcarusVerilog {

    private String projectPath;

    private List<String> listFiles;

    public void setListFiles(List<String> listFiles) {
        this.listFiles = listFiles;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public static void main(String[] args) {
        IcarusVerilog icarusVerilog = new IcarusVerilog();
        icarusVerilog.setListFiles(new ArrayList<String>() {{
            add("code.sv");
            add("tcode.sv");
        }});
        icarusVerilog.setProjectPath("C:\\Users\\vladimir\\TDHDProjects\\TestIcarus");

        String compileStr = icarusVerilog.compile();

        if (!compileStr.equals("")) {
            System.out.println(compileStr);
        } else {
            String runStr = icarusVerilog.run();
            if (!runStr.equals("")) {
                System.out.println(runStr);
            }
        }
    }

    public String compile() {
        List<String> commandCompile = new ArrayList<>();
        commandCompile.add("iverilog");
        commandCompile.add("-o");
        commandCompile.add(projectPath + "\\run");

        for (String listFile : listFiles) {
            commandCompile.add(projectPath + "\\" + listFile);
        }

        return exec(commandCompile);
    }

    public String run() {
        List<String> commandRun = new ArrayList<>();
        commandRun.add("vvp");
        commandRun.add(projectPath + "\\run");
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
