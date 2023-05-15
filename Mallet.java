public class Mallet extends Mover{

    public Mallet(int x, int y, GameArena arena) {
        super(x, y, 0.0103, 25, arena); //check the friction coefficient for mallets properly!
        setTokenColour("BLUE");
    }

    public void boundaryHit(int leftEdge, int rightEdge) {
        char surface = touchingEdge(leftEdge, rightEdge);
        if (surface=='n') return;
        double[] temp = getVel();
        if (surface == 'v') temp[0] = 0;
        if (surface == 'h') temp[1] = 0;
        if (surface == 'b') {
            temp[0] = 0;
            temp[1] = 0;
        }
        setVel(temp);
    }

    //All moves need improvement; idea: acc should be higher at start then approach 0 in a direction
    public void moveVertical(GameArena arena, boolean up, boolean down, int leftEdge, int rightEdge) {
        boundaryHit(leftEdge, rightEdge);
        setYAcc(0);
        if (up) setYAcc(-0.5);
        else if (down) setYAcc(0.5);
    }

    public void stopVertical(GameArena arena, boolean up, boolean down) {
        if (up || down) return;
        if (getYVel()!=0) setYAcc(-(0.5*getYVel()));
        else setYAcc(0);
    }

    public void moveHorizontal(GameArena arena, boolean left, boolean right, int leftEdge, int rightEdge) {
        boundaryHit(leftEdge, rightEdge);
        setXAcc(0);
        if (left) setXAcc(-0.5);
        else if (right) setXAcc(0.5);
    }

    public void stopHorizontal(GameArena arena, boolean left, boolean right) {
        if (left || right) return;
        if (getXVel()!=0) setXAcc(-(0.5*getXVel()));
        else setXAcc(0);
    }

    public void moveKeyPress(GameArena arena, boolean up, boolean left, boolean down, boolean right, int leftEdge, int rightEdge) {
        moveVertical(arena, up, down, leftEdge, rightEdge);
        moveHorizontal(arena, left, right, leftEdge, rightEdge);
        stopVertical(arena, up, down);
        stopHorizontal(arena, left, right);
        move(arena);
    }
}
