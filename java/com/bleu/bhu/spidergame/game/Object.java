package com.bleu.bhu.spidergame.game;

import android.graphics.Paint;

/**
 * Created by Bhuvanesh on 1/1/2016.
 */
abstract public class Object {
    int x;
    int y;
    Paint p;
    int w;
    int h;
    public Object(){
        x = 0;
        y = 0;
        p = new Paint();
        w = 0;
        h = 0;
    }
    public Object(int tx,int ty,int tw, int th){
        x = tx;
        y = ty;
        p = new Paint();
        w = tw;
        h = th;
    }
    abstract public void Update();
    abstract public void Show();
}
