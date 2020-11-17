package tankgame.game;

import javax.print.DocFlavor;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall{
    int x,y;
    private BufferedImage wallImage;
    Rectangle breakableRec;

    public BreakableWall(int x, int y, BufferedImage wallImage){
        this.x=x;
        this.y=y;
        this.wallImage = wallImage;
        this.breakableRec = new Rectangle(x, y, wallImage.getWidth(), wallImage.getHeight());
    }


    public Rectangle getBreakableRec(){
        return this.breakableRec.getBounds();
    }

    public void setWallImage(BufferedImage wallImage){
        this.wallImage = wallImage;
    }

    public void update(){

    }

    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.wallImage,x,y,null);
        g2.drawRect(x,y,this.wallImage.getWidth(),this.wallImage.getHeight());
    }

}
