package ch.cyrillc.x1_fn.app.controller;

import ch.cyrillc.x1_fn.app.App;
import ch.cyrillc.x1_fn.app.model.ESmartKeyType;
import ch.cyrillc.x1_fn.app.model.FnModel;
import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.utils.TextUserInterfaceEvent;
import ch.cyrillc.x1_fn.app.utils.TextUserInterfaceEventListener;
import ch.cyrillc.x1_fn.app.view.TextUserInterface;
import com.github.sarxos.winreg.RegistryException;

/**
 * Created by Fabian on 01.11.15.
 */
public class FnController implements TextUserInterfaceEventListener {

    App app;
    TextUserInterface tui;
    FnRegistry reg;
    FnModel model;

    public FnController(App app) throws RegistryException {
        this.app = app;
        reg = new FnRegistry();
        model = new FnModel();
        tui = new TextUserInterface(app.getVersion(),app.getBuildTimestamp(),this);
        model.addObserver(tui);
        model.updateModel();
        tui.start();

    }

    @Override
    public void handleEvent(TextUserInterfaceEvent event) {
//            System.out.println("Controller called with add event & "+event.getCmd().name()+" input "+event.getOptions());
        switch (event.getCmd()){
            case LIST: {
                ESmartKeyType type = ESmartKeyType.getEnum(event.getOptions());
                if(type == ESmartKeyType.UNKNOWN){
                    tui.print(model.getAllEntries());
                } else {
                    tui.print(model.getEntriesFor(type));
                }
            }
        }

    }




}
