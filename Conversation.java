import java.util.Random;
import java.util.Scanner;

public class Conversation {
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

    public static final String[] mirrorWords = {"I", "me", "am", "you", "my", "your","I'm","you're"};

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
                response = mirrorInput(userInput);
            } else {
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

    private static boolean containsMirrorWords(String input) {
        input = input.toLowerCase();
        for (String word : mirrorWords) {
            if (input.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private static String mirrorInput(String input) {
        String[] words = input.split("\\s+");
        StringBuilder mirroredResponse = new StringBuilder();
        for (String word : words) {
            if(containsMirrorWords(input)){
                mirroredResponse.append(mirrorWord(word)).append(" ").append(word);
            }

        else{
            mirroredResponse.append(word);
            }
        }
        return mirroredResponse.toString().trim();
        
    }
    

    private static String mirrorWord (String input) {
        switch (input.toLowerCase()) {
            case "i":
                return "you";
            case "me":
                return "you";
            case "am":
                return "are";
            case "you":
                return "I";
            case "my":
                return "your";
            case "your":
                return "my";
            case "i'm":
                return "you're";
            case "you're":
                return "I'm";
            default:
                Random random = new Random();
                return cannedResponses[random.nextInt(cannedResponses.length)];
        }
    }
}
