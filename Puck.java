import java.util.Arrays;

public class Puck extends Mover{

    //Following instance variable is required to define Puck in addition to other variables in Mover class
    private double frictCoef = 0.005; // Coefficient of friction of this puck

    /**
     * Constructor for Puck class creates puck and places it in given position on table/GameArena
     * 
     * @param x x-position of puck
     * @param y y- position of puck
     * @param arena GameArena object
     */
    public Puck(int x, int y, GameArena arena) {
        super(1, x, y, 10, arena);
        setTokenColour("BLACK");
    }

    /**
     * Checks if a goal is scored
     * 
     * @return 0 if no goal, 1 if left goal, 2 if right goal
     */
    public int goalCheck() {
        if (!(touchingEdge(191, 841) == 'v' && getYPos()>264 && getYPos()<411)) return 0;
        if (getXPos()<328) return 1;
        else return 2;
    }

    /**
     * Reflects ball off edge of table
     * reduces speed slightly to mimic inelastic collision
     * 
     * @param leftEdge left edge of table
     * @param rightEdge right edge of table
     */
    private void bounce(int leftEdge, int rightEdge) {
        char surface = touchingEdge(leftEdge, rightEdge);
        if (surface!='v' && surface!='h' && surface!='n' && surface!='b') throw new IllegalArgumentException("Surface has to be vertical 'v' or horizontal 'h'!");
        if (surface=='n') return;

        double[] temp = getVel();
        if (surface=='b') {
            temp[0]= -(temp[0]*0.97);
            temp[1]= -(temp[1]*0.97);
        }
        if (surface=='v') temp[0] = -(temp[0]*0.95); //Multiplied by 0.95 to emulate real world where not all energy is conserved while bouncing
        if (surface=='h') temp[1] = -(temp[1]*0.95);

        setVel(temp);
    }

    /**
     * Calculates appropriate acceleration to emulate friction on the puck while moving
     * Uses equations - F=ma & F(friction) = frictCoef * F(Normal)
     * 
     * @return array with x and y components of acceleration caused by friction
     */
    private double[] dynamicFriction() {
        double[] acc = getAcc();
        double fricAcceleration = (9.81*frictCoef);
        double directFric = (getDirection()+Math.PI);
        double[] friction = new double[2];
        friction[0] = fricAcceleration*Math.cos(directFric); //x-component of friction
        friction[1] = fricAcceleration*Math.sin(directFric); //y-component of friction
        
        setAcc(acc[0]+friction[0], acc[1]+friction[1]);
        return Arrays.copyOf(friction, friction.length);
    }

    /**
     * Removes acceleration caused by friction (in order to recalculate later as direction of puck may have changed)
     * 
     * @param friction array with x and y components of acceleration caused by friction
     */
    private void undoFriction(double[] friction) {
        double[] acc = getAcc();
        setAcc(acc[0]-friction[0], acc[1]-friction[1]);
    }

    /**
     * Combines all functions required for movement of puck and applies the movements in GameArena
     * 
     * @param arena GameArena object
     * @param leftEdge Left edge of table
     * @param rightEdge Right edge of table
     */
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
