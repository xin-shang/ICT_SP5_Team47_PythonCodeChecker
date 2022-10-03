package methodAndTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class RunPythonCode {
    private String codeFileName;
    private String pythonIntpreterFileName;
    private String outputFromConsole;
    private String errorMessage;

    public RunPythonCode() {
        ProjectVariable PV = new ProjectVariable();
        pythonIntpreterFileName = PV.getPythonName();
        codeFileName = "./src/txt/StudentAnswer.py";
        outputFromConsole = "";
        errorMessage = "";
    }

    public RunPythonCode(String codeFileName) {
        ProjectVariable PV = new ProjectVariable();
        pythonIntpreterFileName = PV.getPythonName();
        this.codeFileName = codeFileName;
        outputFromConsole = "";
        errorMessage = "";
    }

    public String getOutputFromConsole() {
        return outputFromConsole;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void saveCodeFile(String codes) {
        WriteAndRead WA = new WriteAndRead();
        WA.write2TextFileOutStream(codeFileName, codes);
    }

    public boolean runCode() {

        ProcessBuilder processBuilder = new ProcessBuilder(pythonIntpreterFileName, codeFileName);
        boolean exitCode = true;
        String s;
        errorMessage = "";
        outputFromConsole = "";

        try {
            Process p = processBuilder.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            System.out.println("run code started");
            exitCode = p.waitFor(5, TimeUnit.SECONDS);
            System.out.println("run code ended");
            if (exitCode == false) {
                errorMessage += "Your program does not complete within a reasonable time (5 seconds). \n";
                errorMessage += "The following are the first 10 lines from the output console (if any): \n";
                int line = 0;
                while ((s = stdInput.readLine()) != null && line < 10) {
                    errorMessage += s + "\n";
                    line++;
                }
                p.destroy();
            } else {
                while ((s = stdError.readLine()) != null) {
                    if (s.contains("File")) {
                        int lineNo = s.indexOf("line ");
                        if (lineNo >= 0) {
                            errorMessage += "In line " + s.substring(lineNo + 5) + ": \n";
                        }

                    } else {
                        errorMessage += s + "\n";
                    }
                }
                if (errorMessage.equals("")) {
                    while ((s = stdInput.readLine()) != null) {
                        outputFromConsole = s + "\n";
                    }
                }

                System.out.println("\nExited with error code: " + exitCode);
            }
            return exitCode;

        } catch (IOException e) {
            errorMessage += e.getMessage();
            System.out.println("\nIO Exited with error code:" + exitCode);
            return false;

        } catch (InterruptedException e) {
            errorMessage += e.getMessage();
            System.out.println("\nInter Exited with error code: " + exitCode);
            return false;
        }
    }
}
