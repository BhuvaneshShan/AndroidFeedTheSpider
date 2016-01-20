package com.bleu.bhu.spidergame.game;

import java.util.ArrayList;

/**
 * Created by Bhuvanesh on 1/2/2016.
 */
public class World extends Object{
    public int CANDY_SPEED_Y = 10;
    public static int CANDY_SPEED_X = 4;

    public static int ACCELEROMETER_VARS = 3;
    public static int ACCELEROMETER_X = 0;
    public static int ACCELEROMETER_Y = 1;
    public static int ACCELEROMETER_Z = 2;

    private int CollisionId;

    private ArrayList<CollisionObject> CollisionObjects;
    private ArrayList<CollisionListener> CollisionListeners;

    public World(){
        CollisionObjects = new ArrayList<CollisionObject>();
        CollisionListeners = new ArrayList<CollisionListener>();
        CollisionId = -1;
    }
    public void Update(){
        checkCollision();
    }
    public void Show(){
        //nothing
    }
    public void addListener(CollisionListener cListener){
        CollisionListeners.add(cListener);
    }
    public int RegisterForCollision(Object obj,int x, int y, int w, int h){
        CollisionObjects.add(new CollisionObject(obj, x,y,w,h,NewCollisionId()));
        return CollisionId;
    }
    public int RegisterForCollision(Object obj,int w, int h){
        CollisionObjects.add(new CollisionObject(obj,w,h,NewCollisionId()));
        return CollisionId;
    }
    public int RegisterForCollision(Object obj){
        CollisionObjects.add(new CollisionObject(obj,NewCollisionId()));
        return CollisionId;
    }
    public void UnregisterFromCollision(int cid){
        for(int i=CollisionObjects.size()-1;i>=0;i--) {
            if(CollisionObjects.get(i).id == cid ){
                CollisionObjects.remove(i);
                return;
            }
        }
    }
    private int NewCollisionId(){
        CollisionId++;
        return CollisionId;
    }
    private void checkCollision(){
        for(int i=0;i<CollisionObjects.size();i++){
            for(int j=0;j<CollisionObjects.size();j++){
                if(i!=j) {
                    if (isColliding(CollisionObjects.get(i), CollisionObjects.get(j))) {
                        informListenersAboutCollision(CollisionObjects.get(j));
                    }
                    if (isEngulfed(CollisionObjects.get(i),CollisionObjects.get(j))){
                        informListenersAboutEngulf(CollisionObjects.get(j));
                    }
                }
            }
        }
    }
    private boolean isColliding(CollisionObject obj1,CollisionObject obj2){
        if(obj2.x >= obj1.x && obj2.x+obj2.w <= obj1.x+obj1.w){
            if(obj2.y > obj1.y && obj2.y < obj1.y+obj1.h){
                return true;
            }
        }
        return false;
    }
    private boolean isEngulfed(CollisionObject obj1,CollisionObject obj2){
        if(obj2.x >= obj1.x && obj2.x+obj2.w <= obj1.x+obj1.w){
            if(obj2.y+obj2.h < obj1.y+obj1.h){
                return true;
            }
        }
        return false;
    }
    private void informListenersAboutCollision(CollisionObject obj){
        for(int i=0;i<CollisionListeners.size();i++){
            CollisionListeners.get(i).onCollision(obj);
        }
    }
    private void informListenersAboutEngulf(CollisionObject obj){
        for(int i=0;i<CollisionListeners.size();i++){
            CollisionListeners.get(i).onEngulfed(obj);
        }
    }
}
