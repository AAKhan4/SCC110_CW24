public class Main {
    public static void main(String[] args) {
        GameArena arena = new GameArena(1050, 600);
        Text scoreDisplay1 = new Text("0", 50, 70, 350, "WHITE");
        Text scoreDisplay2 = new Text("0", 50, 940, 350, "WHITE");
        innitAirHockey(arena, scoreDisplay1, scoreDisplay2);

        Puck puck = new Puck(518, 338, arena);
        Mallet player1 = new Mallet(304, 338, arena);
        Mallet player2 = new Mallet(732, 338, arena);
        puck.setXVel(8); //TEST VELOCITY VALUES FOR PUCK
        puck.setYVel(8);
        while (true) {
            player1.moveKeyPress(arena, arena.letterPressed('w'), arena.letterPressed('a'), arena.letterPressed('s'), arena.letterPressed('d'), 190, 518);
            player2.moveKeyPress(arena, arena.upPressed(), arena.leftPressed(), arena.downPressed(), arena.rightPressed(), 518, 845);
            puck.moveAround(arena, 190, 845);
        }
    }

    private static void innitAirHockey(GameArena arena, Text scoreDisplay1, Text scoreDisplay2) {
        arena.addText(new Text("WELCOME TO AIR HOCKEY!", 40, 250, 100, "WHITE"));
        arena.addText(scoreDisplay1);
        arena.addText(scoreDisplay2);
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
