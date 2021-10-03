import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class Player extends Competitor{
    Optional<Square> underMouseS;
    Piece selectedPiece;
    Move thisMove;
    MoveMaker moveMaker;
    public Player(Stage inStage, List<Piece> inMyPieces, List<Piece> inOpPieces, Stage.Turn myTurn) {
        super(inStage, inMyPieces, inOpPieces, myTurn);
        moveMaker = new MoveMaker();
    }

    
   
    @Override
    public void underMouse(Graphics g, Point mouseLoc){
        underMouseS = stage.board.squareAtPoint(mouseLoc);
        if(underMouseS.isPresent()) {
            if(selectedPiece != null){
                selectedPiece.setPos(new Point(mouseLoc.x - 40, mouseLoc.y - 40));
            }
            Square hoverCell = underMouseS.get();
            g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
        }
    }
    @Override 
    public Stage.Turn move(int x, int y){
        if(selectedPiece == null){
            for(Piece p: myPieces){
                if(p.loc.contains(x,y)){
                    selectedPiece = p;
                    board.legalMoves(p, check);
                }
            }
            return myTurn;
        } 
        else{
            underMouseS = board.squareAtPoint(new Point(x,y));
            if((underMouseS.isPresent())){
                thisMove = board.legalMoves(selectedPiece, check);
                
                if(thisMove.squares.contains(underMouseS.get())){    
                    normalMove();
                    selectedPiece = null;
                    unSelectPieces();
                    return opTurn;
                }
                   

            else{
                selectedPiece.setPos(selectedPiece.loc.getLocation());
                selectedPiece = null;
                unSelectPieces();
                return myTurn;
            }
        }
        else{
            selectedPiece.setPos(selectedPiece.loc.getLocation());
            selectedPiece = null;
            unSelectPieces();
            return myTurn;
        }
    }
           
    }
    public void check(){
        
    }
    public void normalMove(){
        moveMaker.move(underMouseS.get(), selectedPiece, opPieces, myPieces, board, check);
    }
    public void unSelectPieces(){
        Square[][] tempSquares = board.squares;
        for(int i = 0; i < tempSquares.length; i++) {
            for(int j = 0; j < tempSquares[i].length; j++) {
                tempSquares[i][j].selected = false;
            }
        }
    }
    
}
