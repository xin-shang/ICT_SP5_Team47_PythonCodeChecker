package methodAndTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import JDBC.QNS.GroupTable.studentQns_T;
import view.InputTerminalPage;

public class RunPythonCode {
    private String codeFileName;
    private String pythonIntpreterFileName;
    public static String outputFromConsole;
    public static String errorMessage;
    WriteAndRead WAR = new WriteAndRead();
    String codes;

    public RunPythonCode() {
        ProjectVariable PV = new ProjectVariable();
        pythonIntpreterFileName = PV.getPythonName();
        codeFileName = WAR.getLocalFolderPath() + "StudentAnswer.py";
        outputFromConsole = "";
        errorMessage = "";
    }

    public RunPythonCode(String codeFileName) {
        ProjectVariable PV = new ProjectVariable();
        pythonIntpreterFileName = PV.getPythonName();
        this.codeFileName = codeFileName;

    }

    public String getOutputFromConsole() {
        return outputFromConsole;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void saveCodeFile(String codes) {
        this.codes = codes;
        WriteAndRead WA = new WriteAndRead();
        WA.write2TextFileOutStream(codeFileName, codes);
    }

    // 1: submit, 2: run, 3: add, 4: change
    public boolean runCode(int state) {
        boolean b_run;
        if (codes.contains("input()")) {
            b_run = runCode_Input(state);

        } else {
            b_run = runCode_NonInput();
        }
        System.out.println(b_run);
        return b_run;
    }

    // only for submit button, it might change it in the future
    public void runCode(studentQns_T DIO) {
        InputTerminalPage IT = new InputTerminalPage(25, 50, pythonIntpreterFileName, codeFileName, 1, DIO, codes);
        Thread t = new Thread() {
            public void run() {
                IT.Init();
            }
        };
        t.start();
    }

    public boolean runCode_NonInput() {
        outputFromConsole = "";
        errorMessage = "";
        ProcessBuilder processBuilder = new ProcessBuilder(pythonIntpreterFileName, codeFileName);
        boolean exitCode = true;
        String s;

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
                    if (codes.contains("input()")) {
                        JFrame jf = new JFrame();
                        JOptionPane.showMessageDialog(jf, "Sorry, We Have Not Open input() For Current Version");
                        return false;

                    } else {
                        while ((s = stdInput.readLine()) != null) {
                            outputFromConsole += s + "\n";
                        }
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

    public boolean runCode_Input(int state) {

        new InputTerminalPage(25, 50, pythonIntpreterFileName, codeFileName, state).Init();

        return InputTerminalPage.checkCodeError;

    }

}
