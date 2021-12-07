public enum vector {
    RIGHT(1,0),
    UPRIGHT(1,1), UP(0,1), UPLEFT(-1,1);

    private int i; private int j;
    private vector(int i, int j) {
        this.i = i; this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getLength() {
        return Math.min(this.i,this.j);
    }
}
