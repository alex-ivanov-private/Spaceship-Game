package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static  SpriteSheet player;
    public static SpriteSheet enemy;
    public static BufferedImage background;
    public static BufferedImage playerBullet;
    public static BufferedImage enemyBullet;

    public static void init(){
        player = new SpriteSheet("/images/Ship/Spritesheet_64x29.png");
        enemy = new SpriteSheet("/images/Enemy/eSpritesheet_40x30.png");
        background = ImageLoader.loadImage("/images/Backgrounds/farback.gif");
        playerBullet = ImageLoader.loadImage("/images/Bullets/bullet2.png");
        enemyBullet = ImageLoader.loadImage("/images/Bullets/enemy_bullet.png");

    }

}
