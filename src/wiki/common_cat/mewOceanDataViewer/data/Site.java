package wiki.common_cat.mewOceanDataViewer.data;

import wiki.common_cat.mewOceanDataViewer.panel.MainWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author common-cat
 * @version 1.00
 */
public class Site {
    //抽象的站点
    public static final String COMMON_POSITION_TYPE="COMMON";
    public static final String OSF_POSITION_TYPE="OSF";
    protected Map<Integer,String> IDToValue;
    protected String positionInfo="";
    protected MainWindow mainWindow;
    protected String siteName;
    protected String siteID;
    protected double latitude=-91,longitude=-181;
    //经纬度 单位为度
    protected Map<Long,Data> timeToData=new HashMap<>();
    //从时间到数据的映射
    public Site(String siteID,String siteName,Map<Integer,String> IDToValue){
        this.siteID=siteID;
        this.siteName=siteName;
        this.IDToValue=IDToValue;
    }

    public Map<Integer, String> getIDToValue() {
        return IDToValue;
    }
    public int getValuesQuantities(){
        return getTimeToData().values().iterator().next().valueQuantities;
    }
    public double getLatitude(){
        return latitude;
    }
    //获得纬度 为-1则没有纬度
    public double getLongitude(){
        return longitude;
    }
    //获得经度
    public void addData(long time,Data data){
        timeToData.put(time,data);
    }
    //加入一条数据
    public Data getDataByTime(String time){
        return timeToData.get(time);
    }
    //获取数据
    public void setPosition(String type,String... position){
        if(type==OSF_POSITION_TYPE){
            String p=position[0];
            if(p.charAt(9)=='N'){
                latitude=transferLatitude(p.substring(0,10));
            }else {
                latitude=-transferLatitude(p.substring(0,10));
            }
            if(p.charAt(20)=='E'){
                longitude=transferLongitude(p.substring(10,21));
            }else {
                longitude=-transferLongitude(p.substring(10,21));
            }
        }
    }
    //初始化站点位置
    public void iniIni(){
        for (Data data0:getTimeToData().values()){
            data0.ini();
        }
    }
    protected double transferLatitude(String position){
        int degree=Integer.parseInt(position.substring(0,2).replaceAll(" ",""));
        int min=Integer.parseInt(position.substring(2,4).replaceAll(" ",""));
        double sec=Double.parseDouble(position.substring(4,9).replaceAll(" ",""));
        positionInfo+=degree+"°"+min+"′"+sec+"″";
        positionInfo+=position.charAt(9)+" ";
        return (degree+min/60+sec/3600);
    }
    protected double transferLongitude(String position){
        int degree=Integer.parseInt(position.substring(0,3).replaceAll(" ",""));
        int min=Integer.parseInt(position.substring(3,5).replaceAll(" ",""));
        double sec=Double.parseDouble(position.substring(5,10).replaceAll(" ",""));
        positionInfo+=degree+"°"+min+"′"+sec+"″";
        positionInfo+=position.charAt(10)+" ";
        return (degree+min/60+sec/3600);
    }
    //将°转化为浮点
    public void appendData(long time,Data data){
        timeToData.put(time,data);
    }
    //追加数据
    public Map<Long,Data> getTimeToData(){
        return timeToData;
    }
    public void clicked(){
        mainWindow.createNewSiteWindow(this);
    }
    //TODO 被点击以后执行
    public void addWindow(MainWindow mainWindow){
        this.mainWindow=mainWindow;
    }
    //连接到主窗体

    public String getSiteName() {
        return siteName;
    }

    public String getSiteID() {
        return siteID;

    }
    public void setMainWindow(MainWindow mainWindow){
        this.mainWindow=mainWindow;
    }
    public String getPosition(){
        return positionInfo;
    }
    //获得位置
    public String toString(){
        return " 站点名:"+siteName+"站点ID:"+siteID;
    }
}
