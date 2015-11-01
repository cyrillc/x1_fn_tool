package ch.cyrillc.x1_fn.app.controller;

import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.view.TextUserInterface;

/**
 * Created by Fabian on 01.11.15.
 */
public class FnController {

    TextUserInterface tui;
    FnRegistry reg;

    public FnController(){
        reg = new FnRegistry();
        tui = new TextUserInterface();
    }

    public void runLoop(){
        tui.printHeader();
    }



}
