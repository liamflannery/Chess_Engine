import java.util.List;

public class MoveDepth {
    List<Piece> simMyPieces;
    List<Piece> simOpPieces;
    Board simBoard;
    public void duplicatePieces(List<Piece> myPieces, List<Piece> opPieces){
        for(Piece p: myPieces){
            switch(p.getClass().getName()){
                case("King"):
                    simMyPieces.add(new King(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Queen"):
                    simMyPieces.add(new Queen(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Rook"):
                    simMyPieces.add(new Rook(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Knight"):
                    simMyPieces.add(new Knight(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Bishop"):
                    simMyPieces.add(new Bishop(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Pawn"):
                    simMyPieces.add(new Pawn(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
            }
        }
        for(Piece p: opPieces){
            switch(p.getClass().getName()){
                case("King"):
                    simOpPieces.add(new King(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Queen"):
                    simOpPieces.add(new Queen(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Rook"):
                    simOpPieces.add(new Rook(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Knight"):
                    simOpPieces.add(new Knight(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Bishop"):
                    simOpPieces.add(new Bishop(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
                case("Pawn"):
                    simOpPieces.add(new Pawn(simBoard.squares[p.loc.boardRow][p.loc.boardColumn], p.isWhite));
                    break;
            }
        }
    }
}
