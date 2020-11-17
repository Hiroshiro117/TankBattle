package tankgame.game;

import tankgame.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author anthony-pc
 */
public class Tank extends GameObjects {


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    Rectangle hitBox;
    private ArrayList<Bullet> ammo;
    private final int R = 2;
    private final float ROTATIONSPEED = 3.0f;

    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    public boolean collision;
    private int tankLive;
    private int health;

    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
        this.ammo = new ArrayList<>();
        this.health = 100;
        this.tankLive = 5;

    }

    public int getTankLive(){
        return tankLive;
    }

    public int getHealth(){
        return health;
    }

    public Rectangle getHitBox(){
       // update();
        return hitBox.getBounds();
    }

    int getX(){
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getVx(){
        return this.vx;
    }

    int getVy(){
        return this.vy;
    }

    void setX(int x){ this.x = x; }

    void setY(int y) { this. y = y;}

    void setVx(int vx){
        this.vx = vx;
    }

    void setVy(int vy){
        this.vy = vy;
    }

    public boolean isUpPressed(){
        return UpPressed;
    }

    public boolean isDownPressed(){
        return DownPressed;
    }

    public boolean isRightPressed(){
        return RightPressed;
    }

    public boolean isLeftPressed(){
        return RightPressed;
    }

    public boolean isShootPressed(){
        return ShootPressed;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed(){
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed(){
        this.ShootPressed = false;
    }
    void setCollision(boolean collision){
        this.collision = collision;
    }

    public void collision(){
        collision = true;
    }

    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if(this.ShootPressed  &&  TRE.tickCount % 20 == 0){
            //System.out.println("shoot bullet");
            Bullet b = new Bullet(x, y, angle, TRE.bulletImage);
            this.ammo.add(b);
        }

        this.ammo.forEach(bullet -> {
            bullet.update();

        });
//        for(int i = 0  ;  i < this.ammo.size()  ;  i++){
//            this.ammo.get(i).update();
//        };
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }




    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if(x >= TRE.WORLD_WIDTH - 88){
            x = TRE.WORLD_WIDTH - 88;
        }

        if (y < 40) {
            y = 40;
        }
        if(y >= TRE.WORLD_HEIGHT - 80){
            y = TRE.WORLD_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        this.ammo.forEach(bullet -> bullet.drawImage(g));
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x, y, this.img.getWidth(), this.img.getHeight());
    }



}
