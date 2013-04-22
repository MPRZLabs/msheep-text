package pl.ijestfajnie.msheep_text;

import java.util.ResourceBundle;

public class Translator {
    
    private ResourceBundle base = ResourceBundle.getBundle("pl/ijestfajnie/msheep_text/EngineBasics");
    
    public String getBasicString(String key) {
        return base.getString(key);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private Translator() {
    }
    
    public static Translator getInstance() {
        return TranslatorHolder.INSTANCE;
    }
    
    private static class TranslatorHolder {
        
        private static final Translator INSTANCE = new Translator();
    }
    //</editor-fold>
}
