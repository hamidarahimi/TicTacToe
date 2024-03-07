
/**
 * Write a description of class GameStats here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameStats
{
   int win = 0;
   int loss = 0;
   int tie = 0;
   // constructure
   public GameStats(){
       
   }
   // keep track their stats using 2 differents methods.
   public void updatWins(){
       this.win++;
   }
   
   public void updatLosses(){
       this.loss++;
   }
   public void updattie(){
       this.tie++;
   }
   
   public int getTotalGamesPlayed(){
       return this.win + this.loss;
   }
   
   // Stats are here.
   public String toString(){
       // Ex Output:
       // Amount of games played: 5
       // Win Part: .5
       String gamePlayed = "The amount of tne gsmes played is " + getTotalGamesPlayed();
       String WinPart = "Your win ratio is " + ((double)(win)/loss);
       String AmountGame = "Your game stats are " + win + " Win " + loss + " loss ";
       
       return gamePlayed + "\n" + WinPart + "\n" + AmountGame;
       }
    }
   

