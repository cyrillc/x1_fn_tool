package ch.cyrillc.x1_fn.app.utils;

import ch.cyrillc.x1_fn.app.model.SmartKeyEntry;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Created by Fabian on 01.11.15.
 */
public class Util {
    public static void printEntriesOnConsole(List<SmartKeyEntry> smartKeyEntries) {
        if(smartKeyEntries.size()>=1) {
            System.out.println("App Name"+ cellDistance("app name", 4)+"Type"+ cellDistance("type", 3)+"AppPath");
            for (SmartKeyEntry entry : smartKeyEntries) {
                System.out.println(entry.getAppName() + cellDistance(entry.getAppName(), 4) + entry.getSmartKeyTypeAsString() + cellDistance(entry.getSmartKeyTypeAsString(), 3) +entry.getAppPath());
            }
        }
    }

    public static String cellDistance(String word, int amountOfTabs) {
        return cellDistance(word.length(),amountOfTabs);
    }

    /**
     * Distance between two words for console output.
     * Amount of tabs is beeing muliplied by 6!
     * @param length
     * @param amountOfTabs
     * @return
     */
    public static String cellDistance(int length, int amountOfTabs) {
        String returnString = "";
        for (int w = 0; w < amountOfTabs*6-length; w++) {
            returnString+= " ";
        }
        return returnString;
    }

    public synchronized HashMap<String,String> readVersionFile() throws Exception{
        HashMap<String,String> versionMap= new HashMap<>();
        try {
            Stream<String> versionLines = Files.lines(Paths.get(this.getClass().getResource("/version.txt").toURI()));
            versionLines.map(s -> s.split("="))
                    .forEach(s -> versionMap.put(s[0],s[1]));

        } catch (URISyntaxException | IOException e) {
            throw new Exception("Error while opening version.txt");
        }
        return versionMap;
    }
}
