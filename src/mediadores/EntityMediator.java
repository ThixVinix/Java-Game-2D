package mediadores;

import java.awt.Rectangle;

import javax.swing.JOptionPane;

import abstracts.Entity;
import aplicacao.Game;
import entidades.BulletsShoot;
import entidades.Enemy;
import enums.EntityActionEnum;
import enums.StatusPersonagemEnum;
import exceptions.ValorNegativoException;
import interfaces.Mediator;

public class EntityMediator implements Mediator {

	@Override
	public boolean notify(Entity entitySolicitante, EntityActionEnum action) {
		switch (action) {
		case ATACAR:
			checkCollisionWithAnotherEntity(entitySolicitante);
		
			break;
		case DEFENDER:

			break;

		case DESVIAR:

			break;

		case RECEBER_DANO:

			break;

		case ALTERAR_VIDA_MAX:

			break;
		case GANHAR_CHANCE_ACERTO:

			break;

		case GANHAR_VIDA:

			break;
		case PERDER_VIDA:

			break;

		case GANHAR_ATAQUE:

			break;
		case PERDER_ATAQUE:

			break;

		case GANHAR_DEFESA:

			break;

		case PERDER_DEFESA:

			break;

		case PERDER_CHANCE_ACERTO:

			break;
		case GANHAR_VELOCIDADE_MOVIMENTO:

			break;
		case PERDER_VELOCIDADE_MOVIMENTO:

			break;
			
		case CHECAR_COLISAO:
			if (entitySolicitante instanceof Enemy) 
				return isCollidingWithAnotherEntity(entitySolicitante, Game.player);
							
			break;
		default:
			break;
		}
		return false;
	}

	private static boolean checkCollisionWithAnotherEntity(Entity entity) {

		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (isCollidingWithAnotherEntity(atual, entity)) {
				if (entity instanceof BulletsShoot && atual instanceof Enemy) {
					entity.setOtherEntity(atual);
					return true;
//					prepararInteracaoBulletsShootComEnemy((BulletsShoot) entity, (Enemy) atual);
				}
			}
		}
		return false;
	}
	
	private static boolean isCollidingWithAnotherEntity(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getIntegerX() + e1.getMaskX(), e1.getIntegerY() + e1.getMaskY(),
				e1.getMaskWidth(), e1.getMaskHeight());
		Rectangle e2Mask = new Rectangle(e2.getIntegerX() + e2.getMaskX(), e2.getIntegerY() + e2.getMaskY(),
				e2.getMaskWidth(), e2.getMaskHeight());

		return e1Mask.intersects(e2Mask);
	}
	
	public static void atacar(Entity atacante, Entity vitima) {

		if (vitima.getVida() > 0) {
			if (!desviar(atacante, vitima)) {
				if (atacante.getAtaque() > vitima.getDefesa()) {
					atacante.setAttacking(true);
					vitima.setAttacked(true);
					int danoDissipado = dissiparDano(atacante, vitima);
					diminuirVida(vitima, danoDissipado);
					verificarStatusPersonagemEnum(vitima);
				} else {
					System.out.println(vitima.getNome() + " bloqueou o ataque!");
				}

			} else {
				System.out.println(vitima.getNome() + " desviou do ataque!");
			}
		}

	}
	
	private static boolean desviar(Entity atacante, Entity defensor) {
		if (Game.random.nextInt(atacante.getChanceAcerto()) <= defensor.getEsquiva()) {
			return true;
		}
		return false;
	}

	private static int dissiparDano(Entity atacante, Entity defensor) {
		int danoDissipado = atacante.getAtaque() - defensor.getDefesa();

		return danoDissipado;
	}

	public static void diminuirVida(Entity entity, double newVida) {

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
	
	private static void verificarStatusPersonagemEnum(Entity entity) {

		if (entity.getVida() > 40) {
			entity.setStatus(StatusPersonagemEnum.VIVO);
		} else if (entity.getVida() > 0 && entity.getVida() <= 40) {
			entity.setStatus(StatusPersonagemEnum.FERIDO);
		} else {
			entity.setStatus(StatusPersonagemEnum.MORTO);
		}

	}
	
	private static void prepararInteracaoBulletsShootComEnemy(BulletsShoot bulletShoot, Enemy enemy) {

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

}
