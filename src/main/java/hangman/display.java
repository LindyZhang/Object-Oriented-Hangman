package hangman;

import java.io.FileNotFoundException;
import java.util.*;

public class display {
    private static int wordLength = 0;
    private List<String> guessedWord;
    private String correctWord;
    private String definition;
    private Set<String> alreadyGuessedLetters = new HashSet<String>();
    public static int userGuesses = 0;
    private int correctGuessCounter = 0;
    private Set<String> guessedLetters = new HashSet<>();
    public int getUserGuesses(){
        return userGuesses;
    }
    public boolean checkWin(){
        return correctGuessCounter == correctWord.length();
    }

    public int getCorrectGuessCounter(){
        return correctGuessCounter;
    }
    public void setCorrectWord(String word){
        correctWord = word;
        wordLength = word.length();
        guessedWord = new ArrayList<>();

        guessedWord.add("_");
    }
    public void gettingGuessedWord(String filename) throws FileNotFoundException {
        readWords readWords = new readWords();
        List<String> lstWords = readWords.wordBank(filename);
        int randomIndex = new Random().nextInt(lstWords.size() / 2) * 2;

        correctWord = lstWords.get(randomIndex).toLowerCase();
        definition = lstWords.get(randomIndex + 1);
        wordLength = correctWord.length();
        List<String> resultingList = new ArrayList<>();
        for (int i = 0; i < wordLength; i++){
            resultingList.add("_");
        }
        guessedWord = resultingList;
    }
    public String getCorrectWord(){
        return correctWord;
    }
    public List<String> getGuessedWord(){
        return guessedWord;
    }
    public String getDefinition(){
        return definition;
    }
    public boolean updateGuess(String letter){
        boolean match = false;
        letter = letter.toLowerCase();
        if (alreadyGuessedLetters.contains(letter)) {
            System.out.println("You have already guessed " + letter);
        } else {
            alreadyGuessedLetters.add(letter);
            for (int i = 0; i < correctWord.length(); i++) {
                if (letter.charAt(0) == correctWord.charAt(i)) {
                    guessedWord.set(i, letter);
                    match = true;
                    correctGuessCounter += 1;
                }
            }
            if (!match) {
                userGuesses += 1;
            }
        }
        System.out.println("Guessed word: " + guessedWord);
        return match;
    }

    public void hangmanGraphicOutput(int guesses){
        switch (guesses) {
            case 0:
                firstGuess();
                break;
            case 1:
                secondGuess();
                break;
            case 2:
                thirdGuess();
                break;
            case 3:
                fourthGuess();
                break;
            case 4:
                fifthGuess();
                break;
            case 5:
                sixthGuess();
                break;
            default:
                finalGuess();
                break;
        }
    }
    private void firstGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }

    private void secondGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }


    private void thirdGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|     /       ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }


    private void fourthGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|     /|      ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }


    private void fifthGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|     /|\\     ");
        System.out.println("|             ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }


    private void sixthGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|     /|\\    ");
        System.out.println("|     /       ");
        System.out.println("|             ");
        System.out.println("\n" + guessedWord);
    }
    private void finalGuess() {
        System.out.println("________      ");
        System.out.println("|      |      ");
        System.out.println("|      0      ");
        System.out.println("|    / | \\     ");
        System.out.println("|     / \\     ");
        System.out.println("\nDUM DUM the word you were trying to guess was: " + correctWord);
    }
}
