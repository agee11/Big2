package com.mygdx.gameAI;

import com.mygdx.entity.ComputerPlayer;
import com.mygdx.gameAI.decisionTrees.IBinaryNode;

public class PlayLowestPair implements IAction, IBinaryNode{

	private ComputerPlayer cpu;
	public PlayLowestPair(ComputerPlayer p){
		cpu = p;
	}
	
	@Override
	public IAction makeDecision() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void doAction() {
		cpu.playLowPair();
	}
	
	

}
