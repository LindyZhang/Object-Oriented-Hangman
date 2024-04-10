package hangman;
import java.util.*;
import java.io.*;

public class game {
    private static display gameDisplay = new display();
    private static boolean hasWon = false;
    public static void guess() throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a letter to guess: ");
        String guess = d.readLine();
        gameDisplay.updateGuess(guess);
        hasWon = gameDisplay.checkWin();
        System.out.println("Your guess: " + guess);
    }

    public static void play() throws IOException {
        System.out.println("Welcome to Object Oriented Hangman!");
        gameDisplay.gettingGuessedWord();
        System.out.println(gameDisplay.getCorrectWord());
        while (gameDisplay.getUserGuesses() < 6 && !hasWon){
            gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
            guess();
        }
        gameDisplay.hangmanGraphicOutput(gameDisplay.getUserGuesses());
        gameOver();
    }

    public static void gameOver(){
        if (hasWon){
            System.out.println("YIPPEE YOU WON!");
        }
    }
    public static void main(String[] args) throws IOException {
        play();
    }
}

