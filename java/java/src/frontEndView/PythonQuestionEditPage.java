package frontEndView;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PythonQuestionEditPage {

        public void checkerPage (JPanel panel) {
                
                // 无布局
		panel.setLayout(null);

                // 
                JLabel prompt_2 = new JLabel ("Welcome to Staff Page ");
                prompt_2.setBounds(50, 10, 600, 50);
                panel.add(prompt_2);
        }
}