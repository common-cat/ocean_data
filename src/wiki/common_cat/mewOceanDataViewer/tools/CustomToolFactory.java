package wiki.common_cat.mewOceanDataViewer.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author common-cat
 * @version V1.00
 */
public class CustomToolFactory {
    //根据类来获得工具
    protected URLClassLoader urlClassLoader;
    public static String TOOLS_PATH="/tools";
    public static String TOOLS_CONFIG_PATH="/tools/customToolsConfig.xml";
    protected Map<String,String> fileNameToClassName=new HashMap<>();
    protected String parentPath;

    private CustomToolFactory(){

    }
    private CustomToolFactory(String parentPath) throws IOException, ParserConfigurationException, SAXException {
        this.parentPath=parentPath;
        urlClassLoader=new URLClassLoader(new URL[]{new URL("file://"+parentPath+TOOLS_PATH)});
        Document document=(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(parentPath+TOOLS_CONFIG_PATH)));
        Node topNode=document.getDocumentElement();
        NodeList nodeList=topNode.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            if(!node.getNodeName().equals("tool")){
                continue;
            }
            fileNameToClassName.put(((Element)node).getAttribute("fileName"),node.getTextContent());
        }
    }
    public static CustomToolFactory newInstance(String parentPath) throws IOException, ParserConfigurationException, SAXException {
        return new CustomToolFactory(parentPath);
    }
    public List<AbstractTool> getTools(Map<String,Site> IDSiteMap,MainWindow mainWindow) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<AbstractTool> list=new ArrayList<>();
        File dir=new File(parentPath+TOOLS_PATH);
        for(File classFile:dir.listFiles()){
            if(!classFile.getName().split("\\.")[1].equals("class")){
                continue;
            }
            list.add((AbstractTool)urlClassLoader.loadClass(fileNameToClassName.get(classFile.getName())).getConstructor(Map.class,MainWindow.class).newInstance(IDSiteMap,mainWindow));
        }

        return list;
    }
}
