package com.pang.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pang.game.Pang;

import static com.pang.game.Constants.Constants.WORLD_HEIGHT;
import static com.pang.game.Constants.Constants.WORLD_WIDTH;

public class GameOverScreen implements Screen {
    private Texture texture;
    private Pang game;
    private OrthographicCamera orthographicCamera;
    private float screenTimer;
    private boolean restartGame;

    private Viewport viewPort;
    public GameOverScreen(Pang game){
        this.game = game;
        this.game.batch = new SpriteBatch();
        restartGame = false;
        texture = new Texture(Gdx.files.internal("images/gameOver.png"));
        orthographicCamera = new OrthographicCamera();
        //Sätt storlek på viewport
        viewPort = new FitViewport(((texture.getWidth()+500) /2),((texture.getHeight()+400) /2), orthographicCamera);
        orthographicCamera.position.set((viewPort.getWorldWidth() / 2), (viewPort.getWorldHeight() / 2), 0);
    }
    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            restartGame = true;
        }
    }
    public void update(float dt){
        handleInput(dt);
        orthographicCamera.update();
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
        if(restartGame){
            game.setScreen(new StartingScreen(game));
            game.assetManager.get("audio/music/nighttideWaltz.ogg",Music.class).stop();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width,height);
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