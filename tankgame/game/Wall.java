package tankgame.game;

import java.awt.*;

public abstract class Wall extends GameObjects{
    public abstract void drawImage(Graphics g);
    public abstract void update();
}
