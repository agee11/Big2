package com.mygdx.entity;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CurrentPlay;

public class Player {

	protected int playerID;
	protected boolean validPlay = false;
	protected Array<Card> hand = new Array<Card>();
	private int set, sigCard, suit, cards;
	public Player(int n){		
		playerID = n;
	}
	
	public int getPlayerID(){
		return playerID;
	}
/*	
	public void playerDraw(Deck deck){
		if(hand.size != 13){
			Card c = deck.Draw();
			c.setPosition(80*hand.size + 200, 0);
			hand.add(c);
		}
	}
*/	
	public void handAdd(Card c){
		hand.add(c);
	}
	public int getCurrentHandSize(){
		return hand.size;
	}
	
	public void clearSelected(){
		for (Card card : hand) {
			card.deselect();
		}
	}
	public void playCards(){
		if(!validPlay){
			System.out.println("This is not a valid play!");
			clearSelected();
		}else{}
		
			CurrentPlay.currentDisplay.clear();
			CurrentPlay.cards = cards;
			CurrentPlay.highCard = sigCard;
			CurrentPlay.suit = suit;
			CurrentPlay.set = set;
			CurrentPlay.player = playerID;
			
			String suit = null;
			String set = null;
			
			switch (CurrentPlay.suit){
			case 1: suit = "Clubs";
					break;
			case 2: suit = "Hearts";
					break;
			case 3: suit = "Spades";
					break;
			case 0: suit = "Daimonds";
					break;
			}
			
			switch (CurrentPlay.cards){
			case 1: set = "";
					break;
			case 2: set = "Pair of ";
					break;
			case 3: set = "Three of a Kind of ";
					break;
			}
			
			switch (CurrentPlay.set){
				case 1: set = "Strait with ";
						break;
				case 2: set = "Flush with ";
						break;
				case 3: set = "Full House of ";
						break;
				case 4: set = "Four of a Kind of ";
						break;
				case 5: set = "Strait Flush with ";
						break;
			}
			System.out.println("Player " + (CurrentPlay.player) + " played a " + set + CurrentPlay.highCard + " " + suit);
			
			if(CurrentPlay.firstPlay){
				CurrentPlay.firstPlay = false;
			}
			for(Iterator<Card> i = hand.iterator(); i.hasNext();){
				Card card = i.next();
				if(card.isSelected()){
					CurrentPlay.currentDisplay.add(card);
					card.deselect();
					i.remove();
					//System.out.println(card.getCardNumber() + card.getCardSuit());
				}
			}
	//	}
		
		validPlay = false;
	}
	
	public void sortHand(){
		hand.sort();
	}
	
	public boolean getValidPlay(){
		return validPlay;
	}
	
	public void setValidPlay(boolean v){
		validPlay = v;
	}
	
	public boolean foundCard(int suit, int num){
		for (Card card : hand) {
			if(card.suit == suit && card.number == num){
				return true;
			}
		}
		return false;
	}
	
	public void currentPlay(){
		Card c;
		//Array<Card> play = new Array<Card>();
		//int[] number = new int[5];
		//int[] suit = new int[5];
		Array<Card> selected = new Array<Card>();
		
		int count = 0;
		for(Iterator<Card> i = hand.iterator(); i.hasNext();){
			c = i.next();
			if(c.isSelected()){
				if(count < 5){
					selected.add(c);
					//number[count] = c.getCardNumber();
					//suit[count] = c.getCardSuit();
				}
				count++;
			}
		}
		selected.sort();
		if(CurrentPlay.firstPlay){
			boolean d3 = false;
			for (Card card : selected) {
				if(card.number == 3 && card.suit == 0){
					d3 = true;
					break;
				}
			}
			if(d3 == false){
				validPlay = false;
				return;
			}
		}
		
		if(count == 1 && (CurrentPlay.cards == 1 || CurrentPlay.cards == 0)){
			//System.out.println("Current Play: " + number[0] + " of " + suit[0]);
			if(selected.get(0).number > CurrentPlay.highCard && (CurrentPlay.highCard != 1 && CurrentPlay.highCard != 2)){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else if(selected.get(0).number == 1 && CurrentPlay.highCard != 2 && CurrentPlay.highCard != 1){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else if(selected.get(0).number == 2 && CurrentPlay.highCard != 2){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else if(selected.get(0).number == CurrentPlay.highCard && selected.get(0).suit > CurrentPlay.suit){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else{
				validPlay = false;
			}
		}else if(count == 2 && selected.get(0).number == selected.get(1).number && (CurrentPlay.cards == 2 || CurrentPlay.cards == 0)){ 
			//System.out.println("Current Play: Pair of " + number[0] + "s");
			if((selected.get(0).number > CurrentPlay.highCard && CurrentPlay.highCard != 1 && CurrentPlay.highCard != 2)
					|| (selected.get(0).number == 1 && CurrentPlay.highCard != 1 && CurrentPlay.highCard != 2)
					|| (selected.get(0).number == 2 && CurrentPlay.highCard != 2)){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				if(selected.get(0).suit > selected.get(1).suit){
					suit = selected.get(0).suit;
				}else{
					suit = selected.get(1).suit;
				}
			}else if(selected.get(0).number == 1 && CurrentPlay.highCard != 2){
				
			}else if(selected.get(0).number == CurrentPlay.highCard && CurrentPlay.suit != 3){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = 3;
			}else{
				validPlay = false;
			}
		}else if(count == 3 && (CurrentPlay.cards == 3 || CurrentPlay.cards == 0) && selected.get(0).number == selected.get(1).number && selected.get(0).number == selected.get(2).number){
			//System.out.println("Current Play: Three of a kind of " + number[0] + "s");
			if(selected.get(0).number > CurrentPlay.highCard && CurrentPlay.highCard != 1 && CurrentPlay.highCard != 2){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else if(selected.get(0).number == 1 && CurrentPlay.highCard != 2){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else if(selected.get(0).number == 2){
				validPlay = true;
				set = 0;
				sigCard = selected.get(0).number;
				cards = count;
				suit = selected.get(0).suit;
			}else{
				validPlay = false;
			}
		}else if(count == 5 && (CurrentPlay.cards == 5 || CurrentPlay.cards == 0)){
			boolean strait = false;
			boolean flush = false;
			
			if((selected.get(2).number == selected.get(0).number && selected.get(3).number == selected.get(4).number) ||
					(selected.get(2).number == selected.get(4).number && selected.get(0).number == selected.get(1).number)){
				//System.out.println("Current Play: Full House of " + number[2]);
				if(CurrentPlay.set < 3 || CurrentPlay.cards == 0 || (CurrentPlay.set == 3 && CurrentPlay.highCard < selected.get(2).number)){
					validPlay = true;
					set = 3;
					sigCard = selected.get(2).number;
					cards = count;
					suit = selected.get(2).suit;
				}else{
					validPlay = false;
				}
			}
			if(selected.get(0).number == selected.get(3).number || selected.get(1).number == selected.get(4).number){
				//System.out.println("Current Play: Four of Kind of" + number[2]);
				if(CurrentPlay.set < 4 || CurrentPlay.cards == 0 || (CurrentPlay.set == 4 && CurrentPlay.highCard < selected.get(2).number)){
					validPlay = true;
					set = 4;
					sigCard = selected.get(2).number;
					cards = count;
					suit = selected.get(2).suit;
				}else{
					validPlay = false;
				}
			}
			if(selected.get(0).number == selected.get(1).number - 1 && selected.get(0).number == selected.get(2).number - 2 && selected.get(0).number == selected.get(3).number - 3 && selected.get(0).number == selected.get(4).number - 4){
				strait = true;
			}else if(selected.get(0).number == 1 && selected.get(1).number == 10 && selected.get(2).number == 11 && selected.get(3).number == 12 && selected.get(4).number == 13){
				strait = true;
			}
			if(selected.get(0).suit == selected.get(1).suit && selected.get(0).suit == selected.get(2).suit && selected.get(0).suit == selected.get(3).suit && selected.get(0).suit == selected.get(4).suit){
				flush = true;
			}
			if(strait && flush){
				//System.out.println("Current Play: Strait Flush " + number[0] + " to " + number[4] + " of " + suit[0]);
				if(CurrentPlay.set < 5 || CurrentPlay.cards == 0 || CurrentPlay.suit < selected.get(0).suit || CurrentPlay.highCard < selected.get(4).number){
					validPlay = true;
					set = 5;
					sigCard = selected.get(4).number;
					cards = count;
					suit = selected.get(0).suit;					
				}
			}else if(strait){
				if((CurrentPlay.set == 1 || CurrentPlay.cards == 0) && (CurrentPlay.highCard < selected.get(4).number || (CurrentPlay.highCard == selected.get(4).number && CurrentPlay.suit < selected.get(4).suit))){
					validPlay = true;
					set = 1;
					sigCard = selected.get(4).number;
					cards = count;
					suit = selected.get(4).suit;
				}else if(selected.get(0).number == 1 && selected.get(4).number == 13){
					if(CurrentPlay.set == 1 || CurrentPlay.cards == 0 && CurrentPlay.highCard > 1){
						validPlay = true;
						set = 1;
						sigCard = 1;
						cards = count;
						suit = selected.get(0).suit;
					}else if(CurrentPlay.set == 1 || CurrentPlay.cards == 0 && CurrentPlay.highCard == 1 && CurrentPlay.suit < selected.get(0).suit){
						validPlay = true;
						set = 1;
						sigCard = 1;
						cards = count;
						suit = selected.get(0).suit;
					}else{
						validPlay = false;
					}
						
				}else{
					validPlay = false;
				}
				//System.out.println("Current Play: Strait " + number[0] + " to " + number[4]);
			}else if (flush){
				if(selected.get(0).number != 1 && selected.get(0).number != 2 && selected.get(1).number != 2){
					//System.out.println("Current Play: Flush " + number[4] + " High");
					if(CurrentPlay.set < 2 || CurrentPlay.cards == 0 || (CurrentPlay.set == 2 && CurrentPlay.suit < selected.get(0).suit)
							|| (CurrentPlay.set == 2 && CurrentPlay.suit == selected.get(0).suit && CurrentPlay.highCard < selected.get(4).number)){
						validPlay = true;
						set = 2;
						sigCard = selected.get(4).number;
						cards = count;
						suit = selected.get(4).suit;
					}else{
						validPlay = false;
					}
				}else{
					if(selected.get(0).number == 2 || selected.get(1).number == 2){
						if(CurrentPlay.set < 2 || CurrentPlay.cards == 0 || (CurrentPlay.set == 2 && CurrentPlay.suit <= selected.get(0).suit)){
							validPlay = true;
							set = 2;
							sigCard = 2;
							cards = count;
							suit = selected.get(0).suit;
						}else{
							validPlay = false;
						}
					}else if(selected.get(0).number == 1){
						if(CurrentPlay.set < 2 || CurrentPlay.cards == 0 || (CurrentPlay.set == 2 && CurrentPlay.suit < selected.get(0).suit) || (CurrentPlay.set == 2 && CurrentPlay.suit == selected.get(0).suit && CurrentPlay.highCard != 2)){
							validPlay = true;
							set = 2;
							sigCard = 1;
							cards = count;
							suit = selected.get(0).suit;
						}else{
							validPlay = false;
						}
						//System.out.println("Current Play: Flush Ace High");
					}
				}
			}			
		}else{
			validPlay = false;
			//System.out.println("Invalid Combination");
		}
	}
	
	public void update(){
		for(Iterator<Card> i = hand.iterator(); i.hasNext();){
			i.next().update();
		}
		currentPlay();
	}
	
	public void render(SpriteBatch sb){
		//if(playerID == 0){
			int j = 0;
			for (Iterator<Card> i = hand.iterator(); i.hasNext();) {				
				Card c = i.next();
				c.setPosition(80*j + 150f, c.sprite.getY());
				j++;
			}
			for (Card card : hand) {
				card.render(sb);
			}

		//}
	}
}
