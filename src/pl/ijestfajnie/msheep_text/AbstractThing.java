package pl.ijestfajnie.msheep_text;

public abstract class AbstractThing implements Thing {
    
    private final String bundleName;
    
    public AbstractThing(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    public String getCodename() {
        return Translator.getInstance().getString("ITEM_" + bundleName.toUpperCase() + "_CODENAME");
    }

    @Override
    public String getName() {
        return Translator.getInstance().getString("ITEM_" + bundleName.toUpperCase() + "_NAME");
    }

    @Override
    public abstract void onUse();
    
}
