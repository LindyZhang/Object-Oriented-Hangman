package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class readWords {
    public List<String> wordBank(String filename) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        scanner.useDelimiter("\\n");

        while (scanner.hasNext()){
            String next = scanner.next();
            words.add(next);
        }
        scanner.close();
        return words;
    }
}
