package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

	public static Texture[] club = new Texture[14];
	public static Texture[] daimond = new Texture[14];
	public static Texture[] heart = new Texture[14];
	public static Texture[] spade = new Texture[14];
	
	public static Texture back1 = new Texture(Gdx.files.internal("Modern/Card-Back-01.png"));
	public static void LoadCards(){
		for(int i = 1; i < 14; i++){
			if(i < 10){
				club[i] = new Texture(Gdx.files.internal("Modern/c0" + i + ".png"));
				daimond[i] = new Texture(Gdx.files.internal("Modern/d0" + i + ".png"));
				heart[i] = new Texture(Gdx.files.internal("Modern/h0" + i + ".png"));
				spade[i] = new Texture(Gdx.files.internal("Modern/s0" + i + ".png"));
			}else{
				club[i] = new Texture(Gdx.files.internal("Modern/c" + i + ".png"));
				daimond[i] = new Texture(Gdx.files.internal("Modern/d" + i + ".png"));
				heart[i] = new Texture(Gdx.files.internal("Modern/h" + i + ".png"));
				spade[i] = new Texture(Gdx.files.internal("Modern/s" + i + ".png"));
			}
		}
	}
}
