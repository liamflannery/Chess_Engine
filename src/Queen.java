public class Queen extends Piece{
    public Queen(Square inLoc, Boolean inCol){
        loc = inLoc;
        loc.piece = this;
        isWhite = inCol;  
        x = loc.x + 2;
        y = loc.y + 2;      
    }
}
