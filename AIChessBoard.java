public class AIChessBoard extends ChessBoard{

    public AIChessBoard(int size) {
        super.board = new Piece[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }

    public boolean isFourIn_v(Action Action, vector v) {
//        ChessBoard testboard = new ChessBoard()
        int numOfPieces = 0;

        // 沿正方向记录相邻子数
        for (int i = 0; i < 5; i++) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        // 沿反方向记录相邻子数
        for (int i = 0; i > -5; i--) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }
        System.out.println(numOfPieces + "is four%%%................");

        return numOfPieces >= 5;
    }


    public boolean isLiveTwoIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 1; i < 3; i++) {
            try {
                if (board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }
        // 沿反方向计数
        for (int i = -1; i > -3; i--) {
            try {
                if (board[Action.getX() + v.getI() * (i - 1)][Action.getY() + v.getJ() * (i - 1)] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }

        return numOfPieces == 2;
    }

    public Action AI_Defense(Action enemyMove) {
        System.out.println(";;;;;;;;;;;;;;;;;;;;");
        for (vector v : findNeighbors(enemyMove)){
            System.out.println(v.name()+"********************8888888888");
        }
        for (vector v: findNeighbors(enemyMove)) {
            System.out.println(v.name() + "**********88");
            System.out.println(isFourIn_v(enemyMove,v) + "{{{{{{{{{{{{{{{{{{{{" + v.name());
            if (isFourIn_v(enemyMove,v)) {
                for (int i = 0; i < 5; i++) {
                    if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                        && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                        return new Action(enemyMove.getX() + v.getI()*i,enemyMove.getY() + v.getJ()*i,enemyMove.getOpposite());
                }
                for (int i = 0; i > -5; i--) {
                    if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                            && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                        return new Action(enemyMove.getX() + v.getI()*i,enemyMove.getY() + v.getJ()*i,enemyMove.getOpposite());
                }
            }

            if (isLiveThreeIn_v(enemyMove,v)) {
                for (int i = 0; i < 5; i++) {
                    if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                            && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                        return new Action(enemyMove.getX() + v.getI()*i,enemyMove.getY() + v.getJ()*i,enemyMove.getOpposite());
                }
                for (int i = 0; i > -5; i--) {
                    if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                            && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                        return new Action(enemyMove.getX() + v.getI()*i,enemyMove.getY() + v.getJ()*i,enemyMove.getOpposite());
                }
            }
        }
        return null;
    }
    
    public Action AI_Offensive(Action MyMove) {
        for (vector v: findNeighbors(MyMove)) {
            if (isFourIn_v(MyMove,v)) {
                if (MyMove.getX() + 4*v.getI() <= board.length - 1 && MyMove.getY() + 4*v.getJ() <= board.length - 1
                        &&  MyMove.getX() + 4*v.getI() >= 0 && MyMove.getY() + 4*v.getJ() >= 0) {
                    return new Action(MyMove.getX() + 4*v.getI(),MyMove.getY() + 4*v.getJ(),MyMove.getPiece());
                }
                else if (MyMove.getX() - 4*v.getI() <= board.length - 1 && MyMove.getY() - 4*v.getJ() <= board.length - 1
                        &&  MyMove.getX() - 4*v.getI() >= 0 && MyMove.getY() - 4*v.getJ() >= 0) {
                    return new Action(MyMove.getX() - 4*v.getI(),MyMove.getY() - 4*v.getJ(),MyMove.getPiece());
                }
            }
            if (isLiveThreeIn_v(MyMove,v)) {
                return new Action(MyMove.getX() - 3*v.getI(),MyMove.getY() - 3*v.getJ(),MyMove.getPiece());
            }
        }
        return null;
    }

    public Action AI_BoringMove(Action MyMove) {
        if (findNeighbors(MyMove).isEmpty()) {
            if ( ! this.isRepeated(new Action(MyMove.getX() + randomV().getI(),MyMove.getY() + randomV().getJ(),MyMove.getPiece()))) {
                System.out.println("hhhhhhhhhhhh" + this.isRepeated(new Action(MyMove.getX() + randomV().getI(),MyMove.getY() + randomV().getJ(),MyMove.getPiece())));
                return new Action(MyMove.getX() + randomV().getI(),MyMove.getY() + randomV().getJ(),MyMove.getPiece());
            }else if ( ! this.isRepeated(new Action(MyMove.getX() - randomV().getI(), MyMove.getY() - randomV().getJ(), MyMove.getPiece()))) {
                return new Action(MyMove.getX() - randomV().getI(), MyMove.getY() - randomV().getJ(), MyMove.getPiece());
            }
        }
        for (vector v: findNeighbors(MyMove)) {
            System.out.println("hhhhhhhhhhhh" + this.isRepeated(new Action(MyMove.getX() + v.getI(),MyMove.getY() + v.getJ(),MyMove.getPiece())));
            if (isLiveTwoIn_v(MyMove,v)) {
                return new Action(MyMove.getX() + 2*v.getI(),MyMove.getY() + 2*v.getJ(),MyMove.getPiece());
            }
        }
        System.out.println(MyMove.getPiece().name() + "#######@@");
        while (true){
            int x = (int)(Math.random()*15);
            int y = (int)(Math.random()*15);
            Action randomAction = new Action(x,y,Piece.WHITE);
            if (!this.isRepeated(randomAction)) return randomAction;
        }
    }

    public int randomNum() {
        if (Math.random() >= 0.5) return -1;
        else return 1;
    }
    
    public int randomNum1() {
        return (int) (Math.random()*15);
    }
    
    public vector randomV() {
        int i = (int) (Math.random() * 5);
        switch (i) {
            case 0 : return vector.UP;
            case 1 : return vector.RIGHT;
            case 2 : return vector.UPLEFT;
            case 3 : return vector.UPRIGHT;
            default: return vector.UP;
        }
    }

}