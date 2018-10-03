package game;

import contracts.Updatable;
import gfx.Assets;
import input.InputHandler;
import state.GameState;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable, Updatable {
    private GameWindow display;
    private Thread thread;

    private boolean isRunning;
    //display fields
    private String title;
    private int width;
    private int height;
    //drawing fields
    private Graphics graphics;
    private BufferStrategy bs;

    public static InputHandler inputHandler;

    private State currentState;



    public GameEngine(String title, int width, int height){
        this.title = title;
        this.setWidth(width);
        this.setHeight(height);


    }

    private void init(){
        this.display = new GameWindow(this.title , this.getWidth(), this.getHeight());
        this.display.getCanvas().createBufferStrategy(2);
        this.bs = this.display.getCanvas().getBufferStrategy();
        this.graphics = this.bs.getDrawGraphics();

        Assets.init();
        this.inputHandler = new InputHandler(this.display.getFrame());

        this.currentState = new GameState(this);
        StateManager.setCurrentState(new GameState(this));



    }



    @Override
    public void tick() {
        if (StateManager.getCurrentState() != null){
            StateManager.getCurrentState().tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics = this.bs.getDrawGraphics();
        graphics.clearRect(0,0, this.getWidth(), this.getHeight());
        //start drawing


        //end drawing
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(graphics);
        }

        this.bs.show();
        graphics.dispose();
    }


    @Override
    public void run() {
        init();
        int fps = 60;
        double ticksPerFrame = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();


        while (isRunning){
            now = System.nanoTime();
            delta += (now-lastTimeTicked) / ticksPerFrame;

            lastTimeTicked = now;
            if (delta >= 1) {
                tick();
                render(this.graphics);
                delta--;
            }
        }
        stop();
    }

    public synchronized void start(){
        if (this.thread != null) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);
            this.thread.start();

    }

    public synchronized void stop(){
        if (this.thread == null) {
            return;
        }
        this.isRunning = false;

        try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
