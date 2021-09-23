import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Queen extends Piece{
    public Queen(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;        
    }
}
