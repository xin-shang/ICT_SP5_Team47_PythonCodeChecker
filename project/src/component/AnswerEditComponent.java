package component;

import java.awt.Dimension;


import javax.swing.Box;
import javax.swing.BoxLayout;
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
                editArea.setTabSize(1);
                editArea.setColumns(400);

                box.add(numList);
                box.add(editArea);

                editScrollPane = new JScrollPane(box);

                this.add(editScrollPane);

        }



}

/**
 * // 获取内容的行数（以换行符计算，满行自动换下一行不算增加行数）
 * int getLineCount()
 */
