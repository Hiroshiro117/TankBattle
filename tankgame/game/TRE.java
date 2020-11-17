package tankgame.game;

import tankgame.GameConstants;
import tankgame.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusStyle;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Objects;


import static javax.imageio.ImageIO.read;

/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel implements Runnable {

    public static final int WORLD_WIDTH = 2010;
    public static final int WORLD_HEIGHT = 2010;
    public static final int SCREEN_WIDTH = 1300; // 43 colunms
    public static final int SCREEN_HEIGHT = 960; // 32 row
    private static Tank t1;
    private static Tank t2;
   // private static Bullet bullet;
    ArrayList<Bullet> bullet = new ArrayList<>();
    ArrayList<GameObjects> gameObjects;
    private BufferedImage world;
    public static BufferedImage bulletImage;
    private Launcher lf;
    private long tick = 0;
    private Background back;
    static long tickCount = 0;

    public TRE(Launcher lf){
        this.lf = lf;
    }

    @Override
    public void run(){
        try {
            this.resetGame();
            while (true) {
                this.tick++;
                this.t1.update(); // update tank
                this.t2.update();

                Collision tanks = new Collision(t1,t2);
                tanks.TankCollision();

                for(int i = 0  ;  i < bullet.size()  ;  i++){
                    Collision bulletCollision = new Collision(bullet.get(i),t1);
                    bulletCollision.BulletCollision();
                }

                for(int i = 1  ;  i < gameObjects.size()  ;  i++){
                    Collision breakableWallCollision = new Collision(t1,gameObjects.get(i));
                    breakableWallCollision.BreakableWallCollision();
                }

                for(int i = 1  ;  i < gameObjects.size()  ;  i++){
                    Collision breakableWallCollision = new Collision(t2,gameObjects.get(i));
                    breakableWallCollision.BreakableWallCollision();
                }

                for(int i = 0  ;  i < gameObjects.size()  ;  i++){
                    Collision unBreakableWallCollision = new Collision(t1, gameObjects.get(i));
                    unBreakableWallCollision.UnBreakableWallCollision();
                }

                for(int i = 0  ;  i < gameObjects.size()  ;  i++){
                    Collision unBreakableWallCollision = new Collision(t2,gameObjects.get(i));
                    unBreakableWallCollision.UnBreakableWallCollision();
                }


                this.repaint();   // redraw game
                tickCount++;
                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if(this.tick > 100000){
                    this.lf.setFrame("end");
                    return;
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame(){
        this.tick = 0;
        this.t1.setX(300);
        this.t1.setY(300);

        this.tick = 0;
        this.t2.setX(1200);
        this.t2.setY(500);
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
//        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
//                GameConstants.GAME_SCREEN_HEIGHT,
//                BufferedImage.TYPE_INT_RGB);

        this.world = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        BufferedImage t1img = null;
        BufferedImage t2img = null;
        BufferedImage breakableWall = null;
        BufferedImage unBreakableWall = null;
        BufferedImage backGroundImage = null;
        gameObjects = new ArrayList<>();

        this.setLayout(null);

        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            t1img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Tank1.gif")));
            t2img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Tank2.gif")));

            breakableWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("BreakableWall.gif")));
            unBreakableWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("unBreakableWall.gif")));
            backGroundImage = read(Objects.requireNonNull((TRE.class.getClassLoader().getResource("Background.bmp"))));
            bulletImage = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Rocket.gif")));

            back = new Background(0,0, backGroundImage);
            back.setBackgroundImage(backGroundImage);

            InputStreamReader ISR = new InputStreamReader(TRE.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(ISR);

            String row = mapReader.readLine();
            String[] mapInfo = row.split("\t");
            int numberOfColumns = Integer.parseInt(mapInfo[0]);
            int numberOfRows = Integer.parseInt(mapInfo[1]);

            for(int currentRow = 0  ;  currentRow < numberOfRows  ;  currentRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int currentColumn = 0  ;  currentColumn < numberOfColumns  ;  currentColumn++){
                    switch (mapInfo[currentColumn]){
                        case "2":
                            BreakableWall BW = new BreakableWall(currentColumn*30,currentRow*30, breakableWall);
                            this.gameObjects.add(BW);

                            break;
                        case "3":
                        case "9":
                            UnbreakableWall UBW = new UnbreakableWall(currentColumn*30, currentRow*30, unBreakableWall);
                            this.gameObjects.add(UBW);

                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


        t1 = new Tank(300, 300, 0, 0, 0, t1img);
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc1);
        this.gameObjects.add(t1);

        t2 = new Tank(1200,500, 0, 0, 180, t2img);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_A, KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc2);
        this.gameObjects.add(t2);

        Bullet bullets = new Bullet(0, 0, 0, bulletImage);

   //     for(int i = 0  ;  i < bullet.size()  ;  i++) {
         //   Bullet bullets = new Bullet(0, 0, 0, bulletImage);
            bullet.add(bullets);
       // }

  //      Bullet bullets = new Bullet(0, 0, 0, bulletImage);
        //bullet.add(bullets);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        Graphics2D buffer = world.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, TRE.WORLD_WIDTH, TRE.WORLD_HEIGHT);

        this.back.drawImage(buffer);

        this.gameObjects.forEach(wall -> {
            wall.drawImage(buffer);
        });

        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);

        int t1GetX = t1.getX();
        int t1GetY = t1.getY();
        int t2GetX = t2.getX();
        int t2GetY = t2.getY();


        if (t1GetX < SCREEN_WIDTH / 4) {
            t1GetX= SCREEN_WIDTH / 4;
        }
        if (t2GetX < SCREEN_WIDTH / 4) {
            t2GetX = SCREEN_WIDTH / 4;
        }
        if (t1GetY < SCREEN_HEIGHT / 2) {
            t1GetY = SCREEN_HEIGHT / 2;
        }
        if (t2GetY < SCREEN_HEIGHT / 2) {
            t2GetY = SCREEN_HEIGHT / 2;
        }
        if(t1GetX > WORLD_WIDTH - SCREEN_WIDTH /4){
            t1GetX = WORLD_WIDTH - SCREEN_WIDTH / 4;
        }

        if(t2GetX > WORLD_WIDTH - SCREEN_WIDTH /4 ){
            t2GetX = WORLD_WIDTH - SCREEN_WIDTH /4;
        }

        if(t1GetY > WORLD_HEIGHT - SCREEN_HEIGHT /2){
            t1GetY = WORLD_HEIGHT - SCREEN_HEIGHT /2;
        }

        if(t2GetX > WORLD_HEIGHT - SCREEN_HEIGHT /2 ){
            t2GetY = WORLD_HEIGHT - SCREEN_HEIGHT /2 ;
        }


        BufferedImage leftHalf = world.getSubimage(t1GetX - SCREEN_WIDTH/4 , t1GetY - SCREEN_HEIGHT/2 , SCREEN_WIDTH/2, SCREEN_HEIGHT);
        BufferedImage rightHalf = world.getSubimage(t2GetX - SCREEN_WIDTH/4 , t2GetY - SCREEN_HEIGHT/2, SCREEN_WIDTH/2, SCREEN_HEIGHT );
        g2.drawImage(leftHalf,0,0,null);
        g2.drawImage(rightHalf, SCREEN_WIDTH/2 + 4, 0, null);
        BufferedImage miniMap = world.getSubimage(0,0, WORLD_WIDTH,WORLD_HEIGHT);

        g2.scale(0.1, 0.1);
        g2.drawImage(miniMap, 5500, 7550, null);

        Font newFont = new Font("San", Font.BOLD , 200);
        g2.setFont(newFont);

        g2.setColor(Color.WHITE);
        g2.drawString("Player1 lives: " + this.t1.getTankLive() , 200,300);
        g2.drawString("Player2 Lives: " + this.t2.getTankLive() , (SCREEN_WIDTH*5) + 200 , 400);
        g2.drawString("Health Remaining: " + this.t1.getHealth() , 200, 800);
        g2.drawString("Health Remaining: " + this.t2.getHealth() , (SCREEN_WIDTH*5) + 200, 875);

        g2.setColor(Color.GREEN);
        g2.fillRect(300, 400, 3000 , 200);
        g2.fillRect((SCREEN_WIDTH*5) + 300, 500, 3000, 200);
    }

}
