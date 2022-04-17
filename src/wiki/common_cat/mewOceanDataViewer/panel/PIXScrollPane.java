package wiki.common_cat.mewOceanDataViewer.panel;

import javax.swing.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
/**
 * @author common-cat
 * @version 1.00
 */
public class PIXScrollPane extends JScrollPane implements ImageObserver {
    public PIXScrollPane(Component component){
        super(component);
        getHorizontalScrollBar().setBackground(Color.WHITE);
        getVerticalScrollBar().setBackground(Color.WHITE);
        setWheelScrollingEnabled(true);
        getVerticalScrollBar().setUI(new BarUI());
        getHorizontalScrollBar().setUI(new BarUI());
    }
    protected class BarUI extends BasicScrollBarUI{

    }
}
