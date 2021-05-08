package com.nicholasblake.game.gamestate;

import com.nicholasblake.game.entities.Player;
import com.nicholasblake.game.mapping.Map;

import java.awt.Graphics;

public class Level1State extends GameState{
    private Player player;
private Map map;
    public Level1State(GameStateManager gsm){
        super(gsm);

    }


    public void init() {
player = new Player(30,30);
xOffset = -400;
yOffset = -800;

map = new Map("/maps/map1.map");

    }


    public void tick() {

player.tick(map.getBlocks(), map.getMovingBlocks());
    }


    public void draw(Graphics g) {
player.draw(g);
map.draw(g);
    }


    public void keyPressed(int k) {
player.keyPressed(k);
    }


    public void keyReleased(int k) {
player.keyReleased(k);
    }
}
