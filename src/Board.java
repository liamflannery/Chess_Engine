import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.Color;

public class Board {

    Square[][] squares = new Square[8][8];
    public int[] boardPos = new int[64];
    int[] possibleMoves = new int[64];
    public Board(){
        for(int i = 0; i < squares.length; i++) {
            for(int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(colToLabel(i),i, (squares[i].length - j), 10+87*i, 10+87*j, null);
            }
        }
    }
    public void setBoard(List<Piece> player, List<Piece> comp){
        List<Piece> allPieces = Stream.concat(player.stream(), comp.stream()).collect(Collectors.toList());
        boardPos = new int[64];
        for(Piece p: allPieces){
            boardPos[convertPos(p)] = pieceToByte(p);
        }
    }
    public List<Square> legalMoves(Piece p){
        List<Square> moves = new ArrayList<Square>();
        possibleMoves = new int[64];
        int pos = convertPos(p);
        findMoves(pos, pieceToByte(p), p.moved);
        for(int i = 0; i < possibleMoves.length; i++){
            if(possibleMoves[i] == 1){
                moves.add(squares[i%8][7 - i/8]);
                squares[i%8][7-i/8].setColor(new Color(91, 230, 255));
            }  
        }
        return moves;
    }
    private void findMoves(int pos, int type, boolean moved){
        int team = Integer.signum(type);
        type = Math.abs(type);
        switch(type){
            case(1):
                possibleMoves[pos + 8] = boardPos[pos + 8] == 0 ? 1: 0;
                if(moved == false){
                    possibleMoves[pos + 8 * 2] = boardPos[pos + 8 * 2] == 0 ? 1: 0;
                }
                if(boardPos[pos + 7] != 0){
                    possibleMoves[pos + 7] = Integer.signum(boardPos[pos + 7]) != team ? 1 : 0;
                }
                if(boardPos[pos + 9] != 0){
                    possibleMoves[pos + 9] = 1;
                }
                break;
               
        }
     
    }
    private int pieceToByte(Piece p){
        int value = 0;
        switch(p.getClass().getName()){
            case("King"):
                value =  6;
                break;
            case("Queen"):
                value =  5;
                break;
            case("Bishop"):
                value =  4;
                break;
            case("Knight"):
                value =  3;
                break;
            case("Rook"):
                value =  2;
                break;
            case("Pawn"):
                value =  1;
                break;
        }
        if (!p.isWhite){
            value = value * -1;
        }
        return value;
    }
    private int convertPos(Piece p){
        int pos = 0;
        pos += p.loc.numCol;
        pos += (p.loc.row - 1) * 8;
        return pos;
    }
    private Square getSquare(int pos){
        int x = 0;
        int y = 0;
        return squares[x][y];
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
