public class Gumoku {
    public static void main(String[] args) {
        aGame game = new aGame(15);
        game.getBoard().display();
        game.play();
    }
}
