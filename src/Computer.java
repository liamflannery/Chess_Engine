import java.util.ArrayList;
import java.util.Arrays;
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
    
        // List<Piece> simMyPieces = new ArrayList<Piece>();
        // List<Piece> simOpPieces = new ArrayList<Piece>();
        // Board simBoard = new Board();
        
        
        
        // simBoard.setBoard(simMyPieces, simOpPieces, board.whiteAtBottom);
        // System.out.println(Arrays.toString(simBoard.boardPos));
        // Move simMove = moveDecider.findBestMove(simBoard, check, simMyPieces);
        // //moveMaker.move(move.squares.get(0), move.piece, simOpPieces, simMyPieces, simBoard, check, move.kCastle, move.qCastle);
        // System.out.println(simMove.squares.toString());
        // moveMaker.move(simMove.squares.get(0), simMove.piece, simOpPieces, simMyPieces, simBoard, check, simMove.kCastle, simMove.qCastle);
        // System.out.println(Arrays.toString(simBoard.boardPos));
        move = moveDecider.findBestMove(board, check, myPieces);
        
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
