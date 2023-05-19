public class Mallet extends Mover{

    /**
     * Constructor for Mallet class
     * Creates mallet and places it in given position on the table/GameArena
     * 
     * @param x x-position of mallet
     * @param y y-position of mallet
     * @param arena GameArena object
     */
    public Mallet(int x, int y, GameArena arena) {
        super(10, x, y, 25, arena); //check the friction coefficient for mallets properly!
        setTokenColour("BLUE");
    }

    /**
     * Determines if a boundary has been hit
     * If boundary is hit it reduces velocity in said direction to 0
     * 
     * @param leftEdge position of the left edge of table
     * @param rightEdge position of the right edge of table
     */
    private void boundaryHit(int leftEdge, int rightEdge) {
        char surface = touchingEdge(leftEdge, rightEdge);
        if (surface=='n') return;
        double[] temp = getVel();
        if (surface == 'v') temp[0] = 0;
        if (surface == 'h') temp[1] = 0;
        if (surface == 'b') {
            temp[0] = 0;
            temp[1] = 0;
        }
        setVel(temp);
    }

    /**
     * If commanded to do so, moves mallet along the vertical axis if possible
     * Movement conducted using acceleration rather than directly using velocity (changes in velocity directly would be too clunky)
     * This adds a more real world touch where one cannot go 0 to 100 speed instantaneously
     * Acceleration decreases with increasing velocity as player approaches maximum speed (cant go faster and can only apply minimal force, force friction = minimal force applied)
     * Friction is not calculated and added to mallets as acceleration here is assumed to already so in account (if friction was added acceleration over time relation would still be same)
     * 
     * @param arena GameArena object
     * @param up boolean command to move up
     * @param down boolean command to move down
     * @param leftEdge left edge of table
     * @param rightEdge right edge of table
     */
    private void moveVertical(GameArena arena, boolean up, boolean down, int leftEdge, int rightEdge) {
        boundaryHit(leftEdge, rightEdge);
        setYAcc(0);
        if (up) {
            if (getYVel()<=-10) {
                setYAcc(0);
                setYVel(-10);
            }
            if (getYVel()>-10) setYAcc(-0.1);
            if (getYVel()>-9) setYAcc(-0.2);
            if (getYVel()>-8.5) setYAcc(-0.3);
            if (getYVel()>-7.5) setYAcc(-0.4);
            if (getYVel()>-6) setYAcc(-0.7);
            if (getYVel()>-4.5) setYAcc(-1.25);
            if (getYVel()>-3.5) setYAcc(-1.75);
            if (getYVel()>-2.5) setYAcc(-2);
            if (getYVel()>-0.5) setYAcc(-0.2);
            if (getYVel()>0) setYAcc(-2);
        } else if (down) {
            if (getYVel()>=10) {
                setYAcc(0);
                setYVel(10);
            }
            if (getYVel()<10) setYAcc(0.1);
            if (getYVel()<9) setYAcc(0.2);
            if (getYVel()<8.5) setYAcc(0.3);
            if (getYVel()<7.5) setYAcc(0.4);
            if (getYVel()<6) setYAcc(0.7);
            if (getYVel()<4.5) setYAcc(1.25);
            if (getYVel()<3.5) setYAcc(1.75);
            if (getYVel()<2.5) setYAcc(2);
            if (getYVel()<0.5) setYAcc(0.2);
            if (getYVel()<0) setYAcc(2);
        }
    }

    /**
     * Checks if mallet is being commanded to move vertically
     * If not this quickly decelerates its y-velocity to 0
     * Quick deceleration again better resembles real world where one cannot instantaneously stop the mallet
     * 
     * @param arena GameArena object
     * @param up Boolean for command to move up
     * @param down boolean for command to move down
     */
    private void stopVertical(GameArena arena, boolean up, boolean down) {
        if (up || down) return;
        if (getYVel()!=0) setYAcc(-(getYVel()/5));
        else setYAcc(0);
    }

    /**
     * If commanded to do so, moves mallet along the horizontal axis if possible
     * Use of acceleration to do so is similar to that in vertical movement
     * 
     * @param arena GameArena object
     * @param left boolean to move left
     * @param right boolean to move right
     * @param leftEdge left edge of table
     * @param rightEdge right edge of table
     */
    private void moveHorizontal(GameArena arena, boolean left, boolean right, int leftEdge, int rightEdge) {
        boundaryHit(leftEdge, rightEdge);
        setXAcc(0);
        if (left) {
            if (getXVel()<=-10) {
                setXAcc(0);
                setXVel(-10);
            }
            if (getXVel()>-10) setXAcc(-0.1);
            if (getXVel()>-9) setXAcc(-0.2);
            if (getXVel()>-8.5) setXAcc(-0.3);
            if (getXVel()>-7.5) setXAcc(-0.4);
            if (getXVel()>-6) setXAcc(-0.7);
            if (getXVel()>-4.5) setXAcc(-1.25);
            if (getXVel()>-3.5) setXAcc(-1.75);
            if (getXVel()>-2.5) setXAcc(-2);
            if (getXVel()>-0.5) setXAcc(-0.2);
            if (getXVel()>0) setXAcc(-2);
        } else if (right) {
            if (getXVel()>=10) {
                setXAcc(0);
                setXVel(10);
            }
            if (getXVel()<10) setXAcc(0.1);
            if (getXVel()<9) setXAcc(0.2);
            if (getXVel()<8.5) setXAcc(0.3);
            if (getXVel()<7.5) setXAcc(0.4);
            if (getXVel()<6) setXAcc(0.7);
            if (getXVel()<4.5) setXAcc(1.25);
            if (getXVel()<3.5) setXAcc(1.75);
            if (getXVel()<2.5) setXAcc(2);
            if (getXVel()<0.5) setXAcc(0.2);
            if (getXVel()<0) setXAcc(2);
        }
    }

    /**
     * Checks if mallet is being commanded to move horizontally
     * If not this quickly decelerates its x-velocity to 0
     * 
     * @param arena GameArena object
     * @param left boolean to move left
     * @param right boolean to move right
     */
    private void stopHorizontal(GameArena arena, boolean left, boolean right) {
        if (left || right) return;
        if (getXVel()!=0) setXAcc(-(getXVel()/5));
        else setXAcc(0);
    }

    /**
     * Combines all movement commands and functions for mallets and applies the movements in GameArena
     * 
     * @param puck Puck object in game
     * @param arena GameArena object
     * @param up boolean determined using letterPressed('w') or upPressed() functions in GameArena
     * @param left determined similarly as boolean up
     * @param down determined similarly as boolean up
     * @param right determined similarly as boolean up
     * @param leftEdge left edge of table
     * @param rightEdge right edge of table
     */
    public void moveKeyPress(Puck puck, GameArena arena, boolean up, boolean left, boolean down, boolean right, int leftEdge, int rightEdge) {
        deflectionCalc(puck);
        moveVertical(arena, up, down, leftEdge, rightEdge);
        moveHorizontal(arena, left, right, leftEdge, rightEdge);
        stopVertical(arena, up, down);
        stopHorizontal(arena, left, right);
        move(arena);
    }
    
    /**
     * Calculates the angle between centres of this mallet and the puck
     * 
     * @param puck puck in game
     */
    private double calcPhi(Puck puck) {
        double phi = Math.atan2((puck.getYPos()-getYPos()), (puck.getXPos()-getXPos()));
        return phi;
    }

    /**
     * Calculates velocities of objects undergoing collision and sets them accordingly
     * Equations used to get velocities x and y for both mallet and puck can be accessed through the link in README.md file
     * 
     * @param puck puck in game
     */
    private void deflectionCalc(Puck puck) {
        double distance = Math.sqrt(((puck.getXPos()-getXPos())*(puck.getXPos()-getXPos()))+((puck.getYPos()-getYPos())*(puck.getYPos()-getYPos())));
        if (distance > (getRadius()+puck.getRadius())) return;
        
        double phi = calcPhi(puck);

        if (distance < (getRadius()+puck.getRadius())) {
            puck.setPos((getXPos()+((getRadius()+puck.getRadius())*Math.cos(phi))), (getYPos()+((getRadius()+puck.getRadius())*Math.sin(phi))));
        }

        double vel_x = ((((getScalarVel()*Math.cos(getDirection()-phi)*(getMass()-puck.getMass()))+(2*puck.getMass()*puck.getScalarVel()*Math.cos(puck.getDirection()-phi)))*(Math.cos(phi)/(getMass()+puck.getMass())))+(getScalarVel()*Math.sin(getDirection()-phi)*Math.cos(phi+(Math.PI/2))));
        double vel_y = ((((getScalarVel()*Math.cos(getDirection()-phi)*(getMass()-puck.getMass()))+(2*puck.getMass()*puck.getScalarVel()*Math.cos(puck.getDirection()-phi)))*(Math.sin(phi)/(getMass()+puck.getMass())))+(getScalarVel()*Math.sin(getDirection()-phi)*Math.sin(phi+(Math.PI/2))));

        double puckVel_x = ((((puck.getScalarVel()*Math.cos(puck.getDirection()-phi)*(puck.getMass()-getMass()))+(2*getMass()*getScalarVel()*Math.cos(getDirection()-phi)))*(Math.cos(phi)/(getMass()+puck.getMass())))+(puck.getScalarVel()*Math.sin(puck.getDirection()-phi)*Math.cos(phi+(Math.PI/2))));
        double puckVel_y = ((((puck.getScalarVel()*Math.cos(puck.getDirection()-phi)*(puck.getMass()-getMass()))+(2*getMass()*getScalarVel()*Math.cos(getDirection()-phi)))*(Math.sin(phi)/(getMass()+puck.getMass())))+(puck.getScalarVel()*Math.sin(puck.getDirection()-phi)*Math.sin(phi+(Math.PI/2))));

        setXVel(vel_x);
        setYVel(vel_y);
        puck.setXVel(puckVel_x);
        puck.setYVel(puckVel_y);
    }
}
