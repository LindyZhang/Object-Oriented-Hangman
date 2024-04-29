package hangman;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class readWordsTest {

    @Test
    public void testWordBank() throws FileNotFoundException {
        readWords readWords = new readWords();
        readWords.wordBank( "src/main/java/hangman/words.txt");
    }
}