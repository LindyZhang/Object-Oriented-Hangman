package hangman;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.util.*;
import java.io.*;
@SpringBootApplication
public class game {
    private static EventBus eventBus;
    private String correctWord;

    public static display gameDisplay;
    private static boolean hasWon = false;
    public game() throws FileNotFoundException {
        eventBus = EventBus.getInstance();
        eventBus.attach(new Observer(), EventType.WELCOME);
        eventBus.attach(new Observer(), EventType.GAME_WON);
        eventBus.attach(new Observer(), EventType.GAME_LOST);
        eventBus.attach(new Observer(), EventType.CORRECT_GUESS);
        eventBus.attach(new Observer(), EventType.INCORRECT_GUESS);
        gameDisplay = new display();
        gameDisplay.gettingGuessedWord();
        correctWord = gameDisplay.getCorrectWord();
        System.out.println(correctWord);
    }
    public void setCorrectWord(String word){
        System.out.println(correctWord);
       correctWord = word;
       gameDisplay.setCorrectWord(word);
        System.out.println(correctWord);
    }

    public boolean getHasWon(){
        return hasWon;
    }
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
    public static void play(game game) throws IOException {
        eventBus.postMessage(EventType.WELCOME);
        while (gameDisplay.getUserGuesses() < 6 && !hasWon){
            gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
            guess();
        }
        gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
        gameOver();
    }

    public boolean handleTurn(char guess) throws IOException {
        return gameDisplay.updateGuess(String.valueOf(guess));
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
        SpringApplication.run(game.class, args);
//        play(new game());
    }
}

