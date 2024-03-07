
import java.util.*;
/**
 * Write a description of class HumanPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HumanPlayer extends APlayer
{
    public HumanPlayer(char symbol, Game game){
        super (symbol, game);
    }

    public Move pickMove(){
        Move res = null;

        Scanner scan = new Scanner(System.in);
        while(true){
            //Ask the player to pick a move

            System.out.println("pick move, input row(letter) and col(int),");
            String input = scan.nextLine().toUpperCase();

            if (input.compareTo("QUIT") == 0){
                return null;
            }
            if (input.length() != 2){
                System.out.println("Invalid Move");
                continue;
            }

            int row = input.charAt(0) - 'A';
            int col = input.charAt(1) - '1';
            res = new Move(row,col);
            System.out.println(res.row + res.col);
            char isValid = game.isValidMove(res);
            switch (isValid ){
                case 'V':
                    return res;

                case 'R':
                    System.out.println("Invalid row");
                    break;
                case 'C':
                    System.out.println("Invalid col");
                    break;
                case 'O':
                    System.out.println("Invalid occupied");
                    break;
            }

        }
    }
}
