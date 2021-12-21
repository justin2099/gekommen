import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class drawTheVeilOfMayer {
    //Action-invoking range
    final static double DELTA_X = 15;
    final static double DELTA_Y = 15;

    public static void main(String[] args) {
        Point[][] PIECES_LOCATIONS;
        PIECES_LOCATIONS = new Point[15][15];
        try {
            File Locations = new File("Mapping15.txt");
            Scanner file = new Scanner(Locations);
            for (int x = 1; x <= 15; x++) {
                String[] temp = file.nextLine().split(" ");
                for (int y = 1; y <= 15; y++) {
                    PIECES_LOCATIONS[x-1][y-1] = new Point(temp[y-1]);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        displayBackground();
        drawTimerForPlayer1(1);
        drawTimerForPlayer2(0);
//        StdDraw.setPenRadius(0.005);
//        StdDraw.setPenColor(Color.RED);
        for (int x = 1; x <= 15; x++) {
            for (int y = 1; y <= 15; y++) {
                Point p0 = PIECES_LOCATIONS[x-1][y-1];
                    StdDraw.square(p0.getX(),p0.getY(),15);
                StdDraw.picture(p0.getX(),p0.getY(),".\\System\\BlackPiece30.png");
                StdDraw.picture(p0.getX(),p0.getY(),".\\System\\WhitePiece25.png");
            }
        }
        StdDraw.picture(363.5,500,".\\System\\Veil.png");
    }


    private static void displayBackground(){
        StdDraw.setCanvasSize(727,1000);
        StdDraw.setXscale(0,727);
        StdDraw.setYscale(0,1000);
        StdDraw.picture(363,500,".\\System\\draft3.jpg",727,1000);
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
