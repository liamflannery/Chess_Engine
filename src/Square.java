import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Square extends Rectangle{
    static int size = 87;
    char col;
    int row;

    public Square(char inCol, int inRow, int inX, int inY){
        super(inX, inY, size, size);
        col = inCol;
        row = inRow;
    }
    void paint(Graphics g, Point mousePos) {
        if(contains(mousePos)) {
            g.setColor(Color.GRAY);
        } 
        else if(col % 2 == 0 && row % 2 != 0 || col % 2 != 0 && row % 2 == 0) {
            g.setColor(Color.WHITE);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
    @Override
    public boolean contains(Point p) {
        if (p != null) {
            return(super.contains(p));
        } else {
            return false;
        }
    }
    
}
