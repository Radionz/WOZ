package zuul.rooms;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
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
}
