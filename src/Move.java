import java.util.List;

public class Move {
   int[] moves;
   boolean willCheck;
   List<Square> squares;
   public Move(int[] movesIn, boolean willCheckIn){
       moves = movesIn;
       willCheck = willCheckIn;
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
