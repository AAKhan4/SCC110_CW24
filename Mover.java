import java.util.Arrays;

public class Mover {
    //Array of 2 values for x & y axes
    private int[] pos = new int[2]; //position
    private double[] vel = {0,0}; //velocity
    private double[] acc = {0.0}; //acceleration

    //Simple constructor initial position of choice. velocity & acceleration = 0
    public Mover(int x, int y) {
        pos[0] = x;
        pos[1] = y;
    }

    //Sets of simple Getters and Mutators
    public int[] getPos() {
        return Arrays.copyOf(pos, pos.length);
    }

    public void setPos(int x, int y) {
        pos[0] = x;
        pos[1] = y;
    }

    public double[] getVel() {
        return Arrays.copyOf(vel, vel.length);
    }

    public void setVel(double x, double y) {
        vel[0] = x;
        vel[1] = y;
    }

    public double[] getAcc() {
        return Arrays.copyOf(acc, acc.length);
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
    //@param = frictCoef - coefficient of friction
    //Uses equations - F=ma; F(friction) = frictCoef * F(Normal)
    public void dynamicFriction(double frictCoef) {
        double fricAcceleration = (9.81*frictCoef);
        double directFric = (getDirection()+Math.PI);
        acc[0] += fricAcceleration*Math.cos(directFric); //Adding friction to x-component of acceleration
        acc[1] += fricAcceleration*Math.sin(directFric); //Adding friction to y-component of acceleration
    }
}