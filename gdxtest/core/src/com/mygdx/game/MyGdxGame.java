package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screen.GameScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.ScreenManager;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img1;
	Sprite sprite;
	boolean canFlip = false;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		TextureManager.LoadCards();
		ScreenManager.setScreen(new GameScreen());
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, .5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ScreenManager.getCurrentScreen() != null){
			ScreenManager.getCurrentScreen().update();
			ScreenManager.getCurrentScreen().render(batch);
		}
	}
	
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null){
			ScreenManager.getCurrentScreen().dispose();
		}
	}

}
