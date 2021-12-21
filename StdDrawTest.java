import java.util.Scanner;

public class StdDrawTest {
    public static void main(String[] args) throws InterruptedException {
        displayBackground();

        Scanner scanner = new Scanner(System.in);
//        while (true){
//            drawCounter(scanner.nextInt());
//            Thread.sleep(1000);
//            myClear();
////            displayBackground();//displayBackground 所包含的那些初始化框架的信息，需要分离出来，否则框框不能动
//        }
        int n = 9;
//        while (n-- > 0){
//            drawCounter(n);
//            Thread.sleep(1000);
//            myClear();
//        }

        while (n-- > 0){
            myClear();
            drawCounter(n);
            drawTimerForPlayer1(n);
            drawTimerForPlayer2(n);
            Thread.sleep(1000);
            StdDraw.picture(397,611,".\\System\\blackWin.png");
            StdDraw.picture(350,616,".\\System\\whiteWin.png");
        }
    }//用\\在String中打出路径

    private static void displayBackground(){
        StdDraw.setCanvasSize(727,1000);
        StdDraw.setXscale(0,727);
        StdDraw.setYscale(0,1000);
        StdDraw.picture(363,500,".\\System\\draft3.jpg",727,1000);
    }

    private static void myClear(){
        StdDraw.picture(363,500,".\\System\\draft3.jpg",727,1000);
    }

    private static void drawCounter(int t){
        if (t <= 10) {
            StdDraw.picture(236,248,".\\Timer\\Big\\"+ t +".png");
        }
        else if (t < 20) {
            int t2 = t%10;
            StdDraw.picture(178,247,".\\Timer\\Big\\10.png");
            StdDraw.picture(289,250,".\\Timer\\Big\\" + t2 + ".png");
        }
        else if (t%10 == 0 && t < 100) {
            int t1 = t/10;
            StdDraw.picture(178,247,".\\Timer\\Big\\" + t1 + ".png");
            StdDraw.picture(289,250,".\\Timer\\Big\\10.png");
        }
        else {
            int t1 = t/10; int t2 = t%10;
            StdDraw.picture(95,244,".\\Timer\\Big\\" + t1 + ".png");
            StdDraw.picture(202,245,".\\Timer\\Big\\10.png");
            StdDraw.picture(304,245,".\\Timer\\Big\\" + t2 + ".png");
        }
    }

    private static void drawTimerForPlayer1(int t) throws  IllegalArgumentException{
        StdDraw.picture(437,216,".\\Timer\\black.png");

        if (t >= 500 || t < 0) throw new IllegalArgumentException("Illegal time player1");
            //            (538,213) (563,207) (591,213) (627,213)
        else if (t == 0) {
            StdDraw.picture(538,213,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(563,207,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,213,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(627,213,".\\Timer\\Arabic\\red0.png");
        }
        else {
            int  min = t/60; int s  = t%60;
            int  s1  = s/10; int s2 = s%10;
            StdDraw.picture(538,213,".\\Timer\\Arabic\\" + min + ".png");
            StdDraw.picture(563,207,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,213,".\\Timer\\Arabic\\" + s1 + ".png");
            StdDraw.picture(627,213,".\\Timer\\Arabic\\" + s2 + ".png");
        }

    }

    private static void drawTimerForPlayer2(int t){
        StdDraw.picture(434,291,".\\Timer\\white.png");

        if (t >= 500 || t < 0) throw new IllegalArgumentException("Illegal time player1");
        else if (t == 0) {
            StdDraw.picture(538,286,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(563,280,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,286,".\\Timer\\Arabic\\red0.png");
            StdDraw.picture(627,286,".\\Timer\\Arabic\\red0.png");
        }
        else {
            int  min = t/60; int s  = t%60;
            int  s1  = s/10; int s2 = s%10;
            StdDraw.picture(538,286,".\\Timer\\Arabic\\" + min + ".png");
            StdDraw.picture(563,280,".\\Timer\\Arabic\\ddot.png");
            StdDraw.picture(591,286,".\\Timer\\Arabic\\" + s1 + ".png");
            StdDraw.picture(627,286,".\\Timer\\Arabic\\" + s2 + ".png");
        }
    }
}
