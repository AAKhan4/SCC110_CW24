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
}
