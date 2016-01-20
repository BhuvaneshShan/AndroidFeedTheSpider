package com.bleu.bhu.spidergame.game;

/**
 * Created by Bhuvanesh on 1/3/2016.
 */
public interface CollisionListener {
    void onCollision(CollisionObject object);
    void onEngulfed(CollisionObject object);
}
