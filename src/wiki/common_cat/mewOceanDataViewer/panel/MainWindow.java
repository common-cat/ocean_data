package wiki.common_cat.mewOceanDataViewer.panel;

import core.Core;
import wiki.common_cat.mewOceanDataViewer.data.Site;
import wiki.common_cat.mewOceanDataViewer.panel.toolWindows.ToolWindowFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author common-cat
 * @version 1.00
 */
public class MainWindow extends JFrame {
    //主窗体
    protected Iterable<Site> sites;
    protected Core core;
    //核心
    public static double GREENWICH_X=-0.425;
    public static double PI=3.1415926;
    //格林尼治偏移 偏移中轴线
    public static String EARTH_MAP_PATH="/assets/map_earth.jpg";
    public static String SITE_ICON_PATH="/assets/site_icon.png";
    public static String SITE_ICON_CLICKED_PATH="/assets/site_clicked_icon.png";
    public static String ICON="/assets/icon.png";
    public static String THUMB_ICON="/assets/thumb.png";
    public static String ABOUT_PATH="/var/about";
    public static String QUOTE_PATH="/var/quote";
    public static String SUPPORT_PATH="/var/support";
    protected String parentPath;
    //父路径
    protected int width,height;
    protected BufferedImage icon;
    protected Container container;
    protected JMenuBar toolBar=new JMenuBar();
    //工具条
    protected PIX pix;
    protected JPanel footBar=new JPanel();
    protected PIXLabel siteName;
    protected PIXLabel SITE_NAME;
    protected PIXLabel siteID;
    protected PIXLabel SITE_ID;
    protected PIXLabel sitePos;
    protected PIXLabel SITE_POS;
    //底部状态条
    protected JMenu menuFile=new JMenu("文件");
    protected JMenu menuAbout=new JMenu("关于");
    //工具栏
    protected JMenuItem menuItemOpen=new JMenuItem("打开");
    protected JMenuItem menuItemOpenFiles=new JMenuItem("打开文件夹");
    protected JMenuItem menuItemAbout=new JMenuItem("关于");
    protected JMenuItem menuItemQuotes=new JMenuItem("引用");
    protected JMenuItem menuItemSupport=new JMenuItem("支持");
    //工具项
    protected Image thumbIcon;
    protected BufferedImage earthMap;
    protected BufferedImage siteICON;
    protected BufferedImage siteClickedICON;

    protected ToolWindowFactory toolWindowFactory;
    protected MapPanel mapPanel;
    protected MapPanel.SitesListPanel sitesListPanel;
    List<MapPanel.MapSite> mapSiteList=new LinkedList<>();
    public MainWindow(Core core,List<Site> sites,String parentPath,int width,int height) throws IOException, FontFormatException {
        this.sites=sites;
        this.core=core;
        this.width=width;
        this.height=height;
        this.parentPath=parentPath;
        ini();
    }
    protected void ini() throws IOException, FontFormatException {
        earthMap=ImageIO.read(new File(parentPath+EARTH_MAP_PATH));
        icon=ImageIO.read(new File(parentPath + ICON));
        thumbIcon=ImageIO.read(new File(parentPath+THUMB_ICON));
        siteICON=ImageIO.read(new File(parentPath+SITE_ICON_PATH));
        siteClickedICON=ImageIO.read(new File(parentPath+SITE_ICON_CLICKED_PATH));
        setIconImage((new ImageIcon(icon).getImage()));
        container=getContentPane();
        setTitle("MEW =w= 海洋数据解析器 V1.00");
        pix=new PIX(parentPath);
        siteID=new PIXLabel(pix);
        sitePos=new PIXLabel(pix);
        siteName=new PIXLabel(pix);
        SITE_NAME=new PIXLabel("选中的站点名称:",pix);
        SITE_ID=new PIXLabel("选中的站点的编号:",pix);
        SITE_POS=new PIXLabel("选中的站点位置:",pix);
        toolWindowFactory=ToolWindowFactory.newInstance(core,pix.PIXFont,icon);
        setLayout(new BorderLayout());
        setSize(width,height);
        mapPanel=new MapPanel(mapSiteList);
        //#
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        for(Site site:sites){
            mapSiteList.add(mapPanel.new MapSite(site));
        }
        mapPanel.ini();
        footBar.setLayout(new FlowLayout());
        footBar.add(SITE_NAME);
        footBar.add(siteName);
        footBar.add(SITE_ID);
        footBar.add(siteID);
        footBar.add(SITE_POS);
        footBar.add(sitePos);
        add(mapPanel,BorderLayout.CENTER);
        add(toolBar,BorderLayout.NORTH);
        add(footBar,BorderLayout.SOUTH);
        toolBar.add(menuFile);
        toolBar.add(menuAbout);
        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindowFactory.createNewToolWindow(ToolWindowFactory.FILE_TEXT_WINDOW,"关于",parentPath+ABOUT_PATH);
            }
        });
        menuItemOpenFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindowFactory.createNewToolWindow(ToolWindowFactory.FILES_SELECTOR,"选择数据文件夹",null);
            }
        });
        menuItemOpen.addActionListener(new ActionListener() {
            protected JFileChooser fileChooser;
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindowFactory.createNewToolWindow(ToolWindowFactory.FILE_SELECTOR,"选择数据文件",null);
            }
        });
        menuItemQuotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindowFactory.createNewToolWindow(ToolWindowFactory.FILE_TEXT_WINDOW,"关于",parentPath+QUOTE_PATH);
            }
        });
        menuItemSupport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindowFactory.createNewToolWindow(ToolWindowFactory.FILE_TEXT_WINDOW,"关于",parentPath+SUPPORT_PATH);
            }
        });
        menuFile.add(menuItemOpenFiles);
        menuFile.add(menuItemOpen);
        menuAbout.add(menuItemAbout);
        menuAbout.add(menuItemQuotes);
        menuAbout.add(menuItemSupport);
        menuItemSupport.setFont(pix.PIXFont);
        menuItemQuotes.setFont(pix.PIXFont);
        menuFile.setFont(pix.PIXFont);
        menuAbout.setFont(pix.PIXFont);
        menuItemAbout.setFont(pix.PIXFont);
        menuItemOpen.setFont(pix.PIXFont);
        menuItemOpenFiles.setFont(pix.PIXFont);
        sitesListPanel=mapPanel.new SitesListPanel();
        add(sitesListPanel,BorderLayout.EAST);
        validate();
        setVisible(true);
    }
    public void flush(Iterable<Site> sites) throws IOException, FontFormatException {
        remove(mapPanel);
        remove(sitesListPanel);
        remove(footBar);
        footBar=new JPanel();
        remove(toolBar);
        this.sites=sites;
        ini();
        validate();
    }
    //刷新
    protected char[] buffer=new char[1024*1024];
    public void createNewSiteWindow(Site site){
        new SiteWindow(this,icon,pix,site);
    }
    //创造新的站点窗体 TODO
    protected class MapPanel extends JPanel{
        //地图界面
        protected java.util.List<MapSite> mapSiteList=new java.util.LinkedList<>();
        protected java.util.List<PIXLabel> addedSitesLabels=new ArrayList<>();
        //已经被加入的标签
        protected int mapWidth,mapHeight;
        //图的尺寸 像素
        Image siteIcon;
        protected PIXScrollPane jScrollPane;
        protected JPanel innerPanel;
        protected JLabel background;
        protected MapPanel(List<MapSite> mapSiteList) throws IOException {
            this.mapSiteList=mapSiteList;
            mapHeight=earthMap.getHeight();
            mapWidth=earthMap.getWidth();
            siteIcon=(new ImageIcon(siteICON)).getImage();
            setLayout(new BorderLayout());
        }
        protected void ini() throws IOException {
            background=new JLabel(new ImageIcon(earthMap));
            setLayout(new GridLayout(1,1));
            innerPanel=new JPanel();
            innerPanel.setLayout(null);
            innerPanel.setPreferredSize(new Dimension(mapWidth,mapHeight));
            background.setBounds(0,0,mapWidth,mapHeight);
            innerPanel.add(background);
            jScrollPane=new PIXScrollPane(innerPanel);
            add(jScrollPane,BorderLayout.CENTER);
            flush();
            validate();
        }
        public void flush(){
            for(PIXLabel PIXLabel:addedSitesLabels){
                innerPanel.remove(PIXLabel);
            }
            int iconWidth=siteICON.getWidth(),iconHeight=siteICON.getHeight();
            for(MapSite mapSite:mapSiteList){
                if(mapSite.isVisual){
                    continue;
                }
                JLabel siteIcon=new JLabel(new ImageIcon(siteICON));
                mapSite.setSiteIcon(siteIcon);
                siteIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        mapSite.site.clicked();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        siteIcon.setIcon(new ImageIcon(siteClickedICON));
                        siteName.setText(mapSite.site.getSiteName());
                        sitePos.setText(mapSite.site.getPosition());
                        siteID.setText(mapSite.site.getSiteID());
                        footBar.validate();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        siteIcon.setIcon(new ImageIcon(siteICON));
                    }
                });
                siteIcon.setBounds(mapSite.x,mapSite.y,iconWidth,iconHeight);
                innerPanel.add(siteIcon,0);
            }
            innerPanel.validate();
            jScrollPane.validate();
        }
        //刷新
        protected class SitesListPanel extends JPanel{
            //右侧操作站点的界面
            protected JPanel sitesPanel=new JPanel();
            //站点列表
            protected PIXScrollPane jScrollPane0;
            protected SitesListPanel(){
                sitesPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
                sitesPanel.setPreferredSize(new Dimension(300,40*mapSiteList.size()));
                ini();
                setLayout(new BorderLayout());
                jScrollPane0=new PIXScrollPane(sitesPanel);
                add(jScrollPane0,BorderLayout.CENTER);
            }
            public void ini(){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        for(MapSite site:mapSiteList){
                            JButton siteLabel=new JButton(site.site.toString());
                            siteLabel.setFont(pix.PIXFont);
                            siteLabel.setBackground(Color.WHITE);
                            siteLabel.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    super.mouseClicked(e);
                                    sitePos.setText(site.site.getPosition());
                                    siteName.setText(site.site.getSiteName());
                                    siteID.setText(site.site.getSiteID());
                                    site.site.clicked();
                                }
                                @Override
                                public void mouseEntered(MouseEvent e){
                                    super.mouseEntered(e);
                                    site.getSiteIcon().setIcon(new ImageIcon(siteClickedICON));
                                }
                                @Override
                                public void mouseExited(MouseEvent e){
                                    super.mouseExited(e);
                                    site.getSiteIcon().setIcon(new ImageIcon(siteICON));
                                }
                            });
                            sitesPanel.add(siteLabel);
                        }
                        validate();
                    }
                });
            }
            //刷新列表
        }
        protected class MapSite{
            protected Site site;
            //站点
            protected double latitude,longitude;
            //经纬度
            protected int x,y;
            //像素位置
            protected JLabel siteIcon;
            protected boolean isVisual=false;
            public MapSite(Site site){
                this.site=site;
                latitude=site.getLatitude();
                longitude=site.getLongitude();
                if(latitude<-90) {
                    isVisual = true;
                }
                double y0=0.5-0.25*Math.log(Math.tan(PI*0.25+0.5*latitude*PI/180));
                double x0=0;
                if(longitude>90){
                    x0=0.25;
                }else if(longitude<-90){
                    x0=-0.25;
                }
                x0+=(0.5+GREENWICH_X+Math.sin(longitude*PI/180)/4);
                //别动这两行
                x=(int)(x0*mapWidth);
                y=(int)(y0*mapHeight);
                if(x<0){
                    x+=mapWidth;
                }
            }

            public JLabel getSiteIcon() {
                return siteIcon;
            }

            public void setSiteIcon(JLabel siteIcon) {
                this.siteIcon = siteIcon;
            }

            public boolean isVisual(){
                return isVisual;
            }
        }
    }
}
