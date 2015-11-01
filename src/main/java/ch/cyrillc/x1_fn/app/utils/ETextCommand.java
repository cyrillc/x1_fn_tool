package ch.cyrillc.x1_fn.app.utils;

import java.util.ArrayList;

/**
 * Created by Fabian on 01.11.15.
 */
public enum ETextCommand {
    HELP("h","menu"),QUIT("q","exit"),LIST("l"),ADD("a","create"),CHANGE("c"),DELETE("d","del"), UNKNOWN;

    private ArrayList<String> aliases;

    ETextCommand(String... validEntries){

        this.aliases = new ArrayList<>();
        for(String entry : validEntries){
            aliases.add(entry);
        }
        this.aliases.add(this.name().toLowerCase());

    }

    private boolean containsCmd(String cmd){
        return aliases.contains(cmd);
    }

    /**
     * Translates the given command into ETextCommand Enum
     * Every Enum can have multiple matching elements
     * i.E: HELP -> help / h / menu
     * @param cmd that matches (or not) a cmdAlias for Enum ETextCommand
     * @return ETextCommand that belongs to cmd or UNKNOWN if no alias matches
     */
    public static ETextCommand translateCommand(String cmd) {
        for (ETextCommand command : ETextCommand.values()){
            if(command.containsCmd(cmd)){
                return command;
            }
        }
        return ETextCommand.UNKNOWN;
    }
}
