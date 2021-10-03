import java.util.List;

public class MoveMaker {
    Move thisMove;
    
    public void move(Square moveTo, Piece pieceToMove, List<Piece> opPieces, List<Piece> myPieces, Board board, boolean check){
        if(moveTo.piece != null){
            Piece killPiece = moveTo.piece;
            killPiece.loc.piece = null;
            killPiece.loc = null;
            opPieces.remove(killPiece);
        }
        pieceToMove.setLoc(moveTo);
        pieceToMove.moved = true;
        board.setBoard(myPieces, opPieces);
        thisMove = board.legalMoves(pieceToMove, check);
        check(check, opPieces, myPieces, pieceToMove, board);
    }
    public void check(boolean check, List<Piece> opPieces, List<Piece> myPieces, Piece selectedPiece, Board board){
        boolean noMoves = true;
        for(Piece p: opPieces){
            Move checkMove = board.legalMoves(p, check);
            if(checkMove.squares != null){
                if(checkMove.squares.size() > 0){
                    noMoves = false;
                    break;
                }
            }
        }
        if(thisMove.willCheck){
            System.out.println("check");
            check = true;
            if(noMoves){
                System.out.println("checkmate");
            }
        }
        else{
            check = false;
            if(noMoves){
                System.out.println("stalemate");
            }
        } 
    }
}
    

