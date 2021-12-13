public class Gumoku {

    public static void display(){
        StdDraw.setCanvasSize(500,500);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(0,500);
        StdDraw.setYscale(0,500);
        StdDraw.filledSquare(250,250,250);
    }

    public static void main(String[] args) {
        display();
        aGame game = new aGame(15);
        game.play();
    }
}
