package com.mygdx.gameAI.conditions;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.entity.Player;
import com.mygdx.gameAI.ICondition;

public class HavePair implements ICondition{

	private ComputerPlayer player;
	
	public HavePair(ComputerPlayer player){
		this.player = player;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		System.out.println("Have pair called");
		return player.pairFound();
	}

}
