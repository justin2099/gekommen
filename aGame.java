public class aGame {
    private Piece player1;
    private Piece player2;

    private ChessBoard board;

    public aGame(int size) {
        player1 = Piece.BLACK;
        player2 = Piece.WHITE;
        board = new ChessBoard(size);
    }

    

    public void play() {

        boolean isGameOver = false;int turn = 0;

        while (!isGameOver) {

            Piece player;

            if (turn%2 == 0) {
                player = player1;
            }
            else {
                player = player2;
            }

            Action action; AClick aClick;

            while (true) {

                AClick tryClick;

                while (true) {
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    if (StdDraw.isMousePressed()) {
                        tryClick = new AClick(StdDraw.mouseX(),StdDraw.mouseY(),player,this.board,500);
                        break;
                    }
                }


                Action tryAction = tryClick.getAction();
                if (! board.isRepeated(tryAction)){
                    if (! board.is33(tryAction)){
                        action = tryAction;
                        aClick = tryClick;
                        break;
                    }else System.out.println("no,33");
                }else System.out.println("no,repeated");
            }

            board.setChess(action);
            aClick.showPiece();

            turn ++;

            if (board.isGameWin(action)){
                System.out.println("You are winner.");
                isGameOver = true;
            }
        }
    }
}


