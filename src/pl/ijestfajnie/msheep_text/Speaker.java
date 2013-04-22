package pl.ijestfajnie.msheep_text;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;

public class Speaker {
    
    private ConsoleReader console;
    private PrintWriter out;
    
    public ConsoleReader getConsole() {
        return console;
    }
    
    private Speaker() {
        try {
            console = new ConsoleReader();
        } catch (IOException ex) {
            Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        out = new PrintWriter(console.getOutput());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    
    public static Speaker getInstance() {
        return SpeakerHolder.INSTANCE;
    }
    
    private static class SpeakerHolder {
        
        private static final Speaker INSTANCE = new Speaker();
    }
    //</editor-fold>
}
