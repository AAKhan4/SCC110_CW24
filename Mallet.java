public class Mallet extends Mover{

    public Mallet(int x, int y) {
        super(x, y, 0.0103, 10); //check the friction coefficient for mallets properly!
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
