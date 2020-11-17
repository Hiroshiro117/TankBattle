package tankgame.game;

import java.awt.*;

public class HealthBar extends GameObjects{

    int x, y, vx, vy;
    Rectangle rec;
    public int health;
    boolean collide = false;

    public HealthBar(int x, int y){
        this.x = x;
        this.y = y;

    }

    public int getHealth() {
        return health;
    }

    public void collision(){
        this.collide = true;
    }

    public void update(){

    }

    public void drawImage(Graphics g){

    }
}
