package com.nicholasblake.game.resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    public static BufferedImage[] blocks;
public static BufferedImage[] playerIdle;
public static BufferedImage[] playerMovement;
    public static BufferedImage[] playerAttack;
    public static BufferedImage[] background;
    public static BufferedImage[] enemy;
    public static BufferedImage[] enemydamage;
    public static BufferedImage[] boss;
    public static BufferedImage[] bossstab;
    public Images(){
        blocks = new BufferedImage[1];
playerIdle= new BufferedImage[1];
playerMovement = new BufferedImage[1];
playerAttack = new BufferedImage[1];
        background = new BufferedImage[1];
        enemy = new BufferedImage[1];
        enemydamage = new BufferedImage[1];
        boss = new BufferedImage[1];
        bossstab = new BufferedImage[1];
        try {
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/brick wall.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            background[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/Backgroundlvl1 (1).png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            playerIdle[0] = ImageIO.read(getClass().getResourceAsStream("/Character/Idle Red attack Big sword + Fire spell changing (1).gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            playerMovement[0] = ImageIO.read(getClass().getResourceAsStream("/Character/Idle Red attack Big sword + Fire spell changing (1).gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            playerAttack[0] = ImageIO.read(getClass().getResourceAsStream("/Character/Red attack Big sword + Fire spell changing (1).gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            enemy[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/GoblinIdle.gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            enemydamage[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/Goblin Pirate Damage animation (1).gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            boss[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/Goblin King sword drawn.gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            bossstab[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/Goblin King sword  attack stab (3).gif"));

        } catch (IOException e){
            e.printStackTrace();
        }
        }
}
