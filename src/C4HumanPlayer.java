import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {


    C4HumanPlayer(Connect4Grid2DArray Connect4Grid){
        super(Connect4Grid);
    }

    @Override
    public int selectColumn(){
        Scanner inputScanner = new Scanner(System.in);
        boolean error = true;
        int column = -1;
        System.out.println("Select a column: ");
        if (inputScanner.hasNextInt()) {
            column = inputScanner.nextInt();
            error = !Connect4Grid.isValidColumn(column) || Connect4Grid.isColumnFull(column);
        }
        else
            inputScanner.next();

        while(error) {
            System.out.println("\nInvalid Column. Please Select new Column!");
            System.out.print("New Column: ");
            if (inputScanner.hasNextInt()) {
                column = inputScanner.nextInt();
                error = !Connect4Grid.isValidColumn(column) || Connect4Grid.isColumnFull(column);
            } else
                inputScanner.next();
        }
        return column;
    }




}