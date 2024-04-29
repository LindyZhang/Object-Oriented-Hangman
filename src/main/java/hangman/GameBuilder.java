package hangman;

import java.io.FileNotFoundException;

public class GameBuilder {
    private String wordsFile;

    public GameBuilder() {
        this.wordsFile = "src/main/java/hangman/words.txt";
    }

    public GameBuilder setHardMode(boolean hardMode) {
        if (hardMode) {
            this.wordsFile = "src/main/java/hangman/hardwords.txt";
        }
        return this;
    }

    public game build() throws FileNotFoundException {
        game newGame = new game();
        newGame.setDifficulty(wordsFile);
        return newGame;
    }
}
