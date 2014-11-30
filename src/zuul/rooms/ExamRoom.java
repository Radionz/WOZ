package zuul.rooms;

import java.util.ArrayList;

import zuul.studies.Exam;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class ExamRoom extends Room{

    private Exam exam;

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
        actions.add("doExam");
    }

    public String doExam(){
    	return exam.toString();
    }

    public Exam getExam() {
        return exam;
    }
}
