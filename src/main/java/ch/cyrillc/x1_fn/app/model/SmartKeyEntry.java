package ch.cyrillc.x1_fn.app.model;

import ch.cyrillc.x1_fn.app.utils.Constants;

/**
 * Created by Fabian on 31.10.15.
 */
public class SmartKeyEntry {

    private String appName;
    private String appPath;
    private ESmartKeyType smartKeyType;

    //Multiple Cascading Constructors

    /**
     * Create new empty SmartkeyEntry
     * AppName = ""
     * AppPath = ""
     * SmartKeyType = NONE
     */
    public SmartKeyEntry() {
        this("");
    }

    /**
     * Create new SmartkeyEntry with AppName
     * AppPath = ""
     * SmartKeyType = NONE
     */
    public SmartKeyEntry(String appName) {
       this(appName,"");
    }

    /**
     * Create Smartkeyentry with AppName & path
     * SmartKeyType is set to NONE
     * @param appName
     * @param appPath
     */
    public SmartKeyEntry(String appName, String appPath) {
       this(appName,appPath,ESmartKeyType.NONE);
    }

    /**
     * Create a fully initialized SmartKeyEntry
     * @param appName
     * @param appPath
     * @param smartKeyType
     */
    public SmartKeyEntry(String appName, String appPath, ESmartKeyType smartKeyType) {
        this.appName = appName;
        this.appPath = appPath;
        this.smartKeyType = smartKeyType;
    }

    // GETTER & SETTER

    public ESmartKeyType getSmartKeyType() {
        return smartKeyType;
    }

    public String getSmartKeyTypeAsString() {
        switch(this.smartKeyType){
            case FUNCTION: {
                return Constants.SMARTKEY_FUNCTION_NAME;
            }
            case HOME: {
                return Constants.SMARTKEY_HOME_NAME;
            }
            case WEBBROWSER: {
                return Constants.SMARTKEY_WEBBROWSER_NAME;
            }
            case WEBCONFERENCE: {
                return Constants.SMARTKEY_WEBCONFERENCE_NAME;
            }
            case NONE: {
                return "no Function keyset set";
            }
            default: {
                return "no Function keyset set";
            }
        }
    }

    public void setSmartKeyType(ESmartKeyType smartKeyType) {
        this.smartKeyType = smartKeyType;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
