package wiki.common_cat.mewOceanDataViewer.panel;

import javax.swing.*;
import java.awt.*;
/**
 * @author common-cat
 * @version 1.00
 */
public class PIXLabel extends JLabel {
    protected static Font font;
    public PIXLabel(){

    }
    //必须先完成初始化
    public PIXLabel(PIX pix){
        super.setFont(pix.PIXFont);
        font=pix.PIXFont;
    }
    public PIXLabel(String s,Font f){
        super(s);
        setFont(f);
    }
    public PIXLabel(String s,PIX pix){
        super(s);
        setFont(pix.PIXFont);
        font=pix.PIXFont;
    }
    public void setFontSize(int size){
        setFont(font.deriveFont(Font.BOLD,size));
    }
    //设置字体大小
    public String toString(){
        return getText();
    }
}
