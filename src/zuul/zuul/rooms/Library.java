package zuul.rooms;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Library extends Room{



	public Library(String description) {
		super(description);
		this.actions = new ArrayList<String>();
        actions.add("readBook");
    }

    public String readBook(){
    	return "WOW I read a book !";
    }

    public boolean checkForschedule() {
        Random random = new Random();
        int hour = random.nextInt(24);
        return hour==1;
    }
}
