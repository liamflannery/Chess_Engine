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
    Color color;
    boolean selected;
    int boardRow;
    int boardColumn;

    public Square(char inCol, int inColNum,int inRow, int inX, int inY, Piece inPiece, int i, int j){
        super(inX, inY, size, size);
        numCol = inColNum;
        col = inCol;
        row = inRow;
        piece = inPiece;
        selected = false;
        boardRow = i;
        boardColumn = j;
    }
    void paint(Graphics g, Point mousePos) {
        if(selected == false){
            if(contains(mousePos)) {
                color = Color.GRAY;
            } 
            if(col % 2 == 0 && row % 2 != 0 || col % 2 != 0 && row % 2 == 0) {
                color = new Color(171, 210, 250);
            }
            else{
                color = new Color(61, 81, 140);
            }
        }
        g.setColor(color);
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
    public void setColor(Color inColor){
        color = inColor;
        selected = true;
    }
    public Point getPos(){
        return new Point(boardRow, boardColumn);
    }
    
}
