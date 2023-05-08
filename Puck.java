public class Puck extends Mover{
    private boolean goal = false;

    public Puck(int x, int y) {
        super(x, y, 0.0103, 5);
    }

    public void reflect(char surface) {
        if (surface!='v' && surface!='h') throw new IllegalArgumentException("Surface has to be vertical 'v' or horizontal 'h'!");

        double[] temp = getVel();
        if (surface=='v') temp[0] = -(temp[0]*0.95); //Multiplied by 0.95 to emulate real world where not all energy is conserved while bouncing
        if (surface=='h') temp[1] = -(temp[1]*0.95);

        setVel(temp);
    }
}
