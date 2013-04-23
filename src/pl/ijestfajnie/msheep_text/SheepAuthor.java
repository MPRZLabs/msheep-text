package pl.ijestfajnie.msheep_text;

public class SheepAuthor implements Author {

    @Override
    public void command(String input) {
        if (input.trim().startsWith(Translator.getInstance().getString("COMMAND_SHEEP"))) {
            sheep();
        }
    }
    
    public void sheep() {
        Speaker.getInstance().slowSay(Translator.getInstance().getString("ENGINE_NAME"), 50);
        Speaker.getInstance().say("", 50);
        Speaker.getInstance().slowSay("           /\\0", 25);
        Speaker.getInstance().slowSay("          /_ \\0", 25);
        Speaker.getInstance().slowSay("         /  _ \\@@@@@@@@@   @", 25);
        Speaker.getInstance().slowSay("        /\\    @#########@ @#@", 25);
        Speaker.getInstance().slowSay("        \\ \\/ @###########@###@", 25);
        Speaker.getInstance().slowSay("         \\  @#############@#@", 25);
        Speaker.getInstance().slowSay("          \\@###############@", 25);
        Speaker.getInstance().slowSay("          @###############@", 25);
        Speaker.getInstance().slowSay("          @###############@", 25);
        Speaker.getInstance().slowSay("           @#############@", 25);
        Speaker.getInstance().slowSay("            @###########@", 25);
        Speaker.getInstance().slowSay("             @#########@", 25);
        Speaker.getInstance().slowSay("              @@@@@@@@@", 25);
        Speaker.getInstance().slowSay("              /|      |\\", 25);
        Speaker.getInstance().slowSay("             / |      | \\", 25);
        Speaker.getInstance().slowSay("            /---      ---\\", 25);
        Speaker.getInstance().say("", 50);
        Speaker.getInstance().say("", 50);
        Master.getInstance().sleep(1000);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private SheepAuthor() {
        Editor.getInstance().addCommand(Translator.getInstance().getString("COMMAND_SHEEP"), this);
        HelpAuthor.getInstance().registerCommand(this, Translator.getInstance().getString("COMMAND_SHEEP_DESCRIPTION"));
    }
    
    public static SheepAuthor getInstance() {
        return SheepAuthorHolder.INSTANCE;
    }
    
    private static class SheepAuthorHolder {
        
        private static final SheepAuthor INSTANCE = new SheepAuthor();
    }
    //</editor-fold>
}
