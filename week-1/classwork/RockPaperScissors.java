import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static String playRound(String playerMove, String computerMove) {
        playerMove = playerMove.trim().toLowerCase();
        computerMove = computerMove.trim().toLowerCase();

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
            (playerMove.equals("paper") && computerMove.equals("rock")) ||
            (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int totalRounds = 5;

        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] results = new String[totalRounds];

        Scanner scanner = new Scanner(System.in);
        int wins = 0;
        int losses = 0;
        int draws = 0;

        String[] demoMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        for (int i = 0; i < totalRounds; i++) {
            playerMoves[i] = demoMoves[i];
            computerMoves[i] = moves[random.nextInt(moves.length)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-15s | %-15s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-10s | %-15s | %-15s | %-15s%n",
                    "Round " + (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("---------------------------------------------------------------");

        double winPercentage = ((double) wins / totalRounds) * 100.0;
        System.out.printf("Final Summary (after %d rounds)%n", totalRounds);
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);

        scanner.close();
    }
}
