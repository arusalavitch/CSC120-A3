import java.util.Random;
import java.util.Scanner;

/**
 * A simple program simulating a conversation between the user and the program.
 */
public class Conversation {

    // Canned responses for the program
    private static final String[] cannedResponses = {
            "Interesting, tell me more.",
            "I see. Please continue.",
            "That's fascinating!",
            "Could you elaborate on that?",
            "I'm not sure I understand. Can you explain further?",
            "Tell me something else.",
            "How do you feel about that?",
            "That's quite intriguing!",
            "Please go on.",
            "I'd like to hear more about that."
    };

    // Mirror words used for transforming user input
    public static final String[] mirrorWords = {"I", "me", "am", "you", "my", "your","I'm","you're"};

    /**
     * Main method to run the conversation program.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("How many rounds of conversation would you like to have? ");
        int rounds = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Hello! What's your name?");
        String name = scanner.nextLine();
        System.out.println("Nice to meet you, " + name + "!");

        StringBuilder transcript = new StringBuilder();
        for (int round = 1; round <= rounds; round++) {
            String userInput = scanner.nextLine();
            transcript.append(name).append(": ").append(userInput).append("\n");

            String response;
            if (containsMirrorWords(userInput)) {
                response = mirrorWord(userInput);
            }
            else {
                response = cannedResponses[random.nextInt(cannedResponses.length)];
            }

            System.out.println(response);
            transcript.append(response).append("\n");
        }

        System.out.println("Bye!");
        System.out.println("\nHere's the conversation transcript:");
        System.out.println(transcript);

        scanner.close();
    }

    /**
     * Checks if the user input contains mirror words.
     * @param input The user input to check
     * @return true if the input contains mirror words, false otherwise
     */
    private static boolean containsMirrorWords(String input) {
        input = input.toLowerCase();
        for (String word : mirrorWords) {
            if (input.contains(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Transforms the user input by replacing mirror words and formulating a response.
     * @param input The user input to transform
     * @return The transformed input with a response
     */
    private static String mirrorWord (String input) {
        String[] words = input.split("\\s+");
        Random random = new Random();
        // Mirroring words
        for (int i = 0; i < words.length; i++) {
            switch (words[i]) {
                case "I":
                    words[i] = "you";
                    break;
                case "me":
                    words[i] = "you";
                    break;
                case "am":
                    words[i] = "are";
                    break;
                case "you":
                    words[i] = "I";
                    break;
                case "my":
                    words[i] = "your";
                    break;
                case "your":
                    words[i] = "my";
                    break;
                case "You're":
                    words[i] = "I'm";
                    break;
                case "I'm":
                    words[i] = "you're";
                    break;
                case "are":
                    words[i] = "am";
                    break;
                case "you're":
                    words[i] = "I'm";
                    break;
                case "you?":
                    words[i]="I?";
                    break;
                case "me?":
                    words[i]="you?";
                    break;
                case "I?":
                    words[i]="you?";
                    break;
                case "myself":
                    words[i]="yourself";
                    break;
            }
        }
        // Formulating response
        String response;
        if (words.length > 0 && words[words.length - 1].endsWith("?")) {
            response = cannedResponses[random.nextInt(cannedResponses.length)];;
        } else {
            response = cannedResponses[random.nextInt(cannedResponses.length)];
        }

        return String.join(" ", words) + "? " + response;
    }
}
