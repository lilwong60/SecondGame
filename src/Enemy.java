package src;

import javax.imageio.ImageIO;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private Rectangle hitBox;
    private Image img = null;

    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        try{
            img = ImageIO.read(getClass().getResource("/resources/Bigleg.png"));
            this.hitBox = new Rectangle(x,y,80,80);
        }
        catch(Exception ex){
        }
    }


    public Rectangle getBounds(){
        return (new Rectangle (x, y,80, 80));
    }


    void drawImage(Graphics g) {
        g.drawImage(img, x, y, 80,80, null);
        Graphics2D g2d =(Graphics2D)g;

//        g.setColor(Color.red);
//        Graphics2D gd =(Graphics2D)g;
//        gd.draw(hitBox);

    }
}
