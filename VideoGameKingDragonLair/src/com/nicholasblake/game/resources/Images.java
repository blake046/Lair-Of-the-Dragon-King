package com.nicholasblake.game.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    public static BufferedImage[] blocks;
    public Images(){
        blocks = new BufferedImage[1];
        try {
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/brick wall.png"));
        } catch (IOException e){
            e.printStackTrace();;
        }
        }
}
