package zuul.studies;

import zuul.io.IO;

import java.io.IOException;

/**
 * Created by user on 13/11/14.
 */
public class Question {


    private int number;
    private boolean done;

    private String body;
    private String question;
    private boolean answer;

    public Question(int number){
        this.done = false;
        this.number = number;
        getLessonFromFile(IO.PossibleFiles.POO_QUESTION.getPath());

    }

    // Basic getters/setters //

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isAnswer() {
        return answer;
    }
    // Basic getters/setters //

    // SKYPE = dorianblanc

    private void getLessonFromFile(String path){
        try {
            body = IO.getFromFile(number, path);
        } catch (IOException e){
            e.printStackTrace();
        }
        if(body == null){
            body = "ERROR";
        }
        parseBody();
    }

    private void parseBody(){
        String splits[] = body.split("\\?");
        this.question = splits[0];
        if(splits[1].length()>1){
            splits[1] = splits[1].substring(1, 2);
        }
        if(splits[1].equals("T")){
            this.answer = true;
            System.out.println("True");
        }else if(splits[1].equals("F")){
            this.answer = false;
            System.out.println("false");
        }else{
            System.out.println(splits[1]);
        }
    }

    @Override
    public String toString(){
        return this.body+'\n';
    }
}
