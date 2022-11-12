package methodAndTool;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class JTextPaneColorDocument_2 extends DefaultStyledDocument {

    // cont是个空容器，下面用来设置容器包含内容
    final StyleContext cont = StyleContext.getDefaultStyleContext();

    final AttributeSet blueColor = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(0, 0, 225));

    final AttributeSet blackColor = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
    final AttributeSet purpleColor = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(170, 80, 153));

    final AttributeSet redColor = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(255, 51, 51));
    final AttributeSet yellowColor = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground,
            new Color(153, 76, 0));

    private int findLastChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    // 找两次，双保险
    private int findFirstChar(String text, int index) {
        // 从后往前找，每找一个减1，找到第一位字符
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    public void remove(int offs, int len) throws BadLocationException {
        // 用来删除
        super.remove(offs, len);

        // get the all code text
        String text = getText(0, getLength());

        // find the most left character
        int leftIndex = findFirstChar(text, offs);
        if (leftIndex < 0)
            leftIndex = 0;
        // find the most right character
        int rightIndex = findLastChar(text, offs);

        // found a match and update matched word with color
        if (text.substring(leftIndex, rightIndex).matches(
                "(\\W)*(as|assert|async|await|break|continue|del|elif|else|except|finally|for|from|global|if|import|in|pass|raise|return|try|while|with|yield)")) {
            setCharacterAttributes(leftIndex, rightIndex - leftIndex, purpleColor, false);
        } else if (text.substring(leftIndex, rightIndex).matches(
                "(\\W)*((False|None|True|and|is|lambda|class|def|nonlocal|not|or))")) {
            setCharacterAttributes(leftIndex, rightIndex - leftIndex, blueColor, false);
        } else if (text.substring(leftIndex, rightIndex).matches(
                "(\\W)*((print|sum|input))")) {
            setCharacterAttributes(leftIndex, rightIndex - leftIndex, yellowColor, false);
        } else if (text.substring(leftIndex, rightIndex).matches(
                "(\\W)*((\\(|\\)|\\{|\\}|\\[|\\]|\\=))")) {
            setCharacterAttributes(leftIndex, rightIndex - leftIndex, redColor, false);
        } else {
            setCharacterAttributes(leftIndex, rightIndex - leftIndex, blackColor, false);
        }
    }

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        // 输入
        super.insertString(offset, str, a);
        // get the all code text
        String text = getText(0, getLength());

        // find the most left character from the index offset
        int before = findFirstChar(text, offset);
        if (before < 0)
            before = 0;

        // find the most right character from the index offset
        int after = findLastChar(text, offset + str.length());
        int leftIndex = before;
        int rightIndex = before;

        // found a match and update matched word with color
        while (rightIndex <= after) {
            if (rightIndex == after || String.valueOf(text.charAt(rightIndex)).matches("\\W")) {
                if (text.substring(leftIndex, rightIndex).matches(
                        "(\\W)*(as|assert|async|await|break|continue|del|elif|else|except|finally|for|from|global|if|import|in|pass|raise|return|try|while|with|yield)"))
                    setCharacterAttributes(leftIndex, rightIndex - leftIndex, purpleColor, false);
                else if (text.substring(leftIndex, rightIndex).matches(
                        "(\\W)*(False|None|True|and|is|lambda|class|def|nonlocal|not|or)"))
                    setCharacterAttributes(leftIndex, rightIndex - leftIndex, blueColor, false);
                else if (text.substring(leftIndex, rightIndex).matches(
                        "(\\W)*(print|sum|input)"))
                    setCharacterAttributes(leftIndex, rightIndex - leftIndex, yellowColor, false);
                else if (text.substring(leftIndex, rightIndex).matches(
                        "((\\(|\\)|\\{|\\}|\\[|\\]|\\=))"))
                    setCharacterAttributes(leftIndex, rightIndex - leftIndex, redColor, false);
                else
                    setCharacterAttributes(leftIndex, rightIndex - leftIndex, blackColor, false);
                leftIndex = rightIndex;
            }
            rightIndex++;
        }
    }

}
