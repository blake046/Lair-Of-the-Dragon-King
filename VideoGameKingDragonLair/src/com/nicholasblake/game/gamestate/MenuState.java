package com.nicholasblake.game.gamestate;

import com.nicholasblake.game.gamestate.GameState;
import com.nicholasblake.game.gamestate.GameStateManager;
import com.nicholasblake.game.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    private String[] options = {"Start","Help","Quit"};
    private int currentSelection = 0;
    protected MenuState(GameStateManager gsm) {
        super(gsm);
    }


    public void init() {

    }


    public void tick() {

    }


    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);
for(int i=0; i<options.length; i++){
    if(i==currentSelection){
        g.setColor(Color.RED);
    }else{
        g.setColor(Color.YELLOW);
    }
g.setFont(new Font("Arial",Font.PLAIN, 50));
    g.drawString(options[i],GamePanel.WIDTH / 2-50, 50+i*50);
}
    }


    public void keyPressed(int k) {
if (k== KeyEvent.VK_DOWN){
currentSelection++;
if(currentSelection>= options.length)
    currentSelection=0;
}else if(k==KeyEvent.VK_UP){
    currentSelection--;
    if(currentSelection< 0)
        currentSelection= options.length-1;
}
if(k==KeyEvent.VK_ENTER){
    if(currentSelection==0){
gsm.states.push(new Level1State(gsm));
    }else if(currentSelection ==1) {


    }else if(currentSelection ==2){
        System.exit(0);
    }
}
    }


    public void keyReleased(int k) {

    }
}
