import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;

public class Board {

    Square[][] squares = new Square[8][8];
    public Board(){
        for(int i = 0; i < squares.length; i++) {
            for(int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(colToLabel(i), j, 10+87*i, 10+87*j, null);
            }
        }
    }
    private char colToLabel(int col) {
        return (char) (col + 65);
    }
    public void paint(Graphics g, Point mousePos) {
        Consumer<Square> paint = square -> {
            square.paint(g, mousePos);
        };
        doToEachSquare(paint);
    }
    public void doToEachSquare(Consumer<Square> func) {
        for(int i=0; i < squares.length; i++) {
            for(int j=0; j < squares[i].length; j++) {
                func.accept(squares[i][j]);
            }
        }
      }
      public Optional<Square> squareAtPoint(Point p) {
        for(int i=0; i < squares.length; i++) {
            for(int j=0; j < squares[i].length; j++) {
                if(squares[i][j].contains(p)) {
                    return Optional.of(squares[i][j]);
                }
            }
        }
        return Optional.empty();
       
    }
}
