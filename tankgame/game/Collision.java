package tankgame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Collision {
    GameObjects object1;
    GameObjects object2;

    Collision(GameObjects object1, GameObjects object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    void TankCollision() {
        if (object1 instanceof Tank && object2 instanceof Tank) {
            if (((Tank) object1).getHitBox().intersects(((Tank) object2).getHitBox())) {
                if (((Tank) object1).isUpPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                if (((Tank) object1).isDownPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                if (((Tank) object2).isUpPressed()) {
                    object2.setX(object2.getX() - object2.getVx());
                    object2.setY(object2.getY() - object2.getVy());
                }
                if (((Tank) object2).isDownPressed()) {
                    object2.setX(object2.getX() - object2.getVx());
                    object2.setY(object2.getY() - object2.getVy());
                }

                System.out.println("2 tanks collisions");
            }
        }
    }

    void BreakableWallCollision() {
        if (object1 instanceof Tank && object2 instanceof BreakableWall) {
            if (((Tank) object1).getHitBox().intersects(((BreakableWall) object2).getBreakableRec())) {
                if (((Tank) object1).isUpPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                if (((Tank) object1).isDownPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                System.out.println("tank and breakable are collisions");
            }
        }
    }

    void UnBreakableWallCollision() {
        if (object1 instanceof Tank && object2 instanceof UnbreakableWall) {
            if (((Tank) object1).getHitBox().intersects(((UnbreakableWall) object2).getRectangle())) {
                if (((Tank) object1).isUpPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                if (((Tank) object1).isDownPressed()) {
                    object1.setX(object1.getX() - object1.getVx());
                    object1.setY(object1.getY() - object1.getVy());
                }
                System.out.println("tank and unbreakablewall are collision");
            }
        }
    }

    void BulletCollision(){
        if(object1 instanceof Bullet  &&  object2 instanceof Tank){
            if(((Tank) object2).isShootPressed()){
                if(((Bullet) object1).getBulletRectangle().intersects(((Tank) object2).getHitBox())){
                    ((Bullet) object1).BulletCollision();
                    ((Tank) object2).setCollision(false);
                    System.out.println("hit");
                }
            }
        }
    }


//    void BulletCollision(){
//        if(object1 instanceof Bullet  &&  object2 instanceof Bullet){
//            if(((Bullet) object1).getBulletRectangle().intersects(((Bullet) object2).getBulletRectangle())){
//                ((Bullet) object1).BulletCollision();
//                ((Bullet) object2).BulletCollision();
//                System.out.println("hit");
//
//            }
//        }
//    }

//    void BulletCollision(){
//        if(object1 instanceof Bullet  &&  object2 instanceof Tank) {
//                if (((Bullet) object1).getBulletRectangle().intersects(((Tank) object2).getHitBox())) {
//                    ((Bullet) object1).BulletCollision();
//                    object2.collision();
//
//                    System.out.println("Bullet and Tank are collision ");
//                }
//
//        }
//    }


}


