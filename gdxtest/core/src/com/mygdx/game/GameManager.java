package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entity.ComputerPlayer;
import com.mygdx.entity.Deck;
import com.mygdx.entity.Player;

public class GameManager {

	private Player[] players;
	private Deck deck;
	private int playerTurn;
	
	public GameManager(){
		players = new Player[4];
		players[0] = new Player(0);
		for(int i = 1; i<4; i++){
			players[i] = new ComputerPlayer(i);
		}
		deck = new Deck(TextureManager.back1);
		deck.shuffle();
		deck.distribute(players);
		for (Player player : players) {
			player.sortHand();
			//System.out.println(player.getCurrentHandSize());
		}
		
		playerTurn = findFirstPlayer(players);
		CurrentPlay.player = playerTurn;
		System.out.println(playerTurn);
	}
	
	public void update(){
		if(!winnerFound()){
			if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
				if(players[playerTurn%4].getValidPlay()){
					players[playerTurn%4].playCards();
					playerTurn++;
				}else{
					System.out.println("This is not a valid play!");
					players[playerTurn%4].clearSelected();
				}

				//System.out.println(CurrentPlay.player);
				//System.out.println(CurrentPlay.suit);
				//System.out.println(CurrentPlay.highCard);
				//System.out.println(CurrentPlay.set);
				
				//if(CurrentPlay.player == playerTurn%4 && players[playerTurn%4].getValidPlay()){
				//	playerTurn++;
				//}
				
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				if(CurrentPlay.firstPlay){
					System.out.println("Can't pass first turn!");
				}else if(CurrentPlay.cards == 0){
					System.out.println("This is a free turn!");
				}else{
					passTurn();
				}
			}  
			
			players[playerTurn%4].update();
		}else{
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
				beginNewRound();
				CurrentPlay.clear();
			}
		}
	}
	
	public void render(SpriteBatch sb){
		players[playerTurn%4].render(sb);
		CurrentPlay.render(sb);
	}
	
	public int lowestHandSize(){
		int temp = 13;
		for (Player player : players) {
			if(player.getCurrentHandSize() < temp){
				temp = player.getCurrentHandSize();
			}
		}
		return temp;
	}
	
	public boolean flushFound(int playerID){
		//players[playerID]
		return false;
	}
	
	public void passTurn(){
		System.out.println("Player " + playerTurn%4 + " Passes");
		playerTurn++;
		if(playerTurn%4 == CurrentPlay.player){
			CurrentPlay.cards = 0;
			CurrentPlay.highCard = 0;
			CurrentPlay.set = 0;
			CurrentPlay.suit = 0;
		}
	}
	
	public void beginNewRound(){
		players = null;
		players = new Player[4];
		for(int i = 0; i<4; i++){
			players[i] = new Player(i);
		}
		deck.dispose();
		deck = null;
		deck = new Deck(TextureManager.back1);
		deck.shuffle();
		deck.distribute(players);
		for (Player player : players) {
			player.sortHand();
		}
		playerTurn = findFirstPlayer(players);
	}
	
	public boolean winnerFound(){
		for (Player player : players) {
			if(player.getCurrentHandSize() <= 0){
				System.out.println("Player " + player.getPlayerID() + " has won the round!");
				return true;
			}
		}		
		return false;
	}
	
	public int findFirstPlayer(Player[] p){
		CurrentPlay.firstPlay = true;
		for (Player player : p) {
			if(player.foundCard(0, 3)){
				return player.getPlayerID();
			}
		}
		return 0;
	}
	
}
