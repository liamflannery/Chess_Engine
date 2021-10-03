import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CheckFinder {
    int[] possibleMoves;
    int[] boardPos;
    int[] testMove;
    List<Piece> enemyPos;
    Piece removedEnemy;
    MoveFinder moveFinder;
    boolean willCheck;
    Move potentialMove;
    boolean inCheck;
    public CheckFinder(int[] inPossibleMoves, int[] inBoardPos, List<Piece> enemyPosIn, boolean inInCheck){
        enemyPos = new ArrayList<Piece>(enemyPosIn);
        removedEnemy = null;
        possibleMoves = inPossibleMoves;
        boardPos = inBoardPos;
        moveFinder = new MoveFinder();
        testMove = new int[64];
        willCheck = false;
        inCheck = inInCheck;
    }
    public int[] findMoves(int pos, int type){    
        for(int j = 0; j < possibleMoves.length; j++){
            testMove = boardPos.clone();
            if(possibleMoves[j] > 0){
                testMove[j] = type;
                testMove[pos] = 0;
                for(Piece enemy:enemyPos){
                    potentialMove = moveFinder.findMoves(enemy.posOnBoard, testMove[enemy.posOnBoard], enemy.moved, testMove, inCheck);
                    willCheck = potentialMove.testCheck();
                    if(enemy.posOnBoard == j && willCheck){
                        break;
                    }
                    
                    if(willCheck){
                        possibleMoves[j] = 0;
                        break;
                    }
                }
            }
        }
        return possibleMoves;
    }
    
}
