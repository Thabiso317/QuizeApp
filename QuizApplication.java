import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
        private static int score = 0;
        private static int correctAnswers = 0;
        private static int incorrectAnswers = 0;
        private static Timer timer;
        private static int secondsRemaining = 10; // Set the timer duration in seconds for each question
        private static boolean answered;
        
    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        askQuestion("What is the capital of France?", "a) Berlin", "b) London", "c) Paris", "c");

        // Add more questions here

        endQuiz();
    }

    private static void askQuestion(String question, String optionA, String optionB, String optionC, String correctAnswer) {
        System.out.println(question);
        System.out.println(optionA);
        System.out.println(optionB);
        System.out.println(optionC);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                secondsRemaining--;

                if (secondsRemaining < 0) {
                    if (!answered) {
                        System.out.println("Time's up! The correct answer is " + correctAnswer);
                        incorrectAnswers++;
                    }
                    nextQuestion();
                }
            }
        }, 0, 1000);

        // Get user input
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        // Check the answer
        if (!answered) {
            if (answer.trim().equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct!");
                score++;
                correctAnswers++;
            } else {
                System.out.println("Incorrect! The correct answer is " + correctAnswer);
                incorrectAnswers++;
            }
            answered = true;
        }

        // Stop the timer
        timer.cancel();
        secondsRemaining = 10; // Reset the timer for the next question

        // Move to the next question
        nextQuestion();
                scanner.close();
    }

    private static void nextQuestion() {
        System.out.println("Your current score: " + score);
        System.out.println("Next question...");
        answered = false;
    }

    private static void endQuiz() {
        System.out.println("Quiz ended!");
        System.out.println("Your final score: " + score);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
    }
    
    }
