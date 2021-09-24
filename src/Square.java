import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Square extends Rectangle{
    static int size = 87;
    char col;
    int numCol;
    int row;
    Piece piece;

    public Square(char inCol, int inColNum,int inRow, int inX, int inY, Piece inPiece){
        super(inX, inY, size, size);
        numCol = inColNum;
        col = inCol;
        row = inRow;
        piece = inPiece;
    }
    void paint(Graphics g, Point mousePos) {
        if(contains(mousePos)) {
            g.setColor(Color.GRAY);
        } 
        if(col % 2 == 0 && row % 2 != 0 || col % 2 != 0 && row % 2 == 0) {
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
    public boolean hasPiece(){
        if(piece != null){
            return true;
        }
        else{
            return false;
        }
    }
    public String toString(){
        return (String.valueOf(col) + "," + String.valueOf(row));
    }
    
}
