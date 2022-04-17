package wiki.common_cat.mewOceanDataViewer.fileReader;

import java.io.FileReader;
import java.util.Locale;

public class FileReaderFactory {
    public static final String OSF_FILES="OSF";
    private FileReaderFactory(){

    }
    public static FileReaderFactory newInstance(){
        return new FileReaderFactory();
    }
    public AbstractFileReader getFileReader(Iterable<String> paths){
        String[] types=paths.iterator().next().split("/");
        String type=types[types.length-1].toUpperCase(Locale.ROOT).split("\\.")[1];
        switch (type){
            case OSF_FILES:
                return getOSFFileReader();
        }
        return null;
    }
    protected AbstractFileReader getOSFFileReader(){
        AbstractFileReader reader=null;
        return new OSFFileReader();
    }
}
