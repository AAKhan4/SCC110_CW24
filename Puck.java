public class Puck extends Mover{
    private boolean goal = false;

    public Puck(int x, int y, GameArena arena) {
        super(x, y, 0.0103, 10, arena);
        setTokenColour("BLACK");
    }

    public void bounce(int leftEdge, int rightEdge) {
        char surface = touchingEdge(leftEdge, rightEdge);
        if (surface!='v' && surface!='h' && surface!='n') throw new IllegalArgumentException("Surface has to be vertical 'v' or horizontal 'h'!");
        if (surface=='n') return;

        double[] temp = getVel();
        if (surface=='v') temp[0] = -(temp[0]*0.95); //Multiplied by 0.95 to emulate real world where not all energy is conserved while bouncing
        if (surface=='h') temp[1] = -(temp[1]*0.95);

        setVel(temp);
    }
}
