package zuul.rooms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import zuul.Game;
import zuul.rooms.Room.Exits;
import zuul.studies.Exam;
import zuul.studies.Question;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class ExamRoom extends Room{

    private Exam exam;
    private boolean examInProcess = false;

    /**
     * Create a rooms described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The rooms's description.
     */
    public ExamRoom(String description) {
        super(description);
        this.exam = new Exam();
        this.actions = new ArrayList<String>();
        actions.add("exam");
    }

	/**
	 * Dynamic call of exam
	 * @return the question string
	 */
    public String exam(){
    	actions.remove("exam");
    	examInProcess = true;
		return exam.askQuestion();
    }

	/**
	 * get the exam
	 * @return current exam
	 */
	public Exam getExam() {
        return exam;
    }

	/**
	 * method allowing you to know if there is an exaù
	 * @return
	 */
    public boolean isExamInProcess() {
		return examInProcess;
	}


	/**
	 * method allowing you to answer a question
	 * @param answer string of the answer
	 * @return if the answer is right
	 */
	public String answerQuestion(String answer) {
		String returned = "";
		if(answer.equals(true))
			returned = exam.answerQuestion(true);
		else
			returned = exam.answerQuestion(false);
		
		if (returned.startsWith("Exam done")) {
			examInProcess = false;
		}
		return returned;
	}
	
    public Room getExit(String direction) {
        if (!examInProcess || actions.contains("learn")) {
        	if (!examInProcess) {
				Game.getPlayer().setCurrentPOOLevel(Game.getPlayer().getCurrentPOOLevel()+1);
			}
        	if (!actions.contains("exam")) {
				actions.add("exam");
			}
			return exits.get(Exits.getAnExit(direction));
		}
        return null;
    }
	
}
