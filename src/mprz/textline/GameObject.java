package mprz.textline;

/**
 * @author michcioperz <michcioperz@gmail.com>
 */
public interface GameObject {
    public String getName();
    public void onUse();
    public GameObject[] getParent();
    public String getCodename();
}
