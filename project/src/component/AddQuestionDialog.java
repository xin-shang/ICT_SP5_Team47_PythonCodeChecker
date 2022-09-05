package component;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import methodAndTool.ScreenUtils;
import methodAndTool.dataIO;

public class AddQuestionDialog extends JDialog {
        // 应该不需要读取
        dataIO DIO = new dataIO();

        final int W = 500;
        final int H = 618;

        // "ID", "Question-Stems", "Solution", "Answer", "ScorePoint"
        JLabel newID, newQuestion, newSolution, newAnswer, newScorePoint;
        JTextArea newQuestion0, newSolution0, newAnswer0, text0_SP;
        // 下拉框

        JTextField score;
        JPanel buttonPanel;
        JButton createNewQuestion;

        public AddQuestionDialog(JFrame frame, String title, boolean isModel) {

                super(frame, title, isModel);

                /**
                 * 设置窗口属性
                 */
                this.setBounds((ScreenUtils.getScreenWidth() - W) / 2, (ScreenUtils.getScreenHeight() - H) / 2, W, H);
                try {
                        frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac
                                                                                                           // 好像不太支持这个，Windows
                        // 咋样要试试。
                        System.out.println("-- ImageIO is Working --");
                } catch (IOException e) {
                        e.printStackTrace();
                }
                this.setResizable(false); // 窗口锁定
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭

                /**
                 * 设置窗口内容
                 */
                //
                newID = new JLabel("ID:" + (DIO.getDblength() + 1));

                //
                newQuestion = new JLabel("Please Write down Question Stem");
                newQuestion0 = new JTextArea(10, 10);
                newQuestion0.setLineWrap(true); // 自动换行

                Box boxQuestion0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Question0 = new JScrollPane(newQuestion0);
                boxQuestion0.add(scrollPane_Question0);

                //
                newSolution = new JLabel("Please Write down Solution of Question");
                newSolution0 = new JTextArea(20, 10);
                newSolution0.setLineWrap(true); // 自动换行

                Box boxSolution0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Solution0 = new JScrollPane(newSolution0);
                boxSolution0.add(scrollPane_Solution0);

                //
                newAnswer = new JLabel("Please Write down Answer of Qiestion");
                newAnswer0 = new JTextArea(10, 10);
                newAnswer0.setLineWrap(true); // 自动换行

                Box boxAnswer0 = Box.createHorizontalBox();
                JScrollPane scrollPane_Answer0 = new JScrollPane(newAnswer0);
                boxAnswer0.add(scrollPane_Answer0);

                //
                newScorePoint = new JLabel("Please Write down Score Point of Qiestion");
                text0_SP = new JTextArea(10, 10); // 这个最后会被换掉 变成下拉框

                //
                buttonPanel = new JPanel();
                buttonPanel.setMaximumSize(new Dimension(500, 80));

                createNewQuestion = new JButton("-- Create a New Question --");

                /**
                 * 组装零件
                 */
                Box box = Box.createVerticalBox();
                box.add(newID);
                box.add(Box.createVerticalStrut(10));
                box.add(newQuestion);
                box.add(boxQuestion0);
                box.add(Box.createVerticalStrut(10));
                box.add(newSolution);
                box.add(boxSolution0);
                box.add(Box.createVerticalStrut(10));
                box.add(newAnswer);
                box.add(boxAnswer0);
                box.add(Box.createVerticalStrut(10));
                box.add(newScorePoint);
                box.add(text0_SP);

                // JScrollPane scrollPane = new JScrollPane(box);

                buttonPanel.add(createNewQuestion);

                this.add(box);
                // this.add(scrollPane);
                this.add(buttonPanel, BorderLayout.SOUTH);

        }

}

// // 设置组件的 首选 大小
// void setPreferredSize(Dimension preferredSize)

// // 设置组件的 最小 大小
// void setMinimumSize(Dimension minimumSize)

// // 设置组件的 最大 大小
// void setMaximumSize(Dimension maximumSize)
