package asteroids.programs;

import java.util.List;

public class WhileStatement extends Statement {

	public WhileStatement(BooleanInterface condition, Statement bodyStatement){
		this.setCondition(condition);
		this.setBodyStatement(bodyStatement);
	}
	
	@Override
	public void run(List<Expression> arguments) {		
		while ((boolean) this.getConditionResult() && !conditionError()) {
			try {
				this.getBodyStatement().setProgram(getProgram());
				this.getBodyStatement().run(arguments);
			} catch (IllegalAccessError error) {
				setConditionError();
			}
		}
		
	}


	//STATEMENT
	private Statement bodyStatement;
	
	public void setBodyStatement(Statement bodyStatement){
		this.bodyStatement= bodyStatement;
		this.getBodyStatement().setProgram(getProgram());
	}
	
	public Statement getBodyStatement(){
		return this.bodyStatement;
	}
	
	
	//CONDITIONERROR
	
	private boolean conditionError = false;
	private boolean conditionError() {
		return this.conditionError;
	}
	
	private void setConditionError(){
		this.conditionError=true;
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
	
	private Boolean getConditionResult(){
		((Expression) this.getCondition()).setProgram(getProgram());

		System.out.println("condres prog > RUN" + ((Expression) this.getCondition()).getProgram());

		boolean conditionResult = (boolean) ((Expression) this.getCondition()).getResult(null, null);
		return conditionResult;
		
	}
	
	
	
	
}
