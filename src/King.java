public class King extends Piece{
    public King(Square inLoc, Boolean inCol){
        moved = false;
        loc = inLoc;
        isWhite = inCol;  
        loc.piece = this;
        x = loc.x + 2;
        y = loc.y + 2;      
    }
}
