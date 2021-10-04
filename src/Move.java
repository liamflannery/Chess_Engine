import java.util.List;

public class Move {
   int[] moves;
   boolean willCheck;
   public int kCastle;
   public int qCastle;
   List<Square> squares;
   public Move(int[] movesIn, boolean willCheckIn, int inKCastle, int inQCastle){
       moves = movesIn;
       willCheck = willCheckIn;
       kCastle = inKCastle;
       qCastle = inQCastle;
   } 
   public Move(List<Square> squaresIn, boolean willCheckIn, int inKCastle, int inQCastle){
       squares = squaresIn;
       willCheck = willCheckIn;
       kCastle = inKCastle;
       qCastle = inQCastle;
   }
   public int[] getMoves(){
    return moves;
   }
   public boolean testCheck(){
       return willCheck;
   }
}
