package ch.cyrillc.x1_fn.app.utils;

import java.util.EventObject;

/**
 * Created by Fabian on 01.11.15.
 */
public class TextUserInterfaceEvent extends EventObject{

    private ETextCommand cmd;
    private String options;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TextUserInterfaceEvent(Object source) {
        super(source);
    }

    /**
     *
     * @return ETextCommand that the event was triggered with
     */
    public ETextCommand getCmd(){
        return this.cmd;
    }

    /**
     * Single or multiple options Strings divided by a Space. can also be ""
     * @return Option string that was optioned with ETextCommand
     *
     */
    public String getOptions(){
        return this.options;
    }

    /**
     *
     * @param options Single or multiple options Strings divided by a Space. can also be ""
     */
    public void setOptions(String options) {
        if(options == null) {
            options = "";
        }
        this.options = options;
    }

    /**
     *
     * @param cmd ETextCommand . can also be UNKNOWN
     */
    public void setCmd(ETextCommand cmd) {
        this.cmd = cmd;
    }
}
