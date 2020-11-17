package tankgame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Background {

    private BufferedImage backgroundImage;
    int x,y;

    public Background(int x, int y, BufferedImage backgroundImage){
        this.x=x;
        this.y=y;
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void drawImage(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.backgroundImage, x, y, TRE.WORLD_WIDTH,TRE.WORLD_HEIGHT,null);

    }

}
