package observer;

public class QuizNotifier implements QuizObserver {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
