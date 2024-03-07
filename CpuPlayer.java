
/**
 * Write a description of class CpuPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CpuPlayer extends APlayer{
    public CpuPlayer(char symbol, Game game){
        super(symbol, game);
        
    }
     public char getSymbol () {
        return symbol;
    }
    public Move pickMove(){
        int row = (int) Math.floor(Math.random() * (game.getboardSize())); 
        
        int col = (int) Math.floor(Math.random() * (game.getboardSize()));
        
        while (game.board[row][col] != game.SYMBUL_BLANK){
            return pickMove();
        }
        return new Move(row, col);
    }
}
   

