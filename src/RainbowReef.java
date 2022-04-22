package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class RainbowReef extends JPanel {

    public static final int WORLD_WIDTH = 720;
    public static final int WORLD_HEIGHT = 1000;
    private BufferedImage world;
    private JFrame jf;
    public static Player katch;
    public static Ball ball;

    private Image background = null;
    public static ArrayList<Enemy> EnemyList = new ArrayList<>();
    public static ArrayList<UnbreakableBlock> UnbreakableBlockList = new ArrayList<>();
    public static ArrayList<Block1> Block1List = new ArrayList<>();
    public static ArrayList<Block2> Block2List = new ArrayList<>();


    public static void main(String[] args) {
        Thread x;
        RainbowReef rReef = new RainbowReef();
        rReef.init();
        try {

            while (rReef.katch.getLives() > 0 && EnemyList.size() > 0) {

                rReef.katch.update();
                rReef.ball.update();
                //rReef.checkBallCollision(katch, ball);
                rReef.repaint();


                System.out.println(rReef.katch);
                rReef.checkBECollision(ball);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void init() {
        this.jf = new JFrame("Super Rainbow Reef");
        this.world = new BufferedImage(RainbowReef.WORLD_WIDTH, RainbowReef.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage katchimg = null;
        BufferedImage ballimg = null;


        //This is very inefficient I know ;(
        EnemyList.add(new Enemy(560, 20));
        EnemyList.add(new Enemy(80, 20));
        EnemyList.add(new Enemy(320, 20));

        for(int i =0;i<RainbowReef.WORLD_HEIGHT; i+=20) {
            UnbreakableBlockList.add(new UnbreakableBlock(0, i));
        }
        for(int i =0;i<RainbowReef.WORLD_HEIGHT; i+=20) {
            UnbreakableBlockList.add(new UnbreakableBlock(RainbowReef.WORLD_WIDTH - 40, i));
        }
        for(int i =0;i<RainbowReef.WORLD_WIDTH; i+=40) {
            UnbreakableBlockList.add(new UnbreakableBlock(i, 0));
        }
        for(int i =0;i<RainbowReef.WORLD_HEIGHT - 600; i+=20) {
            UnbreakableBlockList.add(new UnbreakableBlock(RainbowReef.WORLD_WIDTH - 520, i));
        }
        for(int i =0;i<RainbowReef.WORLD_HEIGHT - 600; i+=20) {
            UnbreakableBlockList.add(new UnbreakableBlock(RainbowReef.WORLD_WIDTH - 240, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(RainbowReef.WORLD_WIDTH - 80, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(RainbowReef.WORLD_WIDTH - 80, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(40, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(40, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(280, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(280, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(400, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(400, i));
        }
        for(int i =120;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(320, i));
        }
        for(int i =100;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(320, i));
        }
        for(int i =100;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(360, i));
        }
        for(int i =120;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(360, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(240, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(440, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(160, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(160, i));
        }
        for(int i =40;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block1List.add(new Block1(520, i));
        }
        for(int i =20;i<RainbowReef.WORLD_HEIGHT - 600; i+=40) {
            Block2List.add(new Block2(520, i));
        }

        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            katchimg = ImageIO.read(getClass().getResource("/resources/Katch.png"));
            ballimg = ImageIO.read(getClass().getResource("/resources/Pop.png"));
            background = ImageIO.read(getClass().getResource("/resources/Background1.bmp"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Player Positions
        katch = new Player(RainbowReef.WORLD_WIDTH / 2, RainbowReef.WORLD_HEIGHT - 160, 0, 0, katchimg);

        //Ball Position
        ball = new Ball(katch.getX(), katch.getY() - 200, 0, 0, ballimg);

        //Player Controls
        PlayerControl pc1 = new PlayerControl(katch, KeyEvent.VK_A, KeyEvent.VK_D);


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(pc1);


        this.jf.setSize(RainbowReef.WORLD_WIDTH, RainbowReef.WORLD_HEIGHT);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }



    public void checkBECollision(Ball ball){
        Rectangle r2 = ball.getBounds();
        for (int i = 0; i < EnemyList.size(); i++) {
            Rectangle r1 = EnemyList.get(i).getBounds();
            if (r1.intersects(r2)) {
                EnemyList.remove(i);
                ball.score += 500;
            }
        }
    }



    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.drawImage(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT, null);
        super.paintComponent(g2);

        //Drawing the Player
        this.katch.drawImage(buffer);
        //Drawing the Ball
        this.ball.drawImage(buffer);
        //Drawing the Enemy
        for (int i = 0; i < EnemyList.size(); i++) {
            EnemyList.get(i).drawImage(buffer);
        }
        //Drawing the UnbreakableBlock
        for (int i = 0; i < UnbreakableBlockList.size(); i++) {
            UnbreakableBlockList.get(i).drawImage(buffer);
        }

        //Drawing Block1
        for (int i = 0; i < Block1List.size(); i++) {
            Block1List.get(i).drawImage(buffer);
        }

        //Drawing Block2
        for (int i = 0; i < Block2List.size(); i++) {
            Block2List.get(i).drawImage(buffer);
        }

        //Drawing the World
        g2.drawImage(world, 0, 0, null);

        //Player Lives
        for (int i = 0; i < katch.getLives(); i++) {
            g2.drawImage(katch.getlivesImg(), RainbowReef.WORLD_WIDTH -660 + (40 * i), RainbowReef.WORLD_HEIGHT - 80, 30, 10, null);
        }
    }
}

