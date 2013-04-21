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
        } catch (IOException ex) {
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
                                sheep.slowSay("There's no more cacao in the machine.", 50);
                                sheep.slowSay(sheep.color("WHY IS THIS WORLD SO CRUEL?!", Color.YELLOW) + " you shout out loud, but not anyone and not anything seems to have heard it.", 50);
                            } else {
                                sheep.slowSay("Awesome! You make a chocolate bar from remains of cacao resting in the machine!", 50);
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
                                        sheep.slowSay("Hunger leaves you as you eat a medium quality chocolate bar.", 100);
                                        sheep.sleep(2000);
                                        sheep.slowSay("Did you really think a chocolate bar made from remains of cacao resting in the machine will be as delicious as a chocolate bar made from fresh chocolate?", 100);
                                        sheep.sleep(4000);
                                        sheep.slowSay("It really wasn't high quality, if there had been any quality.", 115);
                                        sheep.slowSay("Looks like something in the chocolate makes you haunted by visions...", 130);
                                        sheep.effects[1] = "You are haunted by weird visions.";
                                        sheep.sleep(2000);
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
                                        sheep.slowSay("Don't waste chocolate!", Color.YELLOW, 100);
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
                            sheep.slowSay("The machine doesn't seem to care much about any ninjas colliding with it.", 50);
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
                                sheep.slowSay("The door is closed.", 50);
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
                            sheep.slowSay("You decide to do use some ninja skills...", 115);
                            sheep.say("");
                            sheep.slowSay("The distance between you and the door become closer and closer and...", 200);
                            sheep.currentLoc.onLeave();
                        }
                    };
                    sheep.slowSay("You wake up in the chocolate factory.", 100);
                    sheep.slowSay("All alone, with no one nearby.", 100);
                    sheep.slowSay("On own survival.", 150);
                    sheep.say("", 1000);
                    sheep.slowSay("THE HAUNTED NINJA: EPISODE 1", Color.CYAN, 250);
                    sheep.say("", 1000);
                    sheep.slowSay("Also, you feel a bit hungry.", 100);
                    sheep.effects[0] = "You feel hungry.";
                }

                @Override
                public void onLookover() {
                    sheep.slowSay("You see a chocolate machine nearby.", 50);
                    if (map[1].isVisible()) {
                        sheep.slowSay("Also, you can locate the door from your vision", 75);
                    }
                }

                @Override
                public void onLeave() {
                    sheep.slowSay("TO BE CONTINUED...", Color.CYAN, 250);
                    sheep.sleep(2000);
                    try {
                        sheep.console.clearScreen();
                    } catch (IOException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sheep.loadingScreen();
                    sheep.loadLocation(hallway);
                }

                @Override
                public void onVision() {
                    sheep.visionSay("You see yourself smashing through the wall nearby.");
                    sheep.slowSay("After you get back to the real reality, you find out there is something on the wall.", 140);
                    
                    sheep.slowSay("Door!", Color.YELLOW, 200);
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
                    sheep.say("Unsurprisingly, there are door on the right and door on the left, like in a normal hallway, right?", 1500);
                    sheep.say("However, the hallway is way brighter than the production line, thanks to a lamp on the ceiling.", 1300);
                    if (visionDone) {
                        sheep.say("And the walls are painted with your brother's blood. Ugh! What a creepy day...", 1100);
                    } else {
                        onVision();
                    }
                }

                @Override
                public void onVision() {
                    visionDone = true;
                    sheep.visionSay("You see two masked gmans carrying some yellow person");
                    sheep.say("After you get back to the real reality, you find out there is something on the wall.", 1200);
                    sheep.say("YELLOW BLOOD!", Color.YELLOW, 3000);
                    sheep.say("No! They took him!", Color.YELLOW, 1000);
                    sheep.say("Bro! Where are you? Can you hear me?", Color.YELLOW, 2000);
                    sheep.say("You only hear quiet robotic \"Hello, friend!\" voice and decide to shout no more...", 1000);
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
                            sheep.say("That's too high... Let's use some ninja skills...", Color.YELLOW, 1500);
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
                            sheep.say("Things happen like with the door, despite that it takes you a lot less time.", 2000);
                            sheep.say("Anyway, that lamp broke and you found a bunch of keys!", 1000);
                            sheep.inventory[1] = new GameObject() {

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
                                    return true;
                                }

                                @Override
                                public void onNinjaCollide() {
                                    sheep.say("That's simply impossible.", 250);
                                }
                            };
                        }
                    };
                    
                    map[1] = new GameObject() {
                        
                        boolean closed = true;

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
                        
                        boolean closed;

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
                        sheep.slowSay("TO BE CONTINUED...", Color.CYAN, 250);
                        sheep.sleep(2000);
                        sheep.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(LE7ELS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
    }
}
