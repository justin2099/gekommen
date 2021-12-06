import java.util.Scanner;

/**
 * This class serves as a Chess board with a handbook for Gomoku.
 */

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard(){
        board = new Piece[15][15];
    }

    public ChessBoard(int size){
        board = new Piece[size][size];
    }

    public ChessBoard(ChessBoard chessBoard){
        this.board = new Piece[chessBoard.getSize()][chessBoard.getSize()];
        for (int i = 0; i < chessBoard.getSize(); i++) {
            for (int j = 0; j < chessBoard.getSize(); j++) {
                board[i][j] = chessBoard.getChess(i+1,j+1);
            }
        }
    }

    public int getSize(){
        return board.length;
    }

    public void clear(){
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    public void setChess(Action action){
        int x = action.getX(), y = action.getY();
        board[x-1][y-1] = action.getPiece();
    }

    public Piece getChess(int x, int y) {
        return board[x-1][y-1];
    }

    public boolean isRepeated(Action action){
        return this.board[action.getX()-1][action.getY()-1] != Piece.BLANK;
    }

    public boolean is33(Action action) throws ArrayIndexOutOfBoundsException{
        if (action.getPiece() != Piece.BLACK) return false;

        return isLiveThreeX(action) && isLiveThreeY(action)  ||
               isLiveThreeX(action) && isLiveThreeXY(action) ||
               isLiveThreeX(action) && isLiveThreeYX(action) ||
               isLiveThreeY(action) && isLiveThreeXY(action) ||
               isLiveThreeY(action) && isLiveThreeYX(action) ||
               isLiveThreeYX(action) && isLiveThreeXY(action);
    }
    /**
     *  case 1   |X|X|@| | |
     *  case 2   | |X|@|X| |
     *  case 3   | | |@|X|X|
     */
    private boolean isLiveThreeX(Action action){
        int x = action.getX(), y = action.getY();
        boolean threeIsOnX = false, isLiveThreeX = false;
        int i = x;
        while(x<=i && i<=x+2){
            if (i-1 -2 < 0 || i > getSize()){
                i++;
                continue;
            }

            final boolean X1 = this.board[i-1 -2][y-1] == Piece.BLACK;
            final boolean X2 = this.board[i-1 -1][y-1] == Piece.BLACK;
            final boolean X3 = this.board[i-1][y-1] == Piece.BLACK;
            threeIsOnX = X1 && X2 && X3;

            boolean isBlockedX = (i+1 > this.getSize()) || (i-3 < 1);
            if (!isBlockedX)
            isLiveThreeX = threeIsOnX
                     && (this.board[i + 1 - 1][y - 1] != Piece.WHITE)
                     && (this.board[i - 3 - 1][y - 1] != Piece.WHITE);

            if (isLiveThreeX) break;
            i++;
        }
        return isLiveThreeX;
    }

    /**     1 2 3
     *     |Y| | |
     *     |Y|Y| |
     *     |@|@|@|
     *     | |Y|Y|
     *     | | |Y|
     */
    private boolean isLiveThreeY(Action action){
        int x = action.getX(), y = action.getY();
        boolean threeIsOnY = false, isLiveThreeY = false;
        int i = y;
        while(y<=i && i<=y+2){
            if (i-1 -2 < 0 || i > getSize()){
                i++;
                continue;
            }

            final boolean Y1 = this.board[x-1][i-1 -2] == Piece.BLACK;
            final boolean Y2 = this.board[x-1][i-1 -1] == Piece.BLACK;
            final boolean Y3 = this.board[x-1][i-1] == Piece.BLACK;

            threeIsOnY = Y1 && Y2 && Y3;
            boolean isBlockedY = (i+1 > this.getSize()) || (i-3 < 1);
            if (!isBlockedY)
            isLiveThreeY = threeIsOnY
                     && (this.board[x - 1][i + 1 - 1] != Piece.WHITE)
                     && (this.board[x - 1][i - 3 - 1] != Piece.WHITE);

            if (isLiveThreeY) break;
            i++;
        }
        return isLiveThreeY;
    }

    /**
     *     |X|2|3| | | | |
     *     | |X|X|3| | | |
     *     | | |@|@|@| | |
     *     | | | |1|X|X| |
     *     | | | | |1|2|X|
     */
    private boolean isLiveThreeXY(Action action){
        int x = action.getX(), y = action.getY();
        boolean threeIsOnXY = false;
        boolean isLiveThreeXY = false;
        int i = x, j = y;
        while(x<=i && i<=x+2){
            if (i-1 -2 < 0 || i > getSize() || j-1 -2 < 0 || j > getSize()){
                i++; j++;
                continue;
            }

            final boolean XY1 = this.board[i-1 -2][j-1 -2] == Piece.BLACK;
            final boolean XY2 = this.board[i-1 -1][j-1 -1] == Piece.BLACK;
            final boolean XY3 = this.board[i-1][j-1] == Piece.BLACK;

            threeIsOnXY = XY1 && XY2 && XY3;
            boolean isBlockedXY = (i+1 > this.getSize()) || (i-3 < 1)
                                ||(j+1 > this.getSize()) || (j-3 < 1);
            if (!isBlockedXY)
            isLiveThreeXY = threeIsOnXY
                    && (this.board[i + 1 - 1][j + 1 - 1] != Piece.WHITE)
                    && (this.board[i - 3 - 1][j - 3 - 1] != Piece.WHITE);

//            if (isBlockedXY) break;//惊天bug
            if (isLiveThreeXY) break;
            i++; j++;
        }
        return isLiveThreeXY;
    }

    /**
     *     | | | | |1|2|X|
     *     | | | |1|X|X| |
     *     | | |@|@|@| | |
     *     | |X|X|3| | | |
     *     |X|2|3| | | | |
     */
    private boolean isLiveThreeYX(Action action){
        int x = action.getX(), y = action.getY();
        boolean threeIsOnYX = false;
        boolean isLiveThreeYX = false;
        int i = x, j = y;
        while(x<=i && i<=x+2){
            if (i-1 -2 < 0 || i > getSize() || j-1 < 0 || j + 2 > getSize()){
                i++; j--;
                continue;
            }

            final boolean YX1 = this.board[i-1 -2][j-1 +2] == Piece.BLACK;
            final boolean YX2 = this.board[i-1 -1][j-1 +1] == Piece.BLACK;
            final boolean YX3 = this.board[i-1][j-1] == Piece.BLACK;

            threeIsOnYX = YX1 && YX2 && YX3;
            boolean isBlockedYX = (i+1 > this.getSize()) || (i-3 < 1)
                                ||(j+3 > this.getSize()) || (j-1 < 0);
            if (!isBlockedYX)
            isLiveThreeYX = threeIsOnYX
                    && (this.board[i + 1 - 1][j - 1 - 1] != Piece.WHITE)
                    && (this.board[i - 3 - 1][j + 3 - 1] != Piece.WHITE);

            if (isLiveThreeYX) break;
            i++; j--;
        }
        return isLiveThreeYX;
    }

    public boolean isWon(Action action){
        return isFiveX(action) || isFiveY(action) || isFiveXY(action) || isFiveYX(action);
    }

    private boolean isFiveX(Action action){
        final Piece PRESENT = action.getPiece();
        int x = action.getX(), y = action.getY();
        int i = x;
        boolean isFiveX = false;
        while(x<=i && i<=x+4) {
            if (i - 1 - 4 < 0 || i > getSize()) {
                i++;
                continue;
            }

            final boolean X1 = this.board[i - 1 - 4][y - 1] == PRESENT;
            final boolean X2 = this.board[i - 1 - 3][y - 1] == PRESENT;
            final boolean X3 = this.board[i - 1 - 2][y - 1] == PRESENT;
            final boolean X4 = this.board[i - 1 - 1][y - 1] == PRESENT;
            final boolean X5 = this.board[i - 1][y - 1] == PRESENT;
            isFiveX = X1 && X2 && X3 && X4 && X5;
            if (isFiveX) break;
            i++;
        }
        return isFiveX;
    }

    private boolean isFiveY(Action action){
        final Piece PRESENT = action.getPiece();
        int x = action.getX(), y = action.getY();
        int i = y;
        boolean isFiveY = false;
        while(y<=i && i<=y+4) {
            if (i - 1 - 4 < 0 || i > getSize()) {
                i++;
                continue;
            }

            final boolean Y1 = this.board[x-1][i - 1 - 4] == PRESENT;
            final boolean Y2 = this.board[x-1][i - 1 - 3] == PRESENT;
            final boolean Y3 = this.board[x-1][i - 1 - 2] == PRESENT;
            final boolean Y4 = this.board[x-1][i - 1 - 1] == PRESENT;
            final boolean Y5 = this.board[x-1][i - 1] == PRESENT;
            isFiveY = Y1 && Y2 && Y3 && Y4 && Y5;
            if (isFiveY) break;
            i++;
        }
        return isFiveY;
    }

    private boolean isFiveXY(Action action){
        final Piece PRESENT = action.getPiece();
        int x = action.getX(), y = action.getY();
        int i = x, j = y;
        boolean isFiveXY = false;
        while(x<=i && i<=x+4) {
            if (i - 1 - 4 < 0 || i > getSize()
             || j - 1 - 4 < 0 || j > getSize()) {
                i++; j++;
                continue;
            }

            final boolean XY1 = this.board[i - 1 - 4][j - 1 - 4] == PRESENT;
            final boolean XY2 = this.board[i - 1 - 3][j - 1 - 3] == PRESENT;
            final boolean XY3 = this.board[i - 1 - 2][j - 1 - 2] == PRESENT;
            final boolean XY4 = this.board[i - 1 - 1][j - 1 - 1] == PRESENT;
            final boolean XY5 = this.board[i - 1][j - 1] == PRESENT;
            isFiveXY = XY1 && XY2 && XY3 && XY4 && XY5;
            if (isFiveXY) break;
            i++; j++;
        }
        return isFiveXY;
    }

    private boolean isFiveYX(Action action){
        final Piece PRESENT = action.getPiece();
        int x = action.getX(), y = action.getY();
        int i = x, j = y;
        boolean isFiveYX = false;
        while(x<=i && i<=x+4) {
            if (i - 1 - 4 < 0 || i > getSize()
                    || j-1 < 0 || j + 4 > getSize()) {
                i++; j--;
                continue;
            }

            final boolean YX1 = this.board[i - 1 - 4][j - 1 + 4] == PRESENT;
            final boolean YX2 = this.board[i - 1 - 3][j - 1 + 3] == PRESENT;
            final boolean YX3 = this.board[i - 1 - 2][j - 1 + 2] == PRESENT;
            final boolean YX4 = this.board[i - 1 - 1][j - 1 + 1] == PRESENT;
            final boolean YX5 = this.board[i - 1][j - 1] == PRESENT;
            isFiveYX = YX1 && YX2 && YX3 && YX4 && YX5;
            if (isFiveYX) break;
            i++; j--;
        }
        return isFiveYX;
    }


    private void show(){
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (board[j][i] == Piece.BLACK) System.out.print(1);
                else if (board[j][i] == Piece.WHITE) System.out.print(2);
                else System.out.print(0);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(7);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                while (true) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (x> chessBoard.getSize()|| y>chessBoard.getSize())
                        throw new IllegalArgumentException();
                    chessBoard.setChess(new Action(x, y, Piece.BLACK));
                    chessBoard.show();

                    if (scanner.nextInt() == 0) {
                        System.out.println(20999);
                        break;
                    }
                }

                int x = scanner.nextInt();
                int y = scanner.nextInt();
                chessBoard.show();
                ChessBoard chessBoard1 = new ChessBoard(chessBoard);
                chessBoard1.setChess(new Action(x,y,Piece.BLACK));
                System.out.println("***********");
                chessBoard1.show();
                System.out.println(chessBoard1.is33(new Action(x,y,Piece.BLACK)));
                System.out.println(chessBoard1.isWon(new Action(x,y,Piece.BLACK)));
            }
            catch (ArrayIndexOutOfBoundsException x){
                System.out.println(x);
                continue;
            }
            catch (IllegalArgumentException y){
                System.out.println("wrong!!");
                System.out.println(y);
                continue;
            }
        }
    }
}
