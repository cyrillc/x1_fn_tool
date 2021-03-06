package ch.cyrillc.x1_fn.app.registryHandler;

import ch.cyrillc.x1_fn.app.model.ESmartKeyType;
import ch.cyrillc.x1_fn.app.model.SmartKeyEntry;
import ch.cyrillc.x1_fn.app.utils.Constants;
import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CyrillC on 31.10.15.
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


    /**
     * Reads all Registry Entries for the Lenovo SmartKey Tree
     * @return ArrayList of SmartKeyEntries in all Types of SmartKeys
     * @throws RegistryException
     */
    public ArrayList<SmartKeyEntry> getAllEntries() throws RegistryException {
        ArrayList<SmartKeyEntry> smartKeyEntries = new ArrayList<SmartKeyEntry>();
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.HOME));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.FUNCTION));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.WEBBROWSER));
        smartKeyEntries.addAll(this.getEntrysIn(ESmartKeyType.WEBCONFERENCE));
        return smartKeyEntries;
    }


    public void writeSmartKeyEntry(SmartKeyEntry entry) throws RegistryException{
        String keyTree = getTreeFor(entry.getSmartKeyType())+"\\"+entry.getAppName();
        registry.createKey(HKey.HKCU,keyTree);
        registry.writeStringValue(HKey.HKCU,keyTree,Constants.SMARTKEY_APPATH_NAME,entry.getAppPath());
    }

    public void removeSmartKeyEntry(SmartKeyEntry entry) throws RegistryException{
        String keyTree = getTreeFor(entry.getSmartKeyType())+"\\"+entry.getAppName();
        registry.deleteKey(HKey.HKCU,keyTree);
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

}

