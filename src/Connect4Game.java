/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop.
If the user decides to play then:
1. Connect4Grid2DArray is created using the Connect4Grid interface,
2. the two players are initialised - must specify the type to be ConnectPlayer, and
3. the game starts.
 In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface.
 Finally a check is performed to determine a win.
mark: 35
Comment: I have done all the above

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
mark: 10
Comment: I have done the above

Connect4Grid2DArray class (25 marks)
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid.
It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.
It provides as implementation of the method to check whether there is a win.
mark: 25
Comment: I have done the above

ConnectPlayer abstract class (10 marks)
My class provides at lest one non-abstract method and at least one abstract method.
mark: 10
Comment: I have done the above

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
mark: 10
Comment: I have done the above and used one abstract method

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality.
mark: 10
Comment: I have done the above and used one abstract method

Total Marks out of 100: 100

*/

import java.util.ArrayList;
import java.util.Scanner;

public class Connect4Game {
    public Connect4Grid2DArray Connect4Grid = new Connect4Grid2DArray();
    private C4HumanPlayer humanPlayer;
    private C4RandomAIPlayer randomAIPlayer;
    private ArrayList<ConnectPlayer> players = new ArrayList<ConnectPlayer>();

    public static void main(String[] args){
        Connect4Game game = new Connect4Game();

        System.out.println("                                                             ");
        System.out.println("         _____________________     ______                    ");
        System.out.println("        |                     |   |      |       ______      ");
        System.out.println("        |       ______________|   |      |      |      |     ");
        System.out.println("        |      |                  |      |      |      |     ");
        System.out.println("        |      |                  |      |      |      |     ");
        System.out.println("        |      |                  |      |______|      |___  ");
        System.out.println("        |      |                  |                        | ");
        System.out.println("        |      |                  |______________        __| ");
        System.out.println("        |      |______________                   |      |    ");
        System.out.println("        |                     |                  |      |    ");
        System.out.println("        |_____________________|                  |______|    ");

        System.out.println("\nThe objective of the game is to connect four of one's own discs of the same colour " +
                "\nnext to each other vertically, horizontally, or diagonally before your opponent. " +
                "\nThe game is a draw if the vertical grid is filled with player discs and no player has won\n");
        boolean gameOver = false;
        while(!gameOver) {
            game.Connect4Grid.emptyGrid();
            game.players.clear();
            Scanner inputScanner = new Scanner(System.in);
            boolean error = true;
            String playerType;
            System.out.println("\nSpecify if a player is human by typing human or if a player is ai by typing ai");
            int playerNum = 1;

            while (error) {
                System.out.print("Player " + playerNum + ": ");
                playerType = inputScanner.nextLine();
                playerType.trim();
                if (playerType.equalsIgnoreCase("human") || playerType.equalsIgnoreCase("ai")) {
                    if (playerType.equalsIgnoreCase("human")) {
                        game.humanPlayer = new C4HumanPlayer(game.Connect4Grid);
                        game.humanPlayer.setC4Piece((playerNum == 1) ? "R" : "Y");
                        game.players.add(game.humanPlayer);
                    } else {
                        game.randomAIPlayer = new C4RandomAIPlayer(game.Connect4Grid);
                        game.randomAIPlayer.setC4Piece((playerNum == 1) ? "R" : "Y");
                        game.players.add(game.randomAIPlayer);
                    }

                    error = (playerNum == 2) ? false : true;
                    playerNum += (playerNum == 1) ? 1 : 0;
                }
            }

            boolean gameWon = false;
            boolean draw = false;
            int playerTurn = 0;
            System.out.println(game.Connect4Grid.toString());

            while(!gameWon && !draw){
                if(playerTurn == 0) {
                    System.out.println("\nPlayer 1: ");
                    game.Connect4Grid.dropPiece(game.players.get(playerTurn), game.players.get(playerTurn).selectColumn());
                }
                else {
                    System.out.println("\nPlayer 2: ");
                    game.Connect4Grid.dropPiece(game.players.get(playerTurn), game.players.get(playerTurn).selectColumn());
                }

                System.out.println(game.Connect4Grid.toString());


                if(game.players.get(playerTurn).getGameWon()){
                    gameWon = true;
                    System.out.println("Player " + (playerTurn+1) + " Wins!");
                }
                else if(game.Connect4Grid.isGridFull()){
                    draw = true;
                    System.out.println("Draw!");
                }

                playerTurn += (playerTurn == 1)? -1: 1;
            }

            if(gameWon || draw){
                String userInput = "";
                System.out.println("\nWould you like to play again?(Yes/No)");
                userInput = inputScanner.nextLine();
                userInput.trim();
                error = (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("no"))? false: true;
                while(error) {
                    System.out.println("\nInvalid input. Please try again!");
                    System.out.println("Would you like to play again?(Yes/No)");
                    userInput = inputScanner.nextLine();
                    userInput.trim();
                    error = (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("no"))? false: true;
                }
                gameOver = (userInput.equalsIgnoreCase("yes"))? false: true;
                if(gameOver)
                    System.out.println("\nThank you for playing!");
            }
        }



    }
}