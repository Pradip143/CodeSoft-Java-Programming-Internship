import java.util.Random;
import java.util.Scanner;

public class Task1_NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        boolean playAgain = true;
        int maxAttempts = 5;
        int score = 0;
        
        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1;

            System.out.println("A number is selected between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts.\n");
            
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                attempts++;
                
                if (userGuess == targetNumber) {
                    System.out.printf("Correct! You guessed the number in %d attempts.\n", attempts);
                    score += attempts;
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.\n");
                } else {
                    System.out.println("Too high! Try again.\n");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + targetNumber);
            }
            
            System.out.println("Your current score: " + score + "\n");
            
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("Thank you for playing! Your final score: " + score);
        
        scanner.close();
    }
}