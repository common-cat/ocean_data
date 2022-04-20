package wiki.common_cat.mewOceanDataViewer.data;

import java.util.*;

/**
 * @author common-cat
 * @version 1.00
 */
public class Data {
    //某一个时段的数据
    protected double[] rangeMax;
    protected double[] rangeMin;
    protected Integer[] valueIndex;
    protected int valueQuantities;
    //元素类数量
    protected List<double[]> data=new ArrayList<>();
    //原始数据
    protected int dataClass;
    //数据类型
    protected Site site;
    public Data(int dataClass,Integer[] valueIndex,Site site){
        this.site=site;
        this.valueIndex=valueIndex;
        valueQuantities=valueIndex.length;
        this.dataClass=dataClass;
        rangeMax=new double[valueQuantities];
        rangeMin=new double[valueQuantities];
    }
    public void ini(){
        for(int i=0;i<valueQuantities;i++){
            double max=data.get(0)[i],min=data.get(0)[i];
            for(double[] data0:data){
                if(data0[i]>max){
                    max=data0[i];
                }
                if(data0[i]<min){
                    min=data0[i];
                }
            }
            rangeMax[i]=max;
            rangeMin[i]=min;
        }
    }
    public void appendData(double[] lineData){
        data.add(lineData);
    }
    //追加一行数据
    public List<double[]> getData(){
        return data;
    }

    public double[] getRangeMax() {
        return rangeMax;
    }

    public double[] getRangeMin() {
        return rangeMin;
    }
    public Integer[] getValueIndex() {
        return valueIndex;
    }
    public Map<Integer,String> getIDToValue(){
        return site.getIDToValue();
    }
    public Site getSite(){
        return site;
    }
}
