package wiki.common_cat.mewOceanDataViewer.tools;

import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;

import javax.swing.*;
import java.util.Map;

public class TESTTool extends AbstractTool{
    public TESTTool(Map<String, Site> IDSiteMap, MainWindow mainWindow) {
        super(IDSiteMap, mainWindow);
        System.out.println("created!");
    }

    @Override
    public void flush(Map<String, Site> stringSiteMap) {

    }

    @Override
    public void flush() {

    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
