package wiki.common_cat.mewOceanDataViewer.panel.toolWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FileSelectorWindow extends AbstractToolWindow{
    protected JFileChooser fileChooser;
    protected JButton button;
    public FileSelectorWindow(Font font,BufferedImage icon, String title,Component... comps) {
        super(font,icon, title);
        fileChooser=(JFileChooser) comps[0];
        button=(JButton)comps[1];
        super.setLayout(new BorderLayout());
        add(fileChooser,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        fileChooser.setFont(font);
        button.setFont(font);
        fileChooser.setControlButtonsAreShown(false);
    }
}
