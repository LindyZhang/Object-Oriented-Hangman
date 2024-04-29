package hangman;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class gameTest {

    @Test
    public void testHandleTurn() throws FileNotFoundException {
        game game = new game();
        game.setDifficulty("src/main/java/hangman/words.txt");
        boolean result = game.handleTurn('z');
        assertFalse(result);
        assertFalse(game.getHasWon());

    }
}
