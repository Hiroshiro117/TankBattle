package tankgame.menus;

import tankgame.GameConstants;
import tankgame.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class EndGamePanel extends JPanel {

    private BufferedImage menuBackground;
    private JButton start;
    private JButton exit;
    private Launcher lf;

    public EndGamePanel(Launcher lf) {
        this.lf = lf;
        try {
            menuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("Title.bmp"));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        start = new JButton("Restart Game");
        start.setFont(new Font("Courier New", Font.BOLD ,24));
        start.setBounds(150,300,175,50);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("game");
        }));


        exit = new JButton("Exit");
        exit.setFont(new Font("Courier New", Font.BOLD ,24));
        exit.setBounds(150,400,175,50);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));


        this.add(start);
        this.add(exit);

    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground,0,0, 600,550,null);

//        super.paintComponent(g);
//        g2.drawImage(menuBackground,0,0,600,550,this);
    }

}
