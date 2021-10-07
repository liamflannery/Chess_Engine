import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MoveDecider {
    int[] possibleMoves;
    int[] boardPos;
    int[] testMove;
    Point bestMove;
   
   
    public MoveDecider(int[] inPossibleMoves, int[] inBoardPos){

        possibleMoves = inPossibleMoves;
        boardPos = inBoardPos;
        testMove = new int[64];
        bestMove = new Point(0,0);
 
    }
    public int[] findMoves(int pos, int type){    
        for(int j = 0; j < possibleMoves.length; j++){
            testMove = boardPos.clone();
            if(possibleMoves[j] > 0){
                testMove[j] = type;
                testMove[pos] = 0;
            }
            bestMove = findBestMove(testMove, j);
        }
        for(int i = 0; i < possibleMoves.length; i++){
            if(i != bestMove.getX()){
                possibleMoves[i] = 0;
            }
        }
        return possibleMoves;
    }
    public Point findBestMove(int[] currentBoard, int j){
        int boardScore = 0;
        for(int i = 0; i < currentBoard.length; i++){
            if(currentBoard[i] > 0){
                boardScore++;
            }
            else{
                boardScore--;
            }
        }
        if(boardScore < bestMove.getY()){
            return bestMove;
        }
        else{
            return new Point(j,boardScore); 
        }
    }
    
}
