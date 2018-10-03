package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage playerImg;

    public SpriteSheet(String path){
        this.playerImg = ImageLoader.loadImage(path);
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return this.playerImg.getSubimage( x , y, width, height);
    }

}
