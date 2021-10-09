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

    //    whitePieces.add(new Rook(board.squares[7][0], true));
    //    whitePieces.add(new Knight(board.squares[6][0], true));
    //    whitePieces.add(new Bishop(board.squares[5][0], true));
    //    whitePieces.add(new Queen(board.squares[4][0], true));
    //    whitePieces.add(new King(board.squares[3][0], true));
    //    whitePieces.add(new Bishop(board.squares[2][0], true));
    //    whitePieces.add(new Knight(board.squares[1][0], true));
    //    whitePieces.add(new Rook(board.squares[0][0], true));
    //     whitePieces.add(new Pawn(board.squares[7][1], true));
    //     whitePieces.add(new Pawn(board.squares[6][1], true));
    //     whitePieces.add(new Pawn(board.squares[5][1], true));
    //     whitePieces.add(new Pawn(board.squares[4][1], true));
    //     whitePieces.add(new Pawn(board.squares[3][1], true));
    //     whitePieces.add(new Pawn(board.squares[2][1], true));
    //     whitePieces.add(new Pawn(board.squares[1][1], true));
    //     whitePieces.add(new Pawn(board.squares[0][1], true));
        
       blackPieces.add(new Rook(board.squares[0][0], false));
       blackPieces.add(new Queen(board.squares[3][0], false));
       blackPieces.add(new Bishop(board.squares[4][0], false));
       blackPieces.add(new Rook(board.squares[6][0], false));
       blackPieces.add(new King(board.squares[7][0], false));
       blackPieces.add(new Pawn(board.squares[0][1], false));
       blackPieces.add(new Pawn(board.squares[1][1], false));
       blackPieces.add(new Bishop(board.squares[2][1], false));
       blackPieces.add(new Pawn(board.squares[5][1], false));
       blackPieces.add(new Pawn(board.squares[7][1], false));
       blackPieces.add(new Knight(board.squares[2][2], false));
       blackPieces.add(new Pawn(board.squares[4][2], false));
       blackPieces.add(new Pawn(board.squares[6][2], false));

       whitePieces.add(new Pawn(board.squares[5][2], true));
       whitePieces.add(new Bishop(board.squares[0][3], true));
       whitePieces.add(new Knight(board.squares[4][3], true));
       whitePieces.add(new Bishop(board.squares[2][4], true));
       whitePieces.add(new Pawn(board.squares[4][4], true));
       whitePieces.add(new Queen(board.squares[7][4], true));
       whitePieces.add(new Pawn(board.squares[2][5], true));
       whitePieces.add(new Rook(board.squares[5][5], true));
       whitePieces.add(new Pawn(board.squares[1][6], true));
       whitePieces.add(new Pawn(board.squares[6][6], true));
       whitePieces.add(new Pawn(board.squares[7][6], true));
       whitePieces.add(new King(board.squares[7][7], true));
    
        
        for(Piece p: whitePieces){
            p.loc.piece = p;
        }
        for(Piece p: blackPieces){
            p.loc.piece = p;
        }

        white = new Computer(this, whitePieces, blackPieces, Turn.White, 1);
        black = new Computer(this, blackPieces, whitePieces, Turn.Black, 1);
        check = false;
        
        
        board.setBoard(whitePieces, blackPieces, true);
        
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
        // if(currentTurn == Turn.White && white.getClass().getName().equals("Computer")){
        //     currentTurn = white.move(0,0);
        // }
        // if(currentTurn == Turn.Black && black.getClass().getName().equals("Computer")){
        //     currentTurn = black.move(0,0);
        // }
        
        
        
        
    }
    public int compMove(){
         if(currentTurn == Turn.White && white.getClass().getName().equals("Computer")){
            currentTurn = white.move(0,0);
            return 0;
        }
        if(currentTurn == Turn.Black && black.getClass().getName().equals("Computer")){
            currentTurn = black.move(0,0);
            return 0;
        }
        return 0;
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
