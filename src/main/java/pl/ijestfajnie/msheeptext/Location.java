package pl.ijestfajnie.msheeptext;

import java.util.Map;

public interface Location {
    public String getName();
    public String getCodename();
    public Map<String, Thing> getObjects();
    public void onLookover();
    public void onArrival();
    public void onLeave();
}
