package tankgame.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {

    int x,y;
    BufferedImage wallImage;
    Rectangle rectangle;

    public UnbreakableWall(int x, int y, BufferedImage wallImage){
        this.x=x;
        this.y=y;
        this.wallImage=wallImage;
        this.rectangle = new Rectangle(x, y, wallImage.getWidth(),wallImage.getHeight());
    }

    public void update(){

    }

    public Rectangle getRectangle(){
        return this.rectangle.getBounds();
    }

    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.wallImage, x, y, null);
    }
}
