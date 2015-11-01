package ch.cyrillc.x1_fn.app.model;

/**
 * Created by CyrillC on 31.10.15.
 */
public enum ESmartKeyType {
    FUNCTION,HOME,WEBBROWSER, NONE, WEBCONFERENCE, UNKNOWN;

    public static ESmartKeyType getEnum(String textType) {
        textType = textType.replace(" ","");
            for (ESmartKeyType typ : ESmartKeyType.values()){
                if(typ.name().toLowerCase().equals(textType.toLowerCase())){
                    return typ;
                }
            }
        return ESmartKeyType.UNKNOWN;
    }
}
