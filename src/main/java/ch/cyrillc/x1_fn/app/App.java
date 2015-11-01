package ch.cyrillc.x1_fn.app;


import ch.cyrillc.x1_fn.app.controller.FnController;
import ch.cyrillc.x1_fn.app.model.ESmartKeyType;
import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.utils.Util;
import com.github.sarxos.winreg.RegistryException;

import java.util.HashMap;

/**
 * Hello world!
 *
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
        try {
            this.versionMap = (new Util()).readVersionFile();
        } catch (Exception e) {
            System.out.println("Error while starting the application. Reason:");
            System.out.println(e.getMessage());
        }

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
