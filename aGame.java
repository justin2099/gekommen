public class aGame {
    private Piece player1;
    private Piece player2;

    private ChessBoard board;

    public aGame(int size) {
        player1 = Piece.BLACK;
        player2 = Piece.WHITE;
        board = new ChessBoard(size);
    }

    boolean isGameOver = false;int turn = 0;
    public void play() {
        while (!isGameOver) {

            Piece player;

            if (turn%2 == 0) {
                player = player1;
            }
            else {
                player = player2;
            }

            Action action;

            while (true) {
                int x = 0; int y = 0;
                Action tryAction = new Action(x,y,player);
                if (! board.isRepeated(tryAction)){
                    if (! board.is33(tryAction)){
                        action = tryAction; break;
                    }else System.out.println("no,33");
                }else System.out.println("no,repeated");
            }

            board.setChess(action);

            if (board.isGameWin(action)){
                //
                isGameOver = true;
            }
            turn ++;
        }
    }


}
