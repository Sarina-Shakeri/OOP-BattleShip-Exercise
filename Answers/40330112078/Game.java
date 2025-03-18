import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to BattleShip Game!");
        Game game = new Game();
        game.start();
    }

    private Player player1;
    private Player player2;
    private boolean gameOver;
    private static final int BOARD_SIZE = 10;
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 2};

    public Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player 1 name: ");
        String name1 = scanner.nextLine();

        System.out.print("Enter Player 2 name (or type 'AI' for computer opponent): ");
        String name2 = scanner.nextLine();
        boolean isAI = name2.equalsIgnoreCase("AI");

        player1 = new Player(name1, BOARD_SIZE, false);
        player2 = new Player(isAI ? "AI" : name2, BOARD_SIZE, isAI);

        gameOver = false;
    }

    public void start() {
        placeShips(player1);
        placeShips(player2);

        boolean playAgain;
        do {
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private void placeShips(Player player) {
        Board board = player.getBoard();
        Random rand = new Random();

        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int row = rand.nextInt(BOARD_SIZE);
                int col = rand.nextInt(BOARD_SIZE);
                boolean horizontal = rand.nextBoolean();

                placed = board.placeShip(new Ship(size), row, col, horizontal);
            }
        }
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        return new java.util.Scanner(System.in).next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        System.out.println("Starting game...");
        while (!gameOver) {
            takeTurn(player1, player2);
            if (checkGameOver()) break;
            takeTurn(player2, player1);
            if (checkGameOver()) break;
        }
        System.out.println("Game Over!");
    }

    private void takeTurn(Player current, Player opponent) {
        System.out.println(current.getName() + "'s turn:");
        opponent.getBoard().display(true);

        Coordinate[] moves = current.makeMoves(); // دریافت چندین حرکت

        for (Coordinate move : moves) {
            boolean hit = opponent.getBoard().attack(move.getRow(), move.getCol());
            System.out.println((char) ('A' + move.getRow()) + "" + (move.getCol() + 1) + ": " + (hit ? "Hit!" : "Miss!"));
        }
    }

    private boolean checkGameOver() {
        if (player1.getBoard().allShipsSunk()) {
            System.out.println(player2.getName() + " wins!");
            gameOver = true;
            return true;
        } else if (player2.getBoard().allShipsSunk()) {
            System.out.println(player1.getName() + " wins!");
            gameOver = true;
            return true;
        }
        return false;
    }
}