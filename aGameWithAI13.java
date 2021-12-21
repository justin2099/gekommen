import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.Scanner;

public class aGameWithAI13 {
    final private Piece player1 = Piece.BLACK;
    final private Piece player2 = Piece.WHITE;

    private Counter timer1 = new Counter(599);
    private Counter timer2 = new Counter(599);
    private Counter localCounter = new Counter(45);

    private AIChessBoard board;
    private Point[][] PIECES_LOCATIONS;

    //Action-invoking range
    final double DELTA_X = 15;
    final double DELTA_Y = 15;

    public int getSize() {
        return board.getSize();
    }

    public void play() {
        StdDraw.enableDoubleBuffering();
        displayBackground();
        initializeTimer();

//        stdShow(timer1.getT(), timer2.getT(), localCounter.getT());
        stdShow(1, 1, 1);
        boolean isGameOver = false;
        int turn = 0;
        Action action = new Action(0,0,Piece.BLACK);
        Action actionAI = new Action(5,5,Piece.WHITE);
        while (!isGameOver) {
            Piece player;


            if (turn % 2 == 0) {
                player = player1;
                timer1.openCounter();
                timer1.changeReference(Instant.now());

                localCounter.changeReference(Instant.now());
                //功能性的校准，目的是为了让计时器从目前的时间点开始对准钟倒计时

                Action tryAction;

                try {
                    while (true) {
                        Point p = getClick();
                        tryAction = handleClick(p, player);

                        if (tryAction == null) continue;
                        if (board.isRepeated(tryAction)) continue;
                        if (board.is33(tryAction)) {
                            show33();
                            StdDraw.show();
                            continue;
                        }
                        break;
                    }

                    action = tryAction;
                    board.setChess(action);
//            refreshBoardCondition(action);
                    stdShow(timer1.getT(), timer2.getT(), localCounter.getT());
                    StdDraw.show();

                    if (board.isGameWin(action)) {
                        isGameOver = true;
                        showGameOver(player);
                        StdDraw.show();
                    }
                    turn++;

                    timer1.closeCounter();
                    timer2.closeCounter();
                    localCounter.resetT(30);
                } catch (TimeOver | InterruptedException et) {
                    et.printStackTrace();
                    showGameOver(player.getOpposite());
                    StdDraw.show();
                    break;
                }
            } else {
                if (board.AI_Defense(action) != null) {
                    actionAI = board.AI_Defense(action);
                    System.out.println("D");
                } else if (board.AI_Offensive(actionAI) != null) {
                    actionAI = board.AI_Offensive(actionAI);
                    System.out.println("o");
                } else {
                    actionAI = board.AI_BoringMove(actionAI);
                    System.out.println("B");
                }



                board.setChess(actionAI);
                System.out.println(actionAI.getX() + " " + actionAI.getY());
//            refreshBoardCondition(actionAI);
                stdShow(timer1.getT(), timer2.getT(), localCounter.getT());
                StdDraw.show();
                System.out.println(board.isGameWin(actionAI));

                if (board.isGameWin(actionAI)) {
                    isGameOver = true;
                    showGameOver(Piece.WHITE);
                    StdDraw.show();
                }
                turn++;

                timer1.closeCounter();
                timer2.closeCounter();
                localCounter.resetT(30);
            }


        }
    }


    private void displayBackground(){
        StdDraw.setCanvasSize(727,1000);
        StdDraw.setXscale(0,727);
        StdDraw.setYscale(0,1000);
        StdDraw.picture(363,500,".\\System\\draft4.jpg",727,1000);
    }

    private void show(){
        for (int x = 1; x <= getSize(); x++) {
            for (int y = 1; y <= getSize(); y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                double X = p0.getX(); double Y = p0.getY();

                if (board.getChess(x,y) == Piece.WHITE)
                    StdDraw.picture(X,Y,".\\System\\WhitePiece25.png");
                if (board.getChess(x,y) == Piece.BLACK)
                    StdDraw.picture(X,Y,".\\System\\BlackPiece30.png");
            }
        }
    }
    /**
     *33（377，636）
     */
    private void show33() throws InterruptedException {
        StdDraw.picture(377,636,".\\System\\33.png");
        Thread.sleep(10);
        StdDraw.picture(377,636,".\\System\\33.png");
    }

    /**
     * whiteWin: (350,616)
     * blackWin: (397,611)
     */

    private void showGameOver(Piece winner){
        if (winner == Piece.BLACK) {
            StdDraw.picture(370,611,".\\System\\blackWin.png");
        }
        else {
            StdDraw.picture(350,616,".\\System\\whiteWin.png");
        }
    }
    /**
     * 通过这个名为stdShow的方法，一次性展示所有需要展示的内容，故曰stdShow。
     */

//在发展过程中，我发现getclick成为了一个核心，大部分时间在这里度过，多余功能在这里实现。
    private Point getClick() throws TimeOver, InterruptedException {
        while (true){
            Instant nowIns = Instant.now();
            if (timer1.isOn()){
                timer1.changeTime(nowIns);
            }
            if (timer2.isOn()){
                timer2.changeTime(nowIns);
            }
            localCounter.changeTime(nowIns);

            if (StdDraw.isMousePressed()){
                return new Point(StdDraw.mouseX(),StdDraw.mouseY());
            }
            Thread.sleep(10);

            stdShow(timer1.getT(),timer2.getT(),localCounter.getT());
            throwTimeOver();
        }
    }
    /**
     * 任何click需要统一的处理，而click激发出的反应可以有天壤之别。
     * 需要设计一种 divide & conquer 的方法。
     * 在目前的StdDraw的使用环境下，对click唯一需要做出的反应是假如它下在棋盘上，就制造一次下棋的尝试。
     * 因此handleClick方法目前的版本只需要直接实现它就好了。
     */
    private Action handleClick(Point p,Piece piece){
        for (int x = 1; x <= getSize(); x++) {
            for (int y = 1; y <= getSize(); y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                if (Math.abs(p0.getX()-p.getX()) < DELTA_X
                        && Math.abs(p0.getY()-p.getY()) < DELTA_Y){
                    return new Action(x-1,y-1,piece);
                }
            }
        }

        return null;
    }

    private void initializeTimer() {
        timer1.openCounter();
        timer2.closeCounter();
    }

    private void throwTimeOver() throws TimeOver {
        if (localCounter.getT() <= 0){
            throw new TimeOver("local Over");
        }
        if (timer1.getT() <= 0){
            throw new TimeOver("timer1 Over");
        }
        if (timer2.getT() <= 0){
            throw new TimeOver("timer2 Over");
        }
    }

    private void stdShow(long timer1, long timer2, long localCounter){
        for (int x = 1; x <= getSize(); x++) {
            for (int y = 1; y <= getSize(); y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                if (Math.abs(p0.getX()-StdDraw.mouseX()) < DELTA_X
                        && Math.abs(p0.getY()-StdDraw.mouseY()) < DELTA_Y){
                    StdDraw.square(p0.getX(),p0.getY(),15);
                    try {
                        StdDraw.show();
                        Thread.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
//永远被遮住再show

        this.veil();
        drawCounter(localCounter);
        drawTimerForPlayer1(timer1);
        drawTimerForPlayer2(timer2);
        show();

        StdDraw.show();
    }

    private void veil(){
        StdDraw.picture(363.5,500,".\\System\\Veil4.png");
    }

    /**
     * t代表倒计时的秒数
     */
    private void drawCounter(long t) throws IllegalArgumentException{
        if (t >= 100 || t < 0) throw new IllegalArgumentException("Illegal time");
        else if (t <= 10) {
            StdDraw.picture(236,248,".\\Timer\\Big\\"+ t +".png");
        }
        else if (t < 20) {
            long t2 = t%10;
            StdDraw.picture(178,247,".\\Timer\\Big\\10.png");
            StdDraw.picture(289,250,".\\Timer\\Big\\" + t2 + ".png");
        }
        else if (t%10 == 0 && t < 100) {
            long t1 = t/10;
            StdDraw.picture(178,247,".\\Timer\\Big\\" + t1 + ".png");
            StdDraw.picture(289,250,".\\Timer\\Big\\10.png");
        }
        else {
            long t1 = t/10; long t2 = t%10;
            StdDraw.picture( 95,244,".\\Timer\\Big\\" + t1 + ".png");
            StdDraw.picture(202,245,".\\Timer\\Big\\10.png");
            StdDraw.picture(304,245,".\\Timer\\Big\\" + t2 + ".png");
        }
    }

    //black： (437,216)        x :  xx     (538,213) (563,207) (591,213) (627,213)
    private void drawTimerForPlayer1(long t) throws  IllegalArgumentException{
        StdDraw.picture(437,216,".\\Timer\\black.png");

        if (t >= 600 || t < 0) throw new IllegalArgumentException("Illegal time player1");
        else if (t == 0) {
            StdDraw.picture(538,213,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(563,207,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,213,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(627,213,".\\Timer\\Arabic\\red0.png");
        }
        else {
            long  min = t/60; long s  = t%60;
            long  s1  = s/10; long s2 = s%10;
            StdDraw.picture(538,213,".\\Timer\\Arabic\\" + min + ".png");
            StdDraw.picture(563,207,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,213,".\\Timer\\Arabic\\" + s1 + ".png");
            StdDraw.picture(627,213,".\\Timer\\Arabic\\" + s2 + ".png");
        }
    }
    //white： (434,291)        x :  xx     (538,286) (563,280) (591,286) (627,286)
    private void drawTimerForPlayer2(long t){
        StdDraw.picture(434,291,".\\Timer\\white.png");

        if (t >= 600 || t < 0) throw new IllegalArgumentException("Illegal time player1");
        else if (t == 0) {
            StdDraw.picture(538,286,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(563,280,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,286,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(627,286,".\\Timer\\Arabic\\red0.png");
        }
        else {
            long  min = t/60; long s  = t%60;
            long  s1  = s/10; long s2 = s%10;
            StdDraw.picture(538,286,".\\Timer\\Arabic\\" + min + ".png");
            StdDraw.picture(563,280,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,286,".\\Timer\\Arabic\\" + s1 + ".png");
            StdDraw.picture(627,286,".\\Timer\\Arabic\\" + s2 + ".png");
        }
    }
    public aGameWithAI13(int size) {
        board = new AIChessBoard(size);
        PIECES_LOCATIONS = new Point[size][size];
        try {
            File Locations = new File(".\\src\\System\\Mapping13.txt");
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

    public static void main(String[] args) {
        new aGameWithAI13(13).displayBackground();
        new aGameWithAI13(13).stdShow(1,2,3);
//        new aGameWithAI13(13).play();
    }
}
