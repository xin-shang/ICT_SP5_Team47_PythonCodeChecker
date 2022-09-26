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
                jTextArea.append(startString);
        }

        public void QuestionToString(JTextArea jTextArea) {
                String startString_1 = "\n> (" + ChooseQuestionComponent.getTime() + ") Show the " + TextQuestionNum + " Text" + " QuestionID " + (ChooseQuestionComponent.getSelectedRow()+1);
                if (ChooseQuestionComponent.getRow() != ChooseQuestionComponent.chooseQuestionTable.getSelectedRow()) {
                        jTextArea.append(startString_1);
                        TextQuestionNum = TextQuestionNum + 1;
                }
                else{
                        JFrame jf = new JFrame();
                        JOptionPane.showMessageDialog(jf, "The problem shown is the same, \nThe code you have edited will not be saved.");
                        jTextArea.append(startString_1);
                }
        }

        public void EditingToString(JTextArea jTextArea) {
                String editingString = "\n> (" + ChooseQuestionComponent.getTime() + ") Students are editing...";
                jTextArea.append(editingString);
        }

        public void EditEndToString(JTextArea jTextArea) {
                String editingString = "\n> (" + ChooseQuestionComponent.getTime() + ") Edit End";
                jTextArea.append(editingString);
        }

        public void SubmitingToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") System are Submiting...";
                jTextArea.append(submitString);
        }

        public void SubmitSuccessToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit Success";
                jTextArea.append(submitString);
        }

        public void SubmitErrorToString(JTextArea jTextArea) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit Error";
                jTextArea.append(submitString);
        }

        public void RuningToString(JTextArea jTextArea) {
                String RuningString = "\n> (" + ChooseQuestionComponent.getTime() + ") Code is Running...";
                jTextArea.append(RuningString);
        }

        public void RunSuccessToString(JTextArea jTextArea) {
                String RunSuccessString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run Success";
                jTextArea.append(RunSuccessString);
        }

        public void RunErrorToString(JTextArea jTextArea) {
                String RunErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run Error";
                jTextArea.append(RunErrorString);
        }

        public void SubmitAnswerToString(JTextArea jTextArea, String answer) {
                String submitString = "\n> (" + ChooseQuestionComponent.getTime() + ") Submit:\n" + answer;
                jTextArea.append(submitString);
        }

        public void RunAnswerToString(JTextArea jTextArea, String answer) {
                String runString = "\n> (" + ChooseQuestionComponent.getTime() + ") Run:\n" + answer;
                jTextArea.append(runString);
        }

        /**
         * 细节检测： 语法错误。。。。。。
        */
        public void SytaxErroringToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grammar check in progress...";
                jTextArea.append(SytaxErrorString);
        }

        public void SytaxErrorTrueToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grammar check Pass";
                jTextArea.append(SytaxErrorString);
        }

        public void SytaxErrorFalseToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grammar check Error";
                jTextArea.append(SytaxErrorString);
        }

        public void GrabingMarkSchemeToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Grabing Mark Scheme...";
                jTextArea.append(SytaxErrorString);
        }
        
        public void CalculatingMarkToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") System is calculating the score...";
                jTextArea.append(SytaxErrorString);
        }

        public void CalculateMarkDoneToString(JTextArea jTextArea) {
                String SytaxErrorString = "\n> (" + ChooseQuestionComponent.getTime() + ") Score is calculated";
                jTextArea.append(SytaxErrorString);
        }

}
