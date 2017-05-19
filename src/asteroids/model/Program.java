package asteroids.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.programs.Bookmark;
import asteroids.programs.Expression;
import asteroids.programs.Function;
import asteroids.programs.Statement;



public class Program {


	protected Program(List<Function> functionsList, Statement mainStatement){
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
		addTime(dt);
	/*	try {
			main.runAt(this.getBookmark());
	//		return getPrintOuts();
		} catch () {
	//		return null;
		}		*/
		return null;
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
	
	public boolean running(){
		return this.running;
	}
	
	
	//BOOKMARK
	
	private Bookmark bookmark;
	
	public void setBookmark(Bookmark bookmark){
		this.bookmark = bookmark;
	}
	
	public Bookmark getBookmark(){
		return this.bookmark;
	}
	
	public void updateBookmark(Bookmark bookmark){
		
	}
	
	
	//MAIN STATEMENT
	
	Statement mainStatement;
	
	public void setMainStatement(Statement mainStatement){
		this.mainStatement = mainStatement;
		this.mainStatement.setProgram(this);
	}
	
	
	//FUNCTIONS
	
	private Map<String, Function> functions = new HashMap<String, Function>();
	
	protected void setFunctions(List<Function> functions) {
		for (Function function : functions) {
			this.functions.put(function.getName(), function);
			function.setProgram(this);
		}
	}
	
	protected Map<String, Function> getFunctions() {
		return functions;
	}
	
	
	
	//VARIABLES
	
	private Map<String, Expression> variables = new HashMap<String, Expression>();

	
	protected Map<String, Expression> getVariables() {
		return variables;
	}
	
	public void addVariable(String name, Expression expression) {
		// TODO Auto-generated method stub
		this.variables.put(name, expression);

		
	}
	
	
	//PRINTS
	
	private List<Object> prints = new ArrayList<>();

	protected void addPrint(Object object) {
		prints.add(object);
	}
	
	protected List<Object> getPrints(){
		return this.prints;
	}
	


	//SHIP
	Ship ship;
	
	public Ship getShip(){
		return this.ship;
	}

	public void subtractTimeLeft(double dt) {
		// TODO Auto-generated method stub
		
		this.timeLeft-=dt;
		
	}

	
	
	
	
}
