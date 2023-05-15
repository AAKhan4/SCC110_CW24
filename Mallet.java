public class Mallet extends Mover{

    public Mallet(int x, int y, GameArena arena) {
        super(x, y, 0.0103, 25, arena); //check the friction coefficient for mallets properly!
        setTokenColour("BLUE");
    }

    public void boundaryHit() {
        char surface = touchingEdge();
        if (surface=='n') return;
        double[] temp = getVel();
        if (surface == 'v') temp[0] = 0;
        if (surface == 'h') temp[1] = 0;
        setVel(temp);
    }

    public void moveVertical(GameArena arena) {
        boundaryHit();
        setYAcc(0);
        if (arena.letterPressed('w')) setYAcc(-0.5);
        else if (arena.letterPressed('s')) setYAcc(0.5);
    }

    public void stopVertical(GameArena arena) {
        if (arena.letterPressed('w') || arena.letterPressed('s')) return;
        if (getYVel()!=0) setYAcc(-(0.5*getYVel()));
        else setYAcc(0);
    }

    public void moveHorizontal(GameArena arena) {
        boundaryHit();
        setXAcc(0);
        if (arena.letterPressed('a')) setXAcc(-0.5);
        else if (arena.letterPressed('d')) setXAcc(0.5);
    }

    public void stopHorizontal(GameArena arena) {
        if (arena.letterPressed('a') || arena.letterPressed('d')) return;
        if (getXVel()!=0) setXAcc(-(0.5*getXVel()));
        else setXAcc(0);
    }
}
