import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CheckFinder {
    int[] possibleMoves;
    int[] boardPos;
    int[] testMove;
    List<Integer> enemyPos;
    int removedEnemy;
    MoveFinder moveFinder;
    boolean willCheck;
    Move potentialMove;
    boolean isWhite;
    public CheckFinder(int[] inPossibleMoves, int[] inBoardPos, List<Integer> enemyPosIn, boolean isWhiteIn){
        enemyPos = new ArrayList<Integer>(enemyPosIn);
        removedEnemy = -1;
        possibleMoves = inPossibleMoves;
        boardPos = inBoardPos;
        moveFinder = new MoveFinder();
        testMove = new int[64];
        willCheck = false;
        isWhite = isWhiteIn;
    }
    public int[] findMoves(int pos, int type){
        int kingPos = 0;
        int king = 6;
        if(type < 0){
            king = king * -1;
        }
        for(int i = 0; i < boardPos.length; i++){
            if(boardPos[i] == king){
                kingPos = i;
                break;
            }
        }
        for(int j = 0; j < possibleMoves.length; j++){
            testMove = boardPos.clone();
            if(possibleMoves[j] > 0){
                if(removedEnemy >= 0){
                    enemyPos.add(removedEnemy);
                    removedEnemy = -1;
                }
                if(enemyPos.contains(j)){
                     removedEnemy = enemyPos.remove(enemyPos.indexOf(j));
                 }
                else{
                    removedEnemy = -1;
                }
                testMove[j] = type;
                testMove[pos] = 0;
                for(int enemy:enemyPos){
                    potentialMove = moveFinder.findMoves(enemy, testMove[enemy], false, testMove, true);
                    willCheck = potentialMove.testCheck();
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
