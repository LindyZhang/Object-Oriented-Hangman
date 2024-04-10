package hangman;
import java.util.*;
import java.io.*;

public class game {
    private static EventBus eventBus;
    public game(){
        eventBus = EventBus.getInstance();
        eventBus.attach(new Observer(), EventType.WELCOME);
        eventBus.attach(new Observer(), EventType.GAME_WON);
        eventBus.attach(new Observer(), EventType.GAME_LOST);
        eventBus.attach(new Observer(), EventType.CORRECT_GUESS);
        eventBus.attach(new Observer(), EventType.INCORRECT_GUESS);
    }
    private static display gameDisplay = new display();
    private static boolean hasWon = false;
    public static void guess() throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a letter to guess: ");
        String guess = d.readLine();
        boolean correctGuess = gameDisplay.updateGuess(guess);

        if(correctGuess){
            eventBus.postMessage(EventType.CORRECT_GUESS);
        }else{
            eventBus.postMessage(EventType.INCORRECT_GUESS);
        }
        hasWon = gameDisplay.checkWin();
        System.out.println("Your guess: " + guess);
    }
    public static void play() throws IOException {
        new game();
        eventBus.postMessage(EventType.WELCOME);
        gameDisplay.gettingGuessedWord();
        while (gameDisplay.getUserGuesses() < 6 && !hasWon){
            gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
            guess();
        }
        gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
        gameOver();
    }
    public static void gameOver(){
        if (hasWon){
            eventBus.postMessage(EventType.GAME_WON);
        }
        else{
            eventBus.postMessage(EventType.GAME_LOST);
        }

        System.out.println("Definition: " + gameDisplay.getDefinition());
        eventBus.removeObservers();
    }
    public static void main(String[] args) throws IOException {
        play();
    }
}

