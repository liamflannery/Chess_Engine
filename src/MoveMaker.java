import java.util.Arrays;
import java.util.List;

public class MoveMaker {
    Move thisMove;
    List<Integer> castleMoves;
    public MoveMaker(){
         castleMoves = Arrays.asList(5,1,6,2,61,57,62,58);
    }
    
    public void move(Square moveTo, Piece pieceToMove, List<Piece> opPieces, List<Piece> myPieces, Board board, boolean check, int kCastle, int qCastle){
        if(kCastle < 0 && qCastle < 0 || !(castleMoves.contains(moveTo.posOnBoard()))){
            if(moveTo.piece != null && moveTo.piece.isWhite != pieceToMove.isWhite){
                Piece killPiece = moveTo.piece;
                killPiece.loc.piece = null;
                killPiece.loc = null;
                for(Piece p: opPieces){
                    if(p.posOnBoard == moveTo.piece.posOnBoard){
                        opPieces.remove(p);
                        break;
                    }
                }
            }
            pieceToMove.setLoc(moveTo);
            pieceToMove.moved = true;
        }
        else{
            Piece moveRook;
            int row;
            if(moveTo.col == 'B'){
                moveRook = myPieces.get(kCastle);
                row = pieceToMove.loc.boardRow - 1;
            }
            else{
                moveRook = myPieces.get(qCastle);
                row = pieceToMove.loc.boardRow + 1;
            }
            
            Square currentRookLoc = moveRook.loc;
            Square rookMoveTo; 
            
            
            rookMoveTo = board.squares[row][currentRookLoc.boardColumn];
            moveRook.setLoc(rookMoveTo);
            moveRook.moved = true;
            pieceToMove.setLoc(moveTo);
            pieceToMove.moved = true;
        }
            board.setBoard(myPieces, opPieces, board.whiteAtBottom);
            thisMove = board.legalMoves(pieceToMove, check, myPieces);
            check(check, opPieces, myPieces, pieceToMove, board);
            
    }
    public void check(boolean check, List<Piece> opPieces, List<Piece> myPieces, Piece selectedPiece, Board board){
        boolean noMoves = true;
        for(Piece p: opPieces){
            Move checkMove = board.legalMoves(p, check, myPieces);
            if(checkMove.squares != null){
                if(checkMove.squares.size() > 0){
                    noMoves = false;
                    break;
                }
            }
        }
        if(thisMove.willStopKC){
            board.moveFinder.cantKC = true;
        }
        else{
            board.moveFinder.cantKC = false;
        }
        if(thisMove.willStopQC){
            board.moveFinder.cantQC = true;
        }
        else{
            board.moveFinder.cantKC = false;
        }
        if(thisMove.willCheck){
            board.moveFinder.inCheck = true;
            System.out.println("check");
            check = true;
            if(noMoves){
                System.out.println("checkmate");
            }
        }
        else{
            //board.moveFinder.inCheck = false;
            check = false;
            if(noMoves){
                System.out.println("stalemate");
            }
        } 
    }
}
    

