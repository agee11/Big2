package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity{

	protected Texture texture;
	
	public Entity(Texture t){
		texture = t;
	}
	
	public void dispose(){
		texture.dispose();
	}
	public abstract void update();
	public abstract void render(SpriteBatch sb);

	//public abstract int compareTo(Card card);
}
