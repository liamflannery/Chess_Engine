import java.util.List;
import java.util.Random;

public class Computer extends Competitor {
    Piece pieceToMove;
    Move move;
    MoveMaker moveMaker;
    public Computer(Stage inStage, List<Piece> inMyPieces, List<Piece> inOpPieces, Stage.Turn myTurn) {
        super(inStage, inMyPieces, inOpPieces, myTurn);
        moveMaker = new MoveMaker();
        //TODO Auto-generated constructor stub
    }
    @Override
    public Stage.Turn move(int x, int y){
        
        Random rand = new Random();
        pieceToMove = myPieces.get(rand.nextInt(myPieces.size()));
        Random rand2 = new Random();
        move = board.legalMoves(pieceToMove, check, myPieces);
        while(move.squares.size() == 0) {
            pieceToMove = myPieces.get(rand.nextInt(myPieces.size()));
            move = board.legalMoves(pieceToMove, check, myPieces);
        }

        
        try{
            moveMaker.move(move.squares.get(rand2.nextInt(move.squares.size())), pieceToMove, opPieces, myPieces, board, check, move.kCastle, move.qCastle);
        }
        catch(IllegalArgumentException e){
            System.out.println(move.squares.toString());
        }
        
        unSelectPieces();
       
        return opTurn;
    }

   
    
}
