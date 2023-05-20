public class Main {
    public static void main(String[] args) {
        int[] score = {0,0};
        GameArena arena = new GameArena(1050, 600);
        Text welcomeMsg = new Text("WELCOME TO AIR HOCKEY!", 40, 250, 100, "WHITE");
        Text scoreDisplay1 = new Text("0", 50, 70, 350, "WHITE");
        Text scoreDisplay2 = new Text("0", 50, 940, 350, "WHITE");
        innitAirHockey(arena, scoreDisplay1, scoreDisplay2, welcomeMsg);

        Puck puck = new Puck(518, 338, arena);
        Mallet player1 = new Mallet(304, 338, arena);
        Mallet player2 = new Mallet(732, 338, arena);
        
        gameOver(arena, score);
        while (true) {
            goalScore(player1, player2, puck, score, arena, scoreDisplay1, scoreDisplay2);
            if (score[0] == 7 || score[1] == 7) break;
            
            player1.moveKeyPress(puck, arena, arena.letterPressed('w'), arena.letterPressed('a'), arena.letterPressed('s'), arena.letterPressed('d'), 190, 518);
            player2.moveKeyPress(puck, arena, arena.upPressed(), arena.leftPressed(), arena.downPressed(), arena.rightPressed(), 518, 845);
            puck.moveAround(arena, 190, 845);
        }
        
    }

    /**
     * Creates air hockey table prints welcome message and shows score
     * 
     * @param arena
     * @param scoreDisplay1
     * @param scoreDisplay2
     * @param welcomeMsg
     */
    private static void innitAirHockey(GameArena arena, Text scoreDisplay1, Text scoreDisplay2, Text welcomeMsg) {
        arena.addText(new Text("WELCOME TO AIR HOCKEY!", 40, 250, 100, "WHITE"));
        arena.addText(welcomeMsg);
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

    /**
     * Performs a check for goal, if a goal occurs it allocates point to appropriate side and resets puck & mallet in correct position
     * 
     * @param player1
     * @param player2
     * @param puck
     * @param score
     * @param arena
     * @param scoreDisplay1
     * @param scoreDisplay2
     */
    private static void goalScore(Mallet player1, Mallet player2, Puck puck, int[] score, GameArena arena, Text scoreDisplay1, Text scoreDisplay2) {
        int goal = puck.goalCheck();
        if (goal == 0) return;
        for (int i = 0; i < 25; i++) arena.pause();
        String temp = "";
        player1.setPos(304, 338);
        player1.setVel(0, 0);
        player1.setAcc(0, 0);
        player2.setPos(732, 338);
        player2.setVel(0, 0);
        player2.setAcc(0, 0);
        puck.setVel(0, 0);
        puck.setAcc(0, 0);

        if (goal == 2) {
            score[0] ++;
            temp+=score[0];
            scoreDisplay1.setText((temp));
            puck.setPos(568, 338);
            for (int i = 0; i < 50; i++) arena.pause();
            return;
        }

        score[1] ++;
        temp+=score[1];
        scoreDisplay2.setText((temp));
        puck.setPos(468, 338);
        for (int i = 0; i < 50; i++) arena.pause();
    }
    
    private static void gameOver(GameArena arena, int[] score) {
        arena.addRectangle(new Rectangle(100,40, 850, 520, "BLUE", 2));
        arena.addRectangle(new Rectangle(110, 50, 830, 500, "WHITE", 2));
        arena.addText(new Text("GAME OVER", 50, 355, 150, "BLACK", 2));
        Text playerName = new Text("PLAYER ", 40, 405, 250, "DARKGREY", 2);
        arena.addText(playerName);
        if (score[0]>score[1]) playerName.setText(playerName.getText()+"1");
        if (score[0]<score[1]) playerName.setText(playerName.getText()+"2");
        else {
            playerName.setText("NOBODY");
            playerName.setXPosition(420);
        }
    }
}
