package asteroids.model;
import java.awt.geom.IllegalPathStateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.programs.ActionStatement;
import asteroids.programs.Bookmark;
import asteroids.programs.Expression;
import asteroids.programs.Function;
import asteroids.programs.InsufficientRuntimeException;
import asteroids.programs.Statement;



public class Program {
	public Program(List<Function> functionsList, Statement mainStatement){
		System.out.println("PROGRAM CONSTRUCTOR");
		this.setFunctions(functionsList);
		this.setMainStatement(mainStatement);
	}

	
	//RUN
	private boolean running = false;	
	private double timeLeft = 0;
	
	public double getTimeLeft(){
		return timeLeft;
	}

	private void addTime(double dt){
		this.timeLeft += dt;
	}
	
	
	public List<Object> run(double dt){
		System.out.println("RUN PROGRAM with dt =  " + dt + "prev gtl is: "+getTimeLeft()+" ---------------------");
		addTime(dt);
		try {
			System.out.println("RUN WITH BOOKMARK: " + this.getBookmark());		
			this.getMainStatement().setProgram(this);
			this.getMainStatement().runAt(this.getBookmark(), null);
			
			System.out.println("RETURNING GETPRINTS: " + getPrints());
			return getPrints();
			
		} catch (IllegalPathStateException e) {
			System.out.println("RUN PROGRAM > GOT ILLEGALPATHSTATEEXCEPTION, returning null");
			return null;
		}	
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	public boolean running(){
		return this.running;
	}
	
	
	//BOOKMARK
	
	private Bookmark bookmark = new Bookmark();
	
	public void setBookmark(Bookmark bookmark){
		this.bookmark = bookmark;
	}
	
	public Bookmark getBookmark(){
		return this.bookmark;
	}
	
	public void updateBookmark(ActionStatement actionStatement){
		this.bookmark.setFailedAction(actionStatement);
	}
	
	
	//MAIN STATEMENT
	
	Statement mainStatement;
	
	public void setMainStatement(Statement mainStatement){
		this.mainStatement = mainStatement;
		this.mainStatement.setProgram(this);
	}
	
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	//FUNCTIONS
	
	private Map<String, Function> functions = new HashMap<String, Function>();
	
	protected void setFunctions(List<Function> functions) {
		for (Function function : functions) {
			this.functions.put(function.getName(), function);
			function.setProgram(this);
		}
	}
	
	public Map<String, Function> getFunctions() {
		return functions;
	}
	
	
	
	//VARIABLES
	
	private Map<String, Expression> variables = new HashMap<String, Expression>();

	
	public Map<String, Expression> getVariables() {
		return variables;
	}
	
	public void addVariable(String name, Expression expression) {
		// TODO Auto-generated method stub
		this.variables.put(name, expression);

		
	}
	
	
	//PRINTS
	
	private List<Object> prints = new ArrayList<>();
	
	public void addToPrints(Object printResult) {
		// TODO Auto-generated method stub
		this.prints.add(printResult);
		
	}
	
	protected List<Object> getPrints(){
		return this.prints;
	}
	


	//SHIP
	Ship ship;
	
	public Ship getShip(){
		return this.ship;
	}

	public void setShip(Ship ship) {
		System.out.println("SHIP SET");
		this.ship = ship;
	}

	public void subtractTimeLeft(double dt) {
		// TODO Auto-generated method stub
		this.timeLeft -= dt;
		
	}

	
	
	
	
	
	
}
