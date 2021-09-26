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
    public MoveFinder(){
        directionIndex = new int[]{8,-8,-1,1,7,-7,9,-9};
        knightDir = new int[]{-17,-15,-10,-6,6,10,15,17};
        computeSquares();
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
                singleMoves(1,2);
                break;
            case(1):
                singleMoves(0,1);
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
            System.out.println(pos + "," + i);
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
        for(int i = 0; i < knightDir.length; i++){
            move = pos + knightDir[i];
        }
        vetMove();
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
