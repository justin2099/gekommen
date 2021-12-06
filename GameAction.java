public class GameAction extends Action{
    private static int white = 1;
    private static int black = 1;
    private final int whiteNumber;
    private final int blackNumber;

    public GameAction(int x, int y, Piece piece)throws IllegalArgumentException{
        super(x, y, piece);

        switch (piece){
            case BLACK:
                blackNumber = black++;
                whiteNumber = 0;
                break;
            case WHITE:
                whiteNumber = white++;
                blackNumber = 0;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void clearGame(){
        white = 0;
        black = 0;
    }
}
