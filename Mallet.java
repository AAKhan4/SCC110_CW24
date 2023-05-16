public class Mallet extends Mover{

    public Mallet(int x, int y, GameArena arena) {
        super(x, y, 0, 25, arena); //check the friction coefficient for mallets properly!
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
        if (up) {
            if (getYVel()<=-8) {
                setYAcc(0);
                setYVel(-8);
            }
            if (getYVel()>-8) setYAcc(-0.1);
            if (getYVel()>-7) setYAcc(-0.2);
            if (getYVel()>-6.5) setYAcc(-0.3);
            if (getYVel()>-6) setYAcc(-0.4);
            if (getYVel()>-5) setYAcc(-0.7);
            if (getYVel()>-3.5) setYAcc(-1.25);
            if (getYVel()>-2.5) setYAcc(-1.75);
            if (getYVel()>-0.5) setYAcc(-0.2);
            if (getYVel()>0) setYAcc(-2);
        } else if (down) {
            if (getYVel()>=8) {
                setYAcc(0);
                setYVel(8);
            }
            if (getYVel()<8) setYAcc(0.1);
            if (getYVel()<7) setYAcc(0.2);
            if (getYVel()<6.5) setYAcc(0.3);
            if (getYVel()<6) setYAcc(0.4);
            if (getYVel()<5) setYAcc(0.7);
            if (getYVel()<3.5) setYAcc(1.25);
            if (getYVel()<2.5) setYAcc(1.75);
            if (getYVel()<0.5) setYAcc(0.2);
            if (getYVel()<0) setYAcc(2);
        }
    }

    public void stopVertical(GameArena arena, boolean up, boolean down) {
        if (up || down) return;
        if (getYVel()!=0) setYAcc(-(getYVel()/5));
        else setYAcc(0);
    }

    public void moveHorizontal(GameArena arena, boolean left, boolean right, int leftEdge, int rightEdge) {
        boundaryHit(leftEdge, rightEdge);
        setXAcc(0);
        if (left) {
            if (getXVel()<=-8) {
                setXAcc(0);
                setXVel(-8);
            }
            if (getXVel()>-8) setXAcc(-0.1);
            if (getXVel()>-7) setXAcc(-0.2);
            if (getXVel()>-6.5) setXAcc(-0.3);
            if (getXVel()>-6) setXAcc(-0.4);
            if (getXVel()>-5) setXAcc(-0.7);
            if (getXVel()>-3.5) setXAcc(-1.25);
            if (getXVel()>-2.5) setXAcc(-1.75);
            if (getXVel()>-0.5) setXAcc(0.2);
            if (getXVel()>0) setXAcc(-2);
        } else if (right) {
            if (getXVel()>=8) {
                setXAcc(0);
                setXVel(8);
            }
            if (getXVel()<8) setXAcc(0.1);
            if (getXVel()<7) setXAcc(0.2);
            if (getXVel()<6.5) setXAcc(0.3);
            if (getXVel()<6) setXAcc(0.4);
            if (getXVel()<5) setXAcc(0.7);
            if (getXVel()<3.5) setXAcc(1.25);
            if (getXVel()<2.5) setXAcc(1.75);
            if (getXVel()<0.5) setXAcc(0.2);
            if (getXVel()<0) setXAcc(2);
        }
    }

    public void stopHorizontal(GameArena arena, boolean left, boolean right) {
        if (left || right) return;
        if (getXVel()!=0) setXAcc(-(getXVel()/5));
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
