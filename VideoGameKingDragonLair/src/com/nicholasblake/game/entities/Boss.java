package com.nicholasblake.game.entities;

import com.nicholasblake.game.gamestate.GameState;
import com.nicholasblake.game.main.GamePanel;
import com.nicholasblake.game.objects.Block;
import com.nicholasblake.game.objects.MovingBlock;
import com.nicholasblake.game.physics.Attackbox;
import com.nicholasblake.game.physics.Collision;
import com.nicholasblake.game.resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Boss {
    private static final long serialVersionUID=1L;

    public static int health=300;
    //movement booleans
    private boolean right = false, left = false,jumping=false,ducking=false,falling=false,attack=false;
    private boolean topCollision = false;
    //boundary
    private double x,y;
    private int width,height;
    //move speed
    private double moveSpeed = 1.5;
    //jump speed
    private double jumpSpeed = 5;
    private double currentJumpSpeed= jumpSpeed;
    //fall speed
    private double maxFallSpeed=5;
    private double currentFallSpeed=0.1;
    public Boss(int width,int height){
        x=4000+ GamePanel.WIDTH/2;
        y=GamePanel.HEIGHT/2;
        this.width = width;
        this.height = height;
    }
    public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks){
        int iX = (int)x-800-(int) GameState.xOffset+400;
        int iY= (int)y;
        for(int i=0; i<b.length; i++){

            for (int j=0;j<b[0].length; j++){
                if(b[i][j].getId()!=0){
                    //right collision
                    if(Collision.playerBlock(new Point(iX+width+(int)GameState.xOffset,iY+(int)GameState.yOffset+2),b[i][j])||
                            Collision.playerBlock(new Point(iX+ width+(int)GameState.xOffset,iY+height+(int)GameState.yOffset-1 ),b[i][j])){
                        right=false;
                    }
                    //left collision
                    if(Collision.playerBlock(new Point(iX+(int)GameState.xOffset-1,iY+(int)GameState.yOffset+2),b[i][j])||
                            Collision.playerBlock(new Point(iX+ (int)GameState.xOffset-1,iY+height+(int)GameState.yOffset-1 ),b[i][j])) {
                        left = false;
                    }
                    //top collision
                    if(Collision.playerBlock(new Point(iX+(int)GameState.xOffset+1,iY+(int)GameState.yOffset),b[i][j])||
                            Collision.playerBlock(new Point(iX+width+ (int)GameState.xOffset-3,iY+(int)GameState.yOffset ),b[i][j])) {
                        jumping = false;
                        falling = true;
                    }
                    //bottom collision
                    if(Collision.playerBlock(new Point(iX+ (int)GameState.xOffset+2,iY+height+(int)GameState.yOffset+1),b[i][j])||
                            Collision.playerBlock(new Point( iX+width+(int)GameState.xOffset-3,iY+height+(int)GameState.yOffset+1 ),b[i][j])) {
                        y=b[i][j].getY()-height-GameState.yOffset;
                        falling = false;
                        topCollision=true;
                    }else{
                        if(!topCollision&& !jumping){
                            falling=true;
                        }
                    }
                    //falling
                    if((int)y>1200){health-=400;}
                    //attack collision
                    if((int)x-800<435+ (int)GameState.xOffset&& Attackbox.getAttack()==true){
                        x+=40;
                        health -=Attackbox.getDMG();
                    }
                    //death
                    if(health<0) {//game over
                    }
                }
            }
        }
        for(int i = 0;i<movingBlocks.size(); i++){
            if(movingBlocks.get(i).getID()!=0){
                if(movingBlocks.get(i).getID()!=0){
                    //right collision
                    if(Collision.playerMovingBlock(new Point(iX+width+(int)GameState.xOffset,iY+(int)GameState.yOffset+2),movingBlocks.get(i))||
                            Collision.playerMovingBlock(new Point(iX+ width+(int)GameState.xOffset,iY+height+(int)GameState.yOffset-1 ),movingBlocks.get(i))){
                        right=false;
                    }
                    //left collision
                    if(Collision.playerMovingBlock(new Point(iX+(int)GameState.xOffset-1,iY+(int)GameState.yOffset+2),movingBlocks.get(i))||
                            Collision.playerMovingBlock(new Point(iX+ (int)GameState.xOffset-1,iY+height+(int)GameState.yOffset-1 ),movingBlocks.get(i))) {
                        left = false;
                    }
                    //top collision
                    if(Collision.playerMovingBlock(new Point(iX+(int)GameState.xOffset+1,iY+(int)GameState.yOffset),movingBlocks.get(i))||
                            Collision.playerMovingBlock(new Point(iX+width+ (int)GameState.xOffset-3,iY+(int)GameState.yOffset ),movingBlocks.get(i))) {
                        jumping = false;
                        falling = true;
                    }
                    //bottom collision
                    if(Collision.playerMovingBlock(new Point(800+(int)GameState.xOffset+2,iY+height+(int)GameState.yOffset+1),movingBlocks.get(i))||
                            Collision.playerMovingBlock(new Point(800+ width+(int)GameState.xOffset-3,iY+height+(int)GameState.yOffset+1 ),movingBlocks.get(i))) {
                        y=movingBlocks.get(i).getY()-height-GameState.yOffset;
                        falling = false;
                        topCollision=true;

                        GameState.xOffset+=movingBlocks.get(i).getMove();
                    }else{
                        if(!topCollision&& !jumping){
                            falling=true;
                        }
                    }
                }
            }
        }
        topCollision=false;
        if(right){
            x += moveSpeed;

        }
        if(left){
            x -=moveSpeed;
        }
        if(jumping){
            y -= currentJumpSpeed;
            currentJumpSpeed-=.1;
            if(currentJumpSpeed<=0){
                currentJumpSpeed=jumpSpeed;
                jumping=false;
                falling=true;
            }
        }
        if(ducking){

        }
        if(attack){
            Attackbox.bossAttack(20,true);
        }
        if(!attack){
            Attackbox.bossAttack(0,false);
        }
        if(falling){
            y+=currentFallSpeed;
            if(currentFallSpeed<maxFallSpeed){
                currentFallSpeed+=.1;
            }
        }
        if(!falling){
            currentFallSpeed=.1;
        }
        if((int)GameState.xOffset>2000) {
            if ((int) x - 800 < 400 + (int) GameState.xOffset) right = true;
            if ((int) x - 800 > 400 + (int) GameState.xOffset) left = true;
            if ((int) x - 800 > 400 + (int) GameState.xOffset) right = false;
            if ((int) x - 800 < 400 + (int) GameState.xOffset) left = false;
            if((int)x-800<400+ (int)GameState.xOffset&&attack!=true&& Attackbox.getAttack()!=true){attack=true; }
            else{attack=false; }
            //if((int)y>0&&!jumping&&!falling)jumping=true;
        }
    }
    public void draw(Graphics g){

        if((int)x-800>435+ (int)GameState.xOffset|| Attackbox.getAttack()==false){
            if(right&&!attack||left&&!attack){
                g.drawImage(Images.boss[0],(int)x-400- (int)GameState.xOffset,(int)y-80,width+150,height+150,null);
                //g.setColor(Color.BLACK);
                //g.fillRect((int)x,(int)y,width,height);

            }}
        if((int)x-800<435+ (int)GameState.xOffset&& Attackbox.getAttack()==true){g.drawImage(Images.enemydamage[0],(int)x-400- (int)GameState.xOffset,(int)y,width+20,height+20,null);

        }

        if(attack){
            g.drawImage(Images.bossstab[0],(int)x-400- (int)GameState.xOffset,(int)y-100,width+200,height+200,null);

        }

    }
    public void keyPressed(int k){
        if((int)x<0)right=true;
        if((int)x>0)left=true;
        if((int)y>0&&!jumping&&!falling)jumping=true;
        // if(k== KeyEvent.VK_S)ducking=true;
        // if(k== KeyEvent.VK_SPACE)attack=true;
    }
    public void keyReleased(int k){
        if((int)x>0)right=false;
        if((int)x<0)left=false;
        if((int)y<0&&!jumping&&!falling)jumping=false;
    }
}

