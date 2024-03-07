import java.util.*; 
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game{

    // creat a 2D board using 2d array
    char [][] board;
    // size of theboard
    int boardSize;
    APlayer [] typeplayers;
    final char SYMBUL_BLANK = (' ');
    final char SYMBUL_CPU = ('O');
    final char SYMBUL_HUMAN = ('X');

    // constructure for game
    public Game(int boardSize){
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++)

            for (int col = 0; col < boardSize; col++)

                board[row][col] = ' ';

    }

    public String toString(){

        String laststring = "";
        for (int col = 1; col <= this.boardSize; col++)
        {
            laststring += "   "+ col;
        }
        laststring += "\n";

        for (int row = 0; row < boardSize; row++) 
        { 
            laststring += (char)('A' + row) + " ";
            for (int col = 0; col < boardSize; col ++){
                laststring += " " + (board[row][col]);
                if (col != boardSize - 1){
                    laststring += " |";
                }
            }
            if (row!= boardSize - 1){
                laststring += "\n  "; 
                for (int i = 1; i <= boardSize - 1; i++){
                    laststring += "---|";
                }
                laststring += "---";
            }
            laststring += "\n";
        }
        return laststring;
    }

    public int getboardSize(){
        return boardSize;
    }
    char validM;
    protected void resetGame(){
        this.board = new char[boardSize][boardSize];
        for (int i=0; i < this.board.length; i++){

            for (int j=0; j < board[i].length; j++){
                this.board[i][j] = SYMBUL_BLANK;
            }
        }
    }

    protected boolean excutiveMove(Move move, char symbol){
        if (this.isValidMove(move) != 'V'){
            return false;
        }
        else{
            board[move.row][move.col] = symbol;
        }
        return true;
    }

    public char isValidMove(Move move){
        char res = 'V';
        System.out.println(move.row+move.col);
        if (move.row > boardSize || move.row < 0){
            return 'R';
        }
        else if(move.col > boardSize || move.row < 0){
            return 'C';
        }
        else if(board[move.row][move.col] != ' '){
            return 'O';
        }
        return res;
    }

    public char getGameStats(){
        int rowchecker = 0;
        for(int i = 0; i < boardSize; i++){
            rowchecker = 0;
            if (board[i][0] == SYMBUL_HUMAN){
                while(board[i][rowchecker] == SYMBUL_HUMAN){
                    rowchecker++;

                    if (rowchecker == boardSize){
                        return 'X';
                    }
                }
            }
            else if(board[i][rowchecker] == SYMBUL_CPU){
                while(board[i][rowchecker] == SYMBUL_CPU){
                    rowchecker++;
                    if (rowchecker == boardSize){
                        return 'O';
                    }
                }
            }
        }
        // variable help walker through cols
        int colchecker = 0;
        for(int i = 0; i < boardSize; i++){
            //initialise the variable colcheck to 0 to walk theough the next col, when it's at the end
            colchecker = 0;
            if (board[0][i] == SYMBUL_HUMAN){
                while (board[colchecker][i] == SYMBUL_HUMAN){
                    colchecker++;

                    //return 'x' if you are at the end of the ga,e and all the symbols are human
                    if(colchecker == boardSize){
                        return 'X';
                    }
                }
            }
            //if CPU is the first symbul in the diagonal
            int diagonal = 0;
            if(board[0][0] == SYMBUL_CPU){
                while(board[diagonal][diagonal] == SYMBUL_CPU){
                    diagonal++;

                    //return 'O' if you are at the end of diagonal and all the symbuls are CPU
                    if(diagonal == boardSize){
                        return 'O';
                    }
                }
            }
            int rowdiagonal = 0;
            int coldiagonal = boardSize - 1;
            //if human player is the first symbul in diagonal
            if(board[0][boardSize -1] == SYMBUL_HUMAN){
                while (board[coldiagonal][rowdiagonal] == SYMBUL_HUMAN){
                    coldiagonal--;
                    rowdiagonal++;

                    //return 'O' if you are at the end of the diagonal and al symbuls are CPU
                    if(rowdiagonal == boardSize){
                        return 'O';
                    }
                }
            }
            int tiechecker = 0;
            for(int j = 0; j < boardSize; j++){
                tiechecker = 0;

                //if the first posotion in the row is not blank
                if(board[i][0] != SYMBUL_BLANK){
                    while (board[i][tiechecker] != SYMBUL_BLANK){
                        tiechecker++;

                        //if the tiechecker reached at the end of the board Break the loop
                        if(tiechecker == boardSize){
                            break;
                        }
                    }
                }
            }
            //
            if(tiechecker < boardSize){
                break;
            }
            //return 'T' if we are at the end of the board and all positions have been filled
            if (i == boardSize - 1){
                return 'T';
            }
        }
        return '?';
    }
    public char playSingleGame(){
        
        System.out.println(boardSize);
        char res = ' ';
        //creat the playes cpu and human
        HumanPlayer human = new HumanPlayer(SYMBUL_HUMAN, this);
        CpuPlayer cpu = new CpuPlayer( SYMBUL_CPU, this);
        //select randomly which player plays first
        if (Math.random() > 0.5){
            this.excutiveMove(cpu.pickMove(), SYMBUL_CPU);
        }
            while (this.getGameStats() == '?'){
                
                //this.excutiveMove(Cpumove, SYMBUL_CPU);
                // checking if game is still going
                if(this.getGameStats() != '?'){
                    break;
                }
                System.out.println(this.toString());
                //pick up the human move
                Move humanMove = human.pickMove();
                //end the game if the human is quit
                if(humanMove == null)
                    return 'Q';

                this.excutiveMove(humanMove, SYMBUL_HUMAN);
                System.out.println(this);

                //Break from the loop if the game is over
                if(this.getGameStats() != '?')
                    break;

                Move cpuMove = cpu.pickMove();
                this.excutiveMove(cpuMove, SYMBUL_CPU);
                System.out.println("get.toString");
                
            }

        if (this.getGameStats() == 'T'){
            return 'T';
        }
        else if(this.getGameStats() == 'X'){
            return 'X';
        }
        else if(this.getGameStats() == 'O'){
            return 'C';

        
        }
        System.out.println(this.toString());
    return res;
} 

    public static void main(String[] args){
        System.out.println("Welcome to Tic Tac Toe by Hamida Rahimi");
        //Game states here 
        //GameStats GS = new GameStats();
        int fBoard = 0;

        try{
            fBoard = 3;

        }
        catch(Exception e){
            System.out.println("Invalid boardSize. No int by using default value of 3 instead");

        }
        if ((fBoard  < 1) || (fBoard >= 10)){
            System.out.println("Error - Invalid board size. Starting 3x3 game...");
            fBoard = 3;
        }  

        GameStats stats= new GameStats();

        Game game = new Game(fBoard); 
        while(true){
            char result = game.playSingleGame();
  
            if(result == 'Q')
                break;
            else if(result == 'X'){
                //if the game wins update the stats and print out a message
                System.out.println("Congrats, you won! Round whatever again?");
                stats.updatWins();
            }
            else if(result == 'O'){
                System.out.println("cpu wins, get rekt humans");
                stats.updatLosses();
            }
            else{
                System.out.println("#Tieisforsuckers");
                stats.updattie();
            }
            game.resetGame();
            break;
        }
        System.out.println(stats);
        System.out.println("Enjoy");
        //if the game wins update the stats and print out a message
    }

}

