package wiki.common_cat.mewOceanDataViewer.launcher;

import core.Core;
import wiki.common_cat.mewOceanDataViewer.tools.CustomToolFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class Launcher {
    public static void main(String[] args) throws MalformedURLException {
        try {
            Core core=new Core("C:/Users/common-cat/Desktop/MEW");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        CustomToolFactory customToolFactory= null;
        try {
            customToolFactory = CustomToolFactory.newInstance("C:/Users/common-cat/Desktop/MEW");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        try {
            customToolFactory.getTools(null,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
