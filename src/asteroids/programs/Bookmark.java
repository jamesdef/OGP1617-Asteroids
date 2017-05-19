package asteroids.programs;

public class Bookmark {
	
	private int nb_actions_performed = 0;
	
	
	public Integer getNbActionsPerformed(){
		return this.nb_actions_performed;
	}
	
	public void addPerformedAction(){
		this.nb_actions_performed++;
	}
	

}
