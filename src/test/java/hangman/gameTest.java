package hangman;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class gameTest {
    @Test
    public void testingGuess() throws IOException {
        game game = new game();
        game.guess();
    }

    @Test
    public void playGame() throws IOException {
        game game = new game();
        game.play();
    }
}