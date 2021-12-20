public class aGameWithAI extends aGame{
    private AIChessBoard board;

    public aGameWithAI(int size) {
        super(size);
        board = new AIChessBoard(size);
    }

    @Override
    public void play() {
        if (Math.random() >= 0.5){

        }
    }
}
