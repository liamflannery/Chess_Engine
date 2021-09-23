public class Knight extends Piece{
    public Knight(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol; 
        loc.piece = this;   
        x = loc.x + 2;
        y = loc.y + 2;    
    }
}
