package level;

import java.awt.Image;

import javax.swing.ImageIcon;

import global.Settings;

public class Icon {
    public static final ImageIcon WALL = new ImageIcon("assets/blocks/redSquare.png");
    public static final ImageIcon COIN = new ImageIcon("assets/blocks/coin.png");
    public static final ImageIcon FLAG = new ImageIcon("assets/blocks/flag.png");
    public static final ImageIcon FLIP = new ImageIcon(new ImageIcon("assets/blocks/cloud.png")
        .getImage()
        .getScaledInstance(Settings.BLOCK_SIZE, Settings.BLOCK_SIZE, Image.SCALE_SMOOTH)
    );
    public static ImageIcon[] characterIcon = new ImageIcon[] {
        new ImageIcon("assets/characters/mario.gif"),
        new ImageIcon("assets/characters/mario3.gif"),
        new ImageIcon("assets/characters/mario1.gif"),
        new ImageIcon("assets/characters/mario2.gif"),
    };
}
