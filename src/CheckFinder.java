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
    public CheckFinder(int[] inPossibleMoves, int[] inBoardPos, List<Integer> enemyPosIn){
        enemyPos = new ArrayList<Integer>(enemyPosIn);
        removedEnemy = -1;
        possibleMoves = inPossibleMoves;
        boardPos = inBoardPos;
        moveFinder = new MoveFinder();
        testMove = new int[64];
        willCheck = false;
    }
    public int[] findMoves(int pos, int type){
        int kingPos = 0;
        
        int[] storeTestMove = testMove;
        for(int i = 0; i < boardPos.length; i++){
            testMove[i] = boardPos[i];
            if(boardPos[i] == 6){
                kingPos = i;
            }
        }
        for(int j = 0; j < possibleMoves.length; j++){
            testMove = boardPos.clone();
            if(possibleMoves[j] > 0 && type > 0){
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
                    willCheck = false;
                }
                testMove = storeTestMove;
            }
        }
        return possibleMoves;
    }
    
}
