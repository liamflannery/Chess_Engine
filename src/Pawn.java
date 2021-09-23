import java.awt.Graphics;
public class Pawn extends Piece{
    public Pawn(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;        
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, loc.x + 11 , loc.y + 11 , 70, 70, null);
    }
}
