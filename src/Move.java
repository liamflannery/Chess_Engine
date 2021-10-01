import java.util.List;

public class Move {
   int[] moves;
   boolean willCheck;
   public int castle;
   List<Square> squares;
   public Move(int[] movesIn, boolean willCheckIn, int inCastle){
       moves = movesIn;
       willCheck = willCheckIn;
       castle = inCastle;
   } 
   public Move(List<Square> squaresIn, boolean willCheckIn){
       squares = squaresIn;
       willCheck = willCheckIn;
   }
   public int[] getMoves(){
    return moves;
   }
   public boolean testCheck(){
       return willCheck;
   }
}
