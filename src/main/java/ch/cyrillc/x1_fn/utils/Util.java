package ch.cyrillc.x1_fn.utils;

import ch.cyrillc.x1_fn.model.SmartKeyEntry;

import java.util.ArrayList;

/**
 * Created by Fabian on 01.11.15.
 */
public class Util {
    public static void printEntriesOnConsole(ArrayList<SmartKeyEntry> smartKeyEntries) {
        if(smartKeyEntries.size()>=1) {
            System.out.println("App Name"+ cellDistance("app name", 4)+"Type"+ cellDistance("type", 3)+"AppPath");
            for (SmartKeyEntry entry : smartKeyEntries) {
                System.out.println(entry.getAppName() + cellDistance(entry.getAppName(), 4) + entry.getSmartKeyTypeAsString() + cellDistance(entry.getSmartKeyTypeAsString(), 3) +entry.getAppPath());
            }
        }
    }

    private static String cellDistance(String word, int amountOfTabs) {
        int length = word.length();
        String returnString = "";
        for (int w = 0; w < amountOfTabs*6-length; w++) {
            returnString+= " ";
        }
        return returnString;
    }
}
