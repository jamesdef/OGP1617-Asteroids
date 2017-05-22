package asteroids.programs;

import java.util.HashSet;
import java.util.Set;

public class Bookmark {
	
	private int nb_actions_performed = 0;
	
	
	public Integer getNbActionsPerformed(){
		return this.nb_actions_performed;
	}
	
	public void addPerformedAction(){
		this.nb_actions_performed++;
	}
	
	private Set<ActionStatement> performedActionsList = new HashSet<ActionStatement>();

	public void addAction(ActionStatement actionStatement){
		this.performedActionsList.add(actionStatement);
	}
	
	public Boolean hasBeenDone(ActionStatement actionStatement){
		System.out.println("BOOKMARK > CHECKING IF ACTIONSTATEMENT HAS BEEN DONE: " + actionStatement);
		Boolean done = (this.performedActionsList.contains(actionStatement));
		System.out.println("BOOKMARK > result: " + done);

		return(done);
	}
	
	private ActionStatement failedAction = null;
	
	public void setFailedAction(ActionStatement actionStatement){
		System.out.println("BOOKMARK > SETTING FAILED ACTION: " + actionStatement);

		this.failedAction = actionStatement;
	}
	
	public ActionStatement getFailedAction(){
		return this.failedAction;
	}

}
