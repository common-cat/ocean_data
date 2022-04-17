package wiki.common_cat.mewOceanDataViewer.panel;

import wiki.common_cat.mewOceanDataViewer.tools.AbstractTool;
import wiki.common_cat.mewOceanDataViewer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrawDataPanel extends JTabbedPane {
    //数据绘制面板
    protected Map<Integer,AXISDrawer> IDToAxisDrawerMap=new HashMap<>();
    protected Font font;
    protected ToolsPanel toolsPanel;
    protected Map<Integer,String> IDToValue;
    protected Integer[] valuesID;
    protected Data data;
    protected int valueQuantities;
    public DrawDataPanel(Font font, Data data, Map<Integer,String> IDToValue,Integer[] valuesID){
        setFont(font);
        this.font=font;
        this.valuesID=valuesID;
        this.data=data;
        valueQuantities=IDToValue.size();
        this.IDToValue=IDToValue;
        toolsPanel=new ToolsPanel();
        flush();
    }
    public void flush(){
        removeAll();
        for(Integer i:valuesID){
            AXISDrawer aixDrawer=new AXISDrawer(i);
            add(IDToValue.get(i),aixDrawer);
            IDToAxisDrawerMap.put(i,aixDrawer);
        }
        add(toolsPanel,"自定义工具");
    }
    //刷新
    public void setAxisColor(Integer type,Color color){
        IDToAxisDrawerMap.get(type).axisColor=color;
    }
    public void setPointsColor(Integer type,Color color){
        IDToAxisDrawerMap.get(type).pointsColor=color;
    }
    public void setXAxis(int type){
        for(AXISDrawer axisDrawer:IDToAxisDrawerMap.values()){
            axisDrawer.setXAxis(type);
        }
    }
    protected class AXISDrawer extends JPanel{
        //散点图绘制窗口
        protected String xTypeName="?";
        protected String yTypeName="?";
        protected int dataType,xDataType;
        protected double maxX,minX,maxY,minY;
        protected double amplifying=1;
        protected double xBegin,yBegin,xEnd,yEnd;
        protected Color axisColor=Color.RED,pointsColor=Color.BLUE,textColor=Color.GRAY,pointColor=Color.CYAN;
        protected AXISDrawer(Integer dataType){
            yTypeName=IDToValue.get(dataType);
            AXISDrawer.this.dataType=dataType;
            maxY=data.getRangeMax()[dataType];
            minY=data.getRangeMin()[dataType];
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            setRatPosition(e.getX(),e.getY());
                            repaint();
                        }
                    });
                }
            });
        }
        protected int ratX=0,ratY=0;
        //光标的实际位置
        protected void setRatPosition(int ratX,int ratY){
            this.ratX=ratX;
            this.ratY=ratY;
        }
        public void setXAxis(int xDataType){
            xTypeName=IDToValue.get(xDataType);
            this.xDataType=xDataType;
            minX=data.getRangeMin()[xDataType];
            maxX=data.getRangeMax()[xDataType];
            xBegin=minX;
            xEnd=maxX;
        }
        public void paint(Graphics g) {
            double[] xy = pointParseToExactValue(ratX, ratY);
            String str = xTypeName + ":" + xy[0] + " " + yTypeName + ":" + xy[1];
            g.clearRect(0, 0, AXISDrawer.this.getWidth(), AXISDrawer.this.getHeight());
            g.setFont(font);
            g.setColor(axisColor);
            g.drawLine(0, ratY, AXISDrawer.this.getWidth(), ratY);
            g.drawLine(ratX, 0, ratX, AXISDrawer.this.getHeight());
            g.setColor(pointColor);
            g.drawLine(ratX, ratY, ratX, ratY);
            g.setColor(textColor);
            g.drawString(str, ratX + 10, ratY - 10);
            g.setColor(pointsColor);
                try {
                double[] data1 = data.getData().get(0);
                double xL = data1[xDataType], yL = data1[dataType];
                for (double[] data0 : data.getData()) {
                        int[] lXY =exactValueParseToPoint(xL, yL);
                        int[] xY = exactValueParseToPoint(data0[xDataType], data0[dataType]);
                        g.drawLine(lXY[0], lXY[1], xY[0], xY[1]);
                        xL = data0[xDataType];
                        yL = data0[dataType];
                }
            } catch (Exception e) {
            }
        }
        protected int[] exactValueParseToPoint(double x0,double y0){
            int[] ds=new int[2];
            ds[0]=(int)(AXISDrawer.this.getWidth()*((x0-xBegin)/maxX*amplifying));
            ds[1]=(int)(AXISDrawer.this.getHeight()*(1-((y0-yBegin)/maxY*amplifying)));
            return ds;
        }
        protected double[] pointParseToExactValue(int x,int y){
            double[] ds=new double[2];
            ds[0]=xBegin+((double)(x)/AXISDrawer.this.getWidth())*(maxX-xBegin)/amplifying;
            ds[1]=yBegin+((double)(AXISDrawer.this.getHeight()-y)/AXISDrawer.this.getHeight())*(maxY-yBegin)/amplifying;
            return ds;
        }
    }
    public void addTool(AbstractTool tool){

    }
    //TODO
    protected class ToolsPanel extends JPanel{
        //自定义工具界面
        public ToolsPanel(){
            setFont(font);
        }
    }
}
