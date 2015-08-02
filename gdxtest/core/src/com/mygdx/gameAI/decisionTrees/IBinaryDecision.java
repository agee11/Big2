package com.mygdx.gameAI.decisionTrees;

import com.mygdx.gameAI.ICondition;


public interface IBinaryDecision {

	/**
	 * Setter for the true branch.
	 * @param node The node associated with the true branch of the decision.
	 */
	public void setTrueBranch(IBinaryNode node);
	
	public IBinaryNode getTrueBranch();
	
	public void setFalseBranch(IBinaryNode node);
	
	public IBinaryNode getFalseBranch();
	
	public void setCondition(ICondition condition);
	
}
