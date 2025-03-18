public class InputValidator {
    public static boolean isValidCoordinate(String input) {
        if (input.length() < 2 || input.length() > 3) return false;
        char row = input.charAt(0);
        String colStr = input.substring(1);
        if (row < 'A' || row > 'J') return false;

        try {
            int col = Integer.parseInt(colStr);
            return col >= 1 && col <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}