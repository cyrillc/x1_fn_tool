package ch.cyrillc.x1_fn.app.utils;

import ch.cyrillc.x1_fn.app.model.SmartKeyEntry;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by CyrillC on 01.11.15.
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

    public static void printSmartKeyEntry(SmartKeyEntry entry) {
        System.out.println("Entry ->"+entry.toString());
        System.out.println("AppName= "+entry.getAppName());
        System.out.println("AppPath= "+entry.getAppPath());
        System.out.println("FunctionType= "+entry.getSmartKeyType().name());
    }

    /**
     * Read the version.txt that has been created during the maven build.
     * @return Hashmap with "version" and "build.date" keys
     */
    public synchronized HashMap<String,String> readVersionFile() {
        HashMap<String,String> versionMap= new HashMap<>();
        URI version = null;
        Path versionPath = null;
        Stream<String> versionLines = null;
        try {
            version = getClass().getClassLoader().getResource("version.txt").toURI();
            //check if the path is inside a Jar or in the normal classpath (for use in IDE and in jar)
            versionPath = resourceToPath(version.toURL());
            versionLines = Files.lines(versionPath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
            versionLines.map(s -> s.split("="))
                    .forEach(s -> versionMap.put(s[0],s[1]));
            System.out.println("Split up file information -> yes");
        return versionMap;
    }


    private static Path resourceToPath(URL resource)
            throws IOException,
            URISyntaxException {
        Objects.requireNonNull(resource, "Resource URL cannot be null");
        URI uri = resource.toURI();

        String scheme = uri.getScheme();
        if (scheme.equals("file")) {
            return Paths.get(uri);
        }
        if (!scheme.equals("jar")) {
            throw new IllegalArgumentException("Cannot convert to Path: " + uri);
        }

        String s = uri.toString();
        int separator = s.indexOf("!/");
        //Get actual path of file
        String entryName = s.substring(separator + 2);
        URI fileURI = URI.create(s.substring(0, separator));
        FileSystem fs = FileSystems.newFileSystem(fileURI,
                Collections.<String, Object>emptyMap());
            return fs.getPath(entryName);

    }
}
