import java.util.HashSet;
import java.util.Set;

/**
 * This class serves as a Chess board with a handbook for Gomoku.
 * Wu change this code on 12/6.
 I think I find a better way to achieve isFive and is33,
 by 引入方向。只要沿有棋子的正反两方向搜索，够五个便isFive，够三个便isThree。
 */

public class ChessBoard {
    protected Piece[][] board;


    public ChessBoard() {
        board = new Piece[15][15];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    public ChessBoard(int size) {
        board = new Piece[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    public ChessBoard(ChessBoard chessBoard) {
        this.board = new Piece[chessBoard.getSize()][chessBoard.getSize()];
        for (int i = 0; i < chessBoard.getSize(); i++) {
            for (int j = 0; j < chessBoard.getSize(); j++) {
                board[i][j] = chessBoard.getChess(i + 1, j + 1);
            }
        }
    }

    public int getSize() {
        return board.length;
    }

    public void clear() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    public void setChess(Action action) {
        int x = action.getX(), y = action.getY();
        board[x][y] = action.getPiece();
    }

    public Piece getChess(int x, int y) {
        return board[x - 1][y - 1];
    }

    public boolean isRepeated(Action action) {
        return this.board[action.getX()][action.getY()] != Piece.BLANK;
    }

    /* 我从这里开始修改
       嗯
     */


    // 这个方法找出action旁哪些方向还有同色棋子
    public Set<vector> findNeighbors(Action Action) {

        Set<vector> vectorsOfNeighbor = new HashSet<>();

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() + v.getI()][Action.getY() + v.getJ()] == Action.getPiece()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() - v.getI()][Action.getY() - v.getJ()] == Action.getPiece()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        return vectorsOfNeighbor;
    }

    public boolean isFiveIn_v(Action Action, vector v) {
//        ChessBoard testboard = new ChessBoard()
        int numOfPieces = 0;

        // 沿正方向记录相邻子数
        for (int i = 0; i < 5; i++) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }

        }

        // 沿反方向记录相邻子数
        for (int i = 0; i > -5; i--) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }
        System.out.println(v.name() + (numOfPieces == 6));

        return numOfPieces == 6;
    }

    public boolean isLiveThreeIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 1; i < 4; i++) {
            try {
                if (board[Action.getX() + v.getI() * i ][Action.getY() + v.getJ() * i ] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }
        // 沿反方向计数
        for (int i = -1; i > -4; i--) {
            try {
                if (board[Action.getX() + v.getI() * i ][Action.getY() + v.getJ() * i ] == Action.getOpposite())
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

    public boolean isGameWin(Action Action) {

        boolean haveFive = false;

        for (vector v:findNeighbors(Action)) {
            haveFive = isFiveIn_v(Action,v);
        }
        //System.out.println(haveFive + "have five");
        return haveFive;
    }

    public boolean is33(Action Action) {
        int numOf3 = 0;

        for (vector v:findNeighbors(Action)) {
            if (isLiveThreeIn_v(Action,v))
                numOf3 ++;
        }

        return (numOf3 >= 2) && Action.getPiece() == Piece.BLACK;
    }

}