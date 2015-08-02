package com.mygdx.gameAI.conditions;

import com.mygdx.game.GameManager;
import com.mygdx.gameAI.ICondition;

public class PlayerHandSize implements ICondition{

	private boolean exceedsSize = false;
	
	public PlayerHandSize(GameManager game, int size){
		
		if(game.lowestHandSize() > size){
			exceedsSize = true;
		}else{
			exceedsSize = false;
		}
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return exceedsSize;
	}

}
