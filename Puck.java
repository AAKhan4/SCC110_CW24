import java.util.Arrays;

public class Puck extends Mover{
    private enum Goal {NONE, PLAYER1, PLAYER2}; // Choice of enum as it does not let any illegal argument pass through, i.e. if it were boolean array instead maybe both goals could show as true

    public Puck(int x, int y, GameArena arena) {
        super(1, x, y, 0.005, 10, arena);
        setTokenColour("BLACK");
    }

    private Goal goalCheck() {
        if (getXPos()<197 && getYPos()>264 && getYPos()<411) return Goal.PLAYER1;
        if (getXPos()>838 && getYPos()>264 && getYPos()<411) return Goal.PLAYER2;
        return Goal.NONE;
    }

    private void bounce(int leftEdge, int rightEdge) {
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

    //Following function adds appropriate acceleration to emulate friction on the object while moving
    //Uses equations - F=ma; F(friction) = frictCoef * F(Normal)
    private double[] dynamicFriction() {
        double[] acc = getAcc();
        double fricAcceleration = (9.81*getFrictCoef());
        double directFric = (getDirection()+Math.PI);
        double[] friction = new double[2];
        friction[0] = fricAcceleration*Math.cos(directFric); //x-component of friction
        friction[1] = fricAcceleration*Math.sin(directFric); //y-component of friction
        
        setAcc(acc[0]+friction[0], acc[1]+friction[1]);
        return Arrays.copyOf(friction, friction.length);
    }

    private void undoFriction(double[] friction) {
        double[] acc = getAcc();
        setAcc(acc[0]-friction[0], acc[1]-friction[1]);
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
