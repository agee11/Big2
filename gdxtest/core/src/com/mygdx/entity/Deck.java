package com.mygdx.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.TextureManager;

public class Deck extends Entity{

	Array<Card> deck = new Array<Card>();
	private Sprite sprite;
	
	public Deck(Texture texture){
		super(texture);
		for(int i = 0; i < 13; i++){
			deck.add(new Card(TextureManager.club[i+1], i+1, 1));
			deck.add(new Card(TextureManager.daimond[i+1], i+1, 0));
			deck.add(new Card(TextureManager.heart[i+1], i+1, 2));
			deck.add(new Card(TextureManager.spade[i+1], i+1, 3));
		}
		sprite = new Sprite(texture);
		sprite.setOriginCenter();
		sprite.setSize(sprite.getWidth()*.2f, sprite.getHeight()*.2f);
		sprite.setPosition(0, 0);
	}
	
	public void shuffle(){
		deck.shuffle();
	}
	public Card Draw(){
		if(deck.size != 0){
			return deck.pop();
		}else{
			return null;
		}
	}
	
	public void distribute(Player[] p){
		for(int i = 1; deck.size != 0 ; i++){
			p[i%4].handAdd(deck.pop());
		}
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
		sb.end();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void dispose(){
		super.dispose();
		deck = null;
		sprite = null;
	}
	
}
