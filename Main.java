public class Main {
    public static void main(String[] args) {
        GameArena arena = new GameArena(1050, 600);
        innitAirHockey(arena);

        Puck puck = new Puck(518, 338, arena);
        Mallet player1 = new Mallet(304, 338, arena);
        Mallet player2 = new Mallet(732, 338, arena);
        puck.setXVel(2);
        while (true) {
            player1.moveVertical(arena);
            player1.moveHorizontal(arena);
            player1.stopHorizontal(arena);
            player1.stopVertical(arena);
            player1.move(arena);
        }
    }

    private static void innitAirHockey(GameArena arena) {
        arena.addRectangle(new Rectangle(180, 150, 675, 375, "BLUE", 0));
        arena.addRectangle(new Rectangle(190, 160, 655, 355, "WHITE", 0));
        arena.addRectangle(new Rectangle(190, 261, 7, 153, "GREY", 0));
        arena.addRectangle(new Rectangle(838, 261, 7, 153, "GREY", 0));

        arena.addBall(new Ball(518, 338, 100, "BLUE", 0));
        arena.addBall(new Ball(518, 338, 98, "WHITE", 0));
        
        arena.addLine(new Line(518, 160, 518, 288, 1, "BLUE", 0));
        arena.addLine(new Line(518, 388, 518, 515, 1, "BLUE", 0));
    }
}
