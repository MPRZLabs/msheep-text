/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mprz.textline;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;
import org.fusesource.jansi.Ansi.Color;

/**
 *
 * @author michcioperz <michcioperz@gmail.com>
 */
public class mSheep {
    
    private ConsoleReader console;
    private PrintWriter out;
    private GameObject[] inventory;
    private String[] effects;
    private StringsCompleter strCompleter = new StringsCompleter("help", "inventory", "stop", "sheep", "effects", "lookover", "use");
    
    public String color(String text, int color) {
        return "\u001B[" + color + "m" + text + "\u001B[" + Color.WHITE.fg() + "m";
    }
    public String color(String text, Color color) {
        return color(text, color.fg());
    }
    
    private mSheep(int inventoryCount, int effectsCount) {
        try {
            this.init(inventoryCount, effectsCount);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(mSheep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static mSheep getInstance() {
        return mSheepHolder.INSTANCE;
    }

    private void init(int inventoryCount, int effectsCount) throws IOException, InterruptedException {
        inventory = new GameObject[1];
        effects = new String[1];
        console = new ConsoleReader();
        out = new PrintWriter(console.getOutput());
        console.setHandleUserInterrupt(true);
        console.addCompleter(strCompleter);
        out.println(color("MPRZ Tech Labs", Color.CYAN));
        out.println(color("Trasmission begun.", Color.RED));
        out.flush();
        Thread.sleep(1000);
        out.println("mSHEEP Text Game Engine");
        out.println();
        out.println("           /\\0");
        out.println("          /" + color("_", Color.GREEN) + " \\0");
        out.println("         /  " + color("_", Color.GREEN) + " \\@@@@@@@@@   @");
        out.println("        /\\    @#########@ @#@");
        out.println("        \\ \\/ @###########@###@");
        out.println("         \\  @#############@#@");
        out.println("          \\@###############@");
        out.println("          @###############@");
        out.println("          @###############@");
        out.println("           @#############@");
        out.println("            @###########@");
        out.println("             @#########@");
        out.println("              @@@@@@@@@");
        out.println("              /|      |\\");
        out.println("             / |      | \\");
        out.println("            /---      ---\\");
        out.println();
        out.println();
        out.flush();
        Thread.sleep(1000);
    }
    
    private static class mSheepHolder {

        private static final mSheep INSTANCE = new mSheep(1, 1);
    }
}
