package wiki.common_cat.mewOceanDataViewer.fileReader;

import wiki.common_cat.mewOceanDataViewer.data.Site;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.interfaces.DSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractFileReader{
    public abstract Map<String, Site> getData(Iterable<String> paths) throws FileNotFoundException;
}
