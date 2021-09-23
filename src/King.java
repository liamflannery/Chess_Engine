import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece{
    public King(Square inLoc, Boolean inCol){
        loc = inLoc;
        isWhite = inCol;        
    }
}
