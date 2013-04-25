package pl.ijestfajnie.msheeptext;

public class Navigator {
    
    private Navigator() {
    }
    
    public static Navigator getInstance() {
        return NavigatorHolder.INSTANCE;
    }
    
    private static class NavigatorHolder {

        private static final Navigator INSTANCE = new Navigator();
    }
}
