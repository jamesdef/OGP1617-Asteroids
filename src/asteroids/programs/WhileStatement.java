package asteroids.programs;

public class WhileStatement extends Statement {

	public WhileStatement(BooleanExpression condition, Statement bodyStatement){
		this.setCondition(condition);
		this.setBodyStatement(bodyStatement);
	}
	
	@Override
	public void run() {
		removeConditionError();
		while ((boolean) this.getConditionResult() && !conditionError()) {
			try {
				this.getBodyStatement().setProgram(getProgram());
				this.getBodyStatement().run();
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
	
	private void removeConditionError(){
		this.conditionError=false;
	}
	

	
	//CONDITION
	private BooleanExpression condition;
	
	public void setCondition(BooleanExpression condition){
		this.condition = condition;
		((Expression) condition).setProgram(getProgram());
	}
	
	public BooleanExpression getCondition(){
		return this.condition;
	}
	
	private Boolean getConditionResult(){
		((Expression) this.getCondition()).setProgram(getProgram());

		boolean conditionResult = (boolean) ((Expression) this.getCondition()).getResult();
		System.out.println("WHILESTATEMENT > conditionresult: " + conditionResult);

		return conditionResult;
		
	}
	
	@Override
	public boolean navigateToAction(Bookmark bookmark){
		System.out.println("WHILESTATEMENT > NAVIGATETOACTION");

		removeConditionError();
		
		boolean foundBookmark = false;
		while ((boolean) this.getConditionResult() && !conditionError()) {

			try {
				this.getBodyStatement().setProgram(getProgram());

				if(foundBookmark){
					System.out.println("WHILESTATEMENT > FOUNDBOOKMARK, running ");

					this.getBodyStatement().run();
				} else {
					System.out.println("WHILESTATEMENT > searching foundBookmark ");

					foundBookmark = this.getBodyStatement().navigateToAction(bookmark);

				}
			} catch (IllegalAccessError error) {
				setConditionError();
			}
		}
		
		return foundBookmark;
		
	}
	
	
}
