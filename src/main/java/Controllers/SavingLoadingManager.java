package Controllers;

import LinkedList.StoreList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class SavingLoadingManager {

    private static final String FILE_NAME = "store-data.xml";

    private static XStream getXStream() {
        XStream xs = new XStream(new StaxDriver());
        XStream.setupDefaultSecurity(xs);
        xs.allowTypesByWildcard(new String[]{"Objects.**", "LinkedList.**"});
        return xs;
    }

    public static void save(StoreList<?>... lists) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            getXStream().toXML(lists, fw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StoreList<?>[] load() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return null;

        try (FileReader fr = new FileReader(f)) {
            return (StoreList<?>[]) getXStream().fromXML(fr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
