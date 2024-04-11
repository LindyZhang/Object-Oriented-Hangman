package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class readWords {
    public List<String> wordBank() throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/java/hangman/words.txt"));
        scanner.useDelimiter(".\n");

        while (scanner.hasNext()){
            String next = scanner.next();
            words.add(next);
        }
        scanner.close();
        return words;
    }
}
