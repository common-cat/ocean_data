package wiki.common_cat.mewOceanDataViewer.launcher;

import core.Core;
import wiki.common_cat.mewOceanDataViewer.tools.CustomToolFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class LauncherJar {
    public static void main(String[] args) throws MalformedURLException {
        String path0=LauncherJar.class.getResource("").getPath().split("!")[0];
        String path1=path0.substring(6,path0.length());
        File file=new File(path1);
        String path=file.getParent();
        System.out.println(path);
        try {
            Core core=new Core(path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
