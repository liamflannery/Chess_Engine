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
    boolean facingUp;
    public CheckFinder(int[] inPossibleMoves, int[] inBoardPos, List<Piece> enemyPosIn, boolean inInCheck, boolean inFacingUp){
        enemyPos = new ArrayList<Piece>(enemyPosIn);
        removedEnemy = null;
        possibleMoves = inPossibleMoves;
        boardPos = inBoardPos;
        moveFinder = new MoveFinder();
        testMove = new int[64];
        willCheck = false;
        inCheck = inInCheck;
        facingUp = inFacingUp;
    }
    public int[] findMoves(int pos, int type){    
        for(int j = 0; j < possibleMoves.length; j++){
            testMove = boardPos.clone();
            if(possibleMoves[j] > 0){
                if(testMove[j] != 0){
                    for(Piece enemy:enemyPos){
                        if(enemy.posOnBoard == j){
                            removedEnemy = enemy;
                            break;
                        }
                    }
                   
                }
                enemyPos.remove(removedEnemy);
                testMove[j] = type;
                testMove[pos] = 0;
                for(Piece enemy:enemyPos){
                    potentialMove = moveFinder.findMoves(enemy.posOnBoard, testMove[enemy.posOnBoard], enemy.moved, testMove, !facingUp, enemyPos);
                    willCheck = potentialMove.testCheck();
                    
                    if(willCheck){
                        possibleMoves[j] = 0;
                        break;
                    }
                    
                }
            }
            if(removedEnemy != null){
                enemyPos.add(removedEnemy);
                removedEnemy = null;
            }
        }
        return possibleMoves;
    }
    
}
