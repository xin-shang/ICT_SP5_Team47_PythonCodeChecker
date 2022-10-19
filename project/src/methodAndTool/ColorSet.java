package methodAndTool;

import java.awt.Color;

import component.StudentWorkingComponent;

public class ColorSet {

    static int Current_color_set = 1;

    public ColorSet() {
        setColor(Current_color_set);
    }

    public int getCurrent_color_set() {
        return ColorSet.Current_color_set;
    }

    public void setCurrent_color_set(int i) {
        Current_color_set = i;
        setColor(i);

    }

    private void setColor(int i) {
        if (i == 1) {
            ColorSet_1();
        } else {
            ColorSet_2();
        }
    }

    private static void ColorSet_1() {
        StudentWorkingComponent.editTextPane.setDocument(new JTextPaneColorDocument_1());
        StudentWorkingComponent.editTextPane.setBackground(new Color(48, 49, 52));
        StudentWorkingComponent.editTextPane.setCaretColor(Color.WHITE);

    }

    private static void ColorSet_2() {
        StudentWorkingComponent.editTextPane.setDocument(new JTextPaneColorDocument_2());
        StudentWorkingComponent.editTextPane.setBackground(new Color(255, 255, 255));
        StudentWorkingComponent.editTextPane.setCaretColor(Color.BLACK);

    }

}
