package com.mygdx.gameAI.conditions;

import com.mygdx.game.CurrentPlay;
import com.mygdx.gameAI.ICondition;

public class CurrentPlaySize implements ICondition{

	private int limit;
	
	public CurrentPlaySize(int limit){
		this.limit = limit;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return CurrentPlay.cards <= limit;
	}

}
