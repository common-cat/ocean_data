package wiki.common_cat.mewOceanDataViewer.fileReader;

import wiki.common_cat.mewOceanDataViewer.data.Data;
import wiki.common_cat.mewOceanDataViewer.data.Site;

import java.io.*;
import java.util.*;

public class OSFFileReader extends AbstractFileReader implements OSF_POSITION{
    @Override
    public Map<String, Site> getData(Iterable<String> paths) throws FileNotFoundException {
        Map<String,Site> stringSiteMap=new HashMap<>();
        Scanner scanner;
        String nowSiteID="";
        Site nowSite = null;
        Long nowTime=0l;
        String siteName="";
        boolean isOld=false;
        for(String path:paths){
            Long v=Long.parseLong(path.substring(path.length()-10,path.length()-4));
            if(v<200000){
                isOld=true;
            }else {
                isOld=false;
            }
            scanner=new Scanner(new BufferedInputStream(new FileInputStream(path)));
            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                String flag = dataLine.substring(FLAG_BEGIN, FLAG_END);
                switch (Integer.parseInt(flag)) {
                    case SITE:
                        String siteID = dataLine.substring(SITE_ID_BEGIN, SITE_ID_END);
                        if (!stringSiteMap.containsKey(siteID)) {
                            nowSiteID = siteID;
                            nowSite = new Site(siteID,siteName,OSF_ID_TO_NAMES);
                            nowSite.setPosition(Site.OSF_POSITION_TYPE, dataLine.substring(SITE_POSITION_BEGIN, SITE_POSITION_END));
                            if(isOld){
                                stringSiteMap.put(dataLine.substring(SITE_POSITION_BEGIN, SITE_POSITION_END),nowSite);
                                nowSiteID=dataLine.substring(SITE_POSITION_BEGIN, SITE_POSITION_END);
                            }else {
                                stringSiteMap.put(nowSiteID, nowSite);
                            }
                        }
                        nowTime = Long.parseLong(dataLine.substring(SITE_TIME_BEGIN, SITE_TIME_END).replaceAll(" ",""));
                        break;
                    case DATA:
                        List<Integer> valuesIndex=new LinkedList<>();
                        double temp=-1;
                        double depth=-1;
                        double salinity=-1;
                        try{
                            depth = Double.parseDouble(dataLine.substring(DEPTH_BEGIN, DEPTH_END).replaceAll(" ",""));
                            valuesIndex.add(0);
                        }catch (Exception e){
                        }
                        try {
                            temp=Double.parseDouble(dataLine.substring(TEMP_BEGIN, TEMP_END).replaceAll(" ",""));
                            valuesIndex.add(1);
                        }catch (Exception e){

                        }
                        try {
                            salinity = Double.parseDouble(dataLine.substring(SALT_BEGIN, SALT_END).replaceAll(" ",""));
                            valuesIndex.add(2);
                        }catch (Exception e){

                        }
                        double[] data1=new double[]{depth, temp, salinity};
                        if (nowSite.getTimeToData().containsKey(nowTime)) {
                            nowSite.getTimeToData().get(nowTime).appendData(data1);
                        } else {
                            Data data=new Data(DATA,valuesIndex.toArray(new Integer[valuesIndex.size()]),nowSite);
                            data.appendData(data1);
                            nowSite.getTimeToData().put(nowTime,data);
                        }
                        break;
                    case SAILING:
                        //siteName = dataLine.substring(SITE_NAME_BEGIN, SITE_NAME_END);
                        siteName="NO_NAME";
                }
            }
        }
        for(Site site:stringSiteMap.values()){
            site.iniIni();
        }
        return stringSiteMap;
    }
}