package com.interviews.luveen;

/**
 * Implement the state management for a game of Connect Four.
 *
 * @interview Triplebyte
 */
public class ConnectFour {
    /*enum SlotState {
        EMPTY,
        RED,
        YELLOW
    }

    enum Player {
        ONE(SlotState.RED),
        TWO(SlotState.YELLOW);

        private SlotState color;

        public SlotState getColor() {
            return this.color;
        }

        Player(SlotState color) {
            if (color == SlotState.EMPTY) {
                throw new UnsupportedOperationException("Unsupported slot state for a player!");
            }

            this.color = color;
        }
    }

    private SlotState[][] game;
    private int[] nextSlot;
    boolean isGameOver;
    Player curPlayer;

    public static void main(String[] args) {
        ConnectFour g1 = new ConnectFour();
        int col;

        while (!g1.isGameOver) {
            // prompt user to enter column number
            // validate input
            boolean isValidInput;

            if (isValidInput) {
                g1.updateState(col - 1);
            }

            g1.print();
        }

        // print g1.curPlayer wins!
    }

    private void print() {

    }

    private void updateState(int col) {
        // update game state
        game[nextSlot[col]][col] = curPlayer.getColor();

        if (nextSlot[col] > 0) {
            nextSlot[col]--;
        }

        // check for win condition
        for  (Player p : Player.values()) {
            SlotState color = p.getColor();
            boolean hasWon = false;

            // horizontal validation
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    int end = j + 3;
                    boolean isWindowSameColor = true;

                    for (int k = j; k <= end; k++) {
                        if (game[i][k] != color) {
                            isWindowSameColor = false;
                            break;
                        }
                    }

                    if (isWindowSameColor) {
                        this.isGameOver = true;
                        break; //update game state accordingly if needed return instead - easier??
                    }
                }
            }

            // vertical validation
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 2; j++) {
                    int end = j + 3;
                    boolean isWindowSameColor = true;

                    for (int k = j; k <= end; k++) {
                        if (game[k][i] != color) {
                            isWindowSameColor = false;
                            break;
                        }
                    }

                    if (isWindowSameColor) {
                        this.isGameOver = true;
                    }
                }
            }

            // downward diagonal
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    boolean isWindowSameColor = true;

                    for (int k = 0; k < 4; k++) {
                        if (game[i + k][j + k] != color) {
                            isWindowSameColor = false;
                            break;
                        }
                    }

                    if (isWindowSameColor) {
                        this.isGameOver = true;
                    }
                }
            }

            // upward diagonal

        }

    }

    public ConnectFour() {
        game = new SlotState[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                game[i][j] = SlotState.EMPTY;
            }
        }

        nextSlot = new int[7];

        for (int k = 0; k < 7; k++) {
            nextSlot[k] = 5;
        }
    }*/
}
