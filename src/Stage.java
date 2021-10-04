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
    public Stage() {
        currentTurn = Turn.Black;
        // initialWhitePosition = inputWhitePos;
        // initialBlackPosition = inputBlackPos;
        board = new Board();
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();

        whitePieces.add(new Rook(board.squares[0][0], true));
        whitePieces.add(new Rook(board.squares[7][0], true));
        whitePieces.add(new King(board.squares[3][0], true));
        
        blackPieces.add(new Rook(board.squares[0][7], false));
        blackPieces.add(new Rook(board.squares[7][7], false));
        blackPieces.add(new King(board.squares[3][7], false));
        
        

        white = new Player(this, whitePieces, blackPieces, Turn.White);
        black = new Player(this, blackPieces, whitePieces, Turn.Black);
        check = false;
        
        
        board.setBoard(whitePieces, blackPieces, false);
        
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
        if(currentTurn == Turn.White && white.getClass().getName().equals("Computer")){
            currentTurn = white.move(0,0);
        }
        if(currentTurn == Turn.Black && black.getClass().getName().equals("Computer")){
            currentTurn = black.move(0,0);
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
