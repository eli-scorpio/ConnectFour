import java.util.Random;

public abstract class ConnectPlayer {
    protected String C4Piece;
    protected boolean gameWon;
    public Connect4Grid2DArray Connect4Grid;

    ConnectPlayer(Connect4Grid2DArray Connect4Grid){
        this.Connect4Grid = Connect4Grid;
    }

    public abstract int selectColumn();

    public void setC4Piece(String colour){
        C4Piece = colour;
    }

    public String getC4Piece(){
        return C4Piece;
    }

    public void setGameWon(boolean bool){
        gameWon = bool;
    }

    public boolean getGameWon(){
        return gameWon;
    }
    
}