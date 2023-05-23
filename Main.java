public class Main {
    public static void main(String[] args) {
        int[] score = {0,0};
        boolean restart = false;

        GameArena arena = new GameArena(1050, 600);
        MusicManager music = new MusicManager();
        Text welcomeMsg = new Text("WELCOME TO AIR HOCKEY!", 40, 250, 100, "WHITE");
        Text scoreDisplay1 = new Text("0", 50, 70, 350, "WHITE");
        Text scoreDisplay2 = new Text("0", 50, 940, 350, "WHITE");
        innitAirHockey(arena, scoreDisplay1, scoreDisplay2, welcomeMsg);

        Puck puck = new Puck(518, 338, arena);
        Mallet player1 = new Mallet(304, 338, arena);
        Mallet player2 = new Mallet(732, 338, arena);
        
        do{
            if (restart) {
                restart = false;
                score[0]=0;
                score[1]=0;
                String temp = "0";
                scoreDisplay1.setText(temp);
                scoreDisplay2.setText(temp);
                puck.setXPos(518);
            }

            startMenu(arena, menuBorder(), music);
            music.play("SCC110-AirHockey-main\\fanfare.wav");
            
            while (true) {
                boolean stop = false;

                goalScore(player1, player2, puck, score, arena, scoreDisplay1, scoreDisplay2, music);
                if (arena.escPressed()) stop = pauseGame(arena, menuBorder(), pauseMenu(), music, true);
                if (score[0] == 7 || score[1] == 7 || stop) break;
                
                player1.moveKeyPress(puck, arena, arena.letterPressed('w'), arena.letterPressed('a'), arena.letterPressed('s'), arena.letterPressed('d'), 190, 518, music);
                player2.moveKeyPress(puck, arena, arena.upPressed(), arena.leftPressed(), arena.downPressed(), arena.rightPressed(), 518, 845, music);
                puck.moveAround(arena, 190, 845, music);
            }
            restart = gameOver(arena, score, menuBorder(), endGameMenu(), music);
        } while(restart);
        arena.addRectangle(new Rectangle(0, 0, 1050, 600, "BLACK", 3));
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

        arena.addText(new Text("[ESC] - PAUSE", 20, 445, 135, "WHITE", 0));
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
    private static void goalScore(Mallet player1, Mallet player2, Puck puck, int[] score, GameArena arena, Text scoreDisplay1, Text scoreDisplay2, MusicManager music) {
        int goal = puck.goalCheck(music);
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

    /**
     * Creates a box for any menu that pops up
     * 
     * @return array with all rectangles needed to make the box
     */
    private static Rectangle[] menuBorder() {
        Rectangle[] border = {new Rectangle(100,40, 850, 520, "BLUE", 2), 
                              new Rectangle(110, 50, 830, 500, "WHITE", 2)};
        
        return border;
    }

    /**
     * Creates all text that goes into making the end game menu
     * 
     * @return array with all Text objects required to make the end game menu
     */
    private static Text[] endGameMenu() {
        Text[] endMenu = {new Text("GAME OVER", 50, 355, 150, "BLACK", 2), 
                            new Text("PLAYER ", 40, 420, 260, "DARKGREY", 2), 
                            new Text("WINS", 40, 462, 310, "DARKGREY", 2), 
                            new Text("[SPACE] - NEW GAME", 20, 410, 430, "DARKGREY", 2), 
                            new Text("[ENTER] - END GAME", 20, 412, 460, "DARKGREY", 2)};
        
        return endMenu;
    }
    
    /**
     * Creates all text that goes into making the pause menu
     * 
     * @return array with all Text objects required to make the pause menu
     */
    private static Text[] pauseMenu() {
        Text[] pause = {new Text("PAUSED", 50, 410, 150, "BLACK", 2), 
                            new Text("[M] - MUTE SOUND", 20, 420, 360, "DARKGREY", 2), 
                            new Text("[U] - UNMUTE SOUND", 20, 410, 400, "DARKGREY", 2), 
                            new Text("[SPACE] - RESUME", 20, 418, 440, "DARKGREY", 2), 
                            new Text("[ENTER] - QUIT", 20, 436, 480, "DARKGREY", 2)};
        
        return pause;
    }

    /**
     * Responsible for adding and removing the start menu that appears at the start of every game
     * 
     * @param arena GameArena object
     * @param border array holding objects to create the border for the menu
     * @param music MusicManager to play the starting sound (drumroll)
     */
    private static void startMenu(GameArena arena, Rectangle[] border, MusicManager music) {
        for (int i = 0; i < border.length; i++) arena.addRectangle(border[i]);
        Text title = new Text("AIR HOCKEY", 50, 350, 150, "BLACK", 2);
        Text sub = new Text("[SPACE] - START", 20, 420, 460, "DARKGREY", 2);
        arena.addText(title);
        arena.addText(sub);
        music.play("SCC110-AirHockey-main\\drumroll.wav");
        while (true) {
            System.out.print("");
            if (arena.spacePressed()) {
                arena.removeText(title);
                arena.removeText(sub);
                for (int i = 0; i < border.length; i++) arena.removeRectangle(border[i]);
                return;
            }
        }
    }

    /**
     * Creates and/or removes the pause menu in game when called
     * 
     * @param arena GameArena object
     * @param border array holding objects to create the border for the menu
     * @param menu array holding text required to create this menu
     * @param music MusicManager to mute or unmute its sound output
     * @param visible boolean saying whether to create or remove this menu
     * 
     * @return boolean if the game is quit
     */
    private static boolean pauseGame(GameArena arena, Rectangle[] border, Text[] menu, MusicManager music, boolean visible) {
        if (!visible) {
            for (int j=0; j<menu.length; j++) arena.removeText(menu[j]);
            for (int i=0; i<border.length; i++) arena.removeRectangle(border[i]);
            return false;
        }

        for (int i=0; i<border.length; i++) arena.addRectangle(border[i]);
        for (int j=0; j<menu.length; j++) arena.addText(menu[j]);
        while (true) {
            System.out.print("");
            if (arena.letterPressed('m')) music.setStatus(true);
            if (arena.letterPressed('u')) music.setStatus(false);
            if (arena.spacePressed()) {
                pauseGame(arena, border, menu, music, false);
                return false;
            }
            if (arena.enterPressed()) {
                while (arena.enterPressed()) System.out.print("");
                pauseGame(arena, border, menu, music, false);
                return true;
            }
        }
    }

    /**
     * Function in charge of running the end game menu after a game terminates
     * 
     * @param arena GameArena object
     * @param score array holding the end score
     * @param border array holding objects to create the border for the menu
     * @param menu array holding text required to create this menu
     * @param music MusicManager to play end game music
     * 
     * @return whether the user wishes to restart the game or quit
     */
    private static boolean gameOver(GameArena arena, int[] score, Rectangle[] border, Text[] menu, MusicManager music) {
        music.play("SCC110-AirHockey-main\\fanfare.wav");
        for (int i = 0; i < border.length; i++) arena.addRectangle(border[i]);
        for (int j = 0; j < menu.length; j++) arena.addText(menu[j]);
        if (score[0]>score[1]) menu[1].setText(menu[1].getText()+"1");
        else if (score[0]<score[1]) menu[1].setText(menu[1].getText()+"2");
        else {
            menu[1].setText("TIE");
            menu[1].setXPosition(480);
        }
        
        if(score[0]==score[1]) arena.removeText(menu[2]);

        while (true) {
            System.out.print("");
            if (arena.spacePressed()) {
                restartGame(arena, menu, border, score);
                return true;
            }
            if (arena.enterPressed()) return false;
        }
    }

    /**
     * Removes the end game menu in case the user wishes to restart the game
     * 
     * @param arena GameArena object
     * @param menu array holding Texts required for this menu
     * @param border array holding Rectangles required for this menu
     * @param score array holding the final score
     */
    private static void restartGame(GameArena arena, Text[] menu, Rectangle[]border, int[] score) {
        for (int i = 0; i < menu.length; i++) {
            if (i!=2) arena.removeText(menu[i]);
            if (i==2 && score[0]!=score[1]) arena.removeText(menu[i]);
        }
        for (int j = 0; j < border.length; j++) arena.removeRectangle(border[j]);
    }
}
