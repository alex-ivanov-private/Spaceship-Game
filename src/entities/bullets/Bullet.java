package entities.bullets;

import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private BufferedImage image;
    private int bulletVelocity;


    public Bullet(BufferedImage img,int x, int y, int width, int height, int velocityModifier) {
        super(x + 40, y + 12, 20, 10);
        this.image = img;
        this.bulletVelocity = 2 * velocityModifier;
    }

    @Override
    public void tick() {
        this.x += this.getVelocity() * this.bulletVelocity;
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);



    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }

    @Override
    public boolean intersect(Rectangle enemyBoundingBox) {
        return this.boundingBox.intersects(enemyBoundingBox);
    }
}
