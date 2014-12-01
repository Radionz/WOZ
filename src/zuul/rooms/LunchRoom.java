package zuul.rooms;

import zuul.Game;
import zuul.entities.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Nicolas Sarroche, Dorian Blanc
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
    	if(isForced()){
    		Game.getPlayer().forgetALesson();
    		return "You loose a lesson !";
    	}
    	else
    		return "You don't loose a lesson !";
    }

    public boolean isForced(){
        int random = new Random().nextInt(5);
        return random == 1;
    }
}
