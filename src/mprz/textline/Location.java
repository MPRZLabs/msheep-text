package mprz.textline;

/**
 * @author michcioperz <michcioperz@gmail.com>
 */
public interface Location {
    public String getName();
    public GameObject[] getObjectsList();
    public void onLookover();
    public void onArrival();
    public void onLeave();
}
