/**
 * @author Jon
 * 
 * followed a guide on youtube on how
 * to solve a sudoku puzzle with the
 * power of recursion
 * 
*/
public class Solver {
    
    //size of the grid used in algorithm
    private static final int GRID_SIZE = 9;
    
    public static void main(String[] args) {
        
        //this represents the board
        //change the numbers for different results
        int [][] board = {
            {0,4,0,0,3,1,5,6,0},
            {0,0,0,0,0,0,0,4,2},
            {8,0,0,2,0,5,0,0,0},
            {3,1,2,4,0,0,0,8,0},
            {9,0,8,0,2,0,7,0,0},
            {0,0,4,0,0,3,2,0,9},
            {7,0,1,0,5,4,9,2,0},
            {0,3,0,7,0,2,8,5,0},
            {5,0,0,0,1,9,0,0,3}
        };
        
        if(solveBoard(board)){
            System.out.println("Solved.");
        } else {
            System.out.println("Unsolveable.");
        }
        
        printBoard(board);
    }
    
    /**
     * helper method
    */
    private static void printBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------------------------");
            }
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(" " + board[row][column] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method checks if the same 
     * number appears in the same row
     * 
     * @param board - the board array
     * @param number - the number in question
     * @param row - the row where the parameter "number" exists
     * @return true - yes the same number does exist in the same row
     * @return false - no the same number does not exist in the same row 
    */
    private static boolean isNumberInRow(int[][] board, int number, int row){
        
        for(int i = 0; i < GRID_SIZE; i++){ //loop through size of the grid (9)
            if(board[row][i] == number){    //check for number in row at position "i" if equal to number
                return true;                //if number exists then return true
            }
        }
        return false;                       //if number not found return false
    }
    
    /**
     * This method checks if the same 
     * number appears in the same row
     * 
     * @param board - the board array
     * @param number - the number in question
     * @param column - the column where the parameter "number" exists
     * @return true - yes the same number does exist in the same row
     * @return false - no the same number does not exist in the same row 
    */
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i = 0; i < GRID_SIZE; i++){ //loop through size of the grid (9)
            if(board[i][column] == number){ //check for number in column at position "i" if equal to number
                return true;                //if number exists then return true
            }
        }
        return false;                       //if number not found return false
    }
    
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
            
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; j < localBoxColumn + 3; j ++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return  !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board,number,column) &&
                !isNumberInBox(board, number, row, column);
    }
    
    private static boolean solveBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board,numberToTry, row, column)){
                            board[row][column] = numberToTry;
                            
                            if(solveBoard(board)){
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}