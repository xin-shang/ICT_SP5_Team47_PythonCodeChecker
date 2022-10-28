package methodAndTool;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import component.ChooseQuestionComponent;

public class MessagePrintString {

        // 初始化 Date 对象

        int TextQuestionNum = 1;

        public void startToString(JTextArea jTextArea) {
                String startString = "> Python Code Checker Text Start";
                jTextArea.setText(startString);
        }

        public void QuestionToString(JTextArea jTextArea) {
                String startString_1 = "\n> (" + ChooseQuestionComponent.getTime() + ") Show the " + TextQuestionNum
                                + " Text" + " QuestionID " + (ChooseQuestionComponent.getSelectedRow() + 1);
                if (ChooseQuestionComponent.getRow() != ChooseQuestionComponent.chooseQuestionTable.getSelectedRow()) {
                        jTextArea.setText(startString_1);
                        TextQuestionNum = TextQuestionNum + 1;
                } else {
                        JFrame jf = new JFrame();
                        JOptionPane.showMessageDialog(jf,
                                        "The problem shown is the same, \nThe code you have edited will not be saved.");
                        jTextArea.setText(startString_1);
                }
        }

        public void EditingToString(JTextArea jTextArea) {
                String editingString = "\n> (" + ChooseQuestionComponent.getTime() + ") Students are editing...";
                jTextArea.setText(editingString);
        }

        public void EditEndToString(JTextArea jTextArea) {
                String editingString = "\n> (" + ChooseQuestionComponent.getTime() + ") Edit End";
                jTextArea.setText(editingString);
        }

        public void SubmitingToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") System are Submiting...";
                jTextArea.setText(submitString);
        }

        public void SubmitSuccessToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit Success";
                jTextArea.setText(submitString);
        }

        public void SubmitErrorToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit Error";
                jTextArea.setText(submitString);
        }

        public void RuningToString(JTextArea jTextArea) {
                String RuningString = "\n> (" + ChooseQuestionComponent.getTime() + ") Code is Running...";
                jTextArea.setText(RuningString);
        }

        public void RunSuccessToString(JTextArea jTextArea) {
                String RunSuccessString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run Success";
                jTextArea.setText(RunSuccessString);
        }

        public void RunErrorToString(JTextArea jTextArea) {
                String RunErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run Error";
                jTextArea.setText(RunErrorString);
        }

        public void SubmitAnswerToString(JTextArea jTextArea, String answer) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit:\n" + answer;
                jTextArea.setText(submitString);
        }

        public void RunAnswerToString(JTextArea jTextArea, String answer) {
                String runString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run:\n" + answer;
                jTextArea.setText(runString);
        }

        /**
         * 细节检测： 语法错误。。。。。。
         */
        public void SytaxErroringToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime()
                                + ") Grammar check in progress...";
                jTextArea.setText(SytaxErrorString);
        }

        public void SytaxErrorTrueToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grammar check Pass";
                jTextArea.setText(SytaxErrorString);
        }

        public void SytaxErrorFalseToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grammar check Error";
                jTextArea.setText(SytaxErrorString);
        }

        public void GrabingMarkSchemeToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grabing Mark Scheme...";
                jTextArea.setText(SytaxErrorString);
        }

        public void CalculatingMarkToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime()
                                + ") System is calculating the score...";
                jTextArea.setText(SytaxErrorString);
        }

        public void CalculateMarkDoneToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Score is calculated";
                jTextArea.setText(SytaxErrorString);
        }

}
