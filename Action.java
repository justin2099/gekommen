public class Action {
    private Piece piece;
    private int x, y;
    private int turn;

    public Action(){
        x = 0;
        y = 0;
        piece = Piece.BLANK;
    }

    public Action(int x, int y, Piece piece) throws IllegalArgumentException{
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getOpposite() {
        switch (this.getPiece()){
            case WHITE: return Piece.BLACK;
            case BLACK: return Piece.WHITE;
            default: return Piece.BLANK;
        }
    }

}