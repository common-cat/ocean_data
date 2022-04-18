package wiki.common_cat.mewOceanDataViewer.core;

import org.xml.sax.SAXException;
import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.fileReader.AbstractFileReader;
import wiki.common_cat.mewOceanDataViewer.fileReader.FileReaderFactory;
import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;
import wiki.common_cat.mewOceanDataViewer.panel.toolWindows.TEXTToolWindow;
import wiki.common_cat.mewOceanDataViewer.tools.AbstractTool;
import wiki.common_cat.mewOceanDataViewer.tools.CustomToolFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author common-cat
 * @version V1.00
 */
public class Core {
    protected Map<String, Site> sites=new HashMap<>();
    //所有站点的数据
    protected java.util.List<Site> siteList=new ArrayList<>();
    protected String parentPath;
    //父路径
    protected MainWindow mainWindow;
    public static final String CONFIG_PATH="/config/config.xml";
    AbstractFileReader reader;
    FileReaderFactory fileReaderFactory;
    protected CustomToolFactory customToolFactory;
    public Core(String parentPath) throws ParserConfigurationException, IOException, SAXException, FontFormatException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.parentPath=parentPath;
        fileReaderFactory=FileReaderFactory.newInstance();
        customToolFactory=CustomToolFactory.newInstance(parentPath);
        mainWindow=new MainWindow(this,siteList,parentPath,1000,600);
    }
    public void readFiles(Iterable<String> paths, TEXTToolWindow textToolWindow) throws IOException, FontFormatException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        reader=fileReaderFactory.getFileReader(paths);
        sites=reader.getData(paths);
        for(Site site:sites.values()){
            siteList.add(site);
        }
        mainWindow.flush(siteList);
        for(Site site:sites.values()){
            site.setMainWindow(mainWindow);
        }
        textToolWindow.setVisible(false);
    }
    public Map<String, AbstractTool> getCustomTools() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<String,AbstractTool> toolMap=new HashMap<>();
        for(AbstractTool tool:customToolFactory.getTools(sites,mainWindow)){
            toolMap.put(tool.getName(),tool);
        }
        System.out.println(toolMap);
        return toolMap;
    }
}
