package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static src.RainbowReef.ball;
import static src.RainbowReef.katch;

public class Player {


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int origX;
    private int origY;
    private int angle;

//    private Rectangle LeftHitBox;
//    private Rectangle RightHitBox;
    private Rectangle HitBox;
    private int lives = 3;

    private final int R = 5;

    private BufferedImage img;
    private BufferedImage livesImg;
    private boolean RightPressed;
    private boolean LeftPressed;

    Player(int x, int y, int vx, int vy, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.origX = x;
        this.origY = y;
        this.img = img;
        this.HitBox = new Rectangle(img.getWidth(), img.getHeight());
//        this.LeftHitBox = new Rectangle(img.getWidth() / 2,img.getHeight());
//        this.RightHitBox = new Rectangle(img.getWidth() / 2,img.getHeight());

        try{
            livesImg = ImageIO.read(getClass().getResource("/resources/Katch_small.png"));
        }
        catch(IOException ex){
        }
    }





    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }




    public void update() {
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
        HitBox.setLocation(this.x,this.y);
        if(ball.getY() >= 950 ) {
            katch.lives--;
        }
    }


    private void moveLeft() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveRight() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    public BufferedImage getlivesImg(){
        return livesImg;
    }

    public int getLives() {
        return lives;
    }


    public void setLives(int lives){
        this.lives = lives;
    }

    private void checkBorder() {
        if (x < 40) {
            x = 40;
        }
        if (x >= RainbowReef.WORLD_WIDTH - 120) {
            x = RainbowReef.WORLD_WIDTH - 120;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= RainbowReef.WORLD_HEIGHT - 88) {
            y = RainbowReef.WORLD_HEIGHT - 88;
        }
    }

    public Rectangle getLeftBounds(){
        return (new Rectangle (x, y, 80 , 30 ));
    }
    public Rectangle getRightBounds(){
        return (new Rectangle (x + 40, y, 80 / 2, 30 ));
    }


    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }


    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y, null);

//        g.setColor(Color.red);
//        Graphics2D gd =(Graphics2D)g;
//        gd.draw(HitBox);
//        g.setColor(Color.blue);
//        gd.draw(RightHitBox);

    }


}
