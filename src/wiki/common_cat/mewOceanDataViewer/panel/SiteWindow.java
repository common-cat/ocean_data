package wiki.common_cat.mewOceanDataViewer.panel;

import wiki.common_cat.mewOceanDataViewer.data.Data;
import wiki.common_cat.mewOceanDataViewer.data.Site;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * @author common-cat
 * @version 1.00
 */
public class SiteWindow extends JDialog {
        //站点所有的数据窗体
        protected Map<Integer,String> IDToTypeMap;
        protected PIX pix;
        protected int width=1000,height=300;
        protected Site site;
        protected DrawDataPanel drawDataPanel;
        protected SiteValueListPanel siteValueListPanel;
        protected SiteWindow(JFrame frame,BufferedImage icon,PIX pix, Site site){
            super(frame);
            this.site=site;
            this.pix=pix;
            this.drawDataPanel=drawDataPanel;
            setSize(width,height);
            setIconImage(icon);
            setTitle("站点名称:"+site.getSiteName()+" 站点ID："+site.getSiteID()+" owo数据界面");
            ini();
            setVisible(true);
            validate();
    }
    protected void ini(){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    IDToTypeMap=site.getIDToValue();
                    setLayout(new BorderLayout());
                    drawDataPanel=new DrawDataPanel(pix.PIXFont,site.getTimeToData().values().iterator().next(),site.getIDToValue(),site.getTimeToData().values().iterator().next().getValueIndex());
                    add(drawDataPanel,BorderLayout.CENTER);
                    siteValueListPanel=new SiteValueListPanel(site);
                    add(siteValueListPanel,BorderLayout.EAST);
                }
            });
    }
        protected int xAxisType=0;
        protected Map<JRadioButton,Integer> radioButtonIDMap;
        protected class SiteValueListPanel extends JPanel{
            //显示站点信息列表的界面
            protected JPanel buttonPanel=new JPanel();
            protected Map<Long,Data> timeToDataMap;
            protected Map<String,Data> contextToDataMap;
            protected List<Long> sortedTime;
            protected JComboBox sitesComboBox=new JComboBox();
            protected Site site;
            protected ButtonGroup buttonGroup=new ButtonGroup();
            protected SiteValueListPanel(Site site){
                this.site=site;
                SiteValueListPanel.this.setLayout(new GridLayout(site.getValuesQuantities()+2,1));
                flush();
            }
            protected void flush(){
                contextToDataMap=new HashMap<>();
                timeToDataMap=site.getTimeToData();
                sortedTime=new LinkedList<>();
                for(Long time0:timeToDataMap.keySet()){
                    sortedTime.add(time0);
                }
                sortedTime.sort(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return (int)(o1-o2);
                }
                });
                add(new PIXLabel(" +根据时间选择数据",pix));
                add(sitesComboBox);
                add(new PIXLabel(" +选择X轴的量",pix));

                timeToDataMap=site.getTimeToData();
                int i=0;
                sitesComboBox.setFont(pix.PIXFont);
                sitesComboBox.setBackground(Color.WHITE);
                for(Long time1:sortedTime){
                    String time=String.valueOf(time1);
                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append(time.substring(0,4)+"/");
                    stringBuilder.append(time.substring(4,6)+"/");
                    stringBuilder.append(time.substring(6,8)+" ");
                    stringBuilder.append(time.substring(8,10)+":");
                    stringBuilder.append(time.substring(10,12)+":");
                    stringBuilder.append(time.substring(12,14));
                    sitesComboBox.insertItemAt(stringBuilder.toString(),i);
                    contextToDataMap.put(stringBuilder.toString(),timeToDataMap.get(time1));
                    i++;
                }
                sitesComboBox.addItemListener(new ItemListener() {
                @Override
                    public void itemStateChanged(ItemEvent e) {
                        Data selectedData=contextToDataMap.get(e.getItem().toString());
                        SiteWindow.this.remove(drawDataPanel);
                        drawDataPanel=new DrawDataPanel(pix.PIXFont,selectedData,IDToTypeMap,selectedData.getValueIndex());
                        SiteWindow.this.add(drawDataPanel,BorderLayout.CENTER);
                        validate();
                    }
                });
            buttonPanel.setLayout(new GridLayout(IDToTypeMap.size(),1));
            radioButtonIDMap=new HashMap<>();
            for(int value:IDToTypeMap.keySet()){
                JRadioButton button=new JRadioButton(IDToTypeMap.get(value));
                buttonGroup.add(button);
                radioButtonIDMap.put(button,value);
                button.setFont(pix.PIXFont);
                buttonPanel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        xAxisType=radioButtonIDMap.get(button);
                        if(drawDataPanel!=null){
                            drawDataPanel.setXAxis(radioButtonIDMap.get(button));
                        }
                    }
                });
            }
            add(buttonPanel);
        }
        protected void setSite(Site site){
            this.site=site;
        }
    }
}
