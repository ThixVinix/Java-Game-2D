package interfaces;

import entidades.Enemy;

public interface CombatePlayer {

	public abstract void atacarInimigo(Enemy enemy);
	
	public abstract void desviarInimigo();
}
