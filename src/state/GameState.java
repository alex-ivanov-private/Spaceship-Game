package state;

import entities.bullets.Bullet;
import entities.creatures.Enemy;
import entities.creatures.Player;
import game.GameEngine;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State {

    private Player player;
    private ArrayList<Enemy> enemies;


    private  int shotsPerTick;
    private double timePerShoot;
    private long lastTimeShot;
    private double deltaBetweenShots;
    private long score;

    public GameState(GameEngine gameEngine){
        super(gameEngine);
        this.player = new Player(100, 300,64,29);
        this.enemies = new ArrayList<Enemy>();
        this.shotsPerTick = 3;
        this.timePerShoot = 1_000_000_000.0 / this.shotsPerTick;
        this.lastTimeShot = System.nanoTime();
        this.deltaBetweenShots = 0;
        this.score = 0;

    }



    @Override
    public void tick() {
        this.getPlayer().tick();
        this.enemyShoot();

        if (GameEngine.inputHandler.space) {
            if (!this.getPlayer().getHasShot()) {
                this.getPlayer().shoot();
                this.getPlayer().setHasShot(true);
            }
        } else {
            this.getPlayer().setHasShot(false);
        }

        for (Enemy enemy : enemies) {
            enemy.tick();
            if (enemy.getX() <= -50) {
                enemies.remove(enemy);
                return;
            }
            if (enemy.getEnemyBullets().size() > 0) {
                for (Bullet bul : enemy.getEnemyBullets()) {
                    if (bul.getBoundingBox().intersects(this.getPlayer().getBoundingBox())) {
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - enemy.getEnemyDamage());
                        enemy.getEnemyBullets().remove(bul);
                        return;
                    }
                    if (!bul.getBoundingBox().intersects(new Rectangle(this.game.getWidth(), this.game.getHeight()))) {
                        enemy.getEnemyBullets().remove(bul);
                        return;
                    }
                }
            }
        }

        for (Bullet bul : this.getPlayer().getBullets()) {
            for (Enemy enemy : enemies) {

                if (bul.getBoundingBox().intersects(enemy.getBoundingBox())){
                    getPlayer().getBullets().remove(bul);

                    enemy.setHealth(enemy.getHealth() - this.getPlayer().getCreatureDamage());
                    if (enemy.getHealth() <= 0){
                        enemies.remove(enemy);
                        score += 10;
                        return;
                    }
                    return;

                }
            }
        }


        for (Bullet bullet : getPlayer().getBullets()) {
            if (!bullet.intersect(new Rectangle(-50, 0, game.getWidth()+50, game.getHeight()))) {
                this.getPlayer().getBullets().remove(bullet);
                return;
            }
        }


        if (enemies.size() <= 0){
            enemies.addAll(addEnemyOnRandPos());
        }

        for (Enemy enemy : enemies) {
            if (this.getPlayer().getBoundingBox().intersects(enemy.getBoundingBox())){
                this.getPlayer().setHealth(getPlayer().getHealth() - 30);

            }
        }


        if (this.getPlayer().getHealth() <= 0){
            StateManager.setCurrentState(new EndGameState(game, "Game over!"));
        }

        if (score % 500 == 0){
            this.getPlayer().setHealth(100);
        }

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.background,0,0,1782,600,null);
        this.getPlayer().render(graphics);

        for (Enemy enemy : enemies) {
            enemy.render(graphics);
        }

        graphics.setFont(new Font("Arial", Font.BOLD, 18));
        graphics.setColor(Color.white);
        graphics.drawString("Your health is: " + this.getPlayer().getHealth(),10,20);
        graphics.setColor(Color.gray);
        graphics.drawString("SCORE: " + score,10,40);

    }


    private ArrayList<Enemy> addEnemyOnRandPos() {
        int enemyNumber = 10;
        int enemyPosX = 800;
        int enemyPosY = 100;
        int nX = 800;
        int mX = 1500;
        int nY = 100;
        int mY = 550;
        Random rnd = new Random();
        ArrayList<Enemy> list = new ArrayList<>();

        for (int i = 0; i < enemyNumber; i++) {
            do {
                enemyPosX = rnd.nextInt(mX-nX+1)+nX;
                enemyPosY = rnd.nextInt(mY-nY+1)+nY;
            }
            while (!(enemyPosX >= 800 && enemyPosX <= 1500) && !(enemyPosY >= 100 && enemyPosY <= 550));

            Enemy currentEnemy = new Enemy(enemyPosX,enemyPosY,40,30);
            list.add(currentEnemy);
        }

        return list;
    }

    private void enemyShoot(){
        long now = System.nanoTime();
        this.deltaBetweenShots += (now - this.lastTimeShot)/ this.timePerShoot;
        this.lastTimeShot = now;
        int enemyShooting = 0;
        if (this.deltaBetweenShots >= 1 && this.enemies.size() >= 0){
            Random random = new Random();
            enemyShooting = random.nextInt(this.enemies.size());

            this.enemies.get(enemyShooting).shoot();
            this.deltaBetweenShots--;
        }

    }

    public Player getPlayer() {
        return player;
    }
}
