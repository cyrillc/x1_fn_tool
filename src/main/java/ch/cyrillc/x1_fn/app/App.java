package ch.cyrillc.x1_fn.app;


import ch.cyrillc.x1_fn.app.controller.FnController;
import ch.cyrillc.x1_fn.app.model.ESmartKeyType;
import ch.cyrillc.x1_fn.app.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.app.utils.Util;
import com.github.sarxos.winreg.RegistryException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        FnController controller = new FnController();
        controller.runLoop();
        System.out.println("TEST END -------------------------------------------");
        System.out.println( "Build by Cyrillc" );

        FnRegistry fnreg = new FnRegistry();

        try {


            Util.printEntriesOnConsole(fnreg.getEntrysIn(ESmartKeyType.FUNCTION));
            System.out.println("\n################################\nAnd here is a list of all Apps:");
            Util.printEntriesOnConsole(fnreg.getAllEntries());



        }catch (RegistryException e) {
            System.out.println("Exception with the Registry"+ e.getMessage());
        }

    }

}
