package controller;

import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.*;


import javax.swing.*;

public class GameEngine extends JPanel {

        private final int FPS = 240;
        private long started;
        private boolean paused = false;
        private Image background;
        private int levelNum = 0;
        private Timer newFrameTimer;
        private Level level;
        private Bear bear;
        private int lives;
        private Guard guard;
        private Heart heart;
        private Heart heart2;
        private Heart heart3;
        private ArrayList<Heart> hearts;
        private ArrayList<HighScore> highScores;
        private DataStructuresReachingClass dataStructuresReachingClass;
        private JTextField textfield;
        private JLabel timeLabel;
        private JMenuBar menuBar;
        private JFrame window;
        Random rand = new Random();
        int rand_int1 = rand.nextInt(2);
        int rand_int2 = rand.nextInt(2);

        public GameEngine(JFrame window) {
                super();
                try {
                        this.dataStructuresReachingClass = new DataStructuresReachingClass();
                        this.highScores = dataStructuresReachingClass.getHighScores();
                }
                catch (Exception ex) {
                        System.out.println(ex.getMessage());
                }
                this.window = window;
                this.menuBar = window.getJMenuBar();
                this.timeLabel = new JLabel();
                Time time = new Time(0);
                time.setHours(0);
                timeLabel.setText(time.toString());
                menuBar.add(timeLabel);
                lives = 3;
                background = new ImageIcon("src/resource/park.jpg").getImage();
                Image bearimage = new ImageIcon("src/resource/YogiBear.png").getImage();
                bear = new Bear(5,5,60,60,bearimage);
                Image guardimage= new ImageIcon("src/resource/guard.png").getImage();
                Image heartimage = new ImageIcon("src/resource/heartfinal.png").getImage();
                heart = new Heart(720,10,20,20,heartimage);
                heart2 = new Heart(740,10,20,20,heartimage);
                heart3 = new Heart(760,10,20,20,heartimage);
                hearts = new ArrayList<>();
                hearts.add(heart);
                hearts.add(heart2);
                hearts.add(heart3);
                guard = new Guard(300,300,60,60,guardimage);
                this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "move right");
                this.getActionMap().put("move right", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int x = bear.getX();
                                int y = bear.getY();
                                bear.moveRight();
                                if(level.collidedWithObject(bear)) {
                                        bear.setX(x);
                                        bear.setY(y);
                                }
                                window.repaint();
                        }
                });
                this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "move down");
                this.getActionMap().put("move down", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int x = bear.getX();
                                int y = bear.getY();
                                bear.moveDown();
                                if(level.collidedWithObject(bear)) {
                                        bear.setX(x);
                                        bear.setY(y);
                                }
                                window.repaint();
                        }
                });
                this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "move left");
                this.getActionMap().put("move left", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int x = bear.getX();
                                int y = bear.getY();
                                bear.moveLeft();
                                if(level.collidedWithObject(bear)) {
                                        bear.setX(x);
                                        bear.setY(y);
                                }
                                window.repaint();
                        }
                });
                this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "move up");
                this.getActionMap().put("move up", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int x = bear.getX();
                                int y = bear.getY();
                                bear.moveUp();
                                if(level.collidedWithObject(bear)) {
                                        bear.setX(x);
                                        bear.setY(y);
                                }
                                window.repaint();
                        }
                });
                restart();
                newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
                newFrameTimer.start();
                this.started = System.currentTimeMillis();
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(background, 0, 0, 800, 600, null);
                bear.draw(grphcs);
                for(Basket basket: level.getBaskets()){
                        basket.draw(grphcs);
                }
                for(Guard guard : level.getGuards()){
                        guard.draw(grphcs);
                }
                for(Tree tree : level.getTrees()){
                        tree.draw(grphcs);
                }
                for(Rock rock : level.getRocks()){
                        rock.draw(grphcs);
                }
                for(Heart heart : hearts){
                        heart.draw(grphcs);
                }

        }
        public void restart() {
                try {
                        if(levelNum <= 9) {
                                level = new Level("data/level0" + levelNum + ".txt");
                        }
                        else {
                                newFrameTimer.stop();
                                String name = (String) JOptionPane.showInputDialog(
                                        window,
                                        "You finished all levels! Enter your name: ",
                                        "Congratulations!",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        null,
                                        "Your Name"
                                );
                                if (name != null) {
                                        try {
                                                long time = System.currentTimeMillis();
                                                System.out.println(time - started);
                                                Time dbTime = new Time(time - started);
                                                dbTime.setHours(0);
                                                System.out.println(dbTime);
                                                dataStructuresReachingClass.insertScore(new HighScore(name, dbTime, levelNum));
                                                started = System.currentTimeMillis();
                                                highScores = dataStructuresReachingClass.getHighScores();
                                        }
                                        catch (Exception ex) {
                                                System.out.println(ex.getMessage());
                                        }
                                }

                        }
                } catch (IOException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                Image bearimage = new ImageIcon("src/resource/YogiBear.png").getImage();
                bear = new Bear(5,5,60,60,bearimage);
                Image heartimage = new ImageIcon("src/resource/heartfinal.png").getImage();

        }
        public void newGame() {
                try {
                        level = new Level("data/level00.txt");
                } catch (IOException ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                this.levelNum = 0;
                Image bearimage = new ImageIcon("src/resource/YogiBear.png").getImage();
                bear = new Bear(5,5,60,60,bearimage);
                lives = 3;
                Image heartimage = new ImageIcon("src/resource/heartfinal.png").getImage();
                heart = new Heart(720,10,20,20,heartimage);
                heart2 = new Heart(740,10,20,20,heartimage);
                heart3 = new Heart(760,10,20,20,heartimage);
                hearts = new ArrayList<>();
                hearts.add(heart);
                hearts.add(heart2);
                hearts.add(heart3);
                long time = System.currentTimeMillis();
                long elapsedTimeInSeconds = (time - started) / 1000;
                int elapsedMinutes = (int) elapsedTimeInSeconds / 60;
                int elapsedSeconds = (int) elapsedTimeInSeconds - (elapsedMinutes * 60);
                newFrameTimer.start();
                this.started = System.currentTimeMillis();
        }

class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
                long currentTime = System.currentTimeMillis();
                long elapsed = currentTime - started;
                Time time = new Time(elapsed);
                time.setHours(time.getHours() - 1);
                timeLabel.setText(time.toString());
                for(Guard guard : level.getGuards()) {
                        if (rand_int1 == 0) {
                                guard.moveX();
                        } else {
                                guard.moveY();
                        }
                }
                ArrayList<Basket> baskets = level.getBaskets();
//
                level.collidesWithBasket(bear);
                if(level.collidesWithGuard(bear)){
                        if(lives > 1) {
                                restart();
                                int size = hearts.size()-1;
                                hearts.remove(size);
                                lives -= 1;
                                size -= 1;
                        }

                        else {
                                newFrameTimer.stop();
                                String name = (String) JOptionPane.showInputDialog(
                                        window,
                                        "Enter your name",
                                        "Game over",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        null,
                                        "Your Name"
                                );
                                if (name != null) {
                                        try {
                                                System.out.println(currentTime - started);
                                                Time dbTime = new Time(currentTime - started);
                                                dbTime.setHours(0);
                                                System.out.println(dbTime);
                                                dataStructuresReachingClass.insertScore(new HighScore(name, dbTime, levelNum));
                                                started = System.currentTimeMillis();
                                                highScores = dataStructuresReachingClass.getHighScores();
                                        }
                                        catch (Exception ex) {
                                                System.out.println(ex.getMessage());
                                        }
                                }
                        }
                }

                repaint();
                if (level.isOver()) {
                        levelNum += 1;
                        restart();
                }
                repaint();
        }

}

}

