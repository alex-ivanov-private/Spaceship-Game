package entities.creatures;

import contracts.Shooting;
import entities.bullets.Bullet;
import game.GameEngine;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature implements Shooting {
    private final  int DEFAULT_DAMAGE = 50;
        private String name;
        double isMoving;
        boolean hasShot;
        private ArrayList<Bullet> bullets;


    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        this.setCreatureDamage(DEFAULT_DAMAGE);
        this.isMoving = 0;
        this.setBullets(new ArrayList<Bullet>());
        this.hasShot = false;
    }

    @Override
    public void tick() {
        bullets.forEach(entities.bullets.Bullet::tick);

        if (GameEngine.inputHandler.up){
            if (this.y > 0) {
                this.y -= this.getVelocity();
            }
        }else if (GameEngine.inputHandler.down){
            if (this.y < 570) {
                this.y += this.getVelocity();
            }
        }


        if (GameEngine.inputHandler.left){
            if (this.x > 0) {
                this.x -= this.getVelocity();
            }
        }else if (GameEngine.inputHandler.right){
            if (this.x < 735) {
                this.x += this.getVelocity();
            }
        }
        if (GameEngine.inputHandler.space && !hasShot){
            shoot();
            hasShot = true;
        } else if (!GameEngine.inputHandler.space){
            hasShot = false;
        }

        this.getBoundingBox().setBounds(this.x+25, this.y+5, this.width-25, this.height-10);

        if (isMoving > 3){
            isMoving = 0;
        }
        isMoving += 0.3;

        for (Bullet bullet : getBullets()) {
            bullet.tick();
        }



    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player.crop(0,0+(int)isMoving* 29,64,29),this.x,this.y,null);
        //graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(),
              //  (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());

        for (Bullet bullet : getBullets()) {
            bullet.render(graphics);
        }

    }


    public boolean intersect(Rectangle enemyBoundingBox) {
        return this.getBoundingBox().contains(enemyBoundingBox);
    }

    @Override
    public void shoot() {
        this.getBullets().add(new Bullet(Assets.playerBullet, this.x + this.width/4, this.y, this.width, this.height, 1));
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean getHasShot() {
        return this.hasShot;
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }
}
