package view;

import controller.GameEngine;
import model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import controller.DataStructuresReachingClass;

public class YogiBearGUI implements ActionListener {
    private JFrame frame;
    private GameEngine gameArea;
    JMenuBar mb;
    JMenu menu;
    JMenuItem newGame, top10;
    JTextField textField;
    DataStructuresReachingClass dataStructuresReachingClass;

    public YogiBearGUI(){
        frame = new JFrame("Yogi module.Bear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");

        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);

        top10 = new JMenuItem("Top 10");
        top10.addActionListener(this);
        menu.add(newGame);
        menu.add(top10);
        mb.add(menu);
        frame.setJMenuBar(mb);

        gameArea = new GameEngine(frame);
        frame.getContentPane().add(gameArea);
        JMenu menu;
        try {
            dataStructuresReachingClass = new DataStructuresReachingClass();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        frame.setPreferredSize(new Dimension(800,600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(newGame)) {
            gameArea.newGame();
        }
        if(e.getSource().equals(top10)) {
            ArrayList<HighScore> hs = new ArrayList<>();
            try {
                hs = dataStructuresReachingClass.getHighScores();
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            String toDisplay = "";
            for(HighScore highscore : hs) {
                toDisplay = toDisplay + highscore.getName() + " " + highscore.getTime() + " " + highscore.getPoints() + "\n";
            }
            JOptionPane.showMessageDialog(
                    frame,
                    toDisplay,
                    "Top 10",
                    JOptionPane.PLAIN_MESSAGE,
                    null
            );

        }
    }
}
