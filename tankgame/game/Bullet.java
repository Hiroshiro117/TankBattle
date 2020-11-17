package tankgame.game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObjects{

    int x,y,vx,vy,angle;
    int R = 7; // speed

    BufferedImage bulletImage;
    Rectangle bulletRectangle;
  //  private int bullet;
    public boolean collision;



     public Bullet(int x,int y, int angle, BufferedImage bulletImage){
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.bulletImage = bulletImage;
        this.collision = false;
        this.bulletRectangle = new Rectangle(x,y,this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

    public void BulletCollision(){
         this.collision = true;
    }


    public Rectangle getBulletRectangle(){
         return bulletRectangle.getBounds();
    }

    int getX(){
         return this.x;
    }

    int getY(){
         return this.y;
    }

    int getVx(){
         return this.vx;
    }

    int getVy(){
         return this.vy;
    }

    void setX(int x){
         this.x = x;
    }

    void setY(int y){
         this.y = y;
    }

    void setVx(int vx){
         this.vx = vx;
    }

    void setVy(int vy){
         this.vy = vy;
    }

//    public void CheckCollision(){
//         this.collision = true;
//    }

    public void moveForwards(){
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.bulletRectangle.setLocation(x,y);
    }

    public void checkBorder(){

        if( x < 30){
            x = 30;
        }
        if(x >= TRE.SCREEN_WIDTH - 88){
            x = TRE.SCREEN_WIDTH + 650;
        }
        if( y < 40){
            y = 40;
        }
        if(y >= TRE.SCREEN_HEIGHT - 80){
            y = TRE.SCREEN_HEIGHT + 970 ;
        }
    }

    public void update(){
        moveForwards();

    }


    public void removeBullet(){

    }


    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x,y);
        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth()/2.0, this.bulletImage.getHeight()/2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.bulletImage, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

}
