import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
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
    List<Piece> enemyPos = new ArrayList<Piece>();
    List<Piece> friendlyPos = new ArrayList<Piece>();
    MoveFinder moveFinder = new MoveFinder();
    boolean isWhite;
    boolean willCheck;
    public Board(boolean isWhiteIn){
        for(int i = 0; i < squares.length; i++) {
            for(int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square(colToLabel(i),i, (squares[i].length - j), 10+87*i, 10+87*j, null);
            }
        }
        willCheck = false;
        isWhite = isWhiteIn;
    }
    public void setBoard(List<Piece> player, List<Piece> comp){
        List<Piece> allPieces = Stream.concat(player.stream(), comp.stream()).collect(Collectors.toList());
        
        boardPos = new int[64];
        enemyPos = new ArrayList<Piece>();
        friendlyPos = new ArrayList<Piece>();
        for(Piece p: allPieces){
            boardPos[convertPos(p)] = pieceToByte(p);
        }
    }
    public Move legalMoves(Piece p, boolean inCheck){
        List<Square> moves = new ArrayList<Square>();
        possibleMoves = new int[64];
        Move parentMove;
        int pos = convertPos(p);
        parentMove = moveFinder.findMoves(pos, pieceToByte(p), p.moved, boardPos, isWhite, inCheck);
        possibleMoves = parentMove.getMoves();
        willCheck = parentMove.willCheck;
        CheckFinder checkFinder;
        
        if(isWhite){
            if(p.isWhite){
                checkFinder = new CheckFinder(possibleMoves, boardPos, enemyPos, isWhite, inCheck);
            }
            else{
                checkFinder = new CheckFinder(possibleMoves, boardPos, friendlyPos, isWhite, inCheck);
            }
        }
        else{
            if(p.isWhite){
                checkFinder = new CheckFinder(possibleMoves, boardPos, friendlyPos, isWhite, inCheck);
            }
            else{
                checkFinder = new CheckFinder(possibleMoves, boardPos, enemyPos, isWhite, inCheck);
            }
        }     
        
        possibleMoves = checkFinder.findMoves(pos, pieceToByte(p));
            
        
        for(int i = 0; i < possibleMoves.length; i++){
            if(possibleMoves[i] > 0){
                moves.add(squares[i%8][7 - i/8]);
                squares[i%8][7-i/8].setColor(new Color(91, 230, 255));
            } 
        }
        Move moveSquare = new Move(moves, willCheck);
        return moveSquare;
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
        if(isWhite){
            if (!p.isWhite){
                value = value * -1;
                p.posOnBoard = convertPos(p);
                enemyPos.add(p);
            }
            else{
                p.posOnBoard = convertPos(p);
                friendlyPos.add(p);
            }
        }
        else{
            if(p.isWhite){
                value = value * -1;
                p.posOnBoard = convertPos(p);
                enemyPos.add(p);
            }
            else{
                p.posOnBoard = convertPos(p);
                friendlyPos.add(p);
            }
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
