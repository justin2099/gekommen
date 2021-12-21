import java.time.Instant;

public class TestInstant {
    //Action-invoking range
    final static double DELTA_X = 15;
    final static double DELTA_Y = 15;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(10);
        counter.openCounter();
        counter.changeReference(Instant.now());
        for (int i = 0; i <= 20; i++) {
            Thread.sleep(200);
            counter.changeTime(Instant.now());
            System.out.println(counter.getT());
        }
    }

    private static void drawTimerForPlayer1(int t) throws  IllegalArgumentException{
        StdDraw.picture(437,216,".\\Timer\\black.png");

        if (t >= 600 || t < 0) throw new IllegalArgumentException("Illegal time player1");
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

        if (t >= 600 || t < 0) throw new IllegalArgumentException("Illegal time player1");
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
