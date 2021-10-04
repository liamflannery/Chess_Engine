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
        rand = new Random();
        move = board.legalMoves(pieceToMove, check, myPieces);
        moveMaker.move(move.squares.get(rand.nextInt(move.squares.size())), pieceToMove, opPieces, myPieces, board, check, move.kCastle, move.qCastle);
        unSelectPieces();
        return opTurn;
    }

   
    
}
