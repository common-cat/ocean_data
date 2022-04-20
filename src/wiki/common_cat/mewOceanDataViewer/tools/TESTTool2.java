package wiki.common_cat.mewOceanDataViewer.tools;

import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;
import wiki.common_cat.mewOceanDataViewer.panel.PIXLabel;

import javax.swing.*;
import java.util.Map;

public class TESTTool2 extends AbstractTool{
    public TESTTool2(Map<String, Site> IDSiteMap, MainWindow mainWindow) {
        super(IDSiteMap,mainWindow);
        this.IDSiteMap=IDSiteMap;
        this.mainWindow=mainWindow;
    }

    @Override
    public void flush(Map<String, Site> stringSiteMap) {

    }

    @Override
    public void flush() {

    }

    @Override
    public JPanel getPanel() {
        JPanel panel=new JPanel();
        PIXLabel pixLabel=new PIXLabel("测试用自定义工具2",font);
        return panel;
    }

    @Override
    public String getName() {
        return "测试自定义工具2";
    }
}
