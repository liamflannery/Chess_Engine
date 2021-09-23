public class Rook extends Piece{
    public Rook(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;   
        loc.piece = this;
        x = loc.x + 2;
        y = loc.y + 2;     
    }
}
