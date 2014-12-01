package zuul.rooms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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

    public String exam(){
    	actions.remove("exam");
    	examInProcess = true;
		return exam.askQuestion();
    }
    
	public Exam getExam() {
        return exam;
    }
	
    public boolean isExamInProcess() {
		return examInProcess;
	}

	public String answerQuestion(String answer) {
		if(answer.equals(true))
			return exam.answerQuestion(true);
		else
			return exam.answerQuestion(false);
	}
	
}
