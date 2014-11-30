package zuul.rooms;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
 */
public class LunchRoom extends Room{



    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public LunchRoom(String description) {
        super(description);this.actions = new ArrayList<String>();
        actions.add("drinkCoffee");
        actions.add("playBabyfoot");
    }

    public String drinkCoffee(){
    	return "WOW drinkCoffee !";
    }
    
    public String playBabyfoot(){
    	return "WOW playBabyfoot !";
    }
}
