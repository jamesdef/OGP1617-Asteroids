package asteroids.programs;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;

public class BlockStatement extends Statement {
	
	public BlockStatement(List<Statement> statementsList){
		this.setStatementsList(statementsList);
	}
	
	
	//SETSTATEMENTSLIST
	
	private List<Statement> statementsList = new ArrayList<Statement>();
	
	public void setStatementsList(List<Statement> statementsList){
		this.statementsList = statementsList;
	}
	
	public List<Statement> getStatementsList(){
		return this.statementsList;
	}
	
	protected Program program;

	
	//RUN
	@Override
	public void run() {
		System.out.println("BLOCKSTATEMENT > RUN");

		for (Statement statement : statementsList) {
			System.out.println("BLOCKSTATEMENT > RUN STATEMENT : " +statement);

			statement.setProgram(this.getProgram());
			statement.run();
		}
	}
	
	
	public boolean navigateToAction(Bookmark bookmark){
		System.out.println("BLOCKSTATEMENT > NAVIGATETOACTION");
		ActionStatement failedAction = bookmark.getFailedAction();
		System.out.println("BLOCKSTATEMENT > FAILEDACTION IS " + failedAction);

		boolean runFromHere = false;
		
		for (Statement statement : this.getStatementsList()) {
			System.out.println("BLOCKSTATEMENT > RUNFROMHERE IS: " + runFromHere);

			if(runFromHere){
				System.out.println("BLOCKSTATEMENT > RUNNING ");

				statement.setProgram(this.getProgram());
				statement.run();
			} else {
				
				if(statement instanceof ActionStatement){
					System.out.println("BLOCKSTATEMENT > CHECKING IF STATEMENT " + statement + " equals failedAction");
					
					if(statement.equals(failedAction)){
						System.out.println("BLOCKSTATEMENT > found failedAction: RUNNING FROM HERE: " + statement);
						
						runFromHere = true;

						statement.setProgram(this.getProgram());
						statement.run();
						
					} else {
						System.out.println("BLOCKSTATEMENT > this action is not failedAction");
					}
						
				}
				
				if(statement instanceof IfElseStatement){
					System.out.println("BLOCKSTATEMENT > FOUND IFELSESTATEMENT IN BLOCK, DIGGING INTO IT " + statement);

					boolean inStatement = ((IfElseStatement) statement).navigateToAction(bookmark);
					
					if(inStatement){
						System.out.println("BLOCKSTATEMENT > FOUND failedAction in this IFELSESTATEMENT " + statement);
						runFromHere = true;
					}	
				}
				
				if(statement instanceof WhileStatement){
					System.out.println("BLOCKSTATEMENT > FOUND WHILESTATEMENT IN BLOCK, DIGGING INTO IT " + statement);

					boolean inStatement = ((WhileStatement) statement).navigateToAction(bookmark);
					
					if(inStatement){
						System.out.println("BLOCKSTATEMENT > FOUND failedAction in this WhileStatement " + statement);
						runFromHere = true;
					}	
				}
			}
			//end for below this		
			}
	
		System.out.println("BLOCKSTATEMENT >  END OF FOR LOOP");

		if(runFromHere)
			return true;
		return false;
	}

}
