package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CurrentPlay;
import com.mygdx.gameAI.PassTurn;
import com.mygdx.gameAI.PlayLowestPair;
import com.mygdx.gameAI.conditions.*;
import com.mygdx.gameAI.decisionTrees.BinaryDecision;

public class ComputerPlayer extends Player{

	BinaryDecision root;
	BinaryDecision single;
	BinaryDecision pairs;
	BinaryDecision havePair;
	
	public ComputerPlayer(int n) {
		super(n);
		root = new BinaryDecision();
		single = new BinaryDecision();
		pairs = new BinaryDecision();
		havePair = new BinaryDecision();
		
		root.setCondition(new CurrentPlaySize(1));
		root.setTrueBranch(new PassTurn());
		root.setFalseBranch(pairs);
		pairs.setCondition(new HavePair(this));
		pairs.setTrueBranch(havePair);
		pairs.setFalseBranch(new PassTurn());
		havePair.setCondition(new GreaterPairFound(this));
		havePair.setTrueBranch(new PlayLowestPair(this));
		havePair.setFalseBranch(new PassTurn());

	}
	
	public void update(){
		super.update();
		if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
			root.makeDecision().doAction();
		}
	}
	
	public void render(SpriteBatch sb){
		super.render(sb);
	}

	public void playLowSingle(){
		for(Card card : hand){
			if((card.number == CurrentPlay.highCard && card.suit > CurrentPlay.highCard) ||
					card.number > CurrentPlay.highCard){
				card.selected();
				System.out.println(card.number + card.suit);
				//playCards();
				break;
			}
		}
	}
	
	public void playLowPair(){
		int n = CurrentPlay.highCard;
		Card prev = null;;
		
		for (Card card : hand) {
			if(card.number >= n){
				card.selected();
				if(prev == null){
					prev = card;
				}else if(prev.number == card.number){
					//playCards();
					System.out.println(prev.number + prev.suit);
					System.out.println(card.number + card.suit);
					break;
				}else if(prev.number != card.number){
					prev.deselect();
					prev = card;
				}
			}
		}
	}
	
	public boolean pairFound(){
		Card prev = null;
		for (Card card : hand) {
			if(prev == null){
				prev = card;
			}else{
				if(card.number == prev.number){
					return true;
				}else{
					prev = card;
				}
			}
		}
		return false;
	}
	
	public boolean tripleFound(){
		Card prev1 = null, prev2 = null;
		for (Card card : hand) {
			if(prev1 == null){
				prev1 = card;
			}else if (prev2 == null){
				prev2 = card;
			}else if(prev1.number == prev2.number && prev1.number == card.number){
				return true;
			}
		}
		return false;
	}
	
	public boolean greaterPairFound(){
		Card prev = null;
		for (Card card : hand) {
			if(prev == null){
				prev = card;
			}else if(card.number > CurrentPlay.highCard ||
						(card.number == CurrentPlay.highCard && CurrentPlay.suit != 3)){
				return true;
			}
			prev = card;	
		}		
		return false;
	}
	
	public boolean greaterTripleFound(){
		Card prev = null, prev2 = null;
		for (Card card : hand) {
			if(prev == null){
				prev = card;
			}else if(prev2 == null){
				prev2 = card;
			}else if(prev.number == card.number && prev2.number == card.number){
				if(card.number > CurrentPlay.highCard){
					return true;
				}
				prev = prev2;
				prev2 = card;
			}
		}
		
		return false;
	}
	public int bestFlushFound(){
		int hearts=0,spades=0,daimonds=0,clubs=0;
		int highHearts=0, highSpades=0, highDaimonds=0, highClubs=0;
		for (Card card : hand) {
			switch (card.suit) {
			case 2:
				hearts++;
				if(card.number > highHearts){
					highHearts = card.number;
				}
				break;
			case 3:
				spades++;
				if(card.number == 1 && highSpades != 2){
					highSpades = card.number;
				}else if(card.number == 2){
					highSpades = card.number;
				}
				if(card.number > highSpades){
				}
				break;
			case 0:
				daimonds++;
				if(card.number > highDaimonds){
					highDaimonds = card.number;
				}
				break;
			case 1:
				clubs++;
				if(card.number > highClubs){
					highClubs = card.number;
				}
				break;
			default:
				break;
			}
		}
		if(hearts >= 5)
			return 2;
		else if(spades >= 5)
			return 3;
		else if(daimonds >= 5)
			return 0;
		else if (clubs >= 5){
			return 1;
		}else{
			return 4;
		}
	}
	
}
