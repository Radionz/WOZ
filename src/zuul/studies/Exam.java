package zuul.studies;

import java.util.ArrayList;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Exam {

    private ArrayList<Question> questions;
    private int pointerQuestion = 0;
    private int grade = 0;

    public Exam(){
        questions = new ArrayList<Question>(5);
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

	public String answerQuestion(boolean answer) {
		if(questions.get(pointerQuestion).isAnswer() == answer)
			grade++;
		pointerQuestion++;
		return nextQuestion();
	}


	private String nextQuestion() {
		if (pointerQuestion < questions.size()) {
			return questions.get(pointerQuestion).toString();
		}else
			return "Exam done, you've got "+grade;
	}


	public String askQuestion() {
		return questions.get(pointerQuestion).toString();
	}
}
