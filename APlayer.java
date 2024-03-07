
/**
 * Write a description of class APlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class APlayer
{
    char symbol;
    Game game; 

    public APlayer (char symbol, Game game) {
        this.symbol = symbol;
        this.game = game;
    }

    // Access the method that gets letter of the particular player
    public char getLetter () {
        return symbol;
    }

    public Move pickMove(){
        return null;

    }
    
}
