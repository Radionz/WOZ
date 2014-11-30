package zuul.studies;

import java.util.ArrayList;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Exam {

    private ArrayList<Question> questions;

    public Exam(){
        questions = new ArrayList<>(5);
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
