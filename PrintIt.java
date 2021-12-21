import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintIt {
    public static void main(String[] args) {
        try {
            PrintWriter p = new PrintWriter("Mapping15.txt");
            for (int i = 2; i <= 14; i++) {
                for (int j = 2; j <= 14; j++) {
//                if (Math.random()>0.5)
//                StdDraw.picture(531.0/14*i+1177.0/14,521.0/14*j+4771.0/14,"WhitePiece25.png",25,25,10*(Math.random()-0.5));
//                else  StdDraw.picture(531.0/14*i+1177.0/14,521.0/14*j+4771.0/14,"BlackPiece30.png", 10*(Math.random()-0.5));
//                    StdDraw.picture(531.0/14*i+1177.0/14,521.0/14*j+4771.0/14,"WhitePiece25.png",25,25,10*(Math.random()-0.5));
//                    StdDraw.setPenColor(Color.RED);
                    StdDraw.circle(531.0/14*i+1177.0/14,521.0/14*j+4771.0/14,3);

                    p.printf("(%.2f,%.2f) ",531.0/14*i+1177.0/14,521.0/14*j+4771.0/14);
                }
                p.println();
            }
            p.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }

    }
}
