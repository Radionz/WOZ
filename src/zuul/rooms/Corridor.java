package zuul.rooms;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
 */
public class Corridor extends Room{

    private boolean light;

    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public Corridor(String description) {
        super(description);
        this.light = false;
        this.actions = new ArrayList<String>();
        actions.add("switchOn");
        actions.add("switchOff");
    }
    
    public String switchOn(){
    	this.light = true;
    	return "Lights ON !";
    }
    
    public String switchOff(){
    	this.light = true;
    	return "Lights OFF !";
    }

    public boolean isLite() {
        return light;
    }

    public void switchLight() {
        this.light = !this.light;
    }
}
