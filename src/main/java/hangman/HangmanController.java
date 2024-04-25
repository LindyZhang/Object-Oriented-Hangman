package hangman;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/hangman")
public class HangmanController {

    @Controller
    public class Redirect {
        @GetMapping("/")
        public String redirectToIndex() {
            return "redirect:/index.html";
        }
    }

    private static game gameInstance;
    @PostMapping("/start")
    public String startGame() throws FileNotFoundException {

        gameInstance = new game();
        return "Guess a Letter";
    }

    @PostMapping("/guess")
    public String makeGuess(@RequestParam("letter") char letter) throws IOException {
        if (gameInstance == null) {
            return "Please start the game first.";
        }

        boolean correctGuess = gameInstance.handleTurn(letter);

        if (correctGuess) {
            return "Correct guess!";
        } else {
            return "Incorrect guess!";
        }
    }
}
