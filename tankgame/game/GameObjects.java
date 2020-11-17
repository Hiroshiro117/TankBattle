package tankgame.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObjects {
    int x,y,vx,vy,angle;

    Rectangle Rec;
    BufferedImage image;
    boolean collision = true;

//    GameObjects(){
//        this.Rec = new Rectangle(x,y, this.image.getWidth(), this.image.getHeight());
//    }

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
    int getAngle(){
        return this.angle;
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
    void setAngle(int angle){
        this.angle = angle;
    }

//    public Rectangle getRec(){
//        return new Rectangle(x,y, image.getWidth(),image.getHeight());
//    }

    public void collision(){
        collision = false;
    }

    public abstract void drawImage(Graphics g);

    public abstract void update();


  //  public abstract void CheckCollision();
}
