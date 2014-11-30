package zuul.studies;


import zuul.io.IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 13/11/14.
 */
public class Lesson {

    private int number;
    private boolean done;
    private boolean isPOO;


    // List of string : phrase the teacher says in the lesson
    private ArrayList<String> insts;


    public Lesson(boolean isPOO, int number){
        this.done = false;
        this.isPOO = isPOO;
        this.number = number;
        insts = new ArrayList<>(100);
        if(isPOO){
            getLessonFromFile(IO.PossibleFiles.POO_LESSON.getPath());
        }else{
            getLessonFromFile(IO.PossibleFiles.OTHER_LESSON.getPath());
        }
    }

    // Basic getters/setters //
    public boolean isPOO() {
        return isPOO;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getNumber() {
        return number;
    }

    public String getSentence(int number){
        return insts.get(number);
    }
    // Basic getters/setters //

    // SKYPE = dorianblanc

    private void getLessonFromFile(String path){
        String body = null;
        try {
            body = IO.getFromFile(number, path);
        } catch (IOException e){
            e.printStackTrace();
        }
        if(body == null){
            body = "ERROR";
        }
        parseBody(body);
    }

    private void parseBody(String body){
        String splits[] = body.split("\\.");
        Collections.addAll(insts, splits);
    }

    @Override
    public String toString(){
        String res ="";
        for(String s : insts){
            res += s + '\n';
        }

        return res;
    }


}
