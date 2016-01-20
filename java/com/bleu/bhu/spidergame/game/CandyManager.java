package com.bleu.bhu.spidergame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 1/1/2016.
 */
public class CandyManager extends Object implements CollisionListener{
    static String[] candies = {"candy_a1","candy_a3","candy_a5","candy_a6","candy_b1","candy_b3","candy_b4","candy_b6","candy_c1","candy_c2","candy_c4","candy_c6"};
    Bitmap[] candyBitmap;
    ArrayList<Candy> curCandies;
    Random rand;
    int CANDY_COUNT = 3;
    CandyManager(){
        x = 0;
        y = 0;
        candyBitmap = new Bitmap[candies.length];
        curCandies = new ArrayList<Candy>();
        for(int i=0;i<candies.length;i++){
           int id = GameEngine.resources.getIdentifier(candies[i],"drawable","com.bleu.bhu.spidergame");
           candyBitmap[i] = BitmapFactory.decodeResource(GameEngine.resources,id);
        }
        rand = new Random();

        int candySpawnY = 1000;
        for(int i=0;i < CANDY_COUNT; i++) {
            curCandies.add(NewCandy(candySpawnY));
            candySpawnY = curCandies.get(i).y+300;
        }
    }
    public void Update(){
        for(int i=CANDY_COUNT-1;i>=0; i--) {
            curCandies.get(i).Update();
            if(curCandies.get(i).y<0){
                curCandies.get(i).Finally();
                curCandies.remove(i);
                curCandies.add(NewCandy());
            }
        }
    }
    private Candy NewCandy(){
        return new Candy(rand.nextInt(500),1000,candyBitmap[rand.nextInt(candies.length)]);
    }
    private Candy NewCandy(int ty){
        return new Candy(rand.nextInt(500),ty,candyBitmap[rand.nextInt(candies.length)]);
    }
    public void Show(){
        for(int i=0;i < CANDY_COUNT; i++) {
            curCandies.get(i).Show();
        }
    }

    @Override
    public void onCollision(CollisionObject object) {
    }
    @Override
    public void onEngulfed(CollisionObject object){
        for(int i=0; i< CANDY_COUNT; i++){
            Candy candy = curCandies.get(i);
            if(candy.collisionId == object.id){
                curCandies.get(i).Finally();
                curCandies.remove(i);
                curCandies.add(NewCandy());
            }
        }
    }
}
