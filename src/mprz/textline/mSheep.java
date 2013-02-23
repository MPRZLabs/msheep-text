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
        inventory = new GameObject[inventoryCount];
        effects = new String[effectsCount];
        console = new ConsoleReader();
        out = new PrintWriter(console.getOutput());
        console.setHandleUserInterrupt(true);
        console.addCompleter(strCompleter);
        out.println(color("MPRZ Tech Labs", Color.CYAN));
        out.println(color("Trasmission begun.", Color.RED));
        out.flush();
        Thread.sleep(1000);
        sheep();
    }
    
    public void stop() {
        out.println();
        out.println();
        out.println(color("MPRZ Tech Labs", Color.CYAN));
        out.println(color("Transmission done.", Color.RED));
        out.flush();
        console.shutdown();
        System.exit(0);
    }
    
    public void sheep() throws InterruptedException {
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
                out.println(color("ninja", Color.MAGENTA) + "          use the ninja abilities");
                out.flush();
            } else if (line.startsWith("inventory")) {
                for (GameObject iter: inventory) {
                    if (iter != null) {
                        if (iter.isVisible()) {
                            out.println(color(iter.getCodename(), Color.MAGENTA) + " " + iter.getName());
                            out.flush();
                            Thread.sleep(250);
                        }
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
                        if (iter.isVisible())
                            agComp.getCompleters().add(new StringsCompleter(iter.getCodename()));
                    }
                }
                for (GameObject iter: currentLoc.getObjectsList()) {
                    if (iter != null) {
                        if (iter.isVisible())
                        agComp.getCompleters().add(new StringsCompleter(iter.getCodename()));
                    }
                }
                console.removeCompleter(strCompleter);
                console.addCompleter(agComp);
                String useLine = console.readLine(color("mprz:use: ", Color.GREEN));
                boolean objUsed = false;
                for (GameObject iter: inventory) {
                    if (iter != null && !objUsed) {
                        if (useLine.startsWith(iter.getCodename()) && iter.isVisible()) {
                            iter.onUse();
                            objUsed = true;
                        }
                    }
                }
                for (GameObject iter: currentLoc.getObjectsList()) {
                    if (iter != null && !objUsed) {
                        if (useLine.startsWith(iter.getCodename()) && iter.isVisible()) {
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
            } else if (line.startsWith("ninja")) {
                if (effects[0] == null) {
                    StringsCompleter ninjAbilComp = new StringsCompleter("collide");
                    console.removeCompleter(strCompleter);
                    console.addCompleter(ninjAbilComp);
                    String abiLine = console.readLine(color("mprz:", Color.GREEN) + color("ninja: ", Color.MAGENTA));
                    if (abiLine.startsWith("collide")) {
                        AggregateCompleter agComp = new AggregateCompleter();
                        for (GameObject iter: currentLoc.getObjectsList()) {
                            if (iter != null) {
                                if (iter.isVisible())
                                agComp.getCompleters().add(new StringsCompleter(iter.getCodename()));
                            }
                        }
                        console.removeCompleter(ninjAbilComp);
                        console.addCompleter(agComp);
                        String useLine = console.readLine(color("mprz:", Color.GREEN) + color("ninja:", Color.MAGENTA) + color("collide: ", Color.BLUE));
                        boolean objUsed = false;
                        for (GameObject iter: inventory) {
                            if (iter != null && !objUsed) {
                                if (useLine.startsWith(iter.getCodename()) && iter.isVisible()) {
                                    iter.onNinjaCollide();
                                    objUsed = true;
                                }
                            }
                        }
                        for (GameObject iter: currentLoc.getObjectsList()) {
                            if (iter != null && !objUsed) {
                                if (useLine.startsWith(iter.getCodename()) && iter.isVisible()) {
                                    iter.onNinjaCollide();
                                    objUsed = true;
                                }
                            }
                        }
                        if (!objUsed) {
                            out.println("Can't ninja-collide with something that's not on the list!");
                            out.flush();
                        }
                        console.removeCompleter(agComp);
                        console.addCompleter(strCompleter);
                    }
                } else {
                    out.println("Hungry ninja is not ninja.");
                    out.flush();
                }
            }
            Thread.sleep(500);
        }
    }
    
    private static class mSheepHolder {

        private static final mSheep INSTANCE = new mSheep(4, 2);
    }
}
