package wiki.common_cat.mewOceanDataViewer.tools;

import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * @author common-cat
 * @version V1.00
 */
public abstract class AbstractTool {
    //自定义工具类
    protected Map<String,Site> IDSiteMap;
    protected MainWindow mainWindow;
    protected Font font;
    public AbstractTool(Map<String, Site> IDSiteMap,MainWindow mainWindow){
        this.IDSiteMap=IDSiteMap;
        this.mainWindow=mainWindow;
    }
    public void setFont(Font font){
        this.font=font;
    }
    public abstract void flush(Map<String,Site> stringSiteMap);
    //传入新的参数刷新
    public abstract void flush();
    //刷新
    public abstract JPanel getPanel();
    //获得该工具的界面
    public abstract String getName();
    //获取名字
}
