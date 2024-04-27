package hangman;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


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
    public ResponseEntity<Map<String, Object>> startGame() throws FileNotFoundException {

        gameInstance = new game();
        Map<String, Object> response = new HashMap<>();
        response.put("guessedWordStatus", gameInstance.gameDisplay.getGuessedWord());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guess")
    public ResponseEntity<Map<String, Object>> makeGuess(@RequestParam("letter") char letter) throws IOException {
        if (gameInstance == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Please start the game first."));
        }

        boolean correctGuess = gameInstance.handleTurn(letter);
        Map<String, Object> response = new HashMap<>();
        response.put("result", correctGuess ? "Correct guess!" : "Incorrect guess!");
        response.put("remainingGuesses", 6-gameInstance.gameDisplay.getUserGuesses());
        response.put("guessedWordStatus", gameInstance.gameDisplay.getGuessedWord());

        if(gameInstance.gameDisplay.checkWin()){
            response.put("gameOver", true);
            response.put("gameWon", true);
        }
        if(gameInstance.gameDisplay.getUserGuesses() >= 6) {
            System.out.println("Game over");
            response.put("gameOver", true);
        }

        return ResponseEntity.ok(response);
}
    @PostMapping("/hint")
    public ResponseEntity<Map<String, String>> getHint() {
        if (gameInstance == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Please start the game first."));
        }

        String hint = gameInstance.getHint(); // Assuming the hint is the definition

        Map<String, String> response = new HashMap<>();
        response.put("definition", hint); // Using "definition" as the key

        return ResponseEntity.ok(response);
    }
}
