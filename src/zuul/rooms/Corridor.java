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
        actions.add("switchOff");
    }
    
    /**
     * Switch on the light
     * @return
     */
    public String switchOn(){
    	this.light = true;
    	return "Lights ON !";
    }
    
    /**
     * switch off the light
     * @return
     */
    public String switchOff(){
    	this.light = true;
    	return "Lights OFF !";
    }

    /**
     * get the other state of the lab
     */
    public void switchLight() {
        this.light = !this.light;
    }
    
    @Override
    /**
     * return the long description og the corridor
     */
    public String getLongDescription() {
    	if(light)
    		return "You are " + description + " and the light is on.\n" + getItemString() + "\n" + getActionString() + "\n" + getExitString();
    	return "You are " + description + " and the light is off.\n" + getActionString() + "\n" + getExitString();
    }
    
    /**
     * return true if the item in contain in the corridor
     */
    public boolean hasItem(Item item){
    if(light)
        return this.items.contains(item);
    return false;
    }
}
