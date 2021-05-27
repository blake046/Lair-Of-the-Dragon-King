package com.nicholasblake.game.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Music {
public Clip clip;
public void playMusic(String musicLocation)
{

    try{
        File musicPath = new File(musicLocation);
        if(musicPath.exists()) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }
        else{System.out.println("Can't find File");}
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
public void endMusic(){
    try{if(clip!=null){clip.stop();}}catch (Exception e){e.printStackTrace();}}
}
