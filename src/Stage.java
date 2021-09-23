import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Stage {
    Board board;
    List<Piece> pieces; 
    public Stage() {
        board = new Board();
        pieces = new ArrayList<Piece>();
    }

    public void paint(Graphics g, Point mouseLoc) {
        board.paint(g, mouseLoc);
        /*for(Piece p: pieces) {
            p.paint(g);
        }*/

        /*Optional<Square> underMouse = board.cellAtPoint(mouseLoc);
        if(underMouse.isPresent()) {
            Square hoverCell = underMouse.get();
            g.setColor(Color.DARK_GRAY);
            g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
        }*/
    }
}
