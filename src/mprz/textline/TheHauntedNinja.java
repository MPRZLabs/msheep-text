package mprz.textline;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fusesource.jansi.Ansi.Color;

/**
 *
 * @author michcioperz <michcioperz@gmail.com>
 */
public class TheHauntedNinja {
    mSheep sheep;
    LE7ELS le7els;
    
    public TheHauntedNinja() {
        sheep = mSheep.getInstance();
        le7els = new LE7ELS();
        sheep.loadLocation(le7els.start);
    }
    
    public static void main(String[] args) {
        try {
            TheHauntedNinja game = new TheHauntedNinja();
            game.sheep.run();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(TheHauntedNinja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public class LE7ELS {
        
        public Location start;
        public Location hallway;
        
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
                    map = new GameObject[2];
                    map[0] = new GameObject() {
                        
                        public boolean produced = false;

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
                                    Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
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
                                            Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        sheep.out.println("It really wasn't high quality, if you can call it quality.");
                                        sheep.out.println("Looks like something in the chocolate makes you haunted by visions...");
                                        sheep.out.flush();
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        sheep.effects[1] = "You are haunted by weird visions.";
                                        sheep.currentLoc.onVision();
                                    }

                                    @Override
                                    public GameObject[] getParent() {
                                        return sheep.inventory;
                                    }

                                    @Override
                                    public String getCodename() {
                                        return "chocobar";
                                    }

                                    @Override
                                    public boolean isVisible() {
                                        return true;
                                    }

                                    @Override
                                    public void onNinjaCollide() {
                                        sheep.out.println("Don't waste chocolate!");
                                        sheep.out.flush();
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
						
                        @Override
                        public boolean isVisible() {
                            return true;
                        }

                        @Override
                        public void onNinjaCollide() {
                            sheep.out.println("The machine doesn't seem to care much about any ninjas colliding with it.");
                            sheep.out.flush();
                        }
                    };
                    map[1] = new GameObject() {
                        @Override
                        public String getName() {
                            return "Door";
                        }
                    
                        @Override
                        public void onUse() {
                            if (sheep.effects[0] == null) {
                                sheep.out.println("The door is closed.");
                                sheep.out.flush();
                            }
                        }
                    
                        @Override
                        public GameObject[] getParent() {
                            return start.getObjectsList();
                        }
                    
                        @Override
                        public String getCodename() {
                            return "door";
                        }
                    
                        @Override
                        public boolean isVisible() {
                            if (sheep.effects[0] == null) {
                                return true;
                            } else {
                                return false;
                            }
                        }

                        @Override
                        public void onNinjaCollide() {
                            sheep.out.println("You decide to do use some ninja skills...");
                            sheep.out.println("The distance between you and the door become closer and closer and...");
                            sheep.out.flush();
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            sheep.currentLoc.onLeave();
                        }
                    };
                    sheep.out.println("You wake up in the chocolate factory.");
                    sheep.out.println("All alone, with no one nearby.");
                    sheep.out.println("On own survival.");
                    sheep.out.println(sheep.color("THE HAUNTED NINJA: EPISODE 1", Color.CYAN));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.out.flush();
                    sheep.out.println("Also, you feel a bit hungry.");
                    sheep.effects[0] = "You feel hungry.";
                    sheep.out.flush();
                }

                @Override
                public void onLookover() {
                    sheep.out.println("You see a chocolate machine nearby.");
                    if (map[1].isVisible()) {
                        sheep.out.println("Also, you can locate the door from your vision");
                    }
                }

                @Override
                public void onLeave() {
                    try {
                        sheep.out.println(sheep.color("TO BE CONTINUED...",Color.CYAN));
                        sheep.out.flush();
                        Thread.sleep(3000);
                        sheep.loadLocation(hallway);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onVision() {
                    sheep.out.println(sheep.color("You see yourself smashing through the wall nearby. That's it, you're haunted by visions, right?", Color.RED));
                    sheep.out.println("After you get back to the real reality, you find out there is something on the wall.");
                    
                    sheep.out.println(sheep.color("Door!", Color.YELLOW));
                }
            };
            hallway = new Location() {
                
                GameObject[] map;
                boolean visionDone = false;

                @Override
                public String getName() {
                    return "Hallway";
                }

                @Override
                public GameObject[] getObjectsList() {
                    return map;
                }

                @Override
                public void onLookover() {
                    sheep.out.println("Unsurprisingly, there are door on the right and door on the left, like in a normal hallway, right?");
                    sheep.out.println("However, the hallway is way brighter than the production line, thanks to a lamp on the ceiling.");
                    if (visionDone) {
                        sheep.out.println("And the walls are painted with your brother's blood. Ugh! What a creepy day...");
                    } else {
                        onVision();
                    }
                    sheep.out.flush();
                }

                @Override
                public void onVision() {
                    visionDone = true;
                    sheep.out.println(sheep.color("You see two masked gmans carrying some yellow person", Color.RED));
                    sheep.out.println("After you get back to the real reality, you find out there is something on the wall.");
                    sheep.out.println(sheep.color("YELLOW BLOOD!", Color.YELLOW));
                    sheep.out.flush();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.out.println(sheep.color("No! They took him!", Color.YELLOW));
                    sheep.out.flush();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.out.println(sheep.color("Bro! Where are you? Can you hear me?", Color.YELLOW));
                    sheep.out.flush();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.out.println("You only hear quiet robotic \"Hello, friend!\" voice and decide to shout no more...");
                    sheep.out.flush();
                }

                @Override
                public void onArrival() {
                    map = new GameObject[3];
                    
                    map[0] = new GameObject() {

                        @Override
                        public String getName() {
                            return "Lamp";
                        }

                        @Override
                        public void onUse() {
                            sheep.out.println(sheep.color("That's too high... Let's use some ninja skills...", Color.YELLOW));
                            sheep.out.flush();
                        }

                        @Override
                        public GameObject[] getParent() {
                            return map;
                        }

                        @Override
                        public String getCodename() {
                            return "lamp";
                        }

                        @Override
                        public boolean isVisible() {
                            return true;
                        }

                        @Override
                        public void onNinjaCollide() {
                            sheep.out.println("Things happen like with the door, despite that it takes you a lot less time.");
                            sheep.out.println("Anyway, that lamp broke and you found a bunch of keys!");
                            sheep.out.flush();
                            sheep.inventory[1] = new GameObject() {
                                
                                boolean usedOnDoor1 = false;
                                boolean usedOnDoor2 = false;

                                @Override
                                public String getName() {
                                    return "Bunch of keys";
                                }

                                @Override
                                public void onUse() {
                                    
                                }

                                @Override
                                public GameObject[] getParent() {
                                    return sheep.inventory;
                                }

                                @Override
                                public String getCodename() {
                                    return "keys";
                                }

                                @Override
                                public boolean isVisible() {
                                    return !(usedOnDoor1 && usedOnDoor2);
                                }

                                @Override
                                public void onNinjaCollide() {
                                    sheep.out.println("That's simply impossible.");
                                    sheep.out.flush();
                                }
                            };
                        }
                    };
                    
                    map[1] = new GameObject() {

                        @Override
                        public String getName() {
                            return "Door on the left";
                        }

                        @Override
                        public void onUse() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public GameObject[] getParent() {
                            return map;
                        }

                        @Override
                        public String getCodename() {
                            return "door1";
                        }

                        @Override
                        public boolean isVisible() {
                            return true;
                        }

                        @Override
                        public void onNinjaCollide() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                    
                    map[2] = new GameObject() {

                        @Override
                        public String getName() {
                            return "Door on the right";
                        }

                        @Override
                        public void onUse() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public GameObject[] getParent() {
                            return map;
                        }

                        @Override
                        public String getCodename() {
                            return "door2";
                        }

                        @Override
                        public boolean isVisible() {
                            return true;
                        }

                        @Override
                        public void onNinjaCollide() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                }

                @Override
                public void onLeave() {
                    try {
                        sheep.out.println(sheep.color("TO BE CONTINUED...",Color.CYAN));
                        sheep.out.flush();
                        Thread.sleep(3000);
                        sheep.stop();
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
    }
}
