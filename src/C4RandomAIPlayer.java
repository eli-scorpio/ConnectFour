import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {

    C4RandomAIPlayer(Connect4Grid2DArray Connect4Grid){
        super(Connect4Grid);
    }

    @Override
    public int selectColumn(){
        Random generator = new Random();
        boolean error;
        int randomColumn = generator.nextInt(7);
        System.out.println("Column selected: " + randomColumn);
        error = !Connect4Grid.isValidColumn(randomColumn) || Connect4Grid.isColumnFull(randomColumn);

        while(error){
            System.out.println("Invalid Column. Please Select new Column!");
            randomColumn = generator.nextInt(7);
            System.out.println("\nNew Column selected: " + randomColumn);
            error = !Connect4Grid.isValidColumn(randomColumn) || Connect4Grid.isColumnFull(randomColumn);
        }
        return randomColumn;
    }
}