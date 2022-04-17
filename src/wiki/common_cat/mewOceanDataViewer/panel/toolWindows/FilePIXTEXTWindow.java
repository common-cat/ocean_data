package wiki.common_cat.mewOceanDataViewer.panel.toolWindows;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

public class FilePIXTEXTWindow extends TEXTToolWindow{
    char[] buffer;
    public FilePIXTEXTWindow(Font font, Image icon0, int width, int height, BufferedImage icon,String path,String title) {
        super(font, icon0, width, height, icon, title,"loading");
        String text="";
        try {
            Reader reader=new FileReader(path, Charset.forName("UTF-8"));
            buffer=new char[(int)(new File(path)).length()];
            int length=reader.read(buffer);
            text=new String(buffer,0,length);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setText(text);
    }
}
