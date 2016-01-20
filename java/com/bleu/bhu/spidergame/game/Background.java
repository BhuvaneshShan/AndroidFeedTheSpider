package com.bleu.bhu.spidergame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by User on 1/2/2016.
 */
public class Background extends Object{
    String bgs[] = {"bg1","bg2","bg3"};
    Bitmap bitmaps[];
    int curBG;
    public Background(){
        x=0; y=0;
        bitmaps = new Bitmap[bgs.length];
        for(int i=0; i<bgs.length;i++){
            int id = GameEngine.resources.getIdentifier(bgs[i],"drawable","com.bleu.bhu.spidergame");
            bitmaps[i] = BitmapFactory.decodeResource(GameEngine.resources, id);
        }
        curBG = 0;// new Random().nextInt(bgs.length);
    }
    public void Update(){
        //y=y-10;
        //if(y+bitmaps[curBG].getHeight()<0){
        //    y=bitmaps[curBG].getHeight();
        //}
    }
    public void Show(){
        GameEngine.canvas.drawBitmap(bitmaps[curBG],x,y,null);
        //if(y+bitmaps[curBG].getHeight() < GameEngine.canvas.getHeight()){
        //    GameEngine.canvas.drawBitmap(bitmaps[curBG],x,y+bitmaps[curBG].getHeight(),null);
        //}
        //if(y>0){
        //    GameEngine.canvas.drawBitmap(bitmaps[curBG],x,y-bitmaps[curBG].getHeight(),null);
        //}
    }
}
