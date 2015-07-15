package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class SnakeGame extends JPanel implements KeyListener, ActionListener {
    static World world = new World();
    static Food food = new Food();
    static SnakeHead snakeHead = new SnakeHead();
    static SnakeBody [] fullBody = new SnakeBody [100];
    static SnakeTail snakeTail = new SnakeTail();
    
    static public boolean downState = false;
    static public boolean upState = false;
    static public boolean rightState = false;
    static public boolean leftState = false;
    static public boolean isStarted = false;
    
    static private final int SPACEKEYCODE = 32;
    static private final int UPKEYCODE = 38;
    static private final int RIGHTKEYCODE = 39;
    static private final int DOWNKEYCODE = 40;
    static private final int LEFTKEYCODE = 37;
    
    static private int score = 0;
    static private int counter = 0;
    static private int speed = 75;
    static private final int VERYFAST = 35;
    static private final int FAST = 50;
    static private final int NORMAL = 100;
    static private final int SLOW = 150;
    static private final int VERYSLOW = 200;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        world.paint(g);
        world.paintSpaceMessage(g, isStarted);
        
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString("SCORE" , 440, 50); 
        g2d.drawString(String.valueOf(score), 490, 100); 
        
        snakeHead.paint(g2d);
        
        paintSnakeBody(g2d);
        paintSnakeTail(g2d);
             
        if(snakeEeatenFood()) {
            food.newPosition();
            fullBody[score].setAvailability(true);
            score++;
        } else {
            food.paint(g2d);
        }
        
        setSnakeBodyPositions();

    }
    
    public static void paintSnakeBody(Graphics2D g2d) {  
        for(SnakeBody fullBody1 : fullBody) {
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
            snakeTail.setHorizontal(fullBody[score-1].getHorizontal());
            snakeTail.setVertical(fullBody[score-1].getVertical());
            snakeTail.setOrientation(fullBody[score-1].getOrientation());
        }
        
        snakeTail.paint(g2d);
    }
    
    public static void setSnakeBodyPositions() {
        counter = score;
        
        while(counter >= 0) {       
            if(counter == 0) {
                fullBody[counter].setHorizontal(snakeHead.getHorizontal());
                fullBody[counter].setVertical(snakeHead.getVertical());
                fullBody[counter].setOrientation(getOrientation());
            } else {
                fullBody[counter].setHorizontal(fullBody[counter-1].getHorizontal());
                fullBody[counter].setVertical(fullBody[counter-1].getVertical());
                fullBody[counter].setOrientation(fullBody[counter-1].getOrientation());
            } 
            fullBody[counter].setPosition(counter);
            counter--;  
        }
    }
    public static int getScore() {
        return score;
    }
    
    public static String getOrientation() {
        if(upState)
            return "up";
        if(rightState)
            return "right";
        if(downState)
            return "down";
        if(leftState)
            return "left";
        return "noOrientation";
    }
    
    public static boolean snakeEeatenFood() {
        return snakeHead.getBounds().intersects(food.getBounds());
    }
    
    public static boolean isHeadCrashingBody() {
        for(int i = score; i > 0; i--) {
            if(snakeHead.getBounds().intersects(fullBody[i].getBounds()) && 1 != 0) {
                return true;
            } 
        }
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

        for(int i = 0; i < fullBody.length; i++) {
            fullBody[i] = new SnakeBody();
        }
        
        food.newPosition();
        changeMoveState(false, false, false, false);

        score = 0;
        isStarted = false;
    }
    
    public static JMenuBar createManuBar(SnakeGame game) {
        JMenuBar menu= new JMenuBar();
        JMenu speedSubMenu = new JMenu("Speed");
        ButtonGroup speedGroup = new ButtonGroup();
        
        JRadioButtonMenuItem veryFastButton = new JRadioButtonMenuItem(
                "Very Fast");
        JRadioButtonMenuItem fastButton = new JRadioButtonMenuItem(
                "Fast");
        JRadioButtonMenuItem normalButton = new JRadioButtonMenuItem(
                "Normal");
        JRadioButtonMenuItem slowButton = new JRadioButtonMenuItem(
                "Slow");
        JRadioButtonMenuItem verySlowButton = new JRadioButtonMenuItem(
                "Very Slow");
        
        veryFastButton.addActionListener(game);
        fastButton.addActionListener(game);
        normalButton.addActionListener(game);
        slowButton.addActionListener(game);
        verySlowButton.addActionListener(game);
        
        speedSubMenu.add(veryFastButton);
        speedSubMenu.add(fastButton);
        speedSubMenu.add(normalButton);
        speedSubMenu.add(slowButton);
        speedSubMenu.add(verySlowButton);
        
        speedGroup.add(veryFastButton);
        speedGroup.add(fastButton);
        speedGroup.add(normalButton);
        speedGroup.add(slowButton);
        speedGroup.add(verySlowButton);
        
        menu.add(speedSubMenu);
        
        return menu;
    }
      
    public static void  main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Snake Game");
        
        SnakeGame game = new SnakeGame();
        
        JMenuBar menu = createManuBar(game);
        
        frame.addKeyListener(game);
        frame.add(game);
        frame.setJMenuBar(menu);
        frame.setSize(602, 713);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        for(int i = 0; i < fullBody.length; i++) {
            fullBody[i] = new SnakeBody();
        }
       
        while(true) {
             game.repaint();
                        
            if(upState) 
                snakeHead.moveUp();
 
            if(rightState) 
                snakeHead.moveRight();
            
            if(downState) 
                snakeHead.moveDown();           
            
            if(leftState) 
                snakeHead.moveLeft();            
                    
            if(isRightCrashing() || isBotCrashing() ||
                    isTopCrashing() || isLeftCrashing() ||
                    isHeadCrashingBody() || isHeadCrashingTail()) {
                JOptionPane.showMessageDialog(game, "Game Over");
                cleanWorld();
            }

            Thread.sleep(speed);
        }
    } 

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(isStarted) {
            if(e.getKeyCode() == UPKEYCODE) {
                if(!upState && !downState) {
                    changeMoveState(true, false, false, false);
                }
            }

            if(e.getKeyCode() == RIGHTKEYCODE) {
                if(!rightState && !leftState) {
                    changeMoveState(false, true, false, false);
                }
            }

            if(e.getKeyCode() == DOWNKEYCODE) {
                if(!downState && !upState) {
                    changeMoveState(false, false, true, false);           
                }
            }

            if(e.getKeyCode() == LEFTKEYCODE) {
                if(!leftState && !rightState) {
                    changeMoveState(false, false, false, true);
                }
            }
        }
        if(e.getKeyCode() == SPACEKEYCODE) {
                if(!isStarted) {
                    changeMoveState(true, false, false, false);
                }
                isStarted = true;       
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().compareTo("Very Fast") == 0)
            SnakeGame.speed = VERYFAST;
        if(e.getActionCommand().compareTo("Fast") == 0)
            SnakeGame.speed = FAST;
        if(e.getActionCommand().compareTo("Normal") == 0)
            SnakeGame.speed = NORMAL;
        if(e.getActionCommand().compareTo("Slow") == 0)
            SnakeGame.speed = SLOW;
        if(e.getActionCommand().compareTo("Very Slow") == 0)
            SnakeGame.speed = VERYSLOW;
    }
}