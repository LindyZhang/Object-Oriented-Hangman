package hangman;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class displayTest {
    @Test
    public void testingDisplay(){
        display display = new display();
        int guess = 3;
        display.hangmanGraphicOutput(3);
    }

    @Test
    public void testPrints() throws FileNotFoundException {
        display display = new display();
        display.gettingGuessedWord();
        display.hangmanGraphicOutput(4);
    }
}