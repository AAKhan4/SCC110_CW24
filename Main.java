public class Main {
    public static void main(String[] args) {
        GameArena arena = new GameArena(1050, 600);
        innitAirHockey(arena);
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