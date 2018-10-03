package state;


import game.GameEngine;
import gfx.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Alex on 20-Apr-16.
 */
public class MenuState extends JFrame {
    private int screenWidth = 800;
    private int screenHeigth = 600;

    private int buttonWidth = 140;
    private int buttonHeigth = 60;

    JButton Play, Quit, HighScore;
    // It can be useful
    JCheckBox hardDifficulty;

    public MenuState(){

        addButtons();
        addActions();


        getContentPane().setLayout(null);

        Play.setBounds((screenWidth - buttonWidth) / 2, 200, buttonWidth, buttonHeigth); //Position the PLAY button
        Quit.setBounds((screenWidth - buttonWidth) / 2, 380, buttonWidth, buttonHeigth); //Position the QUIT button
        HighScore.setBounds((screenWidth - buttonWidth) / 2, 290, buttonWidth, buttonHeigth);
        hardDifficulty.setBounds(50, 125, buttonWidth*2, buttonHeigth); //Position the hardDifficulty check box

        //Adding buttons
        getContentPane().add(Play).setFont( new Font("Arial", Font.ITALIC, 30));
        getContentPane().add(Quit).setFont( new Font("Arial", Font.ITALIC, 30));
        getContentPane().add(HighScore).setFont( new Font("Arial", Font.ITALIC, 30));
        // If we make hard difficulty
        //getContentPane().add(hardDifficulty); //Add the button to the JFrame

        ImageIcon background = new ImageIcon(ImageIcon.class.getResource("/images/Backgrounds/farback.gif"));

        JLabel lblBackground = new JLabel();
        lblBackground.setIcon(background);
        lblBackground.setBounds(0, 0, 800, 600);

        getContentPane().add(lblBackground);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(screenWidth, screenHeigth);
        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(300, 85, 800, 600);
    }

    private void addButtons(){

        Play = new JButton("Play");
        HighScore = new JButton("HighScore");
        Quit = new JButton("Quit");

        // If we make hard difficulty
        hardDifficulty = new JCheckBox("Hardcore mode?");
    }

    private void addActions(){
        Play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();

                GameEngine game = new GameEngine("BattleShip",800,600);

                game.start();
            }
        }); //Play button

        HighScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0); //Shutdown the program
            }
        });
    }

}
