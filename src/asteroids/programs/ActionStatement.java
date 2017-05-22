package asteroids.programs;

import java.awt.geom.IllegalPathStateException;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.parse.ANTLRParser.sync_return;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import asteroids.model.Program;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {
	
	
	public final static double actionTime = 0.2;
	
	
	@Override
	public void run(List<Expression> arguments){
		System.out.println("ACTIONSTATEMENT > RUNNING TRY " + this);
		
		if(enoughTimeLeft()){
			this.getProgram().subtractTimeLeft(getActionTime());
			try {
				this.executeAction(getProgram());
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalRadiusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalShipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("AS > NOT ENOUGH TIME LEFT, SETTING THIS AS failedA " + this);
			this.getProgram().updateBookmark(this);
			throw new IllegalPathStateException();
		}
	}
	
	public boolean enoughTimeLeft(){
		double timeLeft = this.getProgram().getTimeLeft();
		return !(timeLeft < getActionTime());
	}
	
	
	public double getActionTime(){
		return actionTime;
	}
	
	public abstract void executeAction(Program program) throws IllegalPositionException, IllegalRadiusException, IllegalShipException;
}