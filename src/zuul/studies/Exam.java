package zuul.studies;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
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
