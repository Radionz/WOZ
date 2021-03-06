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
        if(Game.getPlayer().getKnowledges().size() > 0 && Game.getPlayer().getKnowledges().get(Game.getPlayer().getCurrentPOOLevel())!=null){

            actions.remove("lab");
            this.labInProcess = true;
            return lab.askQuestion();
        }
        return "You can't do this lab without the proper lesson !";
    }

    /**
     * Check the answer
     * @param answer the answer
     * @return if the answer is good or not
     */
    public String answerQuestion(String answer){
        String returned = "";
        if(answer.equals("true"))
            returned = lab.answerQuestion(true);
        else
            returned = lab.answerQuestion(false);
        
        if (returned.startsWith("Lab done")) {
        	actions.add("lab");
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

    public Lab getLab(){
        return this.lab;
    }

    /**
     * Set a new Lab and add the action "lab" to action list
     * @param lab
     */
    public void setLab(Lab lab){
        actions.add("lab");
        this.lab= lab;
    }

    /**
     *  specific exit for LabRoom : depends on if it's over, or not.
     * @param direction The exit's direction.
     * @return a room in a certain direction
     */
    public Room getExit(String direction) {
        if (lab.getSuccess() || actions.contains("lab")) {

        	if (lab.getSuccess()) {
        		Game.getPlayer().improveAbilities(lab);
                Game.getPlayer().setCurrentPOOLevel(Game.getPlayer().getCurrentPOOLevel()+1);
			}

            if (!actions.contains("lab")) {
                actions.add("lab");
            }

            return exits.get(Exits.getAnExit(direction));
        }
        return null;
    }

}
