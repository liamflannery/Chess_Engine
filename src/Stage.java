import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class Stage {
    HashMap<String, Point> initialWhitePosition;
    HashMap<String, Point> initialBlackPosition;
    Competitor black;
    Competitor white;
    List<Piece> whitePieces; 
    List<Piece> blackPieces;
    Board board;
    Piece selectedWhitePiece;
    Piece selectedBlackPiece;
    Piece compKing;
    Piece playerKing;
    
    boolean check;
    Move thisMove;
    enum Turn {
        White, Black;
      }
    public Turn currentTurn;
    public Stage(HashMap<String,Point> inputWhitePos, HashMap<String,Point> inputBlackPos) {
        currentTurn = Turn.White;
        initialWhitePosition = inputWhitePos;
        initialBlackPosition = inputBlackPos;
        board = new Board();
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();
        for(Map.Entry<String, Point> piece: initialWhitePosition.entrySet()){
            switch(piece.getKey()){
                case("King"):
                    whitePieces.add(new King(board.squares[(int)piece.getValue().getX()][(int)piece.getValue().getY()], true));
                break;
                case("Queen"):
                    whitePieces.add(new Queen(board.squares[(int)piece.getValue().getX()][(int)piece.getValue().getY()], true));
            }
        }
        for(Map.Entry<String, Point> piece: initialBlackPosition.entrySet()){
            switch(piece.getKey()){
                case("King"):
                    blackPieces.add(new King(board.squares[(int)piece.getValue().getX()][(int)piece.getValue().getY()], false));
                break;
                case("Queen"):
                    blackPieces.add(new Queen(board.squares[(int)piece.getValue().getX()][(int)piece.getValue().getY()], false));
            }
        }
        white = new Player(this, whitePieces, blackPieces, Turn.White);
        black = new Computer(this, blackPieces, whitePieces, Turn.Black);
        check = false;
        
        
        board.setBoard(whitePieces, blackPieces);
        
    }

    public void paint(Graphics g, Point mouseLoc) {
        board.paint(g, mouseLoc);
        for(Piece p: whitePieces) {
            p.paint(g);
        }
        for(Piece p: blackPieces) {
            p.paint(g);
        }
        if(currentTurn == Turn.White && white.getClass().getName().equals("Player")){
            white.underMouse(g, mouseLoc);
        }
        if(currentTurn == Turn.Black && black.getClass().getName().equals("Player")){
            black.underMouse(g, mouseLoc);
        }
        
        
        
    }
  
    public void mouseClicked(int x, int y){
        
       
        if(currentTurn == Turn.White && white.getClass().getName().equals("Player")){
            currentTurn = white.move(x,y);
        }
        if(currentTurn == Turn.Black && black.getClass().getName().equals("Player")){
            currentTurn = black.move(x,y);
        }

        
    }
    
   
    
}
