import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MoveDecider {
    int bestScore;
    Move currentMove;
    int[] boardPos;
    int currentScore;
    Move returnMove; 
    BoardDuplicator boardDuplicator;
    MoveMaker moveMaker;
    public MoveDecider(){
        boardDuplicator = new BoardDuplicator();
        moveMaker = new MoveMaker();
    }
    public Move findBestMove(Board board, int depth, boolean isWhite){
        if(returnMove == null){
            List<Piece> myPieces;
            if(isWhite){
                myPieces = board.whitePos;
            }
            else{
                myPieces = board.blackPos;
            }
            Random rand = new Random();
            Piece randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
            returnMove = board.legalMoves(randomPiece, board.willCheck, myPieces);
            boardPos = board.boardPos.clone();
            while(returnMove.squares.size() < 1){
                randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
                returnMove = board.legalMoves(randomPiece, board.willCheck, myPieces);
            }
            Square storeSquare = returnMove.squares.get(0);
            returnMove.squares.clear();
            returnMove.squares.add(storeSquare);
            returnMove.piece = randomPiece;
            boardPos[returnMove.squares.get(0).posOnBoard()] = board.pieceToByte(randomPiece);
            boardPos[randomPiece.posOnBoard] = 0;
            returnMove.score = boardScore(board.boardPos);
        }
        if(depth == 0 || returnMove.willCheckMate){
            if(returnMove.piece.isWhite == isWhite){
                return returnMove;
            }
            else{
                List<Piece> myPieces;
                if(isWhite){
                    myPieces = board.whitePos;
                }
                else{
                    myPieces = board.blackPos;
                }
                Random rand = new Random();
                Piece randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
                Move newReturnMove = board.legalMoves(randomPiece, board.willCheck, myPieces);
                boardPos = board.boardPos.clone();
                while(newReturnMove.squares.size() < 1){
                    randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
                    newReturnMove = board.legalMoves(randomPiece, board.willCheck, myPieces);
                }
                Square storeSquare = newReturnMove.squares.get(0);
                newReturnMove.squares.clear();
                newReturnMove.squares.add(storeSquare);
                newReturnMove.piece = randomPiece;
                boardPos[newReturnMove.squares.get(0).posOnBoard()] = board.pieceToByte(randomPiece);
                boardPos[randomPiece.posOnBoard] = 0;
                newReturnMove.score = boardScore(board.boardPos);
                return newReturnMove;
            }
            
        }
        if(isWhite){
    
            for(Piece p: board.whitePos){
                currentMove = board.legalMoves(p, board.willCheck, board.whitePos);
                if(currentMove.squares.size() > 0){
                    for(Square moveTo: currentMove.squares){
                        Board testBoard = boardDuplicator.duplicatePieces(board);
                        Piece pieceToMove;
                        pieceToMove = p;
                        for(Piece currentPiece: testBoard.whitePos){
                            if(currentPiece.posOnBoard == p.posOnBoard){
                                pieceToMove = currentPiece;
                                break;
                            }
                        }
                                            
                        Piece moveToPiece;
                        
                        Square moveToClone = new Square(moveTo.col, moveTo.numCol, moveTo.row, moveTo.x, moveTo.y, null, moveTo.boardRow, moveTo.boardColumn);
                        if(moveTo.piece == null){
                            moveToPiece = null;
                        }
                        else{
                            moveToPiece = duplicatePiece(moveTo.piece, moveToClone); 
                            moveToClone.piece = moveToPiece;
                        }
                        
                        moveMaker.move(moveToClone, pieceToMove, testBoard.blackPos, testBoard.whitePos, testBoard, testBoard.moveFinder.inCheck, currentMove.kCastle, currentMove.qCastle);
                        Move testMove = new Move(new ArrayList<Square>(currentMove.squares), currentMove.willCheck,currentMove.kCastle, currentMove.qCastle, currentMove.willStopKC, currentMove.willStopQC);
                        testMove.squares.clear();
                        testMove.squares.add(moveTo);
                        testMove.piece = p;
                        int newDepth = depth - 1;
                        testMove.score = findBestMove(testBoard, newDepth, !isWhite).score;
                        if(testMove.score > returnMove.score){
                            returnMove = testMove;
                        }
                    }
                }
            } 
        }
    
        else{
            for(Piece p: board.blackPos){
                currentMove = board.legalMoves(p, board.willCheck, board.blackPos);
                if(currentMove.squares.size() > 0){
                    for(Square moveTo: currentMove.squares){
                        Board testBoard = boardDuplicator.duplicatePieces(board);
                        Piece pieceToMove;
                        pieceToMove = p;
                        for(Piece currentPiece: testBoard.blackPos){
                            if(currentPiece.posOnBoard == p.posOnBoard && currentPiece.isWhite == p.isWhite){
                                pieceToMove = currentPiece;
                                break;
                            }
                        }
                        Piece moveToPiece;
                        
                        Square moveToClone = new Square(moveTo.col, moveTo.numCol, moveTo.row, moveTo.x, moveTo.y, null, moveTo.boardRow, moveTo.boardColumn);
                        if(moveTo.piece == null){
                            moveToPiece = null;
                        }
                        else{
                            moveToPiece = duplicatePiece(moveTo.piece, moveToClone); 
                            moveToClone.piece = moveToPiece;
                        }
                        moveMaker.move(moveToClone, pieceToMove, testBoard.whitePos, testBoard.blackPos, testBoard, testBoard.moveFinder.inCheck, currentMove.kCastle, currentMove.qCastle);
                        Move testMove = new Move(new ArrayList<Square>(currentMove.squares), currentMove.willCheck,currentMove.kCastle, currentMove.qCastle, currentMove.willStopKC, currentMove.willStopQC);
                        testMove.squares.clear();
                        testMove.squares.add(moveTo);
                        testMove.piece = p;
                        int newDepth = depth - 1;
                        testMove.score = findBestMove(testBoard, newDepth, !isWhite).score;
                        if(testMove.score < returnMove.score){
                            returnMove = testMove;
                        }
                    }
                }
            } 
        }
        return returnMove;
    }
    public int boardScore(int[] boardPosIn){
        int returnScore = 0;
        int value = 0;
        
        for(int i = 0; i < boardPosIn.length; i++){
            switch(boardPosIn[i]){
                case(1):
                    value = 1;
                break;
                case(2):
                    value = 3;
                break;
                case(3):
                    value = 5;
                break;
                case(4):
                    value = 3;
                break;
                case(5):
                    value = 9;
                break;
            }
            if(boardPosIn[i] < 0){
                value = value * -1;
            }
            returnScore = returnScore + value;
            value = 0;
        }
        return returnScore;
    }

    public Piece duplicatePiece(Piece p, Square loc){
        switch(p.getClass().getName()){
            case("King"):
                return new King(loc, p.isWhite);
            case("Queen"):
                return new Queen(loc, p.isWhite);
            case("Rook"):
                return new Rook(loc, p.isWhite);
            case("Knight"):
                return new Knight(loc, p.isWhite);
            case("Bishop"):
                return new Bishop(loc, p.isWhite);
            case("Pawn"):
                return new Pawn(loc, p.isWhite);
            default:
                return null;
        }
    }
}
    
