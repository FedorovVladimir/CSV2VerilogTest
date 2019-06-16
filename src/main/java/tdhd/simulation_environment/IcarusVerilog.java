package tdhd.simulation_environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IcarusVerilog {
    public String compile(String absoluteFolderPath, String[] filesPaths) {
        List<String> commandCompile = new ArrayList<>();
        commandCompile.add("iverilog");
        commandCompile.add("-o");
        commandCompile.add(absoluteFolderPath + "\\run");
        commandCompile.addAll(Arrays.asList(filesPaths));
        return exec(commandCompile);
    }

    public String run(String absoluteFolderPath) {
        List<String> commandRun = new ArrayList<>();
        commandRun.add("vvp");
        commandRun.add(absoluteFolderPath + "\\run");
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
