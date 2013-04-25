package pl.ijestfajnie.msheeptext;

import java.util.Map;


public class AbstractLocation implements Location {
    private String bundleName;
    
    

    @Override
    public String getName() {
        return Translator.getInstance().getString("LOCATION_" + bundleName + "_NAME");
    }

    @Override
    public String getCodename() {
        return Translator.getInstance().getString("LOCATION_" + bundleName + "_CODENAME");
    }

    @Override
    public Map<String, Thing> getObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onLookover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onArrival() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onLeave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
