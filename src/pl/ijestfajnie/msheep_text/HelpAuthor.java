package pl.ijestfajnie.msheep_text;

import java.util.Map;

public class HelpAuthor implements Author {
    
    private Map<Author, String> descriptions;
    
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
        throw new UnsupportedOperationException("TBD.");
    }

    public void registerCommand(Author command, String description) {
        descriptions.put(command, description);
    }
    
    private static class HelpAuthorHolder {

        private static final HelpAuthor INSTANCE = new HelpAuthor();
    }
}
