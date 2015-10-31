package ch.cyrillc.x1_fn.app;


import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.RegistryException;
import com.github.sarxos.winreg.WindowsRegistry;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        try {
            WindowsRegistry reg = WindowsRegistry.getInstance();
            String tree = "SOFTWARE\\Lenovo\\SmartKey\\Application\\Row\\Function\\IntelliJ";
            String value = reg.readString(HKey.HKCU, tree, "AppPath");
            System.out.println("App path tho IntelliJ= " + value);

            String functionTree = "SOFTWARE\\Lenovo\\SmartKey\\Application\\Row\\Function";
            String newKeyWithTree = functionTree+"\\testapp";
            reg.createKey(HKey.HKCU,newKeyWithTree);
            reg.writeStringValue(HKey.HKCU,newKeyWithTree,"AppPath","Some weird path =)");

            List<String> appApps = reg.readStringSubKeys(HKey.HKCU,functionTree);
            System.out.println("All Apps with Function keyboard:");
            for (String appname : appApps) {
                System.out.println(appname  );
            }

            reg.deleteKey(HKey.HKCU,newKeyWithTree);
/*            List<String> keys = reg.readStringSubKeys(HKey.HKLM, branch);
            for (String key : keys) {
                System.out.println(key);
            }*/




        }catch (RegistryException e) {
            System.out.println("Exception with the Registry"+ e.getMessage());
        }

    }
}
