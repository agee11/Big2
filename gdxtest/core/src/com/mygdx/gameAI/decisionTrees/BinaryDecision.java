package com.mygdx.gameAI.decisionTrees;

import com.mygdx.gameAI.IAction;
import com.mygdx.gameAI.ICondition;

public class BinaryDecision implements IBinaryDecision, IBinaryNode{

	private IBinaryNode trueBranch;
	private IBinaryNode falseBranch;
	private ICondition condition;
	
	@Override
	public IAction makeDecision() {
		// TODO Auto-generated method stub
		if(condition.test()){
			return trueBranch.makeDecision();
		}else{
			return falseBranch.makeDecision();
		}
	}

	@Override
	public void setTrueBranch(IBinaryNode node) {
		// TODO Auto-generated method stub
		trueBranch = node;
	}

	@Override
	public IBinaryNode getTrueBranch() {
		// TODO Auto-generated method stub
		return trueBranch;
	}

	@Override
	public void setFalseBranch(IBinaryNode node) {
		// TODO Auto-generated method stub
		falseBranch = node;
	}

	@Override
	public IBinaryNode getFalseBranch() {
		// TODO Auto-generated method stub
		return falseBranch;
	}

	@Override
	public void setCondition(ICondition condition) {
		// TODO Auto-generated method stub
		this.condition = condition;
	}

}
