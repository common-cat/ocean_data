package wiki.common_cat.mewOceanDataViewer.fileReader;

import java.util.HashMap;
import java.util.Map;

public interface OSF_POSITION {
    int FLAG_BEGIN=0,FLAG_END=1;
    int SITE_NAME_BEGIN=24,SITE_NAME_END=30;
    int SITE_TIME_BEGIN=64,SITE_TIME_END=78;
    int SITE_ID_BEGIN=1,SITE_ID_END=11;
    int SITE_POSITION_BEGIN=20,SITE_POSITION_END=41;

    int TEMP_BEGIN=14,TEMP_END=21;
    int DEPTH_BEGIN=2,DEPTH_END=10;
    int SALT_BEGIN=26,SALT_END=33;

    int SITE=2;
    int SAILING=1;
    int DATA=7;

    Map<Integer,String> OSF_ID_TO_NAMES=new HashMap<>(){
        {
            put(0,"DEPTH(M)");
            put(1,"TEMPERATURE(C°)");
            put(2,"SALINITY(%)");
        }
    };
}
