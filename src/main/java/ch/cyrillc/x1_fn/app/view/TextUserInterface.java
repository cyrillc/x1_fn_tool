package ch.cyrillc.x1_fn.app.view;

import ch.cyrillc.x1_fn.app.utils.Util;

/**
 * Created by Fabian on 01.11.15.
 */
public class TextUserInterface {

    public void printHeader(){
        System.out.println("########################");
        System.out.println("########################");
        System.out.println("## X1_fn tool" );
        System.out.println("## Build: "+ (new Util()).getVersion());
//        System.out.println("## From: "+ Util.getTimeStamp());
        System.out.println("## by CyrillC ");
        System.out.println("## follow me on Twitter @cyrillcamenzind ");
        System.out.println("########################");
        System.out.println("########################");

    }



}
