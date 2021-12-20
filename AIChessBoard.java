public class AIChessBoard extends ChessBoard{

    public AIChessBoard(int size) {
        super.board = new Piece[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    @Override
    public boolean isFiveIn_v(Action Action, vector v) {
//        ChessBoard testboard = new ChessBoard()
        int numOfPieces = 0;

        // 沿正方向记录相邻子数
        for (int i = 1; i < 5; i++) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        // 沿反方向记录相邻子数
        for (int i = -1; i > -5; i--) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        return numOfPieces >= 4;
    }


    public boolean isLiveTwoIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 1; i < 3; i++) {
            try {
                if (board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }
        // 沿反方向计数
        for (int i = -1; i > -3; i--) {
            try {
                if (board[Action.getX() + v.getI() * (i - 1)][Action.getY() + v.getJ() * (i - 1)] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }

        return numOfPieces == 2;
    }

    public void AI_Defense(Action enemyMove) {
        for (vector v: findNeighbors(enemyMove)) {

        }
    }


}
