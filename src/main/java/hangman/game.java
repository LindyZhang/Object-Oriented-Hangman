package hangman;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.util.*;
import java.io.*;
@SpringBootApplication
public class game {
    private static EventBus eventBus;
    private String correctWord;
    public String textFile;
    public static display gameDisplay;
    private static boolean hasWon = false;

    public game() throws FileNotFoundException {
        eventBus = EventBus.getInstance();
        eventBus.attach(new Observer(), EventType.WELCOME);
        eventBus.attach(new Observer(), EventType.GAME_WON);
        eventBus.attach(new Observer(), EventType.GAME_LOST);
        eventBus.attach(new Observer(), EventType.CORRECT_GUESS);
        eventBus.attach(new Observer(), EventType.INCORRECT_GUESS);
        game.gameDisplay =  new display();

    }

    public void setDifficulty(String filename) throws FileNotFoundException {

        gameDisplay.gettingGuessedWord(filename);
        correctWord = gameDisplay.getCorrectWord();
        System.out.println(correctWord);

    }

    public void setCorrectWord(String word) {
        System.out.println(correctWord);
        correctWord = word;
        gameDisplay.setCorrectWord(word);
        System.out.println(correctWord);
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public static void guess() throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a letter to guess: ");
        String guess = d.readLine();
        boolean correctGuess = gameDisplay.updateGuess(guess);
        hasWon = gameDisplay.checkWin();
        System.out.println("Your guess: " + guess);

    }

    public static void play(game game) throws IOException {

        game.setDifficulty("src/main/java/hangman/words.txt");
        while (gameDisplay.getUserGuesses() < 6 && !hasWon) {
            gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
            guess();
        }
        gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
        gameOver();
    }

    public boolean handleTurn(char guess) {
        boolean correctGuess = gameDisplay.updateGuess(String.valueOf(guess));
        if(gameDisplay.checkWin()) {
            hasWon = true;
        }

        return correctGuess;
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

    public String getHint() {
        return gameDisplay.getDefinition();
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(game.class, args);
//        play(new game());
    }
}

