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

    /**
     * Dynamic methoc called
     * @return the question asked
     */
    public String lab(){
        actions.remove("lab");
        this.labInProcess = true;
        return lab.askQuestion();
    }

    /**
     * Check the answer
     * @param answer the answer
     * @return if the answer is ggood or not
     */
    public String answerQuestion(String answer){
        String returned = "";
        if(answer.equals(true))
            returned = lab.answerQuestion(true);
        else
            returned = lab.answerQuestion(false);

        if (returned.startsWith("Lab done")) {
            labInProcess = false;
        }
        return returned;
    }


    /**
     * Return true if there is currently a lab
     * @return labInProcess
     */
    public boolean isLabInProcess() {
        return labInProcess;
    }
}
