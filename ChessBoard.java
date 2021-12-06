import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class serves as a Chess board with a handbook for Gomoku.
 * Wu change this code in 12/6.
   I think I find a better way to achieve isFive and is33,
   by 引入方向。只要沿有棋子的正反两方向搜索，够五个便isFive，够三个便isThree。
 */

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        board = new Piece[15][15];
    }

    public ChessBoard(int size) {
        board = new Piece[size][size];
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
        board[x - 1][y - 1] = action.getPiece();
    }

    public Piece getChess(int x, int y) {
        return board[x - 1][y - 1];
    }

    public boolean isRepeated(Action action) {
        return this.board[action.getX() - 1][action.getY() - 1] != Piece.BLANK;
    }

    /* 我从这里开始修改
       嗯
     */


    // 这个方法找出action旁哪些方向还有同色棋子
    public Set<vector> findNeighbors(Action Action) {

        Set<vector> vectorsOfNeighbor = new HashSet<>();

        for (vector v:vector.values()) {
            if (board[Action.getX() + v.getI()][Action.getY() + v.getJ()] == Action.getPiece()){
                vectorsOfNeighbor.add(v);
            }
        }

        return vectorsOfNeighbor;
    }

    public boolean isFiveIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数
        for (int i = 0; i < 5; i++) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        // 沿反方向记录相邻子数
        for (int i = 0; i > -5; i--) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        return numOfPieces == 5;
    }

    public boolean isLiveThreeIn_v(Action Action, vector v) {
        int numOfPieces = 0;
        // 处理越界报错

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 0; i < 3; i++) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else if (board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)] == Action.getOpposite())
                    return false;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }
        // 沿反方向计数
        for (int i = 0; i > -3; i--) {
            try {
                if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else if (board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)] == Action.getOpposite())
                    return false;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        return numOfPieces == 3;
    }

    public boolean isGameWin(Action Action) {

        boolean haveFive = false;

        for (vector v:findNeighbors(Action)) {
            haveFive = isFiveIn_v(Action,v);
        }

        return haveFive;
    }

    public boolean is33(Action Action) {
        int numOf3 = 0;

        for (vector v:findNeighbors(Action)) {
            if (isLiveThreeIn_v(Action,v))
                numOf3 ++;
        }

        return numOf3 >= 2;

    }

}