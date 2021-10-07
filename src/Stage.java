import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
        currentTurn = Turn.White;
        // initialWhitePosition = inputWhitePos;
        // initialBlackPosition = inputBlackPos;
        board = new Board();
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();

       whitePieces.add(new Rook(board.squares[7][0], true));
       whitePieces.add(new Knight(board.squares[6][0], true));
       whitePieces.add(new Bishop(board.squares[5][0], true));
       whitePieces.add(new Queen(board.squares[4][0], true));
       whitePieces.add(new King(board.squares[3][0], true));
       whitePieces.add(new Bishop(board.squares[2][0], true));
       whitePieces.add(new Knight(board.squares[1][0], true));
       whitePieces.add(new Rook(board.squares[0][0], true));
        whitePieces.add(new Pawn(board.squares[7][1], true));
        whitePieces.add(new Pawn(board.squares[6][1], true));
        whitePieces.add(new Pawn(board.squares[5][1], true));
        whitePieces.add(new Pawn(board.squares[4][1], true));
        whitePieces.add(new Pawn(board.squares[3][1], true));
        whitePieces.add(new Pawn(board.squares[2][1], true));
        whitePieces.add(new Pawn(board.squares[1][1], true));
        whitePieces.add(new Pawn(board.squares[0][1], true));
        
       blackPieces.add(new Rook(board.squares[7][7], false));
       blackPieces.add(new Knight(board.squares[6][7], false));
       blackPieces.add(new Bishop(board.squares[5][7], false));
       blackPieces.add(new Queen(board.squares[4][7], false));
       blackPieces.add(new King(board.squares[3][7], false));
       blackPieces.add(new Bishop(board.squares[2][7], false));
       blackPieces.add(new Knight(board.squares[1][7], false));
       blackPieces.add(new Rook(board.squares[0][7], false));
        blackPieces.add(new Pawn(board.squares[7][6], false));
        blackPieces.add(new Pawn(board.squares[6][6], false));
        blackPieces.add(new Pawn(board.squares[5][6], false));
        blackPieces.add(new Pawn(board.squares[4][6], false));
        blackPieces.add(new Pawn(board.squares[3][6], false));
        blackPieces.add(new Pawn(board.squares[2][6], false));
        blackPieces.add(new Pawn(board.squares[1][6], false));
        blackPieces.add(new Pawn(board.squares[0][6], false));
        
        

        white = new Computer(this, whitePieces, blackPieces, Turn.White, 1);
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
