import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

abstract class Question {
    private String id;
    private String prompt;
    private int points;

    public Question(String id, String prompt, int points) {
        this.id = id;
        this.prompt = prompt;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public String getPrompt() {
        return prompt;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean evaluate(String response);
}

class MultipleChoiceQuestion extends Question {
    private String correctOption;

    public MultipleChoiceQuestion(String id, String prompt, int points, String correctOption) {
        super(id, prompt, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean evaluate(String response) {
        return correctOption.equalsIgnoreCase(response.trim());
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String id, String prompt, int points, boolean correctAnswer) {
        super(id, prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String response) {
        return Boolean.parseBoolean(response.trim()) == correctAnswer;
    }
}

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String examId;
    private String title;
    private List<Question> questions;

    public Examination(String examId, String title) {
        this.examId = examId;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getExamId() {
        return examId;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTotalPoints() {
        int sum = 0;
        for (Question q : questions) {
            sum += q.getPoints();
        }
        return sum;
    }
}

class ExamAttempt {
    private Student student;
    private Examination exam;
    private Map<String, String> answers;
    private boolean submitted;

    public ExamAttempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        this.answers = new LinkedHashMap<>();
        this.submitted = false;
        System.out.println(exam.getTitle() + " started by " + student.getName() + ".");
    }

    public boolean recordAnswer(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return false;
        }
        answers.put(question.getId(), answer);
        System.out.println("Answer recorded for " + question.getId() + ".");
        return true;
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }
        submitted = true;
        System.out.println(exam.getTitle() + " submitted by " + student.getName() + ".");

        int totalScore = 0;
        StringBuilder resultBuilder = new StringBuilder("Result: ");
        List<Question> questions = exam.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String ans = answers.get(q.getId());
            boolean correct = ans != null && q.evaluate(ans);
            int pts = correct ? q.getPoints() : 0;
            totalScore += pts;
            resultBuilder.append(q.getId()).append(": ").append(correct ? "Correct" : "Incorrect")
                    .append(" (").append(pts).append(" points)");
            if (i < questions.size() - 1) {
                resultBuilder.append(", ");
            }
        }
        resultBuilder.append(". Total score: ").append(totalScore).append("/").append(exam.getTotalPoints()).append(".");
        System.out.println(resultBuilder.toString());
    }

    public boolean isSubmitted() {
        return submitted;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Examination examA = new Examination("EX1", "Exam A");
        Question q1 = new MultipleChoiceQuestion("Question 1", "Select prime", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", "Earth is flat", 5, false);

        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student student1 = new Student("S1", "Student 1");

        ExamAttempt attempt = new ExamAttempt(student1, examA);
        attempt.recordAnswer(q1, "C");
        attempt.recordAnswer(q2, "True");
        attempt.submit();

        attempt.recordAnswer(q1, "A");
    }
}
