package com.mygdx.gameAI.conditions;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.gameAI.ICondition;

public class GreaterPairFound implements ICondition{

	private ComputerPlayer player;
	
	public GreaterPairFound(ComputerPlayer player){
		this.player = player;
	}
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		System.out.println("Greater Pair found called");
		return player.greaterPairFound();
	}

}
