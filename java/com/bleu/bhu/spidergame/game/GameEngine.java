package com.bleu.bhu.spidergame.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bleu.bhu.spidergame.R;

/**
 * Created by Bhuvanesh on 1/1/2016.
 */
public class GameEngine {
    static Canvas canvas;
    static Resources resources;
    static UserInput userInput;
    static World world;

    Scene gameScene;
    Scene menuScene;
    Scene settingsScene;
    Scene aboutScene;
    Scene emptyScene;

    Scene _curScene;
    public GameEngine() {
        userInput = new UserInput();
        world = new World();
        emptyScene = new Scene();
    }

    public void Init(Context context){
        resources = context.getResources();
        if(userInput.Init(context)) {
            InitGameScene();
            _curScene = gameScene;
        }else{
            _curScene = emptyScene;
        }
    }

    public void Update(){
        _curScene.Update();
    }

    private void Render(){
        _curScene.Show();
    }

    public void Draw(Canvas t_canvas){
        canvas = t_canvas;
        canvas.drawColor(Color.WHITE);
        Update();
        Render();
    }

    public void onPause(){
        userInput.onPause();
    }
    public void onResume(){
        userInput.onResume();
    }

    private void InitGameScene(){
        gameScene = new Scene();
        gameScene.AddObjectToScene(world);
        gameScene.AddObjectToScene(new Background());
        gameScene.AddObjectToScene(new Spider());
        gameScene.AddObjectToScene(new CandyManager());
    }
}
