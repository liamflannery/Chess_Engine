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
    boolean isWhite;
    public CheckFinder(int[] inPossibleMoves, int[] inBoardPos, List<Piece> enemyPosIn, boolean isWhiteIn){
        enemyPos = new ArrayList<Piece>(enemyPosIn);
        removedEnemy = null;
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
                // if(removedEnemy != null){
                //     enemyPos.add(removedEnemy);
                //     removedEnemy = null;
                // }
                // // if(enemyPos.contains()){
                // //      removedEnemy = enemyPos.remove(enemyPos.indexOf(j));
                // //  }
                // else{
                //     removedEnemy = null;
                // }
                testMove[j] = type;
                testMove[pos] = 0;
                for(Piece enemy:enemyPos){
                    potentialMove = moveFinder.findMoves(enemy.posOnBoard, testMove[enemy.posOnBoard], enemy.moved, testMove, enemy.isWhite);
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
