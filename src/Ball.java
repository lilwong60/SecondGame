package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static src.RainbowReef.*;

public class Ball {
    private int x;
    private int y;
    private int ballXdir = -1;
    private double ballYdir = -3;
    public int score = 0;

    private int origX;
    private int origY;
    private int vx;
    private int vy;
    private int angle;



    private final int R = 10;

    private Rectangle hitBox;
    private BufferedImage img;

    Ball(int x, int y, int vx, int vy, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.origX = x;
        this.origY = y;
        this.img = img;
        this.hitBox = new Rectangle(img.getWidth(),img.getHeight());
        try{
            this.hitBox = new Rectangle(x,y,35,35);
        }
        catch(Exception ex){
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getBallYdir() {
        return ballYdir;
    }

    public int getBallXdir() {
        return ballXdir;
    }

    public int getScore() {
        return score;
    }


    public Rectangle getBounds(){
        return (new Rectangle (x, y, 35, 35 ));
    }


    public void update() {
        this.x += getBallXdir();
        this.y += getBallYdir();
        //Player
        Rectangle r1 = katch.getLeftBounds();
        //Ball
        Rectangle r2 = getBounds();

        //Player and Ball Collision
        if (r1.intersects(r2)) {
            ballYdir = -ballYdir;
        }
        //UnbreakableBlock
        for (int i = 0; i < UnbreakableBlockList.size(); i++) {
            Rectangle r3 = UnbreakableBlockList.get(i).getBounds();
            if (r2.intersects(r3)) {
                if (this.x  <= r3.x + 40|| this.y  >= r3.x ) {
                    ballXdir = -ballXdir;
                } else {
                    ballYdir = -ballYdir;
                }
            }
        }
        //Block1
        for (int i = 0; i < Block1List.size(); i++) {
            Rectangle r3 = Block1List.get(i).getBounds();
            if (r2.intersects(r3)) {
                if (this.x  <= r3.x  || this.y  >= r3.x ) {
                    ballXdir = -ballXdir;
                } else {
                    ballYdir = -ballYdir;
                }
                Block1List.remove(i);
                score += 100;
            }
        }
        //Block2
        for (int i = 0; i < Block2List.size(); i++) {
            Rectangle r3 = Block2List.get(i).getBounds();
            if (r2.intersects(r3)) {
                if (this.x  <= r3.x || this.y  >= r3.x ) {
                    ballXdir = -ballXdir;
                } else {
                    ballYdir = -ballYdir;
                }
                Block2List.remove(i);
                score += 100;
            }
        }

            //Left Wall
            if (this.x < 41) {
                ballXdir = -ballXdir;
            }
            //Top Wall
            if (this.y < 20) {
                ballYdir = -ballYdir;
            }
            //Right Walld
            if (this.x > 639) {
                ballXdir = -ballXdir;
            }

            hitBox.setLocation(this.x, this.y);

            if (this.y >= 950) {
                x = katch.getX();
                y = katch.getY() - 200;
                score -= 100;
            }
        }


    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y, null);

        g.setColor(Color.red);
//        Graphics2D gd =(Graphics2D)g;
//        gd.draw(hitBox);
        g.setFont(new Font("Monaco", Font.PLAIN, 20));
        g.drawString("Score: " + score,540,920);

    }



}
