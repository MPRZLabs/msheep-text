package pl.ijestfajnie.msheep_text;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {
    
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run() {
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private Master() {
    }
    
    public static Master getInstance() {
        return MasterHolder.INSTANCE;
    }
    
    private static class MasterHolder {
        private static final Master INSTANCE = new Master();
    }
    //</editor-fold>
}