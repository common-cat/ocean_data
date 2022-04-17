package wiki.common_cat.mewOceanDataViewer.panel.toolWindows;

import wiki.common_cat.mewOceanDataViewer.panel.PIX;
import wiki.common_cat.mewOceanDataViewer.panel.PIXLabel;
import wiki.common_cat.mewOceanDataViewer.panel.PIXScrollPane;
import wiki.common_cat.mewOceanDataViewer.panel.PIXTextArea;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TEXTToolWindow extends AbstractToolWindow{
    protected PIXTextArea textArea;
    public TEXTToolWindow(Font font,Image icon0,int width, int height, BufferedImage icon, String title, String text) {
        super(font,width, height, icon, title);
        textArea=new PIXTextArea(icon,font,"loading");
        addComps((textArea).getScrollPane());
    }
    protected void setText(String text){
        textArea.setText(text);
    }
}
