package com.nicholasblake.game.main;

import com.nicholasblake.game.gamestate.GameStateManager;
import com.nicholasblake.game.resources.Images;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
public class GamePanel extends JPanel implements Runnable,KeyListener {
    private static final long serialVersionUID= 1L;
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1600;
    private Thread thread;
    private boolean isRunning = false;

    private GameStateManager gsm;
    private int FPS=60;
    private long targetTime = 1000/FPS;
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
        setFocusable(true);


        new Images();
        start();
    }
    private void start(){
isRunning=true;
thread=new Thread(this);
thread.start();

    }
    public void run(){
        long start, elapsed, wait;
        gsm=new GameStateManager();

while(isRunning){
start = System.nanoTime();
tick();
repaint();
elapsed=System.nanoTime()-start;
wait = targetTime - elapsed / 1000000;
if(wait <=0 ){
    wait=5;
}
try {
    Thread.sleep(wait);
} catch(Exception e){
    e.printStackTrace();
}


    }}
public void tick(){
gsm.tick();
}
public void paintComponent(Graphics g){
        super.paintComponent(g);

gsm.draw(g);
}

    public void keyTyped(KeyEvent e) {
gsm.keyPressed(e.getKeyCode());
    }

    public void keyPressed(KeyEvent e){
        gsm.keyPressed(e.getKeyCode());
    }


    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }
}
