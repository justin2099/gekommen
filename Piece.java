public enum Piece {
    BLANK, WHITE, BLACK;

    public Piece getOpposite(){
        switch (this){
            case BLACK: return WHITE;
            case WHITE: return BLACK;
            case BLANK: return BLANK;
            default:    return null;
        }
    }
}
