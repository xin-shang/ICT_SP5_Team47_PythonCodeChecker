package component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class AnswerEditComponent extends Box {

        /**
         * 暂时用不到了
         */
        Box box = Box.createHorizontalBox();

        JTextArea editArea;
        JList<String> numList;

        JScrollPane editScrollPane;

        int num = 0;

        public AnswerEditComponent() {
                super(BoxLayout.Y_AXIS);

                numList = new JList<String>();
                numList.setPreferredSize(new Dimension(50, 400));
                numList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 只能选一行

                editArea = new JTextArea(60, 400);
                editArea.setLineWrap(true); // 自动换行
                editArea.setColumns(400);

                box.add(numList);
                box.add(editArea);

                editScrollPane = new JScrollPane(box);

                this.add(editScrollPane);

        }

        // 理想按一下回车响应一下， int num + 1
        private void Button_Item_EnterChecker(JButton enterCheck2) {
                enterCheck2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub

                        }
                });
        }

        // 获取当前行数，
        private int getNowNumber() {

                System.out.println("ENTER  ::  ");
                return 0;
        }

        // 返还Sting 行数到列表

}

/**
 * // 获取内容的行数（以换行符计算，满行自动换下一行不算增加行数）
 * int getLineCount()
 */
