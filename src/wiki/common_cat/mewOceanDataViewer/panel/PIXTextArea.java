package wiki.common_cat.mewOceanDataViewer.panel;

import javax.swing.*;
import java.awt.*;
/**
 * @author common-cat
 * @version 1.00
 */
public class PIXTextArea extends JTextArea {
    protected Font font;
    protected PIXScrollPane scrollPane;
    public PIXTextArea(Image icon,PIX pix,String text){
        this(icon,pix.PIXFont,text);
    }
    public PIXTextArea(Image icon,Font font,String text){
        setFont(font);
        setEditable(false);
        setText(text);
        setLineWrap(true);
        setPreferredSize(new Dimension(600,2000));
        scrollPane=new PIXScrollPane(this);
    }
    public JScrollPane getScrollPane(){
        return scrollPane;
    }

}
