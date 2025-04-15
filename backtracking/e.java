public class e {
    private static int count = 0;

    public static boolean nQueens(char board[][], int row) {
        // base case: if all queens are placed
        if (row == board.length) {
            printBoard(board);
            count++;
            return true;
        }

        // column loop
        for (int j = 0; j < board.length; j++) {
            if (isSafe(board, row, j)) {
                board[row][j] = 'Q';
                nQueens(board, row +1);
                // if(nQueens(board, row + 1)){
                //     return true;
                // }; // function call
                board[row][j] = 'x'; // backtracking step
            }
        }
        return false;
    }

    public static boolean isSafe(char[][] board, int row, int col) {
        // check the column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // check the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // check the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void printBoard(char board[][]) {
        System.out.println("---- Chess Board ----");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 4;
        char board[][] = new char[n][n];
    
        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'x';
            }
        }
       if(nQueens(board, 0)){
        System.out.println("Solution is possible:");
        printBoard(board);
       } 
       else{
        System.out.println("Solution is not possible");
       }
        System.out.println("Number of possibilities are: " + count);
    }
}    