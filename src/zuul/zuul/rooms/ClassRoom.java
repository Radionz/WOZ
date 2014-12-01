package zuul.rooms;

import java.util.ArrayList;

import zuul.Game;
import zuul.rooms.Room.Exits;
import zuul.studies.Lesson;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class ClassRoom extends Room{

    private Lesson lesson;
    private int sentenceNb;


    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public ClassRoom(String description, boolean isPOO, int playerLevel) {
        super(description);
        this.sentenceNb = 0;
        this.lesson = new Lesson(true, playerLevel);
        this.actions = new ArrayList<String>();
        actions.add("learn");
    }
    
    public String learn(){
    	String returned = new String();
    	if (lesson.isPOO()) {
			returned += "You have to learn the course to the bitter end.\n";
		}
    	actions.add("nextSentence");
    	actions.remove("learn");
    	return returned += displaySentences();
    	
    }
    
    public String nextSentence(){
    	return displaySentences();
    }
    
    public Room getExit(String direction) {
        if (lesson.isDone()) {
        	if (lesson.isPOO()) {
				Game.getPlayer().setCurrentPOOLevel(Game.getPlayer().getCurrentPOOLevel()+1);
			}
        	actions.add("learn");
        	actions.remove("nextSentence");
			return exits.get(Exits.getAnExit(direction));
		}
        displaySentences();
        return null;
    }

    /**
     * Return the n'the phrase of the lesson, to be display
     * in the game part.
     * @return String of the n'th phrase of the lesson
     */
    public String displaySentences(){
        String res = lesson.getSentence(sentenceNb);
        sentenceNb++;
        return res;
    }
}
