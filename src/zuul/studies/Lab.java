package zuul.studies;

import zuul.Game;

/**
 * Created by user on 13/11/14.
 */
public class Lab {

    private Question[] questions;
    private boolean succeed;

    public Lab(int i){
        questions = new Question[3];
        System.arraycopy(Game.getQuestions(), i * 3, questions, 0, i);
        this.succeed = false;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void isSucceed(){
        for(Question q : questions){
            this.succeed &= q.isDone();
        }
    }

    public boolean getSuccess(){
        return this.succeed;
    }
}
