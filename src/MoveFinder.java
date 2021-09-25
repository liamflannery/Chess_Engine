import java.util.Arrays;

public class MoveFinder {
    int pos;
    int type;
    boolean moved;
    int[] boardPos;
    int[] moves;
    int[] borders;
    boolean isWhite;
    int team;
    int move;
    int[] directionIndex;
    public MoveFinder(){
        directionIndex = new int[]{-9,9,-7,7,-1,1,-8,8};
    }
    public int[] findMoves(int position, int inType, boolean inMoved, int[] inBoardPos, boolean isWhiteIn){
        pos = position;
        type = inType;
        moved = inMoved;
        boardPos = inBoardPos;
        moves = new int[64];
        isWhite = isWhiteIn;
        if(isWhite){
            team = -1;
        }
        else{
            team = 1;
        }
       switch(type){
            case(-1):
                singleMoves(6,7);
                break;
            case(1):
                singleMoves(7,8);
                break;
            case(6):
                singleMoves(0, 8);
                break;
            case(-6):
                singleMoves(0, 8);
                break;
            case(5):
                slidingMoves(0, 8);
                break;
            default:
                Arrays.fill(moves, 1);
        }
        
        return moves;
    }
    public void singleMoves(int direction, int directionEnd){
        for(int i = direction; i < directionEnd ; i++){
            move = pos + directionIndex[i];
            if(move < boardPos.length && move >= 0){
                vetMove();
            }
            
        }
    }
    public void slidingMoves(int direction, int directionEnd){
        for(int i = direction; i < directionEnd ; i++){
            for(int j = 1; j < 8; j++){
                move = pos + directionIndex[i] * j;
                if(move < boardPos.length && move >= 0){
                    if(boardPos[move] != 0){
                        if(boardPos[move] * type < 0){
                            vetMove();
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        vetMove();
                    }
                        
                    
                }
                else{
                    break;
                }
            }
            
        }
    }
    public void vetMove(){
        if(boardPos[move] != 0){
            if(boardPos[move] * type < 0){
                moves[move] = 1;
            }
        }
        else{
            moves[move] = 1;
        }
    }
}
