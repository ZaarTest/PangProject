package com.pang.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pang.game.Pang;

import static com.pang.game.Constants.Constants.*;

public class StartingScreen implements Screen {
    private Texture texture;
    private Pang game;
    private OrthographicCamera orthographicCamera;
    private float screenTimer;

    private Viewport viewPort;
    public StartingScreen(Pang game){
        this.game = game;
        screenTimer = 0f;
        texture = new Texture(Gdx.files.internal("images/pang.png"));
        orthographicCamera = new OrthographicCamera();
        //Sätt storlek på viewport  och skalera
        viewPort = new FitViewport((texture.getWidth()+250)/2,(texture.getHeight()+100)/2, orthographicCamera);
        System.out.println(texture.getWidth()+"  "+texture.getHeight());
        orthographicCamera.position.set((viewPort.getWorldWidth()/2 ), (viewPort.getWorldHeight()/2), 0);
    }
    @Override
    public void show() {

    }
    public void update(float dt){
        orthographicCamera.update();
        screenTimer+=dt;
    }
    @Override
    public void render(float dt) {
        //Logix
        update(dt);
        //Sätt skärmen svart
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();
        if(screenTimer>5){
            dispose();
            game.setScreen(new Level1(game));

        }
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width,height);
        System.out.println(width+" "+height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
