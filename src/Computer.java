import java.util.List;
import java.util.Random;

public class Computer extends Competitor {
    Piece pieceToMove;
    Move move;
    MoveMaker moveMaker;
    MoveDecider moveDecider;
    int depth;
    public Computer(Stage inStage, List<Piece> inMyPieces, List<Piece> inOpPieces, Stage.Turn myTurn, int inDepth) {
        super(inStage, inMyPieces, inOpPieces, myTurn);
        moveMaker = new MoveMaker();
        moveDecider = new MoveDecider();
        depth = inDepth;
        //TODO Auto-generated constructor stub
    }
    @Override
    public Stage.Turn move(int x, int y){
    
        move = moveDecider.findBestMove(board, check, myPieces, depth);
        
        try{
            moveMaker.move(move.squares.get(0), move.piece, opPieces, myPieces, board, check, move.kCastle, move.qCastle);
        }
        catch(IllegalArgumentException e){
            System.out.println(move.squares.toString());
        }
        
        unSelectPieces();
       
        return opTurn;
    }

   
    
}
