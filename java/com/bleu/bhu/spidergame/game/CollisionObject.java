package com.bleu.bhu.spidergame.game;

/**
 * Created by User on 1/2/2016.
 */
public class CollisionObject {
    public int x;
    public int y;
    public int w;
    public int h;
    int id;
    public CollisionObject(Object obj,int tid){
        x = obj.x;
        y = obj.y;
        w = obj.w;
        h = obj.h;
        id = tid;
    }
    public CollisionObject(Object obj,int tw, int th, int tid){
        x = obj.x;
        y = obj.y;
        w = tw;
        h = th;
        id = tid;
    }
    public CollisionObject(Object obj, int tx, int ty, int tw, int th, int tid){
        x = tx;
        y = ty;
        w = tw;
        h = th;
        id = tid;
    }
}
