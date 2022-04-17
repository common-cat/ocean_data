package wiki.common_cat.mewOceanDataViewer.panel.toolWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author common-cat
 * @version 1.00
 */
public abstract class AbstractToolWindow extends JDialog {
    protected int width,height;
    protected Component[] components;
    protected Font font;
    public AbstractToolWindow(Font font,BufferedImage icon, String title){
        this(font,400,600,icon,title);
    }
    public AbstractToolWindow(Font font,int width,int height,BufferedImage icon,String title){
        this.width=width;
        this.height=height;
        setIconImage(icon);
        setTitle(title);
        setSize(width,height);
        this.font=font;
        setVisible(true);
    }
    protected void addComps(Component... comps){
        if(comps!=null){
            for(Component c:comps){
                add(c);
                c.setFont(font);
            }
        }
    }
}
