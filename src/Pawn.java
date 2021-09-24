import java.awt.Graphics;
public class Pawn extends Piece{
    public Pawn(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;
        loc.piece = this;   
        x = loc.x + 2;
        y = loc.y + 2;     
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, x + 11 , y + 11 , 60, 60, null);
    }
}
