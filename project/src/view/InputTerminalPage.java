package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import JDBC.QNS.GroupTable.studentQns_T;
import Type.markScheme;
import component.AddQuestionComponent;
import component.ChangeQuestionComponent;
import component.ChooseQuestionComponent;
import component.StudentWorkingComponent;
import javaswingdev.chart.PieChart;
import methodAndTool.ProjectVariable;
import methodAndTool.RunPythonCode;
import methodAndTool.RunnableClass_ErrorStream;
import methodAndTool.RunnableClass_InputStream;
import methodAndTool.ScreenUtils;
import methodAndTool.keywordAnalysis;

public class InputTerminalPage extends JFrame {
    private static final int GAP = 3;
    private JTextArea textarea;
    private JTextField textfield;

    ArrayList<String> output = new ArrayList<String>();
    ArrayList<String> inputstream = new ArrayList<String>();
    static ArrayList<String> errorstream = new ArrayList<>();

    int rows;
    int cols;
    public static int b_process_if_end;
    String pythonPath;
    String pythonIntpreterFileName;
    int progrocessEndCount = 0;
    JPanel jp = new JPanel();

    public static boolean checkCodeError;

    // 1: run code 2:submit code 3: add question 4: edit question
    int state;

    // score page value
    ProjectVariable PV;
    ScorePage SP;
    keywordAnalysis KA;
    studentQns_T DIO;
    String solution;

    int total_score;
    ArrayList<String> passedKeywordList;
    PieChart passKeyword;
    PieChart keyword;
    String suggestSolution;

    public InputTerminalPage(int rows, int cols, String pythonIntpreterFileName, String pythonPath, int state) {
        this.rows = rows;
        this.cols = cols;
        this.state = state;
        this.pythonPath = pythonPath;
        this.pythonIntpreterFileName = pythonIntpreterFileName;
        b_process_if_end = 1;
    }

    public InputTerminalPage(int rows, int cols, String pythonIntpreterFileName, String pythonPath, int state,
            studentQns_T DIO, String solution) {
        PV = new ProjectVariable();
        this.rows = rows;
        this.cols = cols;
        this.state = state;
        this.DIO = DIO;
        this.solution = solution;
        this.pythonPath = pythonPath;
        this.pythonIntpreterFileName = pythonIntpreterFileName;
        b_process_if_end = 1;

    }

    private void setValue_submit() {
        int selectedRow = ChooseQuestionComponent.getSelectedRow();
        String id = (String) DIO.getData_id(selectedRow);
        // System.out.println(id);
        List<markScheme> mkl = new ArrayList<markScheme>();
        mkl = DIO.getSelectedMarkScheme(id);

        KA = new keywordAnalysis();

        String userAnswer = this.getOutput();

        String correctAnswer = DIO.getData(selectedRow, 3).toString();
        String suggestSolution = DIO.getData(selectedRow, 2).toString();

        int answerScore = PV
                .StringToInt(DIO.getData(selectedRow, 4)
                        .toString());
        int total_score = KA.getKeyWordSocre(solution,
                userAnswer,
                correctAnswer,
                answerScore, mkl);
        int passed_answerScore = KA.getAnswerScore(userAnswer,
                correctAnswer,
                answerScore);
        PieChart keyword = PV.getKeywordPieChart(mkl,
                answerScore);

        ArrayList<String> passedKeywordList = KA
                .getPassedKeywordlist(suggestSolution,
                        mkl);
        PieChart passKeyword = PV.getPassedPieChart(solution,
                userAnswer,
                correctAnswer, answerScore,
                passed_answerScore,
                mkl);

        // set value
        this.suggestSolution = suggestSolution;
        this.passKeyword = passKeyword;
        this.passedKeywordList = passedKeywordList;
        this.keyword = keyword;
        this.total_score = total_score;
    }

    public void Init() {

        ScreenUtils su = new ScreenUtils();
        setIconImage(su.getItemPath("PythonLogo").getImage()); // Mac
        textarea = prepareTextArea(rows, cols);
        textfield = prepareTextField(cols, textarea);

        jp.setLayout(new BorderLayout(GAP, GAP));
        jp.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        jp.add(new JScrollPane(textarea), BorderLayout.CENTER);
        jp.add(textfield, BorderLayout.SOUTH);

        this.setTitle("Terminal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(jp);
        pack();
        setLocationByPlatform(true);
        setLocation((ScreenUtils.getScreenWidth() - ScreenUtils.getDesignWindow_width()) / 2,
                (ScreenUtils.getScreenHeight() - ScreenUtils.getDesignWindow_heigh()) / 2);
        setVisible(true);

        textarea.append("Detected keyword \"input()\", please input your first value: ");
    }

    private void setAnswer_output(String string_output) {

        // submit
        if (state == 1) {
            StudentWorkingComponent.terminalArea.append(string_output + "\n");
            // get ready to submit the data to score page
            setValue_submit();
            ScorePage SP = new ScorePage(total_score,
                    passedKeywordList,
                    keyword, passKeyword,
                    solution, suggestSolution);
            SP.init();
        }
        // run code
        else if (state == 2) {
            StudentWorkingComponent.terminalArea.append(string_output + "\n");
        }
        // add question
        else if (state == 3) {
            AddQuestionComponent.newAnswer0.setText(string_output);
            JFrame jf = new JFrame();
            JOptionPane.showMessageDialog(jf,
                    "Press Submit Again To Add the question");
        }
        // edit question
        else if (state == 4) {
            ChangeQuestionComponent.cAnswer0.setText(string_output);
            JFrame jf = new JFrame();
            JOptionPane.showMessageDialog(jf,
                    "Press Update Again To Change the question");
        }
    }

    private void setError_output(String error) {
        JFrame jf = new JFrame();
        // submit
        if (state == 1) {
            StudentWorkingComponent.terminalArea.append("> " + error + "\n");
        }
        // run
        else if (state == 2) {
            StudentWorkingComponent.terminalArea.append("> " + error + "\n");
        }
        // add
        else if (state == 3) {
            JOptionPane.showMessageDialog(jf,
                    error);
            AddQuestionComponent.newAnswer0.setText(error);
        }
        // run
        else if (state == 4) {
            JOptionPane.showMessageDialog(jf,
                    error);
            ChangeQuestionComponent.cAnswer0.setText(error);
        }
    }

    public String getOutput() {
        String output = "";

        for (String s : inputstream) {
            output += s + "\n";
        }
        return output;
    }

    private String getOutput(ArrayList<String> output) throws IOException, InterruptedException {
        inputstream = runCode(pythonIntpreterFileName, pythonPath, output);
        String string_output = "";
        for (String s : inputstream) {
            string_output += s + "\n";
        }
        return string_output;
    }

    private String getError(ArrayList<String> error) {
        String error_output = "";
        for (String s : error) {
            error_output += s + "\n";
        }
        return error_output;
    }

    private JTextField prepareTextField(int cols, JTextArea textArea) {
        JTextField textField = new JTextField(cols);
        textField.addActionListener(new TextFieldListener(textArea));
        return textField;
    }

    private JTextArea prepareTextArea(int rows, int cols) {
        JTextArea textArea = new JTextArea(rows, cols);
        textArea.setEditable(false);
        textArea.setFocusable(false);

        return textArea;
    }

    private class TextFieldListener implements ActionListener {

        private JTextArea textArea;

        public TextFieldListener(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                // System.out.println(progrocessEndCount);

                JTextComponent textComponent = (JTextComponent) evt.getSource();

                String text = textComponent.getText();
                // clear the textfield
                textComponent.setText("");
                // add the input value inside the list
                output.add(text);

                String string_output = getOutput(output);
                if (b_process_if_end == 0) {
                    System.out.println(errorstream.size());

                    textArea.setText(string_output);
                    textArea.append("\nEnd");

                    JFrame jf = new JFrame();
                    JOptionPane.showMessageDialog(jf,
                            "END");
                    textfield.setEditable(false);

                    // append the result to textArea

                    // set runpython outputFromConsole as final result
                    RunPythonCode.outputFromConsole = string_output;
                    // System.out.println(RunPythonCode.outputFromConsole);
                    checkCodeError = false;
                    // close the frame
                    setVisible(false);
                    dispose();
                    setAnswer_output(string_output);

                } else {
                    if (errorstream.size() == 0) {
                        textArea.setText(string_output);

                    } else {
                        JLabel label = new JLabel("ERROR");
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        JFrame jf = new JFrame();
                        JOptionPane.showMessageDialog(jf,
                                label);
                        String error = getError(errorstream);

                        // textArea.setText(error);
                        setError_output(error);

                        RunPythonCode.errorMessage = error;
                        checkCodeError = true;

                        // close the frame
                        setVisible(false);
                        dispose();
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> runCode(String pythonIntpreterFileName, String pythonPath,
            ArrayList<String> userinput)
            throws IOException, InterruptedException {
        // make sure every loop for errorstream is clear
        errorstream.clear();
        int wa = 1;
        int inputCount = userinput.size();
        ArrayList<String> lines_result = new ArrayList<>();

        while (wa == 1) {
            ProcessBuilder pb = new ProcessBuilder(pythonIntpreterFileName, pythonPath);
            pb.redirectErrorStream(false);
            final Process p = pb.start();
            new Thread(new RunnableClass_ErrorStream(errorstream, p)).start();

            if (inputCount > userinput.size()) {
                wa = 0;
                p.destroy();
            } else {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                new Thread(new RunnableClass_InputStream(lines_result, p)).start();

                for (int i = 1; i <= inputCount; i++) {
                    int ArrayIndex = i - 1;
                    if (i == inputCount) {
                        bufferedWriter.write(userinput.get(ArrayIndex));
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    } else {
                        bufferedWriter.write(userinput.get(ArrayIndex));
                        bufferedWriter.flush();
                        bufferedWriter.newLine();
                    }
                }
                wa = p.waitFor();
                b_process_if_end = wa;
                inputCount++;
            }
        }

        return lines_result;
    }

}