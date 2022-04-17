package wiki.common_cat.mewOceanDataViewer.panel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * @author common-cat
 * @version 1.00
 */
public class PIX {
    protected Font PIXFont;
    protected String parentPath;
    protected String pixPath="/assets/PIX.ttf";
    public PIX(String parentPath) throws IOException, FontFormatException {
        this.parentPath=parentPath;
        PIXFont=Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(parentPath+pixPath));
        PIXFont=PIXFont.deriveFont(Font.BOLD,14);
    }
    public Font getPIXFont(){
        return PIXFont;
    }
    public void setSize(int size){
        PIXFont=PIXFont.deriveFont(Font.BOLD,size);
    }
}
