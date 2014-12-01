package zuul.rooms;


import java.util.ArrayList;

import zuul.Game;
import zuul.studies.Lab;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class LabRoom extends Room{

    private Lab lab;
    private boolean labInProcess;
    public LabRoom(String description){
        super(description);
        this.lab= new Lab(Game.getPlayer().getCurrentPOOLevel());
        this.actions = new ArrayList<String>();
        labInProcess = false;
        actions.add("lab");
    }

    public String lab(){
        actions.remove("lab");
        this.labInProcess = true;
        return lab.askQuestion();
    }

    public String answerQuestion(String answer){
        String returned = "";
        if(answer.equals(true))
            returned = lab.answerQuestion(true);
        else
            returned = lab.answerQuestion(false);

        if (returned.startsWith("Exam done")) {
            labInProcess = false;
        }
        return returned;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public boolean isLabInProcess() {
        return labInProcess;
    }
}
