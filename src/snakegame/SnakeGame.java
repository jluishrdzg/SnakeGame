package snakegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnakeGame extends JPanel implements KeyListener, MouseListener {
    static World world = new World();
    static Food food = new Food();
    static Lives live = new Lives();
    static Mine [] mines = new Mine [50];
    static SnakeHead snakeHead = new SnakeHead();
    static SnakeBody [] snakeBody = new SnakeBody [100];
    static SnakeTail snakeTail = new SnakeTail();
    
    static public boolean downState = false;
    static public boolean upState = false;
    static public boolean rightState = false;
    static public boolean leftState = false;
    
    static public boolean isStarted = false;
    static public boolean isCrashed = false;
    
    static public boolean isByLevels = true;
    static public boolean isByScore = false;
    
    static public boolean isLiveShown = false;
    
    static public boolean isFinishLevel = false;
    static public boolean isFailLevel = false;
    static public boolean isSnakeTookLive = false;
    static public boolean isGameOver = false;
    
    static private final int SPACEKEYCODE = 32;
    static private final int UPKEYCODE = 38;
    static private final int RIGHTKEYCODE = 39;
    static private final int DOWNKEYCODE = 40;
    static private final int LEFTKEYCODE = 37;
    static private final int ACHARKEYCODE = 65;
    static private final int SCHARKEYCODE = 83;
    static private final int DCHARKEYCODE = 68;
    static private final int WCHARKEYCODE = 87;
    
    static private int score = 0;
    static private int speed = 75;
    static private int counterSpeed = 3;
    static private int highScore = 0;
    static private int lives = 5;
    static private int counter = 0;
    static private int minesOnWorld = 0;
    static private int level = 1;
    static private int restOfFood = 0;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        world.paint(g2d);
        snakeHead.paint(g2d);
        paintSnakeTail(g2d);
        paintSnakeBody(g2d);
        if(isByLevels) {
            paintLive(g2d);
            paintMine(g2d);
        }
        
        if(snakeEeatenFood()) {
            food.newPosition();
            if(isByLevels) {
                restOfFood--;
            }
            snakeBody[score].setAvailability(true);
            score++;
            Sound.EAT.play();
        } else { food.paint(g2d); }
        
        setSnakeBodyPositions();

    }
    
    public static void paintSnakeBody(Graphics2D g2d) {  
        for(SnakeBody fullBody1 : snakeBody) {
            if (fullBody1.getAvailability()) {
                fullBody1.paint(g2d);
            }
        }
    }
    
    public static void paintSnakeTail(Graphics2D g2d) {
        if(!isStarted) {
            snakeTail.setOrientation("up");
        } 
        
        if(score == 0) {
            snakeTail.setHorizontal(snakeHead.getHorizontal());
            snakeTail.setVertical(snakeHead.getVertical());
            snakeTail.setOrientation(getOrientation());
        } else {
            snakeTail.setHorizontal(snakeBody[score-1].getHorizontal());
            snakeTail.setVertical(snakeBody[score-1].getVertical());
            snakeTail.setOrientation(snakeBody[score-1].getOrientation());
        }
        
        snakeTail.paint(g2d);
    }
    
    public static void setSnakeBodyPositions() {
        counter = score;
        
        while(counter >= 0) {       
            if(counter == 0) {
                snakeBody[counter].setHorizontal(snakeHead.getHorizontal());
                snakeBody[counter].setVertical(snakeHead.getVertical());
                snakeBody[counter].setOrientation(getOrientation());
            } else {
                snakeBody[counter].setHorizontal(snakeBody[counter-1].getHorizontal());
                snakeBody[counter].setVertical(snakeBody[counter-1].getVertical());
                snakeBody[counter].setOrientation(snakeBody[counter-1].getOrientation());
            } 
            
            counter--;  
        }
    }
    
    public static void paintLive(Graphics2D g2d) {
        if(isLiveShown) live.paint(g2d);
        else live.getCoordinates();
        
        if(snakeTakenLive()) {
            if(lives != 5) lives++;
            isLiveShown = false;
            live.resetCoordinates();
            isSnakeTookLive = true;
        }
    }
    
    public static void paintMine(Graphics2D g2d) {
        Mine mine = new Mine();
        if(mine.isGotCoortinates()) {
            mines[minesOnWorld] = mine;
            minesOnWorld++; 
        }
        
        for(int i = 0 ; i < minesOnWorld; i++) {
            mines[i].paint(g2d);
        }
    }
    
    public static int getScore() {
        return score;
    }
    
    public static int getHighScore() {
        return highScore;
    }
    
    private static void setHighScore(int score) {
        if(score > highScore && isByScore) highScore = score;
    }
    
    public static int getLives() {
        return lives;
    }
    
    public static int getCurrentWorld() {
        return level;
    }
    
    public static int getRestOfFood() {
        return restOfFood;
    }
    
    public static int getCounterSpeed() {
        return counterSpeed;
    }
    
    public static int getMinesOnWorld() {
        return minesOnWorld;
    }
    public static String getOrientation() {
        if(upState)  return "up";
        if(rightState) return "right";
        if(downState) return "down";
        if(leftState) return "left";
        return "noOrientation";
    }
    
    public static boolean snakeEeatenFood() {
        return snakeHead.getBounds().intersects(food.getBounds());
    }
    
    public static boolean snakeTakenLive() {
         return snakeHead.getBounds().intersects(live.getBounds());
    }
    
    public static boolean isHeadCrashingBody() {
        for(int i = score; i > 0; i--) 
            if(snakeHead.getBounds().intersects(snakeBody[i].getBounds()) && 1 != 0) 
                return true;
        return false;
    }
    
    private static boolean isHeadCrashingMine() {
        for(int i = minesOnWorld -1; i >= 0; i--) 
            if(snakeHead.getBounds().intersects(mines[i].getBounds()) && 1 != 0) 
                return true;
        return false;
    }
    
    public static boolean isHeadCrashingTail() {
        return snakeHead.getBounds().intersects(snakeTail.getBounds()) && score != 0;
    }
    
    public static boolean isRightCrashing() {
        return snakeHead.getHorizontal() > 401 - 20;
    }
    
    public static boolean isBotCrashing() {
        return snakeHead.getVertical() > 661 - 20;
    }
 
    public static boolean isLeftCrashing() {
        return snakeHead.getHorizontal() < -1;
    }
    
    public static boolean isTopCrashing() {
        return snakeHead.getVertical() < -1;
    }   
    
    public static void changeMoveState(boolean upState, boolean rightState, 
        boolean downState, boolean leftState) {
            SnakeGame.upState = upState;
            SnakeGame.rightState = rightState;
            SnakeGame.downState = downState;
            SnakeGame.leftState = leftState;
    }
    
    public static void cleanWorld() {
        snakeHead.resetPosition();
        snakeTail.setHorizontal(snakeHead.getHorizontal());
        snakeTail.setVertical(snakeHead.getVertical());

        for(int i = 0; i < snakeBody.length; i++)
            snakeBody[i] = new SnakeBody();
        
        food.newPosition();
        changeMoveState(false, false, false, false);
        
        if(lives == 0) {
            level = 1;
            lives = 5;
            isGameOver = true;
        }

        score = 0;
        isStarted = false;
        isCrashed = true;
        
        minesOnWorld = 0;
        speed = 75;
        counterSpeed = 3;
    }
      
    public static void  main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        
        frame.addKeyListener(game);
        frame.addMouseListener(game);
        frame.add(game);
        frame.setSize(602, 690);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        for(int i = 0; i < snakeBody.length; i++)
            snakeBody[i] = new SnakeBody();
       
        while(true) {
            game.repaint();
            
            if(!isStarted) restOfFood =  level * 2 + 8;
 
            if (restOfFood == 0) {
                isFinishLevel = true;
                cleanWorld();
                level++;
                speed--;
            }
           
            if(upState) snakeHead.moveUp();
            if(rightState) snakeHead.moveRight();           
            if(downState) snakeHead.moveDown();                
            if(leftState) snakeHead.moveLeft();            

            if(isRightCrashing() || isBotCrashing() ||
                    isTopCrashing() || isLeftCrashing() ||
                    isHeadCrashingBody() || isHeadCrashingTail() ||
                    isHeadCrashingMine()) {
                if(isByLevels) lives--;
                isFailLevel = true;
                cleanWorld();
            }
            setHighScore(getScore());
            Thread.sleep(speed);
        }
    } 

    @Override
    public void keyPressed(KeyEvent e) {
        if(isStarted) {
            if(e.getKeyCode() == UPKEYCODE || e.getKeyCode() == WCHARKEYCODE) 
                if(!upState && !downState) 
                    changeMoveState(true, false, false, false);    

            if(e.getKeyCode() == RIGHTKEYCODE || e.getKeyCode() == DCHARKEYCODE) 
                if(!rightState && !leftState) 
                    changeMoveState(false, true, false, false);

            if(e.getKeyCode() == DOWNKEYCODE || e.getKeyCode() == SCHARKEYCODE) 
                if(!downState && !upState) 
                    changeMoveState(false, false, true, false);           
                
            if(e.getKeyCode() == LEFTKEYCODE || e.getKeyCode() == ACHARKEYCODE) 
                if(!leftState && !rightState) 
                    changeMoveState(false, false, false, true);
        }
        
        if(e.getKeyCode() == SPACEKEYCODE) {
            if(!isByLevels && !isByScore) {
                JOptionPane.showMessageDialog(this, "choose a type of game");
                return;
            }

            if(!isStarted) {
                changeMoveState(true, false, false, false);
                isStarted = true;   
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        // Select by levels Option
        if(x >= 450 && x <= 465 && y >= 288 && y <= 303 && !isStarted) {
            isByLevels = true;
            isByScore = false;  
            cleanWorld();
        }
        // Select by score option
        if(x >= 450 && x <= 465 && y >= 313 && y <= 326 && !isStarted) {
            isByScore = true;
            isByLevels = false;
            cleanWorld();
        }
        // Click on speed's levels
        if(x >= 488 && x <= 503 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 1;
            speed = 150;
        }
        
        if(x >= 504 && x <= 519 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 2;
            speed = 110;
        }
        
        if(x >= 520 && x <= 535 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 3;
            speed = 75;
        }
        
        if(x >= 536 && x <= 551 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 4;
            speed = 70;
        }
        
        if(x >= 552 && x <= 567 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 5;
            speed = 60;
        }
        
        if(x >= 568 && x <= 583 && y >= 220 && y <= 235 && isByScore) {
            counterSpeed = 6;
            speed = 50;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}