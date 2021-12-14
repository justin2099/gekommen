public class AClick {
    private double x; private double y;
    private Piece player;
    private ChessBoard board;
    private double pictureSize;

    public AClick(double x, double y, Piece player, ChessBoard board, double pictureSize) {
        this.x = x; this.y = y; this.player = player; this.board = board; this.pictureSize = pictureSize;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int findI() {
        int n = (int) (this.x * (this.board.getSize()-1) / (this.pictureSize/2));
        return ((n + 1)/2);
    }

    public int findJ() {
        int n = (int) (this.y * (this.board.getSize()-1) / (this.pictureSize/2));
        return ((n + 1)/2);
    }

    public double findX() {
        return findI()*(pictureSize/(board.getSize() - 1));
    }

    public double findY() {
        return findJ()*(pictureSize/(board.getSize() - 1));
    }

    public void showPiece() {
        switch (player){
            case BLACK -> StdDraw.picture(findX(),findY(),"BlackPiece30.png");
            case WHITE -> StdDraw.picture(findX(),findY(),"WhitePiece30.png");
        }
    }

    public Action getAction() {
        return new Action(findI(),findJ(),player);
    }

    public static void main(String[] args) {
        AClick aClick = new AClick(250,250,Piece.BLACK,new ChessBoard(15),500);
        System.out.println(aClick.findI() + " " + aClick.findJ());
        System.out.println(aClick.findX() + " " + aClick.findY());
    }
}
