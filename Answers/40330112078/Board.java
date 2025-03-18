class Board {
    private char[][] grid;
    private int size;
    private Ship[][] ships;

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        this.ships = new Ship[size][size];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '~';
                ships[i][j] = null;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        int shipSize = ship.getSize();

        if (horizontal && col + shipSize > size) return false;
        if (!horizontal && row + shipSize > size) return false;

        for (int i = 0; i < shipSize; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            if (ships[r][c] != null) return false;
        }

        for (int i = 0; i < shipSize; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            ships[r][c] = ship;
            grid[r][c] = 'S';
        }

        return true;
    }

    public boolean attack(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Invalid move! Try again.");
            return false;
        }

        if (ships[row][col] != null) {
            if (grid[row][col] == 'X') {
                System.out.println("Already hit this spot!");
                return false;
            }
            grid[row][col] = 'X';
            ships[row][col].hit();
            return true;
        } else {
            if (grid[row][col] == 'O') {
                System.out.println("Already missed this spot!");
                return false;
            }
            grid[row][col] = 'O';
            return false;
        }
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ships[i][j] != null && !ships[i][j].isSunk()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display(boolean hideShips) {
        System.out.print("  ");
        for (int col = 0; col < size; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < size; col++) {
                if (hideShips && grid[row][col] == 'S') {
                    System.out.print("~ "); // مخفی کردن کشتی‌ها
                } else {
                    System.out.print(grid[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
}
