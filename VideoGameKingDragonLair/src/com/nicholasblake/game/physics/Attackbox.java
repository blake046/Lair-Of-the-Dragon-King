package com.nicholasblake.game.physics;

public class Attackbox {
    public static int dmg=0;
    public static boolean swg=false;
    public static int dmgb=0;
    public static boolean swgb=false;
    public static boolean playerAttack(int damage,boolean swing){dmg=damage;
    swg=swing;
        return swing;}
    public static boolean bossAttack(int damage,boolean swing){dmgb=damage;
        swgb=swing;
        return swing;}
        public static boolean getAttack(){return swg;}
        public static int getDMG(){return dmg;}
    public static boolean getBossAttack(){return swgb;}
    public static int getBossDMG(){return dmgb;}
}
