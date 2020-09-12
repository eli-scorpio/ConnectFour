import java.util.ArrayList;

public class Connect4Grid2DArray implements Connect4Grid{
    final int rowLength = 7;
    final int columnLength = 6;
    private String[][] board = new String[7][6];
    private int lastColumn = 0;
    private int lastRow = 0;
    private String lastPiece = "";

    public String[][] getBoard(){
        return board;
    }

    public void emptyGrid(){
        for(int rowIndex = 0; rowIndex < columnLength; rowIndex++){
            for(int columnIndex = 0; columnIndex < rowLength; columnIndex++){
                board[columnIndex][rowIndex] = "0";
            }
        }
    }

    @Override
    public String toString(){
        String boardString = "\n          BOARD     \n";
        boardString  += "   0  1  2  3  4  5  6\n";
        int rowCount = -1;
        for(int row = 0; row < columnLength; row++){
            rowCount++;
            boardString += rowCount + "  ";
            for(int column = 0; column < rowLength; column++){
                 boardString += board[column][row] + "  ";
            }
            boardString += "\n";
        }
        return boardString;
    }

    public boolean isValidColumn(int column){
        if(column >= 0 && column < rowLength)
            return true;
        else
            return false;
    }

    public boolean isColumnFull(int column){
        if(board[column][0].equalsIgnoreCase("0"))
            return false;
        else
            return true;
    }

    public void dropPiece(ConnectPlayer player, int column){
        boolean freeSpace = false;
        lastColumn = column;
        lastPiece = player.getC4Piece();
        for(int row = 5; row >= 0 && !freeSpace; row--){
            freeSpace = (board[column][row].equalsIgnoreCase("0"))? true: false;
            board[column][row] = (freeSpace)? player.C4Piece: board[column][row];
            lastRow = row;
        }
        player.setGameWon(didLastPieceConnect4());
    }

    public boolean didLastPieceConnect4(){
        boolean stopLeftCheck = false;
        boolean stopRightCheck = false;
        int pieceCounter = 1;

        //Check Down
        if(lastRow+3 < columnLength && board[lastColumn][lastRow+1].equalsIgnoreCase(lastPiece)){
            for(int i = 1; i <= 3; i++ ){
                if(board[lastColumn][lastRow+i].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                if(pieceCounter == 4)
                    return true;
            }
        }

        //Check Horizontal
        pieceCounter = 1;
        for (int i = 1; i <= 3; i++) {
            if (!stopRightCheck && lastColumn+i < rowLength) {
                if (board[lastColumn + i][lastRow].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopRightCheck = true;
            }
            if (!stopLeftCheck && lastColumn-i >= 0) {
                if (board[lastColumn - i][lastRow].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopLeftCheck = true;
            }
            if (pieceCounter == 4)
                return true;
        }

        //Check Up-Right and Down-Left
        pieceCounter = 1;
        stopLeftCheck = false;
        stopRightCheck = false;
        for (int i = 1; i <= 3; i++) {
            if (!stopRightCheck && lastRow-i >= 0 && lastColumn+i < rowLength) {
                if (board[lastColumn + i][lastRow - i].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopRightCheck = true;
            }
            if (!stopLeftCheck && lastRow+i < columnLength && lastColumn-i >= 0) {
                if (board[lastColumn - i][lastRow + i].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopLeftCheck = true;
            }
            if (pieceCounter == 4)
                return true;
        }


        //Check Up-Left and Down-Right
        pieceCounter = 1;
        stopLeftCheck = false;
        stopRightCheck = false;
        for (int i = 1; i <= 3; i++) {
            if (!stopRightCheck && lastRow+i < columnLength && lastColumn+i < rowLength) {
                if (board[lastColumn + i][lastRow + i].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopRightCheck = true;
            }
            if (!stopLeftCheck && lastRow-i >= 0 && lastColumn-i >= 0) {
                if (board[lastColumn - i][lastRow - i].equalsIgnoreCase(lastPiece))
                    pieceCounter++;
                else
                    stopLeftCheck = true;
            }
            if (pieceCounter == 4)
                return true;
        }

        return false;
    }

    public boolean isGridFull(){
        for(int column = 0; column < rowLength; column++){
            if(board[column][0].equalsIgnoreCase("0"))
                return false;
        }
        return true;
    }
}