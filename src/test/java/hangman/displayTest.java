package hangman;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class displayTest {
    @Test
    public void testingDisplay(){
        display display = new display();

        for(int i = 0 ; i < 7; i++){
            display.hangmanGraphicOutput(i);
        }
    }

    @Test
    public void testPrints() throws FileNotFoundException {
        display display = new display();
        display.gettingGuessedWord( "src/main/java/hangman/words.txt");
        display.hangmanGraphicOutput(4);
    }
}