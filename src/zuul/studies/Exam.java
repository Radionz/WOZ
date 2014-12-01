package zuul.studies;

import java.util.ArrayList;

import zuul.Game;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Exam {

    private ArrayList<Question> questions;
    private int pointerQuestion = 0;
    private int grade = 0;

    public Exam(){
        questions = new ArrayList<Question>();
        questions.add(Game.getQuestions()[1]);
        questions.add(Game.getQuestions()[2]);
        questions.add(Game.getQuestions()[3]);
        questions.add(Game.getQuestions()[4]);
        questions.add(Game.getQuestions()[5]);
    }


    public ArrayList<Question> getQuestions() {
        return questions;
    }

	public String answerQuestion(boolean answer) {
		Question q = questions.get(pointerQuestion);
		if(q.isAnswer() == answer)
			grade++;
		pointerQuestion++;
		return nextQuestion();
	}


	private String nextQuestion() {
		if (pointerQuestion < questions.size()) {
			return questions.get(pointerQuestion).getQuestion();
		}else
			return "Exam done, you've got "+grade;
	}


	public String askQuestion() {
		return questions.get(pointerQuestion).getQuestion();
	}
}
