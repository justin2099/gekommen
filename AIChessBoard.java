import java.util.HashSet;
import java.util.Set;

public class AIChessBoard extends ChessBoard{

    public AIChessBoard(int size) {
        super.board = new Piece[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
    }
    public Set<vector> findMoreNeighbors(Action Action) {

        Set<vector> vectorsOfNeighbor = new HashSet<>();

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() + v.getI()][Action.getY() + v.getJ()] == Action.getPiece()
                    || board[Action.getX() + 2*v.getI()][Action.getY() + 2*v.getJ()] == Action.getPiece()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() - v.getI()][Action.getY() - v.getJ()] == Action.getPiece()
                    || board[Action.getX() - 2*v.getI()][Action.getY() - 2*v.getJ()] == Action.getPiece()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        return vectorsOfNeighbor;
    }

    public Set<vector> findEnemy(Action Action) {

        Set<vector> vectorsOfNeighbor = new HashSet<>();

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() + v.getI()][Action.getY() + v.getJ()] == Action.getOpposite()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        for (vector v:vector.values()) {
            try {
                if (board[Action.getX() - v.getI()][Action.getY() - v.getJ()] == Action.getOpposite()) {
                    vectorsOfNeighbor.add(v);
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        return vectorsOfNeighbor;
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
                else if (super.board[Action.getX() + v.getI() * (i+1)][Action.getY() + v.getJ() * (i+1)] == Action.getPiece())
                    continue;
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
                else if (super.board[Action.getX() + v.getI() * (i-1)][Action.getY() + v.getJ() * (i-1)] == Action.getPiece())
                    continue;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                break;
            }
        }

        System.out.println((numOfPieces >= 5 )+ "~~~~~~~~~~~~~~~~~" +v.name()+" "+ Action.getPiece().name());
        return numOfPieces >= 5;
    }

    public boolean isMoreLiveThreeIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 1; i < 4; i++) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else if (super.board[Action.getX() + v.getI() * (i-1)][Action.getY() + v.getJ() * (i-1)] == Action.getPiece())
                    continue;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }
        // 沿反方向计数
        for (int i = -1; i > -4; i--) {
            try {
                if (super.board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece()) {
                    numOfPieces++;
                }
                else if (super.board[Action.getX() + v.getI() * (i-1)][Action.getY() + v.getJ() * (i-1)] == Action.getPiece())
                    continue;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }

        return numOfPieces == 2;
    }


    public boolean isLiveTwoIn_v(Action Action, vector v) {
        int numOfPieces = 0;

        // 沿正方向记录相邻子数；若有敌子在后，不为活三，输出false
        for (int i = 1; i < 3; i++) {
            try {
                System.out.println(board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)].name()+"000000000000000000000000000000000");
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
                System.out.println(board[Action.getX() + v.getI() * (i + 1)][Action.getY() + v.getJ() * (i + 1)].name()+"000000000000000000000000000000000");
                if (board[Action.getX() + v.getI() * (i - 1)][Action.getY() + v.getJ() * (i - 1)] == Action.getOpposite())
                    return false;
                else if (board[Action.getX() + v.getI() * i][Action.getY() + v.getJ() * i] == Action.getPiece())
                    numOfPieces++;
                else break;
            }catch (IndexOutOfBoundsException exception) {
                return false;
            }
        }

        return numOfPieces == 1;
    }

    public Action AI_Defense(Action enemyMove) {
        for (vector v: findMoreNeighbors(enemyMove)) {
            System.out.println(v.name() + " weixiannnnnnnnnnn");
            try {
                if (isFourIn_v(enemyMove, v)) {
                    System.out.println(v.name() + " more weixiannnnnnnnnnn");
                    for (int i = 0; i < 5; i++) {
                        if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                            return new Action(enemyMove.getX() + v.getI() * i, enemyMove.getY() + v.getJ() * i, enemyMove.getOpposite());
                    }
                    for (int i = 0; i > -5; i--) {
                        if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                            return new Action(enemyMove.getX() + v.getI() * i, enemyMove.getY() + v.getJ() * i, enemyMove.getOpposite());
                    }
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
            try {
                if (isLiveThreeIn_v(enemyMove, v)) {
                    for (int i = 0; i < 5; i++) {
                        if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                                && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                            return new Action(enemyMove.getX() + v.getI() * i, enemyMove.getY() + v.getJ() * i, enemyMove.getOpposite());
                    }
                    for (int i = 0; i > -5; i--) {
                        if (board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] != enemyMove.getPiece()
                                && board[enemyMove.getX() + v.getI() * i][enemyMove.getY() + v.getJ() * i] == Piece.BLANK)
                            return new Action(enemyMove.getX() + v.getI() * i, enemyMove.getY() + v.getJ() * i, enemyMove.getOpposite());
                    }
                }
            }catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        return null;
    }
    
    public Action AI_Offensive(Action MyMove) {
        for (vector v: findMoreNeighbors(MyMove)) {
            if (isFourIn_v(MyMove,v)) {
                try {
                    for (int i = 0; i < 5; i++) {
                        Action tryAction;
                        try {
                            tryAction = new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                        } catch (IndexOutOfBoundsException e) {
                            continue;
                        }
                        if (((!isRepeated(tryAction)) && board[MyMove.getX() - v.getI()][MyMove.getY() - v.getJ()] == MyMove.getPiece()))
                            return new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                        if (((!isRepeated(tryAction)) && board[MyMove.getX() - v.getI()][MyMove.getY() - v.getJ()] == Piece.BLANK)
                              && board[MyMove.getX() + 2*v.getI()][MyMove.getY() + 2*v.getJ()] == Piece.BLANK)
                            return new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                    }

                    for (int i = 0; i > -5; i--) {
                        Action tryAction;
                        try {
                            tryAction = new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                        } catch (IndexOutOfBoundsException e) {
                            continue;
                        }
                        if ((!isRepeated(tryAction)) && board[MyMove.getX() + (i + 1) * v.getI()][MyMove.getY() + (i + 1) * v.getJ()] == MyMove.getPiece())
                            return new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                    }
                }catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
            if (isLiveThreeIn_v(MyMove,v) && ! this.isRepeated(new Action(MyMove.getX() - 3*v.getI(),MyMove.getY() - 3*v.getJ(),MyMove.getPiece()))) {
                return new Action(MyMove.getX() - 3*v.getI(),MyMove.getY() - 3*v.getJ(),MyMove.getPiece());
            }
        }
        return null;
    }

    public Action AI_BoringMove(Action MyMove) {
        if (findNeighbors(MyMove).isEmpty() && (findEnemy(MyMove).isEmpty())) {
            while (true) {
                try {
                    Action tryAction = new Action(MyMove.getX() + randomV().getI(), MyMove.getY() + randomV().getJ(), MyMove.getPiece());
                    Action tryAction2= new Action(MyMove.getX() - randomV().getI(), MyMove.getY() - randomV().getJ(), MyMove.getPiece());
                    if (!this.isRepeated(tryAction)) {
                        return tryAction;
                    } else if (!this.isRepeated(tryAction2)) {
                        return tryAction2;
                    }
                }catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        for (vector v: findNeighbors(MyMove)) {
            if (isLiveTwoIn_v(MyMove,v)) {
                for (int i = 0; i < 5; i++) {
                    Action tryAction;
                    try {
                        tryAction = new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                    }catch (IndexOutOfBoundsException e){
                        continue;
                    }
                    if (! isRepeated(tryAction))
                        return new Action(MyMove.getX()+i* v.getI(),MyMove.getY()+i*v.getJ(),MyMove.getPiece());
                }

                for (int i = 0; i > -5; i--) {
                    Action tryAction;
                    try {
                        tryAction = new Action(MyMove.getX() + i * v.getI(), MyMove.getY() + i * v.getJ(), MyMove.getPiece());
                    }catch (IndexOutOfBoundsException e){
                        continue;
                    }
                    if (! isRepeated(tryAction))
                        return new Action(MyMove.getX()+i* v.getI(),MyMove.getY()+i*v.getJ(),MyMove.getPiece());
                }
            }
        }

        return AI_FirstMove(MyMove.getPiece());
    }

    public Action AI_FirstMove(Piece piece) {


        for (int i = 0; i < 10; i++) {
            int x = randomNum1(3,6);
            int y = randomNum1(3,6);
            Action randomAction = new Action(x,y,piece);
            if (!this.isRepeated(randomAction)) return randomAction;
        }

        for (int i = 0; i < 10; i++) {
            int x = randomNum1(5,6);
            int y = randomNum1(5,6);
            Action randomAction = new Action(x,y,piece);
            if (!this.isRepeated(randomAction)) return randomAction;
        }

        for (int i = 0; i < 10; i++) {
            int x = randomNum1(9,6);
            int y = randomNum1(9,6);
            Action randomAction = new Action(x,y,piece);
            if (!this.isRepeated(randomAction)) return randomAction;
        }

        while (true) {
            int x = (int) (Math.random()*15);
            int y = (int) (Math.random()*15);
            Action randomAction = new Action(x,y,piece);
            if (!this.isRepeated(randomAction)) return randomAction;
        }

    }

    public int randomNum() {
        if (Math.random() >= 0.5) return -1;
        else return 1;
    }
    
    public int randomNum1(int l,int o) {
        return (int) (Math.random()*(l+1) + o - l/2);
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