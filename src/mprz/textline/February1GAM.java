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
 * @author michcioperz <michcioperz@gmail.com>
 */
public class February1GAM {
    
    ConsoleReader console;
    PrintWriter out;
    Colors colors = new Colors();
    Location currentLoc;
    GameObject[] inventory;
    String[] effects;
    StringsCompleter strCompleter = new StringsCompleter("help", "inventory", "stop", "sheep", "effects", "lookover", "use");
    
    public void init() throws IOException, InterruptedException {
        inventory = new GameObject[1];
        effects = new String[1];
        console = new ConsoleReader();
        out = new PrintWriter(console.getOutput());
        console.setHandleUserInterrupt(true);
        console.addCompleter(strCompleter);
        out.println(colors.color("MPRZ Tech Labs", Color.CYAN));
        out.println(colors.color("Trasmission begun.", Color.RED));
        out.flush();
        Thread.sleep(1000);
        out.println("mSHEEP Text Game Engine");
        out.println();
        out.println("           /\\0");
        out.println("          /" + colors.color("_", Color.GREEN) + " \\0");
        out.println("         /  " + colors.color("_", Color.GREEN) + " \\@@@@@@@@@   @");
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
        currentLoc = new LE7ELS().start;
        currentLoc.onArrival();
    }
    
    public void run() throws InterruptedException, IOException {
        String line;
        while (true) {
            line = console.readLine(colors.color("mprz: ", Color.GREEN));
            if (line.startsWith("stop")) {
                out.println();
                out.println();
                out.println(colors.color("MPRZ Tech Labs", Color.CYAN));
                out.println(colors.color("Transmission done.", Color.RED));
                out.flush();
                break;
            } else if (line.startsWith("help")) {
                out.println(colors.color("help", Color.MAGENTA) + "           shows help");
                out.println(colors.color("stop", Color.MAGENTA) + "           exits game");
                out.println(colors.color("inventory", Color.MAGENTA) + "      lists your character's inventory");
                out.println(colors.color("effects", Color.MAGENTA) + "        lists physical and psychical effects affecting your character");
                out.println(colors.color("sheep", Color.MAGENTA) + "          uses " + colors.color("the Mysterious Magic of Green Unicorns from Parallel Universe Where Edison Never Existed", Color.CYAN) + " to draw a black hole in time-space continuum fabric");
                out.println(colors.color("lookover", Color.MAGENTA) + "       asks your character's eyes for things it sees");
                out.println(colors.color("use", Color.MAGENTA) + "            lets you use an object from the inventory or the environment");
                out.flush();
            } else if (line.startsWith("inventory")) {
                for (GameObject iter: inventory) {
                    if (iter != null) {
                        out.println(colors.color(iter.getCodename(), Color.MAGENTA) + " " + iter.getName());
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
                out.println("mSHEEP Text Game Engine");
                out.println();
                out.println("           /\\0");
                out.println("          /" + colors.color("_", Color.GREEN) + " \\0");
                out.println("         /  " + colors.color("_", Color.GREEN) + " \\@@@@@@@@@   @");
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
                String useLine = console.readLine(colors.color("mprz:use: ", Color.GREEN));
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            February1GAM donatello = new February1GAM();
            donatello.init();
            donatello.run();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public class LE7ELS {
        
        Location start;
        
        public LE7ELS() {
            start = new Location() {
                
                GameObject[] map;

                @Override
                public String getName() {
                    return "Chocolate Factory";
                }

                @Override
                public GameObject[] getObjectsList() {
                    return map;
                }

                @Override
                public void onArrival() {
                    map = new GameObject[1];
                    map[0] = new GameObject() {
                        
                        boolean produced = false;

                        @Override
                        public String getName() {
                            return "Chocolate producing machine";
                        }

                        @Override
                        public void onUse() {
                            if (produced) {
                                out.println("There's no more cacao in the machine.");
                                out.flush();
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                out.println(colors.color("WHY IS THIS WORLD SO CRUEL?!", Color.YELLOW) + " you shout out loud, but not anyone and not anything seems to have heard it.");
                                out.flush();
                            } else {
                                out.println("Awesome! You make a chocolate bar using remains of cacao resting in the machine!");
                                out.flush();
                                produced = true;
                                inventory[0] = new GameObject() {

                                    @Override
                                    public String getName() {
                                        return "Chocolate bar";
                                    }

                                    @Override
                                    public void onUse() {
                                        inventory[0] = null;
                                        effects[0] = null;
                                        out.println("Hunger leaves you as you eat a medium quality chocolate bar.");
                                        out.println("Did you really think a chocolate bar made from remains of cacao resting in the machine will be as delicious as a chocolate bar made from fresh chocolate?");
                                        out.flush();
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        out.println("Okay, you didn't have time to think. That's sure a good reason. Now, why don't you get out of here?");
                                        out.flush();
                                    }

                                    @Override
                                    public GameObject[] getParent() {
                                        return inventory;
                                    }

                                    @Override
                                    public String getCodename() {
                                        return "chocobar";
                                    }
                                };
                            }
                        }

                        @Override
                        public GameObject[] getParent() {
                            return start.getObjectsList();
                        }

                        @Override
                        public String getCodename() {
                            return "machine";
                        }
                    };
                    out.println("You wake up in the chocolate factory.");
                    out.println("All alone, with no one nearby.");
                    out.println("On own survival.");
                    out.flush();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.println("Also, you feel a bit hungry.");
                    effects[0] = "You feel hungry.";
                    out.flush();
                }

                @Override
                public void onLeave() {
                    
                }

                @Override
                public void onLookover() {
                    out.println("Only thing you can see is a chocolate machine nearby.");
                }
            };
        }
    }
}
