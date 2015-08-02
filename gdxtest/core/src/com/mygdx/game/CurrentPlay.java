package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.entity.Card;

public class CurrentPlay {

	public static int player;
	public static int cards;
	public static int suit;
	public static int highCard;
	public static int set;
	public static Array<Card> currentDisplay = new Array<Card>();
	public static boolean firstPlay;
	
	public static void render(SpriteBatch sb){
		int j = 0;
		for (Iterator<Card> i = currentDisplay.iterator(); i.hasNext();) {				
			Card c = i.next();
			c.setPosition(Gdx.graphics.getWidth()/3 + 80*j, Gdx.graphics.getHeight()/2);
			j++;
		}
		for (Card card : currentDisplay) {
			card.render(sb);
		}
	}
	
	public static void clear(){
		player = 0;
		cards = 0;
		suit = 0;
		highCard = 0;
		set = 0;
		firstPlay = true;
		currentDisplay.clear();
	}
}
