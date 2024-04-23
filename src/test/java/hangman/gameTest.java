package hangman;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class gameTest {

    @Test
    public void playGame() throws IOException {
        game game = new game();
        game.setCorrectWord("s");
        String input = "s\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        hangman.game.play(game);
        assertTrue(game.getHasWon());
    }
}
