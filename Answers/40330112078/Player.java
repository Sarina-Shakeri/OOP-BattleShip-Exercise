import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private boolean isAI;

    public Player(String name, int boardSize, boolean isAI) {
        this.name = name;
        this.board = new Board(boardSize);
        this.isAI = isAI;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Coordinate[] makeMoves() {
        if (isAI) {
            return new Coordinate[]{AIPlayer.generateMove(board.getSize())};
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print(name + ", enter your moves (e.g., A5 B7 C3): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            Coordinate[] moves = new Coordinate[parts.length];
            for (int i = 0; i < parts.length; i++) {
                if (InputValidator.isValidCoordinate(parts[i])) {
                    moves[i] = new Coordinate(parts[i].charAt(0) - 'A', Integer.parseInt(parts[i].substring(1)) - 1);
                } else {
                    System.out.println("Invalid input: " + parts[i]);
                    return makeMoves(); // دریافت دوباره ورودی در صورت خطا
                }
            }
            return moves;
        }
    }
}