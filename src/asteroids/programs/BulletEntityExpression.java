package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Bullet;

public class BulletEntityExpression extends EntityExpression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {

		Set<? extends Bullet> bullets = getWorld().getAllBullets();

		bullets.removeIf(bullet -> !isFiredFromShip((Bullet) bullet));
		
		return bullets.stream().findAny().orElse(null);
	}


	private boolean isFiredFromShip(Bullet bullet) {
		return (this.getShip().equals(bullet.getSource()));
	}

}