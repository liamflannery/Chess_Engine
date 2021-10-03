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
    public Player(Stage inStage, List<Piece> inMyPieces, List<Piece> inOpPieces, Stage.Turn myTurn) {
        super(inStage, inMyPieces, inOpPieces, myTurn);
    }

    Optional<Square> underMouseS;
    Piece selectedPiece;
    Move thisMove;
   
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
                    if(thisMove.willCheck){
                        check = true;
                        boolean checkmate = true;
                        if(opPieces.contains(selectedPiece)){
                            for(Piece p: myPieces){
                                Move checkMove = board.legalMoves(p, check);
                                if(checkMove.squares != null){
                                    if(checkMove.squares.size() > 0){
                                        checkmate = false;
                                        break;
                                    }
                                }
                            }
                            if(checkmate){
                                System.out.println("checkmate");
                            }
                        }
                    }
                    else{
                        check = false;
                    }
                    return normalMove();
                   
                    
                    // switch(thisMove.castle){
                    //     default:
                    //         return normalMove();
                    //     // case(0):
                    //     //     return normalMove();
                    //     // case(1):
                    //     //     System.out.println("castle");
                    //     //     if(underMouseS.get() == board.squares[0][6]){
                    //     //         return normalMove();
                    //     //     }
                    //     // break;
                    // }
                    //System.out.println(Arrays.toString(board.boardPos));
                    }

            else{
                resetPiece();
                return myTurn;
            }
        }
        else{
            resetPiece();
            return myTurn;
        }
    }
           
    }
    public Stage.Turn normalMove(){
        if(underMouseS.get().piece != null){
            Piece killPiece = underMouseS.get().piece;
            killPiece.loc.piece = null;
            killPiece.loc = null;
            opPieces.remove(killPiece);
        }
        selectedPiece.setLoc(underMouseS.get());
        selectedPiece.moved = true;
        board.setBoard(myPieces, opPieces);
        thisMove = board.legalMoves(selectedPiece, check);
        selectedPiece = null;
        unSelectPieces();
        return opTurn;
    }
    public void unSelectPieces(){
        Square[][] tempSquares = board.squares;
        for(int i = 0; i < tempSquares.length; i++) {
            for(int j = 0; j < tempSquares[i].length; j++) {
                tempSquares[i][j].selected = false;
            }
        }
    }
    public void resetPiece(){
        selectedPiece.setPos(selectedPiece.loc.getLocation());
        selectedPiece = null;
        unSelectPieces();
        
    }
    
}
