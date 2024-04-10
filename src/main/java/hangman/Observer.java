package hangman;

public class Observer implements IObserver{
    @Override
    public void update(EventType eventType) {

        if(eventType == EventType.WELCOME){
            System.out.println("Welcome to Object Oriented Hangman!");
        } else if (eventType == EventType.GAME_WON) {
            System.out.println("YIPPEE YOU WON!");
        } else if (eventType == EventType.GAME_LOST) {
            System.out.println("GAME OVER!");
        } else if (eventType == EventType.CORRECT_GUESS) {
            System.out.println("Correct Guess!");
        } else if (eventType == EventType.INCORRECT_GUESS) {
            System.out.println("Incorrect Guess");
        }
    }
}
