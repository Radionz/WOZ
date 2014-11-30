package zuul.rooms;


import java.util.ArrayList;

import zuul.studies.Lab;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class LabRoom extends Room{

    private Lab lab;

    public LabRoom(String description){
        super(description);
        this.actions = new ArrayList<String>();
        actions.add("makeLabExercise");
    }

    public String makeLabExercise(){
    	return "WOW amazing lab experience !";
    }


    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
