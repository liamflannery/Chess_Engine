public class Move {
   int[] moves;
   boolean willCheck;
   public Move(int[] movesIn, boolean willCheckIn){
       moves = movesIn;
       willCheck = willCheckIn;
   } 
   public int[] getMoves(){
    return moves;
   }
   public boolean testCheck(){
       return willCheck;
   }
}
