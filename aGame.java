import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 实现一次五子棋游戏的核心类。
 * 提供一个play方法。
 */
public class aGame {
    final private Piece player1 = Piece.BLACK;
    final private Piece player2 = Piece.WHITE;

    private ChessBoard board;
    private Point[][] PIECES_LOCATIONS;

    public aGame(int size) {
        board = new ChessBoard(size);
        PIECES_LOCATIONS = new Point[size][size];
        try {
            File Locations = new File("Mapping.txt");
            Scanner file = new Scanner(Locations);
            for (int x = 1; x <= this.getSize(); x++) {
                String[] temp = file.nextLine().split(" ");
                for (int y = 1; y <= this.getSize(); y++) {
                    PIECES_LOCATIONS[x-1][y-1] = new Point(temp[y-1]);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public int getSize() {
        return board.getSize();
    }

    public void play(){
        displayBackground();
        boolean isGameOver = false;int turn = 0;
        while (!isGameOver){
            Piece player;
            if (turn%2 == 0) player = player1;
            else             player = player2;

            Action tryAction;
            while (true){
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Point p = getClick();
                tryAction = handleClick(p,player);
                if (tryAction == null) continue;
                if (board.isRepeated(tryAction)) continue;
                if (board.is33(tryAction)) continue;
                break;
            }

            Action action = tryAction;
            board.setChess(action);
//            refreshBoardCondition(action);
            show();

            if (board.isGameWin(action)){
                isGameOver = true;
            }
            turn++;
        }
    }

    private void displayBackground(){
        StdDraw.setCanvasSize(727,1000);
        StdDraw.setXscale(0,727);
        StdDraw.setYscale(0,1000);
        StdDraw.picture(363,500,"draft3.jpg",727,1000);
    }

/**
 * 原本我把这个方法叫做displayBoardCondition，旨在通过绘制棋盘状态的方式保持画面与底层的一致。
 * 然而由于StdDraw绘制时有逐一绘制的效果，我将这个方法改为了refresh，来取得视效上的优化。
 * 这让我必须在主程序里面让棋盘状态与画面保持一致。刚刚我就搞错了。
 * 而是否当计时器加入之后要改成动画呢？稍后再试。
 */
    private void refreshBoardCondition(Action action) throws IllegalArgumentException{
        int x = action.getX(); int y = action.getY();
        double X = PIECES_LOCATIONS[x-1][y-1].getX();
        double Y = PIECES_LOCATIONS[x-1][y-1].getY();

        String pictureName;
        switch (action.getPiece()) {
            case WHITE:
                pictureName = "WhitePiece25.png";
                break;
            case BLACK:
                pictureName = "BlackPiece30.png";
                break;
            default:
                throw new IllegalArgumentException("Wrong Piece");
        }

        StdDraw.picture(X,Y,pictureName);
    }
/**
 * show方法用于同步测试。
 * 现阶段的问题在于：重复复绘制制。
 */
    private void show(){
        for (int x = 1; x <= getSize(); x++) {
            for (int y = 1; y <= getSize(); y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                double X = p0.getX(); double Y = p0.getY();

                if (board.getChess(x,y) == Piece.WHITE)
                    StdDraw.picture(X,Y,"WhitePiece25.png");
                if (board.getChess(x,y) == Piece.BLACK)
                    StdDraw.picture(X,Y,"BlackPiece30.png");
            }
        }
    }

    /**
     * 不止是getClicked，when not Clicked， 应当追踪鼠标的行动，并显示落子的定点。
     */

    private static Point getClick(){
        while (true){
            if (StdDraw.isMousePressed()){
                return new Point(StdDraw.mouseX(),StdDraw.mouseY());
            }
        }
    }
/**
 * 任何click需要统一的处理，而click激发出的反应可以有天壤之别。
 * 需要设计一种 divide & conquer 的方法。
 */

/**
 * 在目前的StdDraw的使用环境下，对click唯一需要做出的反应是假如它下在棋盘上，就制造一次下棋的尝试。
 * 因此handleClick方法目前的版本只需要直接实现它就好了。
 */
    private Action handleClick(Point p,Piece piece){
      //Action-invoking range
        final double DELTA_X = 13;
        final double DELTA_Y = 13;

        for (int x = 1; x <= getSize(); x++) {
            for (int y = 1; y <= getSize(); y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                if (Math.abs(p0.getX()-p.getX()) < DELTA_X
                 && Math.abs(p0.getY()-p.getY()) < DELTA_Y){
                    return new Action(x,y,piece);
                }
            }
        }

        return null;
    }
}

class Point{
    double x; double y;
    public Point(double x, double y){
        this.x = x; this.y = y;
    }
    /**
     *(x,y)
     */
    public Point(String s){
        String[] format = s.split("[(,)]");
        x = Double.parseDouble(format[1]);
        y = Double.parseDouble(format[2]);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

}