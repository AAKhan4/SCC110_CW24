import java.util.Arrays;

public class Mover {
    //Array of 2 values for x & y axes
    private Ball token;
    private double[] vel = {0,0}; //velocity
    private double[] acc = {0,0}; //acceleration
    private double frictCoef; //Coefficient of friction
    private int radius;

    //Simple constructor initial position of choice. velocity & acceleration = 0
    public Mover(double x, double y, double frictCoef, int radius, GameArena arena) {
        this.token = new Ball(x, y, (2*radius), null, 1);
        this.frictCoef = frictCoef;
        this.radius = radius;

        arena.addBall(token);
    }

    //Sets of simple Getters and Mutators

    public double getXPos() {
        return token.getXPosition();
    }

    public double getYPos() {
        return token.getYPosition();
    }

    public void setXPos(double x) {
        token.setXPosition(x);
    }

    public void setYPos(double y) {
        token.setYPosition(y);
    }

    public void setPos(double x, double y) {
        token.setXPosition(x);
        token.setYPosition(y);
    }

    public void setTokenColour(String col) {
        token.setColour(col);
    }

    public double[] getVel() {
        return Arrays.copyOf(vel, vel.length);
    }
    
    public double getXVel() {
        return vel[0];
    }

    public double getYVel() {
        return vel[1];
    }

    public void setXVel(double x) {
        vel[0] = x;
    }

    public void setYVel(double y) {
        vel[0] = y;
    }

    public void setVel(double x, double y) { //individual values inserting
        vel[0] = x;
        vel[1] = y;
    }

    public void setVel(double[] vel) { //values inserted together in array
        this.vel[0] = vel[0];
        this.vel[1] = vel[1];
    }

    public double[] getAcc() {
        return Arrays.copyOf(acc, acc.length);
    }

    public void setXAcc(double x) {
        acc[0] = x;
    }

    public void setYAcc(double y) {
        acc[1] = y;
    }

    public void setAcc(double x, double y) {
        acc[0] = x;
        acc[1] = y;
    }

    //Returns direction of motion in radians.
    public double getDirection() { 
        return Math.atan2(vel[1], vel[0]);
        // tan(a) = y/x where a is the direction <- line above performs arc tan and gives ans in radians
    }

    //Following function adds appropriate acceleration to emulate friction on the object while moving
    //Uses equations - F=ma; F(friction) = frictCoef * F(Normal)
    public void dynamicFriction() {
        double fricAcceleration = (9.81*frictCoef);
        double directFric = (getDirection()+Math.PI);
        acc[0] += fricAcceleration*Math.cos(directFric); //Adding friction to x-component of acceleration
        acc[1] += fricAcceleration*Math.sin(directFric); //Adding friction to y-component of acceleration
    }

    public void undoFriction() {
        double fricAcceleration = (9.81*frictCoef);
        double directFric = (getDirection()+Math.PI);
        acc[0] -= fricAcceleration*Math.cos(directFric); //Removing friction to x-component of acceleration
        acc[1] -= fricAcceleration*Math.sin(directFric); //Removing friction to y-component of acceleration
    }

    public char touchingEdge() { //Values here for table boundaries are not set
        if ((getXPos()-radius) <= 190 || (getXPos()+radius) >= 845) {
            setXPos(((getXPos()-radius)<=190)? (190+radius):(845-radius)); //In case mover runs out of table this moves it back to boundary
            return 'v'; //'v' for vertical boundary touched
        }
        if ((getYPos()-radius) <= 160 || (getYPos()+radius) >= 515) {
            setYPos(((getYPos()-radius)<=160)? (160+radius):(515-radius));
            return 'h'; //'h' for horizontal boundary touched
        }
        return 'n'; //'n' for no boundary touched
    }

    public void move(GameArena arena) {
            arena.pause();
            setVel(vel[0]+acc[0], vel[1]+acc[1]);
            setPos(getXPos()+vel[0], getYPos()+vel[1]);
    }
}