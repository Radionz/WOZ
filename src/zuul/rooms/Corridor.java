package zuul.rooms;

import java.util.ArrayList;

import zuul.entities.items.Item;

/**
 * @author Nicolas Sarroche, Dorian Blanc
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
    }
    
    public String switchOn(){
        actions.add("switchOff");
        actions.remove("switchOn");
    	this.light = true;
    	return "Lights ON !";
    }
    
    public String switchOff(){
    	actions.remove("switchOff");
        actions.add("switchOn");
    	this.light = true;
    	return "Lights OFF !";
    }

    public void switchLight() {
        this.light = !this.light;
    }
    
    @Override
    public String getLongDescription() {
    	if(light)
    		return "You are " + description + " and the light is on.\n" + getItemString() + "\n" + getActionString() + "\n" + getExitString();
    	return "You are " + description + " and the light is off.\n" + getActionString() + "\n" + getExitString();
    }
    
    public boolean hasItem(Item item){
    if(light)
        return this.items.contains(item);
    return false;
    }
}
