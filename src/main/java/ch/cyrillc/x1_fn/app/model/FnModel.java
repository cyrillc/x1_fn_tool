package ch.cyrillc.x1_fn.app.model;

import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.utils.Util;
import com.github.sarxos.winreg.RegistryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * Created by CyrillC on 01.11.15.
 */
public class FnModel extends Observable{

    FnRegistry reg;
    ArrayList<SmartKeyEntry> entries;

    public FnModel() throws RegistryException{
        reg = new FnRegistry();
    }

    /**
     * Read in the registry and store it.
     * @throws RegistryException
     */
    public void updateModel() throws RegistryException {
        entries = reg.getAllEntries();
    }


    /**
     * Removes an entry from the Registry.
     * Entry can be filled with only the appname. It will be matched
     * in lower case for any real matches in the model.
     * CAUTION: CALL appNameExists() before to ensure the appname is real
     * @param entry full entry or only filled with appName
     * @throws RegistryException either if registry has errors or entry is null
     */
    public void removeEntry(SmartKeyEntry entry) throws RegistryException{
        if(!entryExists(entry)){
            entry = getRealEntryFromAppName(entry.getAppName());
            if(entry == null) {
                throw new RegistryException();
            }
        }
        reg.removeSmartKeyEntry(entry);
        updateModel();
        setChanged();
        notifyObservers();
    }

    private SmartKeyEntry getRealEntryFromAppName(String appName) {
        for(SmartKeyEntry entry : entries){
            if(entry.getAppName().toLowerCase().equals(appName.toLowerCase())) {
                return entry;
            }
        }
        return null;
    }

    private boolean entryExists(SmartKeyEntry entry) {
        for (SmartKeyEntry smartKeyEntry : entries) {
            if (smartKeyEntry.getAppName().equals(entry.getAppName())) {
                if(smartKeyEntry.getSmartKeyType().equals(entry.getSmartKeyType())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add SmartKey entry to the Model and the registry.
     * Ensure that the Entry is complete!!
     * TODO: check if entry is complete in method
     * @param entry a fully filled Entry!!
     * @throws RegistryException
     */
    public void addEntry(SmartKeyEntry entry) throws RegistryException{
        if(!entryExists(entry)){
            this.entries.add(entry);
            reg.writeSmartKeyEntry(entry);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Move entry to a new Location in the Registry (Change its SmartKeyType)
     * @param entry
     * @param targetType Which keyboard should be shown
     */
    public void MoveEntry(SmartKeyEntry entry, ESmartKeyType targetType){
        //TODO: Implement
        setChanged();
        notifyObservers();
    }

    /**
     * @return all Entries currently in the Registry
     */
    public ArrayList<SmartKeyEntry> getAllEntries(){
        return entries;
    }

    /**
     * All entries for a certain SmartKeyType
     * @param type
     * @return
     */
    public List<SmartKeyEntry> getEntriesFor(ESmartKeyType type){
        if(type.equals(ESmartKeyType.UNKNOWN)) {
            return getAllEntries();
        } else {
            //Awesome Lambda expression
            return entries.stream().filter(e -> e.getSmartKeyType().equals(type)).collect(Collectors.toList());
        }
    }

    /**
     * Check if an appName does exist in the current registry (is beeing checkd on lowerCase)
     * @param appName
     * @return
     */
    public boolean appNameExists(String appName) {
        return entries.stream().anyMatch(e -> e.getAppName().toLowerCase().equals(appName.toLowerCase()));
    }
}
