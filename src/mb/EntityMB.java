package mb;

import java.awt.Rectangle;
import java.util.IllegalFormatCodePointException;

import javax.swing.JOptionPane;

import abstracts.Entity;
import aplicacao.Game;
import entidades.Bullets;
import entidades.BulletsShoot;
import entidades.Enemy;
import entidades.Lifepack;
import entidades.Player;
import entidades.Weapon;
import enums.StatusPersonagemEnum;
import exceptions.ValorNegativoException;
import exceptions.VidaMaximaException;
import ferramentas.Constantes;
import world.Gate;
import world.Mapa;
import world.WallTile;

/**
 * @author ThixVinix
 */
public class EntityMB {

	/**
	 * VERIFICA SE UMA ENTIDADE ESTA COLIDINDO COM OUTRA ENTIDADE ATRAVES DE SUAS
	 * MASCARAS (A ORDEM DOS PARAMETROS NAO INTERFERE NA VERIFICACAO).
	 */
	public boolean isCollidingWithAnotherEntity(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getIntegerX() + e1.getMaskX(), e1.getIntegerY() + e1.getMaskY(),
				e1.getMaskWidth(), e1.getMaskHeight());
		Rectangle e2Mask = new Rectangle(e2.getIntegerX() + e2.getMaskX(), e2.getIntegerY() + e2.getMaskY(),
				e2.getMaskWidth(), e2.getMaskHeight());

		return e1Mask.intersects(e2Mask);
	}

	public boolean isCollidingWithWallTiles(Entity entity) {

		Rectangle wallTileMask = null;

		Rectangle entityMask = new Rectangle(entity.getIntegerX() + entity.getMaskX(),
				entity.getIntegerY() + entity.getMaskY(), entity.getMaskWidth(), entity.getMaskHeight());

		for (int i = 0; i < Game.wallTiles.size(); i++) {
			WallTile wallTile = Game.wallTiles.get(i);
			wallTileMask = new Rectangle(wallTile.getX(), wallTile.getY(), wallTile.getWidth(), wallTile.getHeight());

			if (entityMask.intersects(wallTileMask)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCollidingWithGatesClosed(Entity entity) {

		Rectangle gateTileMask = null;
		
		Rectangle entityMask = verificarProximaPosicaoEntidade(entity);
		
		for (int i = 0; i < Game.gates.size(); i++) {
			Gate gateTile = Game.gates.get(i);
			
			gateTileMask = new Rectangle(gateTile.getX(), gateTile.getY(), gateTile.getWidth(), gateTile.getHeight());

			if (entityMask.intersects(gateTileMask)) {
				if (gateTile.isClose()) {
//					entity.setDoubleY(entity.getDoubleY() + 1);
					return true;
				} 
				
			}
		}
		return false;
	}
	
	public Rectangle verificarProximaPosicaoEntidade(Entity entity) {
		Rectangle entityMask = null;
		int rightDir = entity.getIntegerX() + entity.getMaskX();
		int leftDir = entity.getIntegerX() - entity.getMaskX();
		int upDir = entity.getIntegerY() - entity.getMaskY();
		int downDir = entity.getIntegerY() + entity.getMaskY();
		
		int nextHorizontalPosition;
		int nextVerticalPosition;
		
//		if (entity.isRight()) {
//			 rightDir = entity.getIntegerX() + entity.getMaskX() + 2;
//		}
//		
//		if (entity.isLeft()) {
//			leftDir = entity.getIntegerX() + entity.getMaskX() - 2;
//		}
//		
//		if (entity.isUp()) {
//			upDir = entity.getIntegerY() + entity.getMaskY() - 2;
//		}
//		
//		if (entity.isDown()) {
//			downDir = entity.getIntegerY() + entity.getMaskY() + 2;
//		}
		
		if (entity.isRight()) {
			entityMask = new Rectangle(entity.getIntegerX() + entity.getMaskX() + 3,
					entity.getIntegerY() + entity.getMaskY(), entity.getMaskWidth(), entity.getMaskHeight());
		} else if (entity.isLeft()) {
			 entityMask = new Rectangle(entity.getIntegerX() + entity.getMaskX() - 3,
					entity.getIntegerY() + entity.getMaskY(), entity.getMaskWidth(), entity.getMaskHeight());
		} 
		
		if (entity.isUp()) {
			entityMask = new Rectangle(entity.getIntegerX() + entity.getMaskX(),
					entity.getIntegerY() + entity.getMaskY() - 3, entity.getMaskWidth(), entity.getMaskHeight());
		} else if (entity.isDown()) {
			entityMask = new Rectangle(entity.getIntegerX() + entity.getMaskX(),
					entity.getIntegerY() + entity.getMaskY() + 3, entity.getMaskWidth(), entity.getMaskHeight());
		}
		
	
		return entityMask;
	}

	public void aumentarVida(Entity entity, double newVida) {

		try {
			if (newVida < 0.0) {
				throw new ValorNegativoException(newVida);
			}
		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da vida passado pelo parâmetro não deve ser negativo",
					"ValorNegativoException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);

		}

		entity.setVida(entity.getVida() + newVida);

		if (entity.getVida() > entity.getVidaMax()) {
			entity.setVida(entity.getVidaMax());
		}

		verificarStatusPersonagemEnum(entity);
	}

	public void diminuirVida(Entity entity, double newVida) {

		try {

			if (newVida < 0.0) {
				throw new ValorNegativoException(newVida);
			}

			entity.setVida(entity.getVida() - newVida);
			verificarStatusPersonagemEnum(entity);

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da vida passado pelo parâmetro não deve ser negativo",
					"ValorNegativoException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void alterarVidaMax(Entity entity, double newVidaMax) {

		try {
			if (newVidaMax == 0.0) {
				throw new VidaMaximaException(newVidaMax);
			}

			if (newVidaMax < 0.0) {
				throw new ValorNegativoException(newVidaMax);
			}

			if (entity.getVidaMax() < entity.getVida()) {
				entity.setVidaMax(newVidaMax);
				entity.setVida(entity.getVidaMax());
			} else {
				entity.setVida(newVidaMax);
			}

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da 'vidaMax' passado pelo parâmetro não deve ser negativo",
					"ValorNegativoException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		} catch (VidaMaximaException e) {
			JOptionPane.showMessageDialog(null, "O valor da 'vidaMax' passado pelo parâmetro não deve ser '0'",
					"VidaMaximaException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void checkCollisionLifePack(Entity entity) {

		if (entity instanceof Player) {
			prepararInteracaoLifepackComPlayer((Player) entity);

		}

	}

	public void checkCollisionBullets(Entity entity) {
		if (entity instanceof Player) {
			prepararInteracaoBulletsComPlayer((Player) entity);
		}
			
	}

	public void checkCollisionRifle(Entity entity) {
		if (entity instanceof Player) {
			prepararInteracaoRifleComPlayer((Player) entity);

		}
	}

	public void checkCollisionGates(Entity entity) {
		if (entity instanceof Player) {
			prepararInteracaoGatesComPlayer((Player) entity);
		}
	}

	public void checkCollisionWithAnotherEntity(Entity entity) {

		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (isCollidingWithAnotherEntity(atual, entity)) {
				if (entity instanceof BulletsShoot && atual instanceof Enemy) {
					prepararInteracaoBulletsShootComEnemy((BulletsShoot) entity, (Enemy) atual);
				}
			}
		}
	}

	public void alterarVelocidade(Entity entity, double newSpeed) {
		try {
			if (newSpeed < 0.0) {
				throw new ValorNegativoException(newSpeed);
			}

			entity.setDoubleSpeed(newSpeed);

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da 'velocidade' passado pelo parâmetro não deve ser negativo",
					"VidaMaximaException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void alterarAtaque(Entity entity, int newAtaque) {
		try {
			if (newAtaque < 0.0) {
				throw new ValorNegativoException(newAtaque);
			}

			entity.setAtaque(newAtaque);

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor do 'ataque' passado pelo parâmetro não deve ser negativo",
					"VidaMaximaException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void alterarDefesa(Entity entity, int newDefesa) {
		try {
			if (newDefesa < 0.0) {
				throw new ValorNegativoException(newDefesa);
			}

			entity.setDefesa(newDefesa);

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da 'defesa' passado pelo parâmetro não deve ser negativo",
					"VidaMaximaException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void atacar(Entity atacante, Entity defensor) {

		if (defensor.getVida() > 0) {
			if (!desviar(atacante, defensor)) {
				if (atacante.getAtaque() > defensor.getDefesa()) {
					atacante.setAttacking(true);
					defensor.setAttacked(true);
					int danoDissipado = dissiparDano(atacante, defensor);
					diminuirVida(defensor, danoDissipado);
					verificarStatusPersonagemEnum(defensor);
				} else {
					System.out.println(defensor.getNome() + " bloqueou o ataque!");
				}

			} else {
				System.out.println(defensor.getNome() + " desviou do ataque!");
			}
		}

	}

	private boolean desviar(Entity atacante, Entity defensor) {
		if (Game.random.nextInt(atacante.getChanceAcerto()) <= defensor.getEsquiva()) {
			return true;
		}
		return false;
	}

	private int dissiparDano(Entity atacante, Entity defensor) {
		int danoDissipado = atacante.getAtaque() - defensor.getDefesa();

		return danoDissipado;
	}

	private void verificarStatusPersonagemEnum(Entity entity) {

		if (entity.getVida() > 40) {
			entity.setStatus(StatusPersonagemEnum.VIVO);
		} else if (entity.getVida() > 0 && entity.getVida() <= 40) {
			entity.setStatus(StatusPersonagemEnum.FERIDO);
		} else {
			entity.setStatus(StatusPersonagemEnum.MORTO);
		}

	}

	private void prepararInteracaoLifepackComPlayer(Player player) {

		for (int i = 0; i < Game.lifePacks.size(); i++) {

			Lifepack atual = Game.lifePacks.get(i);

			if (isCollidingWithAnotherEntity(player, atual)) {

				if (player.getVida() >= Constantes.VIDA_MAXIMA_PLAYER) {
					continue;
				} else {
					player.setVida(player.getVida() + Constantes.CURA_LIFEPACK);

					if (player.getVida() > Constantes.VIDA_MAXIMA_PLAYER) {
						player.setVida(Constantes.VIDA_MAXIMA_PLAYER);
					}

					Game.lifePacks.remove(atual);
					Game.entities.remove(atual);
					verificarStatusPersonagemEnum(player);
				}
			}
		}
	}

	private void prepararInteracaoBulletsComPlayer(Player player) {

		for (int i = 0; i < Game.bullets.size(); i++) {

			Bullets atual = Game.bullets.get(i);

			if (isCollidingWithAnotherEntity(player, atual)) {
				if (player.getBulletsPistol() < Constantes.MUNICAO_MAXIMA_PLAYER) {
					player.setBulletsPistol(player.getBulletsPistol() + Constantes.QUANTIDADE_MUNICAO);
					if (player.getBulletsPistol() >= Constantes.MUNICAO_MAXIMA_PLAYER) {
						player.setBulletsPistol(Constantes.MUNICAO_MAXIMA_PLAYER);
					}
					Game.bullets.remove(atual);
					Game.entities.remove(atual);
				}

			}
		}
	}

	private void prepararInteracaoRifleComPlayer(Player player) {
		for (int i = 0; i < Game.rifles.size(); i++) {
			Weapon atual = Game.rifles.get(i);

			if (isCollidingWithAnotherEntity(player, atual)) {
				player.setArmed(true);
				Game.rifles.remove(atual);
				Game.entities.remove(atual);
			}

		}
	}

	private void prepararInteracaoBulletsShootComEnemy(BulletsShoot bulletShoot, Enemy enemy) {

		double vidaInimigoAntes = enemy.getVida();
		atacar(bulletShoot, enemy);
		double vidaInimigoDepois = enemy.getVida();

		if (vidaInimigoAntes > vidaInimigoDepois) {
			Game.bulletsShoot.remove(bulletShoot);
			if (enemy.getStatus() == StatusPersonagemEnum.MORTO) {
				Game.enemies.remove(enemy);
				Game.entities.remove(enemy);
			}

		}

	}

	private void prepararInteracaoGatesComPlayer(Player player) {
		Rectangle gateTileMask = null;

		Rectangle playerMask = new Rectangle(player.getIntegerX() + player.getMaskX(),
				player.getIntegerY() + player.getMaskY(), player.getMaskWidth(), player.getMaskHeight());

		for (int i = 0; i < Game.gates.size(); i++) {
			Gate gateTile = Game.gates.get(i);
			if (gateTile.isClose()) {
				gateTileMask = new Rectangle(gateTile.getX(), gateTile.getY(), gateTile.getWidth(),
						gateTile.getHeight());
			} else {
			gateTileMask = new Rectangle(gateTile.getX(), gateTile.getY(), gateTile.getWidth(),
					gateTile.getHeight() / 3);
			}

			
			if (playerMask.intersects(gateTileMask)) {

				if (Game.mapas.containsKey(gateTile.getId())) {
					Game.mudarCenario(gateTile);
				} else {
					Game.instanciarNovoCenario(gateTile);
				}
			}

			
		}
	}
}
