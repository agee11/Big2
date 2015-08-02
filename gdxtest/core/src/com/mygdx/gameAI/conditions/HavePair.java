package com.mygdx.gameAI.conditions;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.gameAI.ICondition;

public class HavePair implements ICondition{

	private ComputerPlayer player;
	
	public HavePair(ComputerPlayer player){
		this.player = player;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return player.pairFound();
	}

}
