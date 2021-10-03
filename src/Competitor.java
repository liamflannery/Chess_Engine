
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class Competitor {
    Stage stage;
    Board board;
    List<Piece> myPieces;
    List<Piece> opPieces;
    boolean check;
    Stage.Turn myTurn;
    Stage.Turn opTurn;
    public Competitor(Stage inStage, List<Piece> inMyPieces, List<Piece> inOpPieces, Stage.Turn inMyTurn){
        myPieces = inMyPieces;
        opPieces = inOpPieces;
        stage = inStage;
        board = stage.board;
        myTurn = inMyTurn;
        if(myTurn == Stage.Turn.White){
            opTurn = Stage.Turn.Black;
        }
        else{
            opTurn = Stage.Turn.White;
        }
    }
    public Stage.Turn move(int inX, int inY){
        return null;
    }
    public void underMouse(Graphics g, Point mouseLoc){};
  
}
