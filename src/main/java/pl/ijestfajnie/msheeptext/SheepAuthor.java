package pl.ijestfajnie.msheeptext;

public class SheepAuthor implements Author {

    @Override
    public void command(String input) {
        if (input.trim().startsWith(Translator.getInstance().getString("COMMAND_SHEEP"))) {
            sheep();
        }
    }
    
    public void sheep() {
        Speaker.getInstance().slowSay(Translator.getInstance().getString("ENGINE_NAME"), 50);
        Speaker.getInstance().emptyLine(50);
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
        Speaker.getInstance().emptyLine(50);
        Speaker.getInstance().emptyLine(50);
        Master.getInstance().sleep(1000);
    }
    
    private SheepAuthor() {
        Editor.getInstance().addCommand(Translator.getInstance().getString("COMMAND_SHEEP"), this);
        HelpAuthor.getInstance().registerCommand(this, Translator.getInstance().getString("COMMAND_SHEEP_DESCRIPTION"));
    }
    
    public static SheepAuthor getInstance() {
        return SheepAuthorHolder.INSTANCE;
    }

    @Override
    public String getCodename() {
        return Translator.getInstance().getString("COMMAND_SHEEP");
    }
    
    private static class SheepAuthorHolder {
        
        private static final SheepAuthor INSTANCE = new SheepAuthor();
    }
}
