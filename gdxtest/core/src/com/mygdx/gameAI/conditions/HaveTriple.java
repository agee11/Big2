package com.mygdx.gameAI.conditions;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.gameAI.ICondition;

public class HaveTriple implements ICondition{

	private ComputerPlayer player;
	public HaveTriple(ComputerPlayer player){
		this.player = player;
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return player.tripleFound();
	}

}
