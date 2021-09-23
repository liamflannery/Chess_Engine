public class Bishop extends Piece{
    public Bishop(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;  
        loc.piece = this;
        x = loc.x + 2;
        y = loc.y + 2;      
    }
}
