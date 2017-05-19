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
	
	@Override
	public void setProgram(Program program){
		this.program = program;
		for (Statement statement : statementsList) {
			statement.setProgram(program);
		}
		
	}

	
	//RUN
	@Override
	public void run(List<Expression> arguments) {
		for (Statement statement : statementsList) {
			statement.run(arguments);
		}
	}
	

	public Integer nested_nb_actionStatements(){
		int nb =0;
		
		for (Statement statement : this.getStatementsList()) {
			if(statement instanceof ActionStatement)
				nb++;
			if(statement instanceof BlockStatement)
				
				nb += ((BlockStatement) statement).nested_nb_actionStatements();
		}
		
		return nb;
	}
	
	public void navigateToAction(Bookmark bookmark, List<Expression> arguments){
			
		int nbActionsPerformed = bookmark.getNbActionsPerformed();
		int actionStatementCounter = 0;
		boolean runFromHere = false;
		
			for (Statement statement : this.getStatementsList()) {
				
				if(runFromHere){
					statement.run(arguments);
				} else {
					if(statement instanceof ActionStatement)
						actionStatementCounter++;
					
					if(actionStatementCounter > nbActionsPerformed){
						runFromHere = true;
						statement.run(arguments);
					}
			}
		}
		
	}
	

}
