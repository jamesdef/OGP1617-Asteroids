package asteroids.programs;

import java.util.List;

public class IfElseStatement extends Statement{

	public IfElseStatement(BooleanInterface condition, Statement ifBody, Statement elseBody) {
		System.out.println("IFELSESTATEMENT " + ifBody);

		this.setCondition(condition);
		this.setThenStatement(ifBody);
		this.setElseStatement(elseBody);
	}
	
	private Boolean getConditionResult(){
		((Expression) this.getCondition()).setProgram(getProgram());
		boolean conditionResult = (boolean) ((Expression) this.getCondition()).getResult(null, null);
		return conditionResult;
		
	}

	@Override
	public void run(List<Expression> arguments) {
		System.out.println("IFELSESTATEMENT > RUN");
		if(this.getConditionResult()){
			this.getThenStatement().setProgram(this.getProgram());
			this.getThenStatement().run(arguments);
		} else {
			if(this.getElseStatement() != null){
				this.getElseStatement().setProgram(this.getProgram());
				this.getElseStatement().run(arguments);
			}
		}
	}
	
	//CONDITION
	private BooleanInterface condition;
	
	public void setCondition(BooleanInterface condition){
		this.condition = condition;
		((Expression) condition).setProgram(getProgram());
	}
	
	public BooleanInterface getCondition(){
		return this.condition;
	}
	
	//THEN
	private Statement thenStatement;
	
	public void setThenStatement(Statement thenStatement){
		this.thenStatement= thenStatement;
		this.getThenStatement().setProgram(getProgram());

	}
	
	public Statement getThenStatement(){
		return this.thenStatement;
	}
	
	//ELSE STATEMENT
	private Statement elseStatement;
	
	public void setElseStatement(Statement elseStatement){
		this.elseStatement= elseStatement;
		if(elseStatement != null){
			this.getElseStatement().setProgram(getProgram());

		}

	}
	
	public Statement getElseStatement(){
		return this.elseStatement;
	}
	
	@Override
	public boolean navigateToAction(Bookmark bookmark, List<Expression> arguments){
		if(this.getConditionResult()){
			System.out.println("IFELSESTATEMENT > NAVIGATETOACTION thenStatement");
			return this.getThenStatement().navigateToAction(bookmark, arguments);
		} else {
			System.out.println("IFELSESTATEMENT > NAVIGATETOACTION elseStatement");
			return this.getElseStatement().navigateToAction(bookmark, arguments);
		}
		
	}
}
