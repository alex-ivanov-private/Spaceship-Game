package entities.creatures;

import contracts.Shooting;
import entities.bullets.Bullet;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Creature implements Shooting {

    private int velocityModifier;
    private int enemyDamage;
    private ArrayList<Bullet> enemyBullets;


    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setVelocityModifier(1);
        this.setEnemyBullets(new ArrayList<>());
        this.setEnemyDamage(5);
    }

    public int getX(){
        return this.x;
    }

    @Override
    public void tick() {
        this.x -= this.getVelocity();
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        this.enemyBullets.forEach(entities.bullets.Bullet::tick);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.enemy.crop(0,0,40,30), this.x, this.y, null);
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.render(graphics);
        }
    }

    @Override
    public boolean intersect(Rectangle enemyBoundingBox) {
        return this.boundingBox.intersects(enemyBoundingBox);
    }

    public int getVelocityModifier() {
        return velocityModifier;
    }

    public void setVelocityModifier(int velocityModifier) {
        this.velocityModifier = velocityModifier;
    }

    @Override
    public void shoot() {
        this.getEnemyBullets().add(new Bullet(Assets.enemyBullet, this.x - this.width, this.y, this.width,this.height, -1 ));
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public int getEnemyDamage() {
        return this.enemyDamage;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }
}
