package pl.ijestfajnie.msheeptext;

import java.util.Iterator;
import java.util.Map;

public class HelpAuthor implements Author {
    
    private Map<Author, String> descriptions;
    private int commandNameSpace = 16;
    
    @Override
    public void command(String input) {
        if (input.trim().startsWith(Translator.getInstance().getString("COMMAND_HELP"))) {
            help();
        }
    }
    
    private HelpAuthor() {
        Editor.getInstance().addCommand(Translator.getInstance().getString("COMMAND_HELP"), this);
        HelpAuthor.getInstance().registerCommand(this, Translator.getInstance().getString("COMMAND_HELP_DESCRIPTION"));
    }
    
    public static HelpAuthor getInstance() {
        return HelpAuthorHolder.INSTANCE;
    }

    public void help() {
        for (Iterator<Author> it = Editor.getInstance().getCommands().values().iterator(); it.hasNext();) {
            Author command = it.next();
            String helpStr = command.getCodename();
            for (int i = helpStr.length(); i < getCommandNameSpace();) {
                helpStr+=" ";
                i = helpStr.length();
            }
            helpStr+=descriptions.get(command);
            Speaker.getInstance().say(helpStr, true);
        }
    }

    public void registerCommand(Author command, String description) {
        descriptions.put(command, description);
    }

    /**
     * @return the commandNameSpace
     */
    public int getCommandNameSpace() {
        return commandNameSpace;
    }

    /**
     * @param commandNameSpace the commandNameSpace to set
     */
    public void setCommandNameSpace(int commandNameSpace) {
        this.commandNameSpace = commandNameSpace;
    }

    @Override
    public String getCodename() {
        return Translator.getInstance().getString("COMMAND_HELP");
    }
    
    private static class HelpAuthorHolder {

        private static final HelpAuthor INSTANCE = new HelpAuthor();
    }
}
