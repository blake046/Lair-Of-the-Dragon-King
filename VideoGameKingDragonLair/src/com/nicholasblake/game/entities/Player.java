package com.nicholasblake.game.entities;

import com.nicholasblake.game.gamestate.GameState;
import com.nicholasblake.game.main.GamePanel;
import com.nicholasblake.game.objects.Block;
import com.nicholasblake.game.objects.MovingBlock;
import com.nicholasblake.game.physics.Attackbox;
import com.nicholasblake.game.physics.Collision;
import com.nicholasblake.game.resources.Images;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Player  {
private int health = 160;

    private static final long serialVersionUID=1L;
    //movement booleans
    private boolean right = false, left = false,jumping=false,ducking=false,falling=false,attack=false;
    private boolean topCollision = false;
    //boundary
    private double x,y;
    private int width,height;
    //move speed
    private double moveSpeed = 2.5;
    //jump speed
    private double jumpSpeed = 5;
    private double currentJumpSpeed= jumpSpeed;
    //fall speed
    private double maxFallSpeed=5;
    private double currentFallSpeed=0.1;
    public Player(int width, int height){
x=GamePanel.WIDTH/2;
y=GamePanel.HEIGHT/2;
this.width = width;
this.height = height;
    }
    public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks){
        int iX = (int)x;
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
            if(Collision.playerBlock(new Point(iX+(int)GameState.xOffset+2,iY+height+(int)GameState.yOffset+1),b[i][j])||
                    Collision.playerBlock(new Point(iX+ width+(int)GameState.xOffset-3,iY+height+(int)GameState.yOffset+1 ),b[i][j])) {
                y=b[i][j].getY()-height-GameState.yOffset;
                falling = false;
                topCollision=true;
            }else{
                if(!topCollision&& !jumping){
                falling=true;
            }
                //damage
                if( Attackbox.getBossAttack()==true){
                    GameState.xOffset-=1;
                    health -=Attackbox.getBossDMG();
                }
                if(health<0){
                    System.out.println("you died");
                }
        }
            }}}
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
            if(Collision.playerMovingBlock(new Point(iX+(int)GameState.xOffset+2,iY+height+(int)GameState.yOffset+1),movingBlocks.get(i))||
                    Collision.playerMovingBlock(new Point(iX+ width+(int)GameState.xOffset-3,iY+height+(int)GameState.yOffset+1 ),movingBlocks.get(i))) {
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
    GameState.xOffset += moveSpeed;

}
if(left){
    GameState.xOffset -=moveSpeed;
}
if(jumping){
    GameState.yOffset -= currentJumpSpeed;
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
    Attackbox.playerAttack(12,true);
}
if(!attack){
    Attackbox.playerAttack(0,false);
}
if(falling){
GameState.yOffset+=currentFallSpeed;
if(currentFallSpeed<maxFallSpeed){
    currentFallSpeed+=.1;
}
}
if(!falling){
    currentFallSpeed=.1;
}
    }
    public void draw(Graphics g){

if(!right&&!left&&!attack){
        g.drawImage(Images.playerIdle[0],(int)x-5,(int)y-15,width+20,height+20,null);}
if(right&&!attack||left&&!attack){
    g.drawImage(Images.playerMovement[0],(int)x-5,(int)y-15,width+20,height+20,null);
    //g.setColor(Color.BLACK);
    //g.fillRect((int)x,(int)y,width,height);
}
if(attack){
    g.drawImage(Images.playerAttack[0],(int)x+1,(int)y-5,width+20,height+30,null);

}

    }
public void keyPressed(int k){
if(k==KeyEvent.VK_D)right=true;
    if(k== KeyEvent.VK_A)left=true;
    if(k==KeyEvent.VK_W&&!jumping&&!falling)jumping=true;
    if(k== KeyEvent.VK_S)ducking=true;
    if(k== KeyEvent.VK_SPACE)attack=true;
}
public void keyReleased(int k){
    if(k==KeyEvent.VK_D)right=false;
    if(k== KeyEvent.VK_A)left=false;
    if(k==KeyEvent.VK_W&&!jumping&&!falling)jumping=false;
    if(k== KeyEvent.VK_S)ducking=false;
    if(k== KeyEvent.VK_SPACE)attack=false;
}
}
