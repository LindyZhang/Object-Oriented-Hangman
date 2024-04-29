package hangman;

public class Observer implements IObserver {
    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.WELCOME) {
            speak("Welcome to Object Oriented Hangman!");
        } else if (eventType == EventType.GAME_WON) {
            speak("YOU WON!");
        } else if (eventType == EventType.GAME_LOST) {
            speak("GAME OVER!, you lost");
        } else if (eventType == EventType.CORRECT_GUESS) {
            speak("Correct Guess!");
        } else if (eventType == EventType.INCORRECT_GUESS) {
            speak("Incorrect Guess");
        }
    }

    private void speak(String text) {
        try {
            // MAC IMPLEMENTATION
            String[] cmd = {"say", text};
            Runtime.getRuntime().exec(cmd);

            // WINDOWS IMPLEMENTATION
            // Runtime.getRuntime().exec("nircmd.exe speak text \"" + text + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

