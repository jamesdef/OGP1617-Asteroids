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
		
		if(enoughTimeLeft()){
			this.getProgram().subtractTimeLeft(getActionTime());
		//	executeActionStatement(getProgram());
			this.getProgram().getBookmark().addPerformedAction();
		}
		else {
			this.getProgram().setRunning(false);	
		}
	}
	
	public boolean enoughTimeLeft(){
		double timeLeft = this.getProgram().getTimeLeft();
		return (timeLeft >= getActionTime());
	}
	
	
	public double getActionTime(){
		return actionTime;
	}
	
	public abstract void executeAction(Program program) throws IllegalPositionException, IllegalRadiusException, IllegalShipException;
}