package ch.cyrillc.x1_fn.registryHandler;

import ch.cyrillc.x1_fn.model.ESmartKeyType;
import ch.cyrillc.x1_fn.model.SmartKeyEntry;
import ch.cyrillc.x1_fn.utils.Constants;
import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 31.10.15.
 */
public class FnRegistry {

    WindowsRegistry registry;

    public FnRegistry(){
        registry = WindowsRegistry.getInstance();
    }

    /**
     * Returns all Entries from a given Type as SmartKeyentrys
     * @param smartKeyType
     * @return ArrayList of SmartKeyEntries in the given Type
     * @throws RegistryException
     */
    public ArrayList<SmartKeyEntry> getEntrysIn(ESmartKeyType smartKeyType) throws RegistryException{
        ArrayList<SmartKeyEntry> smartKeyEntries = new ArrayList<SmartKeyEntry>();
        String tree = getTreeFor(smartKeyType);
        List<String> appNameList = registry.readStringSubKeys(HKey.HKCU, tree);

        for (String appName : appNameList) {
            String appPath = registry.readString(HKey.HKCU,tree+"\\"+appName, Constants.SMARTKEY_APPATH_NAME);
            smartKeyEntries.add(new SmartKeyEntry(appName,appPath,smartKeyType));
        }
        return smartKeyEntries;
    }

    public ArrayList<SmartKeyEntry> getAllEntries() throws RegistryException {
        ArrayList<SmartKeyEntry> smartKeyEntries = new ArrayList<SmartKeyEntry>();
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.HOME));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.FUNCTION));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.WEBBROWSER));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.WEBCONFERENCE));
        return smartKeyEntries;
    }

    private SmartKeyEntry createSmartKeyFromEntry(String tree){
        SmartKeyEntry entry = null;

        //TODO: Implement do I need it though?

        return entry;
    }

    private String getTreeFor(ESmartKeyType smartKeyType) {
        switch (smartKeyType){
            case FUNCTION: return Constants.SMARTKEY_TREE_FUNCTION;
            case HOME: return Constants.SMARTKEY_TREE_HOME;
            case WEBBROWSER: return Constants.SMARTKEY_TREE_WEBBROWSER;
            case WEBCONFERENCE: return Constants.SMARTKEY_TREE_WEBCONFERENCE;
            default: return Constants.SMARTKEY_TREE_HOME;
        }
    }
//    WindowsRegistry reg = WindowsRegistry.getInstance();
//    String tree = "SOFTWARE\\Lenovo\\SmartKey\\Application\\Row\\Function\\IntelliJ";
//    String value = reg.readString(HKey.HKCU, tree, "AppPath");
//    System.out.println("App path tho IntelliJ= " + value);
//
//    String functionTree = "SOFTWARE\\Lenovo\\SmartKey\\Application\\Row\\Function";
//    String newKeyWithTree = functionTree+"\\testapp";
//    reg.createKey(HKey.HKCU,newKeyWithTree);
//    reg.writeStringValue(HKey.HKCU,newKeyWithTree,"AppPath","Some weird path =)");
//
//    List<String> appApps = reg.readStringSubKeys(HKey.HKCU,functionTree);
//    System.out.println("All Apps with Function keyboard:");
//    for (String appname : appApps) {
//        System.out.println(appname  );
//    }
//
//    reg.deleteKey(HKey.HKCU,newKeyWithTree);

}

