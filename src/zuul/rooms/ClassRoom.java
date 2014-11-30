package zuul.rooms;

import java.util.ArrayList;

import zuul.studies.Lesson;

/**
 * Created by user on 13/11/14.
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
        this.lesson = new Lesson(isPOO, playerLevel);
        this.actions = new ArrayList<String>();
        actions.add("learn");
        actions.add("nextSentence");
    }
    
    public String learn(){
    	return displaySentences();
    }
    
    public String nextSentence(){
    	return displaySentences();
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
