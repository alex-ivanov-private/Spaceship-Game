package state;


import game.GameEngine;
import game.GameWindow;
import gfx.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameState extends State {
    int screenWidth = 800;
    int screenHeight = 600;

    int buttonWidth = 140;
    int buttonHeight = 60;
    private String message;
    //private Button button = new Button("Try again!");
    JButton Menu, Quit;
    JFrame frame = new JFrame();


    public EndGameState(GameEngine gameEngine, String message){
        super(gameEngine);

        this.message = message;
        addButtons();
        addActions();

        frame.getContentPane().setLayout(null);


        Menu.setBounds((screenWidth - buttonWidth) / 2, 200, buttonWidth, buttonHeight);
        Quit.setBounds((screenWidth - buttonWidth) / 2, 290, buttonWidth, buttonHeight);

        frame.getContentPane().add(Menu).setFont( new Font("Arial", Font.ITALIC, 30));
        frame.getContentPane().add(Quit).setFont( new Font("Arial", Font.ITALIC, 30));

        ImageIcon background = new ImageIcon(ImageIcon.class.getResource("/images/Backgrounds/farback.gif"));

        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(background);
        lblBackground.setBounds(0, 0, 800, 600);

        frame.getContentPane().add(lblBackground);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(screenWidth, screenHeight);
        frame.setTitle("BattleShip");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(287, 75, 800, 600);


    }

    private void addButtons(){

        Menu = new JButton("Menu");
        Quit = new JButton("Quit");
    }

    private void addActions(){
        Menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                new MenuState();
            }
        }); //Play button

        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0); //Shutdown the program
            }
        });
    }

    @Override
    public void tick() {
        game.stop();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawImage(Assets.background, 0, 0, null);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        graphics.drawString(this.message, 300, 250);
        //button.setBounds(350, 350, 140, 60);
    }


}
