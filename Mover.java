import java.util.Arrays;

public class Mover {
    // Following instance variables define information needed to represent all Movers
    private Ball token; //Sprite of this Mover to represent it in GameArena
    private double prop_mass; // Mass of this Mover set always in proportions i.e. real_mass/puck_mass
    private double[] vel = {0,0}; // Array for velocity, contains x and y components
    private double[] acc = {0,0}; // Array for acceleration, contains x and y components
    
    private int radius;

    /**
     * Constructor sets mass, position and size of token and adds it to GameArena
     * 
     * @param mass proportional mass of Mover
     * @param x x position of Mover
     * @param y y position of Mover
     * @param radius 1/2 diameter of Mover
     * @param arena GameArena object
     */
    public Mover(double mass, double x, double y, int radius, GameArena arena) {
        this.prop_mass = mass;
        this.token = new Ball(x, y, (2*radius), null, 1);
        this.radius = radius;

        arena.addBall(token);
    }

    /**
     * Gets proportional mass of Mover
     * 
     * @return proportional mass of Mover
     */
    public double getMass() {
        return prop_mass;
    }

    /**
     * Changes proportional mass of Mover
     * 
     * @param mass new proportional mass
     */
    public void setMass(double mass) {
        prop_mass = mass;
    }

    /**
     * Gets position of Mover
     * 
     * @return array containing x and y coordinates of Mover
     */
    public double[] getPos() {
        double[] temp = {getXPos(), getYPos()};
        return Arrays.copyOf(temp, temp.length);
    }

    /**
     * Gets x position of Mover
     * 
     * @return x coordinate of mover
     */
    public double getXPos() {
        return token.getXPosition();
    }

    /**
     * Gets y position of Mover
     * 
     * @return y coordinate of mover
     */
    public double getYPos() {
        return token.getYPosition();
    }

    /**
     * Changes x position of mover
     * 
     * @param x new x coordinate
     */
    public void setXPos(double x) {
        token.setXPosition(x);
    }

    /**
     * Changes y position of mover
     * 
     * @param y new y coordinate
     */
    public void setYPos(double y) {
        token.setYPosition(y);
    }

    /**
     * Changes overall position of Mover
     * 
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void setPos(double x, double y) {
        token.setXPosition(x);
        token.setYPosition(y);
    }

    /**
     * Changes colour of Ball object linked to this Mover
     * Getter is not needed for this as Ball class has one already and it doesn't need to be easier to reach as no new class made requires it
     * 
     * @param col new colour
     */
    public void setTokenColour(String col) {
        token.setColour(col);
    }

    /**
     * Gets velocity of Mover
     * 
     * @return Array with x and y components of velocity
     */
    public double[] getVel() {
        return Arrays.copyOf(vel, vel.length);
    }
    
    /**
     * Gets x component of velocity of Mover
     * 
     * @return x component of velocity of Mover
     */
    public double getXVel() {
        return vel[0];
    }

    /**
     * Gets y component of velocity of Mover
     * 
     * @return y component of velocity of Mover
     */
    public double getYVel() {
        return vel[1];
    }

    /**
     * Calculates and returns the scalar magnitude of total velocity of Mover
     * 
     * @return magnitude of velocity of Mover
     */
    public double getScalarVel() {
        return (Math.sqrt((vel[0]*vel[0])+(vel[1]*vel[1])));
    }

    /**
     * Changes x component of velocity of Mover
     * 
     * @param x new x component of velocity of Mover
     */
    public void setXVel(double x) {
        vel[0] = x;
    }

    /**
     * Changes y component of velocity of Mover
     * 
     * @param y new y component of velocity of Mover
     */
    public void setYVel(double y) {
        vel[1] = y;
    }

    /**
     * Changes overall velocity of Mover using 2 independent values
     * 
     * @param x new x component of velocity of Mover
     * @param y new y component of velocity of Mover
     */
    public void setVel(double x, double y) { //individual values inserting
        vel[0] = x;
        vel[1] = y;
    }

    /**
     * Changes overall velocity of Mover using values in an array of size 2
     * 
     * @param vel Array holding new x and y components of velocity for Mover
     */
    public void setVel(double[] vel) { //values inserted together in array
        this.vel[0] = vel[0];
        this.vel[1] = vel[1];
    }

    /**
     * Gets acceleration of Mover
     * 
     * @return Array with x and y components of acceleration
     */
    public double[] getAcc() {
        return Arrays.copyOf(acc, acc.length);
    }

    /**
     * Changes x component of acceleration of Mover
     * 
     * @param x new x component of acceleration of Mover
     */
    public void setXAcc(double x) {
        acc[0] = x;
    }

    /**
     * Changes y component of acceleration of Mover
     * 
     * @param y new y component of acceleration of Mover
     */
    public void setYAcc(double y) {
        acc[1] = y;
    }

    /**
     * Changes overall acceleration of Mover using 2 independent values
     * 
     * @param x new x component of acceleration of Mover
     * @param y new y component of acceleration of Mover
     */
    public void setAcc(double x, double y) {
        acc[0] = x;
        acc[1] = y;
    }

    /**
     * Gets radius of Mover
     * 
     * @return radius of Mover
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Changes radius of Mover
     * 
     * @param r new radius
     */
    public void setRadius(int r) {
        radius = r;
    }

    /**
     * Returns direction of motion of Mover in radians
     * 
     * @return direction of motion of Mover
     */
    public double getDirection() { 
        return Math.atan2(vel[1], vel[0]);
        // tan(a) = y/x where a is the direction <- line above performs arc tan and gives ans in radians
    }

    /**
     * Determines whether Mover is touching a boundary it should not cross
     * 
     * @param leftEdge left boundary
     * @param rightEdge right boundary
     * 
     * @return 'n' if not touching, 'v' for vertical boundary, 'h' for horizontal boundary, 'b' for both
     */
    public char touchingEdge(int leftEdge, int rightEdge) { //Values here for table boundaries are not set
        if (((getXPos()-radius) <= leftEdge || (getXPos()+radius) >= rightEdge) && ((getYPos()-radius) <= 160 || (getYPos()+radius) >= 515)) {
            setXPos(((getXPos()-radius)<=leftEdge)? (leftEdge+radius+1):(rightEdge-radius-1));
            setYPos(((getYPos()-radius)<=160)? (160+radius):(515-radius));
            return 'b';
        }
        if ((getXPos()-radius) <= leftEdge || (getXPos()+radius) >= rightEdge) {
            if (getYPos()>266 && getYPos()<409) setXPos(((getXPos()-radius)<=leftEdge)? (leftEdge+radius+1):(rightEdge-radius-1)); //In case mover runs out of table this moves it back to boundary
            return 'v'; //'v' for vertical boundary touched
        }
        if ((getYPos()-radius) <= 160 || (getYPos()+radius) >= 515) {
            setYPos(((getYPos()-radius)<=160)? (160+radius):(515-radius));
            return 'h'; //'h' for horizontal boundary touched
        }
        return 'n'; //'n' for no boundary touched
    }

    /**
     * Applies effects of velocity and acceleration on Mover to determine and move it to its new position
     * 
     * @param arena GameArena
     */
    public void move(GameArena arena) {
            arena.pause();
            setVel(vel[0]+acc[0], vel[1]+acc[1]);
            setPos(getXPos()+vel[0], getYPos()+vel[1]);
    }
}