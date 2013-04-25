package pl.ijestfajnie.msheeptext;

import java.util.List;
import java.util.ResourceBundle;

public class Translator {
    
    private List<ResourceBundle> bundles;
    
    public String getString(String key) {
        while (bundles.iterator().hasNext()) {
            ResourceBundle current = bundles.iterator().next();
            if (current.containsKey(key)) {
                return current.getString(key);
            }
        }
        return null;
    }
    
    public void addBundle(ResourceBundle bundle) {
        bundles.add(bundle);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private Translator() {
        addBundle(ResourceBundle.getBundle("pl/ijestfajnie/msheep_text/EngineBasics"));
    }
    
    public static Translator getInstance() {
        return TranslatorHolder.INSTANCE;
    }
    
    private static class TranslatorHolder {
        
        private static final Translator INSTANCE = new Translator();
    }
    //</editor-fold>
}
