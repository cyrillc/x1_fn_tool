package ch.cyrillc.x1_fn.app.model;

import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.utils.Util;
import com.github.sarxos.winreg.RegistryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * Created by Fabian on 01.11.15.
 */
public class FnModel extends Observable{

    FnRegistry reg;

    ArrayList<SmartKeyEntry> entries;

    public FnModel() throws RegistryException{
        reg = new FnRegistry();
    }

    public void updateModel() throws RegistryException {
        entries = reg.getAllEntries();
        setChanged();
        notifyObservers();
    }

    public void removeEntry(SmartKeyEntry entry) throws RegistryException{
        if(entryExists(entry)){
            this.entries.add(entry);
            reg.removeSmartKeyEntry(entry);
            setChanged();
            notifyObservers();
        }
    }

    private boolean entryExists(SmartKeyEntry entry) {
        for (SmartKeyEntry smartKeyEntry : entries) {
            if (smartKeyEntry.getAppName().equals(entry.getAppName())) {
                return true;
            }
        }
        return false;
    }

    public void addEntry(SmartKeyEntry entry) throws RegistryException{
        if(!entryExists(entry)){
            this.entries.add(entry);
            reg.writeSmartKeyEntry(entry);
            setChanged();
            notifyObservers();
        }
    }

    public void MoveEntry(SmartKeyEntry entry, ESmartKeyType targetType){
        //TODO: Implement
        setChanged();
        notifyObservers();
    }

    public ArrayList<SmartKeyEntry> getAllEntries(){
        return entries;
    }

    public List<SmartKeyEntry> getEntriesFor(ESmartKeyType type){
        if(type.equals(ESmartKeyType.UNKNOWN)) {
            return getAllEntries();
        } else {
            return entries.stream().filter(e -> e.getSmartKeyType().equals(type)).collect(Collectors.toList());
        }
    }
}
