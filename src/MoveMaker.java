import java.util.List;

public class MoveMaker {
    Move thisMove;
    
    public void move(Square moveTo, Piece pieceToMove, List<Piece> opPieces, List<Piece> myPieces, Board board, boolean check, int kCastle, int qCastle){

        if(kCastle < 0 && qCastle < 0){
            if(moveTo.piece != null){
                Piece killPiece = moveTo.piece;
                killPiece.loc.piece = null;
                killPiece.loc = null;
                opPieces.remove(killPiece);
            }
            pieceToMove.setLoc(moveTo);
            pieceToMove.moved = true;
        }
        else{
            Piece moveRook;
            int row;
            System.out.println(kCastle + ", " + qCastle);
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
    

