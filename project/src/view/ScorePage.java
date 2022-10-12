package view;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import component.ChooseQuestionComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javaswingdev.chart.PieChart;
import methodAndTool.ScreenUtils;
import methodAndTool.keywordAnalysis;

public class ScorePage extends JFrame implements ActionListener {

    keywordAnalysis KA = new keywordAnalysis();

    // create frame
    JFrame frame = new JFrame("Score Page");
    // Page title
    private JLabel Socre_label_1 = new JLabel();

    // keyword diagrame title
    private JLabel keyword_pie_label = new JLabel("Keyword Distribution");
    // student score title
    private JLabel passedKeyword_pie_label = new JLabel("Your Score");

    // bottom button
    JButton return_Button, feedback_button;

    int score;
    PieChart keyword_pie;
    PieChart passedKeyword_pie;
    String solution;
    String suggestedAnswer;

    FeedbackPage feedbackPage = new FeedbackPage("Feedback", frame);

    public ScorePage(int score, PieChart keyword_pie, PieChart passedKeyword_pie, String solution,
            String suggestedAnswer) {
        this.score = score;
        this.keyword_pie = keyword_pie;
        this.passedKeyword_pie = passedKeyword_pie;
        this.solution = solution;
        this.suggestedAnswer = suggestedAnswer;

        // Feedback Page Setting
        feedbackPage.setSize(ScreenUtils.getDesignWindow_width(),
                ScreenUtils.getDesignWindow_heigh());
        feedbackPage.setLocationRelativeTo(frame);
    }

    public void init() {

        frame.setLocation((ScreenUtils.getScreenWidth() - ScreenUtils.getDesignWindow_width()) / 2,
                (ScreenUtils.getScreenHeight() - ScreenUtils.getDesignWindow_heigh()) / 2); // 窗口位置
        frame.setSize(ScreenUtils.getDesignWindow_width(), ScreenUtils.getDesignWindow_heigh()); // 设置窗口（宽，高）
        try {
            frame.setIconImage(ImageIO.read(new File(ScreenUtils.getItemPath("PythonLogo")))); // Mac
                                                                                               // 好像不太支持这个，Windows
            System.out.println("-- ImageIO is Working --");
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setResizable(true); // 窗口锁定
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 违规操作关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Color red = new Color(255, 0, 0);
        // Color yellow = new Color(100, 0, 0);
        ///////////////////////
        // keyword_pie = new PieChart();
        // keyword_pie.addData(new ModelPieChart("aaa", 50, red));

        // passedKeyword_pie = new PieChart();
        // passedKeyword_pie.addData(new ModelPieChart("aaa", 100, yellow));
        ///////////////////////

        Socre_label_1.setText("Score: " + score);

        Box title_box = Box.createHorizontalBox();
        title_box.add(Box.createHorizontalGlue());
        title_box.add(Socre_label_1, BorderLayout.CENTER);
        title_box.add(Box.createHorizontalGlue());

        // create center keyword chart title box
        Box PieChart_box_keyword_title = Box.createHorizontalBox();
        PieChart_box_keyword_title.add(Box.createHorizontalGlue());
        PieChart_box_keyword_title.add(keyword_pie_label);
        PieChart_box_keyword_title.add(Box.createHorizontalGlue());

        // create box combine title and chart(left side)
        Box PieChart_box_keyword = Box.createVerticalBox();
        PieChart_box_keyword.add(PieChart_box_keyword_title);
        PieChart_box_keyword.add(Box.createVerticalStrut(5));
        PieChart_box_keyword.add(keyword_pie);

        // create center keyword chart title box
        Box PieChart_box_Passedkeyword_title = Box.createHorizontalBox();
        PieChart_box_Passedkeyword_title.add(Box.createHorizontalGlue());
        PieChart_box_Passedkeyword_title.add(passedKeyword_pie_label);
        PieChart_box_Passedkeyword_title.add(Box.createHorizontalGlue());

        // create box combine title and chart(right side)
        Box PieChart_box_Passedkeyword = Box.createVerticalBox();
        PieChart_box_Passedkeyword.add(PieChart_box_Passedkeyword_title);
        // PieChart_box_Passedkeyword.add(Box.createVerticalStrut(5));
        PieChart_box_Passedkeyword.add(passedKeyword_pie);

        // create box combine left and right chart component
        Box PieChart_box = Box.createHorizontalBox();
        PieChart_box.add(PieChart_box_keyword);
        PieChart_box.add(Box.createHorizontalStrut(5));
        PieChart_box.add(PieChart_box_Passedkeyword);

        return_Button = new JButton("RETURN");
        feedback_button = new JButton("FEEDBACK");

        return_Button.setPreferredSize(new Dimension(300, 50));
        return_Button.addActionListener(this);
        feedback_button.setPreferredSize(new Dimension(300, 50));
        feedback_button.addActionListener(this);

        Box buttonBoder = Box.createHorizontalBox();
        buttonBoder.add(Box.createHorizontalGlue());
        buttonBoder.add(return_Button);
        buttonBoder.add(Box.createHorizontalGlue());
        buttonBoder.add(Box.createHorizontalGlue());
        buttonBoder.add(feedback_button);
        buttonBoder.add(Box.createHorizontalGlue());

        Box box = Box.createVerticalBox();
        box.add(title_box);
        box.add(Box.createVerticalStrut(50));
        box.add(PieChart_box);
        box.add(buttonBoder, BorderLayout.SOUTH);
        box.add(Box.createVerticalStrut(10));

        frame.add(box);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("RETURN")) {
            frame.setVisible(false);

        } else if (actionCommand.equals("FEEDBACK")) {
            int selectedRow = ChooseQuestionComponent.getSelectedRow();

            if (selectedRow >= 0) {

                Thread t = new Thread() {
                    public void run() {
                        feedbackPage.showFeedbackResult(solution, suggestedAnswer);
                    }
                };
                t.start();

                // Make the pop up dialog center align to parent window
                feedbackPage.setLocationRelativeTo(frame);
                // Show the feedback dialog
                feedbackPage.setVisible(true);
                System.out.println("-- The Show Feedback Button is Working --");
            } else {
                JFrame jf = new JFrame();
                JOptionPane.showMessageDialog(jf, "Please Select A Question");
            }
        }

    }

}
