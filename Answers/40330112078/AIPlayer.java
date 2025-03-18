import java.util.Random;

public class AIPlayer {
    public static Coordinate generateMove(int boardSize) {
        Random rand = new Random();
        int row = rand.nextInt(boardSize);
        int col = rand.nextInt(boardSize);
        return new Coordinate(row, col);
    }
}