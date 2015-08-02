package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Card extends Entity implements Comparable<Card>{

	protected int number;
	protected int suit;
	protected Sprite sprite;
	private boolean selected = false;
	
	private boolean canFlip = false;
	
	public Card(Texture texture, int n, int s) {
		super(texture);
		number = n;
		suit = s;
		sprite = new Sprite(texture);
		sprite.setSize(sprite.getWidth()*.15f, sprite.getHeight()*.15f);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	}
	
	public int getCardNumber(){
		return number;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void deselect(){
		if(selected){
			selected();
		}
	}
	
	public int getCardSuit(){
		return suit;
	}

	public void setPosition(float x, float y){
		sprite.setX(x);
		sprite.setY(y);
	}
	
	@Override
	public void update() {
		if(Gdx.input.justTouched()){
			if(Gdx.input.getX() <= sprite.getX() + 80 
					&& Gdx.input.getX() >= sprite.getX()
					&& Gdx.graphics.getHeight() - Gdx.input.getY() >= sprite.getY() 
					&& Gdx.graphics.getHeight() - Gdx.input.getY()<= sprite.getY() + sprite.getHeight()){
					
				selected();
			}
		}
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.begin();
		sb.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
		sb.end();
	}
	
	public void dispose(){
		sprite = null;
		texture.dispose();
	}
	protected void selected(){
		if(!selected){
			sprite.setY(sprite.getY() + 30);
			selected = true;
		}else{
			sprite.setY(sprite.getY() - 30);
			selected = false;			
		}
	}
	protected void flip(){
		if(canFlip){
			if(sprite.getScaleX() <= 1f){
				sprite.setScale(sprite.getScaleX() + .03f, 1f);
			}else{
				canFlip = false;
			}
		}else{
			if(sprite.getScaleX() >= 0){
				sprite.setScale(sprite.getScaleX() - .04f, 1f);
			}else{
				if(sprite.getTexture() == texture){
					sprite.setTexture(texture);					
				}else{
					sprite.setTexture(texture);
				}
				canFlip = true;
			}
		}
	}

	@Override
	public int compareTo(Card card) {
		// TODO Auto-generated method stub
		return Integer.compare(number, card.getCardNumber());
	}

}
