import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] choices = {"rock", "paper", "scissors"};
        
        System.out.println("Welcome to Utkarsh's Rock, Paper, Scissors Game!");
        
        while (true) {
            // Player's turn
            System.out.print("Enter your choice (rock, paper, scissors): ");
            String playerChoice = scanner.nextLine().toLowerCase();
            
            if (!isValidChoice(playerChoice)) {
                System.out.println("Invalid choice. Please enter rock, paper, or scissors.");
                continue;
            }
            
            // Computer's turn
            int randomIndex = (int) (Math.random() * choices.length);
            String computerChoice = choices[randomIndex];
            
            // Determine winner
            String result = determineWinner(playerChoice, computerChoice);
            System.out.println("Computer chose: " + computerChoice);
            System.out.println(result);
            
            // Ask for replay
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }
        
        scanner.close();
        System.out.println("Thanks for playing!");
    }
    
    // Method to check if player's choice is valid
    public static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }
    
    // Method to determine the winner
    public static String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (playerChoice.equals("rock") && computerChoice.equals("scissors") ||
                   playerChoice.equals("paper") && computerChoice.equals("rock") ||
                   playerChoice.equals("scissors") && computerChoice.equals("paper")) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}

