package com.nicholasblake.game.gamestate;

import com.nicholasblake.game.entities.Boss;
import com.nicholasblake.game.entities.Enemy;
import com.nicholasblake.game.entities.Player;
import com.nicholasblake.game.main.GamePanel;
import com.nicholasblake.game.mapping.Map;
import com.nicholasblake.game.resources.Images;
import com.nicholasblake.game.resources.Music;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState{
    private Player player;
    private Enemy enemy;
    private Boss boss;
    Music musicObject = new Music();

private Map map;
    public Level1State(GameStateManager gsm){
        super(gsm);



        musicObject.playMusic("res/Songs/Level 1.wav");

    }


    public void init() {
player = new Player(64,64);
xOffset = -400;
yOffset = -864;
enemy = new Enemy(64,64);
boss=new Boss(100,100);
map = new Map("/maps/map1.map");

    }


    public void tick() {
enemy.tick(map.getBlocks(),map.getMovingBlocks());
player.tick(map.getBlocks(), map.getMovingBlocks());
boss.tick(map.getBlocks(),map.getMovingBlocks());
        if(yOffset>-500){
            gsm.states.push(new Level1State(gsm));
            musicObject.endMusic();
        }
    }


    public void draw(Graphics g) {

        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);
        g.drawImage(Images.background[0],-350,-1400-(int)GameState.yOffset,3000,3000,null);
player.draw(g);
map.draw(g);
enemy.draw(g);
boss.draw(g);

    }


    public void keyPressed(int k) {
player.keyPressed(k);
        if(k== KeyEvent.VK_ESCAPE){
            gsm.states.push(new MenuState(gsm));
            musicObject.endMusic();
        }

    }


    public void keyReleased(int k) {
player.keyReleased(k);
    }
}
