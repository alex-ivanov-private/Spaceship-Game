package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private String title;

    private JFrame frame;
    private Canvas canvas;

    private int width,height;

    public GameWindow(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        initFrame();
        initCanvas();
        this.getFrame().add(this.getCanvas());
        this.getFrame().pack();
    }

    private void initCanvas() {
        this.setCanvas(new Canvas());
        this.getCanvas().setPreferredSize(new Dimension(this.width, this.height));
        this.getCanvas().setMinimumSize(new Dimension(this.width, this.height));
        this.getCanvas().setMaximumSize(new Dimension(this.width, this.height));
        this.getCanvas().setFocusable(false);
    }

    private void initFrame(){
        this.setFrame(new JFrame(this.title));

        this.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getFrame().setSize(this.width, this.height);
        this.getFrame().setFocusable(true);
        this.getFrame().setVisible(true);
        this.getFrame().setResizable(false);
        this.getFrame().setLocationRelativeTo(null);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
