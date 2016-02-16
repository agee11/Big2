package com.mygdx.gameAI;

import com.mygdx.game.GameManager;
import com.mygdx.gameAI.decisionTrees.IBinaryNode;

public class PassTurn implements IAction, IBinaryNode{

	private GameManager game;
	public PassTurn(){
		//this.game = game;
	}
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		//game.passTurn();
		System.out.println("Pass Turn called.");
	}

	@Override
	public IAction makeDecision() {
		// TODO Auto-generated method stub
		return this;
	}

}
