/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ijestfajnie.msheep_text;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michciu
 */
public class Editor {
    
    private Map<String, Author> commands;
    
    private Editor() {
        commands = new HashMap<>();
    }
    
    public Map<String, Author> getCommands() {
        return commands;
    }
    
    public Author getCommand(String command) {
        return commands.get(command);
    }
    
    public void addCommand(String command, Author parser) {
        commands.put(command, parser);
    }
    
    public void addCommands(Map<? extends String, ? extends Author> newCommands) {
        commands.putAll(newCommands);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    public static Editor getInstance() {
        return EditorHolder.INSTANCE;
    }
    
    private static class EditorHolder {
        
        private static final Editor INSTANCE = new Editor();
    }
    //</editor-fold>
}
