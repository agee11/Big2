package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameManager;
import com.mygdx.game.TextureManager;

public class GameScreen extends Screen{

	private GameManager gameManager;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		//deck = new Deck(TextureManager.back1);
		//deck.shuffle();
		//p1 = new Player(1);
		//p2 = new Player(2);
		//p3 = new Player(3);
		//p4 = new Player(4);
		gameManager = new GameManager();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		gameManager.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		gameManager.render(sb);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//deck.dispose();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
