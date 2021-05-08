package com.nicholasblake.game.physics;


import com.nicholasblake.game.objects.Block;
import com.nicholasblake.game.objects.MovingBlock;

import java.awt.*;

public class Collision {
    public static boolean playerBlock(Point p, Block b){
        return b.contains(p);
    }
    public  static boolean playerMovingBlock(Point p, MovingBlock b){
        return  b.contains(p);
    }
}
