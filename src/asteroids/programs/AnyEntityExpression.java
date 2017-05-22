package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;

public class AnyEntityExpression extends EntityExpression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		
		Set<Entity> entities = this.getWorld().getAllEntities();

		return entities.stream().findAny().orElse(null);
	}

}
