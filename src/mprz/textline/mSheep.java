package mprz.textline;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.StringsCompleter;
import org.fusesource.jansi.Ansi.Color;

/**
 *
 * @author michcioperz <michcioperz@gmail.com>
 */
public class mSheep {
    
    public ConsoleReader console;
    public PrintWriter out;
    public GameObject[] inventory;
    public String[] effects;
    public StringsCompleter strCompleter = new StringsCompleter("help", "inventory", "stop", "sheep", "effects", "lookover", "use");
    public Location currentLoc;
    
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
    
    public void stop() {
        out.println();
        out.println();
        out.println(color("MPRZ Tech Labs", Color.CYAN));
        out.println(color("Transmission done.", Color.RED));
        out.flush();
    }
    
    public void sheep() throws InterruptedException, InterruptedException {
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
    
    public void run() throws InterruptedException, IOException {
        currentLoc.onArrival();
        String line;
        while (true) {
            line = console.readLine(color("mprz: ", Color.GREEN));
            if (line.startsWith("stop")) {
                stop();
                break;
            } else if (line.startsWith("help")) {
                out.println(color("help", Color.MAGENTA) + "           shows help");
                out.println(color("stop", Color.MAGENTA) + "           exits game");
                out.println(color("inventory", Color.MAGENTA) + "      lists your character's inventory");
                out.println(color("effects", Color.MAGENTA) + "        lists physical and psychical effects affecting your character");
                out.println(color("sheep", Color.MAGENTA) + "          uses " + color("the Mysterious Magic of Green Unicorns from Parallel Universe Where Edison Never Existed", Color.CYAN) + " to draw a black hole in time-space continuum fabric");
                out.println(color("lookover", Color.MAGENTA) + "       asks your character's eyes for things it sees");
                out.println(color("use", Color.MAGENTA) + "            lets you use an object from the inventory or the environment");
                out.flush();
            } else if (line.startsWith("inventory")) {
                for (GameObject iter: inventory) {
                    if (iter != null) {
                        out.println(color(iter.getCodename(), Color.MAGENTA) + " " + iter.getName());
                        out.flush();
                        Thread.sleep(250);
                    }
                }
            } else if (line.startsWith("effects")) {
                for (String iter: effects) {
                    if (iter != null) {
                        out.println(iter);
                        out.flush();
                        Thread.sleep(250);
                    }
                }
            } else if (line.startsWith("sheep")) {
                sheep();
            } else if (line.startsWith("use")) {
                AggregateCompleter agComp = new AggregateCompleter();
                for (GameObject iter: inventory) {
                    if (iter != null) {
                        agComp.getCompleters().add(new StringsCompleter(iter.getCodename()));
                    }
                }
                for (GameObject iter: currentLoc.getObjectsList()) {
                    if (iter != null) {
                        agComp.getCompleters().add(new StringsCompleter(iter.getCodename()));
                    }
                }
                console.removeCompleter(strCompleter);
                console.addCompleter(agComp);
                String useLine = console.readLine(color("mprz:use: ", Color.GREEN));
                boolean objUsed = false;
                for (GameObject iter: inventory) {
                    if (iter != null && !objUsed) {
                        if (useLine.startsWith(iter.getCodename())) {
                            iter.onUse();
                            objUsed = true;
                        }
                    }
                }
                for (GameObject iter: currentLoc.getObjectsList()) {
                    if (iter != null && !objUsed) {
                        if (useLine.startsWith(iter.getCodename())) {
                            iter.onUse();
                            objUsed = true;
                        }
                    }
                }
                if (!objUsed) {
                    out.println("Can't use something that's not on the list!");
                    out.flush();
                }
                console.removeCompleter(agComp);
                console.addCompleter(strCompleter);
            } else if (line.startsWith("lookover")) {
                currentLoc.onLookover();
            }
            Thread.sleep(500);
        }
    }
    
    private static class mSheepHolder {

        private static final mSheep INSTANCE = new mSheep(1, 1);
    }
}
