package com.bleu.bhu.spidergame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.bleu.bhu.spidergame.R;

/**
 * Created by User on 1/1/2016.
 */
public class Spider extends Object implements CollisionListener{
    Bitmap spider[];
    Paint spiderLine;
    int CUR_STATE;

    int CollisionId;
    CollisionObject cObject;

    static int STATE_MOUTH_CLOSE = 0;
    static int STATE_MOUTH_OPEN = 1;static int STATE_LICK = 2;

    boolean initialised = false;
    public Spider(){
        spider = new Bitmap[3];
        spider[STATE_MOUTH_CLOSE] = BitmapFactory.decodeResource(GameEngine.resources, R.drawable.spider_eye_open);
        spider[STATE_MOUTH_OPEN] = BitmapFactory.decodeResource(GameEngine.resources, R.drawable.spider_mouth_open);
        spider[STATE_LICK] = BitmapFactory.decodeResource(GameEngine.resources, R.drawable.spider_tongue_licking_1);

        CUR_STATE = STATE_MOUTH_CLOSE;
        spiderLine = new Paint();
        spiderLine.setStyle(Paint.Style.FILL_AND_STROKE);
        spiderLine.setColor(Color.BLACK);

        GameEngine.world.addListener(this);
    }
    public void Update(){
        if(initialised) {

        }else{
            x = GameEngine.canvas.getWidth() / 2 - spider[CUR_STATE].getWidth() / 2;
            y = spider[CUR_STATE].getHeight() / 2;
            w = spider[STATE_MOUTH_CLOSE].getWidth();
            h = spider[STATE_MOUTH_CLOSE].getHeight();
            int cx = x+w/4;
            int cy = y+h/3;
            int cw = w/2;
            int ch = h/3;
            cObject = new CollisionObject(this,cx,cy,cw,ch,0);
            CollisionId = GameEngine.world.RegisterForCollision(this, x, y, w, h);
        }
    }

    public void Show(){
        GameEngine.canvas.drawRect(GameEngine.canvas.getWidth() / 2, 0, GameEngine.canvas.getWidth() / 2 + 5, y, spiderLine);
        if(CUR_STATE == STATE_MOUTH_CLOSE) {
            GameEngine.canvas.drawBitmap(spider[STATE_MOUTH_CLOSE], x, y, null);
        }else if(CUR_STATE == STATE_MOUTH_OPEN){
            GameEngine.canvas.drawBitmap(spider[STATE_MOUTH_OPEN], x, y, null);
        }else if(CUR_STATE == STATE_LICK){
            GameEngine.canvas.drawBitmap(spider[STATE_LICK], x, y, null);
        }
    }

    @Override
    public void onCollision(CollisionObject collisionObject) {
        if (collisionObject.id != CollisionId) {
           CUR_STATE = STATE_MOUTH_OPEN;

        }
    }
    @Override
    public void onEngulfed(CollisionObject collisionObject) {
        if (collisionObject.id != CollisionId) {
            CUR_STATE = STATE_MOUTH_CLOSE;
        }
    }
}
