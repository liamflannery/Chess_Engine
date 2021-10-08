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

    public Move findBestMove(Board board, boolean check, List<Piece> myPieces){
        if(myPieces.get(0).isWhite){
            bestScore = Integer.MIN_VALUE;
        }
        else{
            bestScore = Integer.MAX_VALUE;
        }
        Random rand = new Random();
        Piece randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
        returnMove = board.legalMoves(randomPiece, check, myPieces);
        boardPos = board.boardPos.clone();
        while(returnMove.squares.size() < 1){
            randomPiece = myPieces.get(rand.nextInt(myPieces.size()));
            returnMove = board.legalMoves(randomPiece, check, myPieces);
        }
        Square storeSquare = returnMove.squares.get(0);
        returnMove.squares.clear();
        returnMove.squares.add(storeSquare);
        returnMove.piece = randomPiece;
        boardPos[returnMove.squares.get(0).posOnBoard()] = board.pieceToByte(randomPiece);
        boardPos[randomPiece.posOnBoard] = 0;
        bestScore = boardScore(boardPos);
        System.out.println(randomPiece.toString() + returnMove.squares.toString());
            for(Piece piece: myPieces){
                currentMove = board.legalMoves(piece, check, myPieces);
                
                
                for(Square moveTo : currentMove.squares){
                    boardPos = board.boardPos.clone();
                    boardPos[moveTo.posOnBoard()] = board.pieceToByte(piece);
                    boardPos[piece.posOnBoard] = 0;
                    currentScore = boardScore(boardPos);
                    // System.out.println("Looking at " + piece.toString() + " going to " + moveTo.posOnBoard() + " where the score will be: " + currentScore);
                    // System.out.println("Board should be: " + Arrays.toString(boardPos));
                        if(((currentScore > bestScore) && piece.isWhite) || ((currentScore < bestScore) && !(piece.isWhite))){
                            bestScore = currentScore;
                            returnMove = new Move(new ArrayList<Square>(currentMove.squares), currentMove.willCheck,currentMove.kCastle, currentMove.qCastle, currentMove.willStopKC, currentMove.willStopQC);
                            returnMove.squares.clear();
                            returnMove.squares.add(moveTo);
                            returnMove.piece = piece;
                        }
                    }
                }              
        return returnMove;
    }
        

    public int boardScore(int[] boardPosIn){
        int returnScore = 0;
        int value = 0;
        
        for(int i = 0; i < boardPosIn.length; i++){
            switch(Math.abs(boardPosIn[i])){
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
        
}
    
