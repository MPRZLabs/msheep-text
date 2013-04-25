package pl.ijestfajnie.msheeptext;

import java.io.IOException;
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
        while (true) {
            String input = null;
            try {
                input = Speaker.getInstance().getConsole().readLine("master: ");
            } catch (IOException ex) {
                Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
            }
            Editor.getInstance().getCommand(input.trim().split(" ")[0]).command(input);
        }
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