package src;

import javax.imageio.ImageIO;
import java.awt.*;

public class Block2 {
    private int x;
    private int y;
    private Rectangle hitBox;
    private Image img = null;

    Block2(int x, int y) {
        this.x = x;
        this.y = y;
        try{
            img = ImageIO.read(getClass().getResource("/resources/Block2.gif"));
            this.hitBox = new Rectangle(x, y, 40, 20);
        }
        catch(Exception ex) {
        }
    }


    public Rectangle getBounds(){
        return (new Rectangle (x, y,40, 20));
    }


    void drawImage(Graphics g) {
        g.drawImage(img, x, y, 40,20, null);
        Graphics2D g2d =(Graphics2D)g;

//        g.setColor(Color.red);
//        Graphics2D gd =(Graphics2D)g;
//        gd.draw(hitBox);

    }
}
