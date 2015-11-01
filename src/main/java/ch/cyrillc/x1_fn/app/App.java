package ch.cyrillc.x1_fn.app;
import ch.cyrillc.x1_fn.app.controller.FnController;
import ch.cyrillc.x1_fn.app.utils.Util;

import java.util.HashMap;

/**
 * created by CyrillC on 31.10.15
 */
public class App 
{
    HashMap<String, String> versionMap;
    long AppStartTime;
    FnController controller;

    public static void main( String[] args ) {

        App app = new App();
    }

    public App(){
        this.AppStartTime = System.currentTimeMillis();
            this.versionMap = (new Util()).readVersionFile();

        try {
            controller = new FnController(this);
        } catch (Exception e) {
            System.out.println("Error in the Application. Sorry ;) ->"+ e.getMessage());
        }
    }


    public String getBuildTimestamp() {
        return versionMap.get("build.date");
    }
    public String getVersion() {
        return versionMap.get("version");
    }
}
