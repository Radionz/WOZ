package zuul.rooms;

import java.util.ArrayList;

import zuul.Game;
import zuul.rooms.Room.Exits;
import zuul.studies.Lesson;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class ClassRoom extends Room{

    private Lesson[] lessons;
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
        this.lessons = Game.getLessons();
        this.lesson = lessons[0];
        this.actions = new ArrayList<String>();
        actions.add("learn");
    }

    public Lesson getLesson(){
        return this.lesson;
    }
    
    public String learn(){
    	String returned = "";
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
        if (lesson.isDone() || actions.contains("learn")) {
        	if (lesson.isPOO()) {
        		Game.getPlayer().learn(lesson);
				Game.getPlayer().setCurrentPOOLevel(Game.getPlayer().getCurrentPOOLevel()+1);
			}
        	if (!actions.contains("learn")) {
				actions.add("learn");
			}
        	
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

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
