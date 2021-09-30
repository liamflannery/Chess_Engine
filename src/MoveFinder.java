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
    int[][] numSquaresToEdge;
    int[] knightDir;
    boolean willCheck;
    Move returnMove;
    public MoveFinder(){
        directionIndex = new int[]{8,-8,-1,1,7,-7,9,-9};
        knightDir = new int[]{-17,-15,-10,-6,6,10,15,17};
        willCheck = false;
        computeSquares();
    }
    public Move findMoves(int position, int inType, boolean inMoved, int[] inBoardPos, boolean isWhiteIn){
        willCheck = false;
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
                singleMoves(1,2);
                singleMoves(5, 6);
                singleMoves(7, 8);
                pawnMoves();
                break;
            case(1):
                singleMoves(0,1);
                singleMoves(4, 5);
                singleMoves(6, 7);
                pawnMoves();
                break;
            case(6):
                singleMoves(0, 8);
                castle();
                break;
            case(-6):
                castle();
                singleMoves(0, 8);
                break;
            case(5):
                slidingMoves(0, 8);
                break;
            case(-5):
                slidingMoves(0, 8);
                break;
            case(-2):
                slidingMoves(0, 4);
                break;
            case(2):
                slidingMoves(0, 4);
                break;
            case(4):
                slidingMoves(4, 8);
                break;
            case(-4):
                slidingMoves(4, 8);
                break;
            case(3):
                knightMoves();
                break;
            case(-3):
                knightMoves();
                break;
            default:
                Arrays.fill(moves, 1);
        }
        returnMove = new Move(moves, willCheck);
        return returnMove;
    }
    public void singleMoves(int direction, int directionEnd){
        for(int i = direction; i < directionEnd ; i++){
            move = pos + directionIndex[i];
            if(move < boardPos.length && move >= 0 && numSquaresToEdge[pos][i] > 0){
                if(!(Math.abs(type) == 1 && boardPos[move] != 0 && i < 3)){
                    if(!(Math.abs(type) == 1 && boardPos[move] == 0 && i > 3))
                        vetMove();
                }
            }
            
        }
    }
    public void slidingMoves(int direction, int directionEnd){
        for(int i = direction; i < directionEnd ; i++){
            for(int j = 1; j <= numSquaresToEdge[pos][i]; j++){
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
    public void knightMoves(){
        int inc = 0;
      //  -17,-15,-10,-6,6,10,15,17
        for(int i = 0; i < knightDir.length; i++){
            switch(knightDir[i]){
                case(-17):
                    if(numSquaresToEdge[pos][2] >= 1 && numSquaresToEdge[pos][1] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(-15):
                if(numSquaresToEdge[pos][1] >= 2 && numSquaresToEdge[pos][3] >= 1){
                    inc = knightDir[i];
                }
                break;
                case(-10):
                    if(numSquaresToEdge[pos][1] >= 1 && numSquaresToEdge[pos][2] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(-6):
                if(numSquaresToEdge[pos][3] >= 2 && numSquaresToEdge[pos][1] >= 1){
                    inc = knightDir[i];
                }
                break;
                case(17):
                    if(numSquaresToEdge[pos][3] >= 1 && numSquaresToEdge[pos][0] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(15):
                if(numSquaresToEdge[pos][0] >= 2 && numSquaresToEdge[pos][2] >= 1){
                    inc = knightDir[i];
                }
                break;
                case(10):
                    if(numSquaresToEdge[pos][0] >= 1 && numSquaresToEdge[pos][3] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(6):
                if(numSquaresToEdge[pos][2] >= 2 && numSquaresToEdge[pos][0] >= 1){
                    inc = knightDir[i];
                }
                break;
                default:
                    inc = knightDir[i];
                    
            }
            move = pos + inc;
            vetMove();
        }
        
        
    }
    public void pawnMoves(){
        if(!(moved)){
            move = pos + 16 * type;
            if(boardPos[move] == 0 && boardPos[pos + 8 * type] == 0){
                vetMove();
            }
            
        }
    }
    public void castle(){
        
    }
    public void vetMove(){
        if(move > 0 && move < boardPos.length){
            if(boardPos[move] != 0){
                if(boardPos[move] * type < 0){
                    if(boardPos[move] * type < 0 && Math.abs(boardPos[move]) == 6){
                        moves[move] = 2;
                        willCheck = true;
                    }
                    else{
                        moves[move] = 1;
                    }
                   
                }
            }
            else{
                moves[move] = 1;
            }
    }
    }
    public void computeSquares(){
        numSquaresToEdge = new int[64][];
        for(int squareIndex = 0; squareIndex < 64; squareIndex++){
            int y = squareIndex / 8;
				int x = squareIndex - y * 8;

				int north = 7 - y;
				int south = y;
				int west = x;
				int east = 7 - x;
				numSquaresToEdge[squareIndex] = new int[8];
				numSquaresToEdge[squareIndex][0] = north;
				numSquaresToEdge[squareIndex][1] = south;
				numSquaresToEdge[squareIndex][2] = west;
				numSquaresToEdge[squareIndex][3] = east;
				numSquaresToEdge[squareIndex][4] = Math.min(north, west);
				numSquaresToEdge[squareIndex][5] = Math.min(south, east);
				numSquaresToEdge[squareIndex][6] = Math.min(north, east);
				numSquaresToEdge[squareIndex][7] = Math.min(south, west);
        }
    }
}
