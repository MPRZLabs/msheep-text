package pl.ijestfajnie.msheeptext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi.Color;

public class Speaker {
    
    private ConsoleReader console;
    private PrintWriter out;
    private long defaultDelay = 500;
    private boolean defaultLn = true;
    private Color defaultColor = Color.WHITE;
    private SlowSayDefaultColorMode ssdcm = SlowSayDefaultColorMode.NULL;
    
    private Speaker() {
        try {
            console = new ConsoleReader();
        } catch (IOException ex) {
            Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        out = new PrintWriter(console.getOutput());
    }
    
    public void slowSay(String text, Color color, long letterDelay, long finalDelay, boolean ln, SlowSayDefaultColorMode ssdcm) {
        Color locColor;
        if (color != null) {
            locColor = color;
        } else {
            switch (ssdcm) {
                case GETDEFAULTCOLOR:
                    locColor = getDefaultColor();
                    break;
                case NULL:
                default:
                    locColor = null;
                    break;
            }
        }
        for (int i = 0; i < text.length(); i++) {
            say("" + text.charAt(i), locColor, letterDelay, false);
        }
        Master.getInstance().sleep(finalDelay);
        if (ln) {
            out.println();
        }
    }
    
    public void slowSay(String text, Color color, long letterDelay, long finalDelay, boolean ln) {
        slowSay(text, color, letterDelay, finalDelay, ln, getSsdcm());
    }
    
    public void slowSay(String text, long letterDelay) {
        slowSay(text, null, letterDelay, 0, getDefaultLn(), getSsdcm());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Color stuff">
    public String color(String text, int color, boolean backToWhite) {
        return "\u001B[" + color + "m" + text + (backToWhite ? "\u001B[" + getDefaultColor().fg() + "m" : "");
    }
    
    public String color(String text, Color color, boolean backToWhite) {
        return color(text, getDefaultColor().fg(), backToWhite);
    }
    
    public String color(String text, Color color) {
        return color(text, color.fg(), false);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Basic messages showing">
    public void say(String text, Color color, long delay, boolean ln) {
        String prt;
        if (color != null) {
            prt = color(text, color, false);
        } else {
            prt = text;
        }
        if (ln) {
            getOutput().println(prt);
        } else {
            getOutput().print(prt);
        }
        Master.getInstance().sleep(delay);
    }
    
    public void say(String text, Color color, long delay) {
        say(text, color, delay, getDefaultLn());
    }
    
    public void say(String text, Color color, boolean ln) {
        say(text, color, getDefaultDelay(), ln);
    }
    
    public void say(String text, long delay, boolean ln) {
        say(text, getDefaultColor(), delay, ln);
    }
    
    public void say(String text, Color color) {
        say(text, color, getDefaultDelay(), false);
    }
    public void say(String text, long delay) {
        say(text, getDefaultColor(), getDefaultDelay(), getDefaultLn());
    }
    
    public void say(String text, boolean ln) {
        say(text, getDefaultColor(), getDefaultDelay(), ln);
    }
    //</editor-fold>
    
    public void emptyLine(long delay) {
        say("", getDefaultColor(), delay, true);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Fields encapsulation">
    /**
     * @return the console
     */
    public ConsoleReader getConsole() {
        return console;
    }
    
    /**
     * @return the out
     */
    public PrintWriter getOutput() {
        return out;
    }

    /**
     * @return the defaultLn
     */
    public boolean getDefaultLn() {
        return defaultLn;
    }

    /**
     * @param defaultLn the defaultLn to set
     */
    public void setDefaultLn(boolean defaultLn) {
        this.defaultLn = defaultLn;
    }

    /**
     * @return the SlowSayDefaultColorMode
     */
    public SlowSayDefaultColorMode getSsdcm() {
        return ssdcm;
    }

    /**
     * @param ssdcm the SlowSayDefaultColorMode to set
     */
    public void setSsdcm(SlowSayDefaultColorMode ssdcm) {
        this.ssdcm = ssdcm;
    }

    /**
     * @return the defaultColor
     */
    public Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * @param defaultColor the defaultColor to set
     */
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }
    
    /**
     * @return the defaultDelay
     */
    public long getDefaultDelay() {
        return defaultDelay;
    }
    
    /**
     * @param defaultDelay the defaultDelay to set
     */
    public void setDefaultDelay(long defaultDelay) {
        this.defaultDelay = defaultDelay;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    
    public static Speaker getInstance() {
        return SpeakerHolder.INSTANCE;
    }
    
    private static class SpeakerHolder {
        
        private static final Speaker INSTANCE = new Speaker();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="SlowSay default color mode enum">
    public enum SlowSayDefaultColorMode {
        NULL,
        GETDEFAULTCOLOR
    }
    //</editor-fold>
}
