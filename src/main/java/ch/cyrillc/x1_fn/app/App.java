package ch.cyrillc.x1_fn.app;


import ch.cyrillc.x1_fn.model.ESmartKeyType;
import ch.cyrillc.x1_fn.registryHandler.FnRegistry;
import ch.cyrillc.x1_fn.utils.Util;
import com.github.sarxos.winreg.RegistryException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
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
