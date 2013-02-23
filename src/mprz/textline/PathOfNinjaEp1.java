package mprz.textline;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fusesource.jansi.Ansi.Color;

/**
 *
 * @author michcioperz <michcioperz@gmail.com
 */
public class TheGame {
    mSheep sheep;
    LE7ELS le7els;
    
    public TheGame() {
        sheep = mSheep.getInstance();
        le7els = new LE7ELS();
        sheep.currentLoc = le7els.start;
    }
    
    public static void main(String[] args) {
        try {
            TheGame game = new TheGame();
            game.sheep.run();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(TheGame.class.getName()).log(Level.SEVERE, null, ex);
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
                                sheep.out.println("There's no more cacao in the machine.");
                                sheep.out.flush();
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                sheep.out.println(sheep.color("WHY IS THIS WORLD SO CRUEL?!", Color.YELLOW) + " you shout out loud, but not anyone and not anything seems to have heard it.");
                                sheep.out.flush();
                            } else {
                                sheep.out.println("Awesome! You make a chocolate bar using remains of cacao resting in the machine!");
                                sheep.out.flush();
                                produced = true;
                                sheep.inventory[0] = new GameObject() {

                                    @Override
                                    public String getName() {
                                        return "Chocolate bar";
                                    }

                                    @Override
                                    public void onUse() {
                                        sheep.inventory[0] = null;
                                        sheep.effects[0] = null;
                                        sheep.out.println("Hunger leaves you as you eat a medium quality chocolate bar.");
                                        sheep.out.println("Did you really think a chocolate bar made from remains of cacao resting in the machine will be as delicious as a chocolate bar made from fresh chocolate?");
                                        sheep.out.flush();
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        sheep.out.println("Okay, you didn't have time to think. That's sure a good reason. Now, why don't you get out of here?");
                                        sheep.out.flush();
                                    }

                                    @Override
                                    public GameObject[] getParent() {
                                        return sheep.inventory;
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
                    sheep.out.println("You wake up in the chocolate factory.");
                    sheep.out.println("All alone, with no one nearby.");
                    sheep.out.println("On own survival.");
					sheep.out.println(sheep.color("PATH OF THE NINJA: EPISODE 1", Color.CYAN));
                    sheep.out.flush();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(February1GAM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.out.println("Also, you feel a bit hungry.");
                    sheep.effects[0] = "You feel hungry.";
                    sheep.out.flush();
                }

                @Override
                public void onLeave() {
                    
                }

                @Override
                public void onLookover() {
                    sheep.out.println("Only thing you can see is a chocolate machine nearby.");
                }
            };
        }
    }
}
