package com.mygdx.gameAI.conditions;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.gameAI.ICondition;

public class GreaterTripleFound implements ICondition{

	private ComputerPlayer player;
	
	public GreaterTripleFound(ComputerPlayer player){
		this.player = player;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return player.greaterTripleFound();
	}

}
