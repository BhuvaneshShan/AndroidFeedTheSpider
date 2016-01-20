package com.bleu.bhu.spidergame.game;

import android.graphics.Bitmap;

/**
 * Created by Bhuvanesh on 1/2/2016.
 */
public class Candy extends Object {
    Bitmap bitmap;
    float vals[];
    int collisionId;
    public Candy(int tx, int ty, Bitmap tbitmap){
        x = tx;
        y = ty;
        bitmap = tbitmap;
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        vals =  new float[GameEngine.world.ACCELEROMETER_VARS];
        collisionId = GameEngine.world.RegisterForCollision(this);
    }
    public void Update(){
        y=y-GameEngine.world.CANDY_SPEED_Y;
        vals = GameEngine.userInput.getAccelerometerValues();
        x = x - GameEngine.world.CANDY_SPEED_X * (int)vals[GameEngine.world.ACCELEROMETER_Y]; // '-' because accl vals are inverse
        if(x<0){
            x = 0;
        }else if(x+bitmap.getWidth()>GameEngine.canvas.getWidth()){
            x = GameEngine.canvas.getWidth() - bitmap.getWidth();
        }
    }
    public void Show(){
        GameEngine.canvas.drawBitmap(bitmap,x,y,null);
    }
    public void Finally(){
        GameEngine.world.UnregisterFromCollision(collisionId);
    }
}
