public class Puck extends Mover{
    private boolean goal = false;

    public Puck(int x, int y, GameArena arena) {
        super(x, y, 0.005, 10, arena);
        setTokenColour("BLACK");
    }

    public void bounce(int leftEdge, int rightEdge) {
        char surface = touchingEdge(leftEdge, rightEdge);
        if (surface!='v' && surface!='h' && surface!='n' && surface!='b') throw new IllegalArgumentException("Surface has to be vertical 'v' or horizontal 'h'!");
        if (surface=='n') return;

        double[] temp = getVel();
        if (surface=='b') {
            temp[0]= -(temp[0]*0.95);
            temp[1]= -(temp[1]*0.95);
        }
        if (surface=='v') temp[0] = -(temp[0]*0.95); //Multiplied by 0.95 to emulate real world where not all energy is conserved while bouncing
        if (surface=='h') temp[1] = -(temp[1]*0.95);

        setVel(temp);
    }

    public void moveAround(GameArena arena, int leftEdge, int rightEdge) {
        double[] tempFric = {0,0};
        if (getXVel()<0.01 && getXVel()>-0.01) setXVel(0);
        if (getYVel()<0.01 && getYVel()>-0.01) setYVel(0);
        if (!(getXVel()+getYVel()<0.05 && getYVel()+getXVel()>-0.05)) tempFric = dynamicFriction();
        move(arena);
    	undoFriction(tempFric);
        bounce(leftEdge, rightEdge);
    }
}
