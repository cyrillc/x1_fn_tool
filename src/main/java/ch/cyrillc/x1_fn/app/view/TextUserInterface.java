package ch.cyrillc.x1_fn.app.view;

import ch.cyrillc.x1_fn.app.controller.FnController;
import ch.cyrillc.x1_fn.app.model.SmartKeyEntry;
import ch.cyrillc.x1_fn.app.utils.ETextCommand;
import ch.cyrillc.x1_fn.app.utils.TextUserInterfaceEvent;
import ch.cyrillc.x1_fn.app.utils.TextUserInterfaceEventListener;
import ch.cyrillc.x1_fn.app.utils.Util;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by CyrillC on 01.11.15.
 */
public class TextUserInterface implements Observer{

    private Scanner scanner;
    private String version;
    private String buildTimestamp;
    private TextUserInterfaceEventListener listener;

    /**
     * TUI Constructor
     * @param version current version of the Build
     * @param buildtimestamp
     * @param fnController needed as the eventListener
     */
    public TextUserInterface(String version, String buildtimestamp, FnController fnController) {
        scanner = new Scanner(System.in);
        this.version = version;
        this.buildTimestamp = buildtimestamp;
        listener = fnController;
        printHeader();
    }

    /**
     * Starts the TUI Loop to work with the Application
     */
    public void start(){
        printMenu();
        loop();
        printGoodBye();
    }


    private void loop() {
        boolean close = false;
        while(!close){
            String answer = scanner.nextLine();
            close = handleInput(close, answer);
        }

    }

    /**
     *
     * Handles the given Answer and creates ETextcommands if possible.
     * Returns the boolean close (to see if the Program is finished or not)
     *
     */
    private boolean handleInput(boolean close, String answer) {
        String cmd = answer.split(" ")[0];
        ETextCommand command = ETextCommand.translateCommand(cmd);
        //+1 Needed to remove the space in front
        String input = (answer.contains(" ")?answer.substring(answer.indexOf(" ")+1):"");

        if(command == ETextCommand.QUIT){
            close = true;
        } else if(command == ETextCommand.HELP) {
            printMenu();
        } else if(command == ETextCommand.UNKNOWN){
            System.out.println("Command not found");
            System.out.println("Enter h for Help" );
        } else {
            listener.handleEvent(getTextUserInterfaceEvent(command, input));
        }
        return close;
    }

    private TextUserInterfaceEvent getTextUserInterfaceEvent(ETextCommand command, String input) {
        TextUserInterfaceEvent event = new TextUserInterfaceEvent(this);
        event.setCmd(command);
        event.setOptions(input);
        return event;
    }

    private void printHeader(){
        System.out.println("########################");
        System.out.println("########################");
        System.out.println("## X1_fn tool" );
        System.out.println("## Build: " + version);
        System.out.println("## From: "+ buildTimestamp);
        System.out.println("## by CyrillC ");
        System.out.println("## follow me on Twitter @cyrillcamenzind ");
        System.out.println("########################");
        System.out.println("########################");

    }


    private void printMenu() {
        System.out.println("list"+Util.cellDistance(4,6)+"List all Settings for Lenovo Keyboard");
        System.out.println("add <APPNAME> <TYPE> <APPPATH>"+Util.cellDistance("add <APPNAME> <TYPE> <APPPATH>",6)+"Add Application");
        System.out.println("del <APPNAME>"+Util.cellDistance(13,6)+"Remove Application assignment (keyboard goes back to default)");
        System.out.println("change <APPNAME> <newFunctionSet>"+Util.cellDistance("change <APPNAME> <newFunctionSet>",6)+"Change Application assignment");
        System.out.println("quit"+Util.cellDistance(4,6)+"Close Application");
    }

    private void printGoodBye() {
        System.out.println("\n\nThanks for using X1_FN "+version+"\nKeep up to date, check my github @cyrillc");
    }
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Registry has been updated.");
    }

    public void print(List<SmartKeyEntry> entries) {
        Util.printEntriesOnConsole(entries);
    }

    public void illegalInput(String message) {
       System.out.println(">Illegal Input> "+message);
    }

    public void error(String errorMessage) {
        System.out.println(">ERROR> "+errorMessage);
    }
}
