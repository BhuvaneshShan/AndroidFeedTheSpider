package com.bleu.bhu.spidergame.game;

import java.util.ArrayList;

/**
 * Created by User on 1/1/2016.
 */
public class Scene{
    ArrayList<Object> objects;
    public Scene(){
        objects = new ArrayList<Object>();
    }
    public void AddObjectToScene(Object obj){
        objects.add(obj);
    }
    public void Update(){
        for(int i=0;i<objects.size();i++){
            objects.get(i).Update();
        }
    }
    public void Show(){
        for(int i=0;i<objects.size();i++){
            objects.get(i).Show();
        }
    }
}
