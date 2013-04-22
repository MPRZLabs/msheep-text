package pl.ijestfajnie.msheep_text;

public class Master {
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private Master() {
    }
    
    public static Master getInstance() {
        return MasterHolder.INSTANCE;
    }
    
    private static class MasterHolder {
        private static final Master INSTANCE = new Master();
    }
    //</editor-fold>
}