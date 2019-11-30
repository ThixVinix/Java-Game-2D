package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import abstracts.Entity;
import aplicacao.Game;
import enums.EntityActionEnum;
import enums.StatusPersonagemEnum;
import exceptions.ValorNegativoException;
import ferramentas.Constantes;
import interfaces.CombateEnemy;
import interfaces.CondicoesEntity;
import world.Camera;
import world.Mapa;

public class Enemy extends Entity implements CondicoesEntity, CombateEnemy {

	public int rightDir = 0, leftDir = 1, upDir = 2, downDir = 3;
	public int dir = 3;

	/**
	 * @see SE COLOCAR "maxFrames = 60" SIGNIFICA QUE A IMAGEM VAI ATUALIZAR A CADA
	 *      1 SEGUNDO.
	 */
	private int framesWalkEnemy1 = 0, maxFramesWalkEnemy1 = 5, imagemAtualWalkEnemy1 = 0,
			imagemMaximaWalkEnemy1 = (Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1 - 1);

	private int framesAttackEnemy1 = 0, maxFramesAttackEnemy1 = 5, imagemAtualAttackEnemy1 = 0,
			imagemMaximaAttackEnemy1 = (Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1 - 1);

	private int attackedFrames = 0;
//	private boolean setMoved(true) = false;

	private BufferedImage[] rightEnemy1;
	private BufferedImage[] leftEnemy1;
	private BufferedImage[] upEnemy1;
	private BufferedImage[] downEnemy1;

	private BufferedImage[] rightEnemy1Attacked;
	private BufferedImage[] leftEnemy1Attacked;
	private BufferedImage[] upEnemy1Attacked;
	private BufferedImage[] downEnemy1Attacked;

	private BufferedImage[] rightAttack;
	private BufferedImage[] leftAttack;
	private BufferedImage[] upAttack;
	private BufferedImage[] downAttack;

	private BufferedImage[] rightAttackAndAttacked;
	private BufferedImage[] leftAttackAndAttacked;
	private BufferedImage[] upAttackAndAttacked;
	private BufferedImage[] downAttackAndAttacked;

	public Enemy(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		nascer();
		setMask(Constantes.MASCARA_ENEMY_1_X, Constantes.MASCARA_ENEMY_1_Y, Constantes.MASCARA_ENEMY_1_WIDTH,
				Constantes.MASCARA_ENEMY_1_HEIGHT);
		gerarDescricao();
		gerarAtributos();
		definirImagensEnemy1();
		inicializarSituacoesMomentaneas();
	}

	@Override
	public void nascer() {
		setStatus(StatusPersonagemEnum.VIVO);

	}

	@Override
	public void morrer() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}

	@Override
	public void gerarAtributos() {
		setAtaque(Constantes.ATAQUE_ENEMY_1);
		setDefesa(Constantes.DEFESA_ENEMY_1);
		setVidaMax(Constantes.VIDA_MAXIMA_ENEMY_1);
		setVida(Constantes.VIDA_ENEMY_1);
		setEsquiva(Constantes.ESQUIVA_ENEMY_1);
		setChanceAcerto(Constantes.CHANCE_DE_ACERTO_ENEMY_1);
		setDoubleSpeed(Constantes.VELOCIDADE_ENEMY_1);
	}

	@Override
	public void gerarDescricao() {
		setNome(Constantes.NOME_2);
		setGuilda(Constantes.GUILDA_2);
		setEstoria(Constantes.ESTORIA_ENEMY);

	}

	@Override
	public void gerarInventario() {
		// SEM IMPLEMENTACAO

	}

	public void inicializarSituacoesMomentaneas() {
		setMoved(false);
		setAttacked(false);
		setAttacking(false);
	}

	private void definirImagensEnemy1() {

		// WALK ENEMY
		rightEnemy1 = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		leftEnemy1 = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		upEnemy1 = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		downEnemy1 = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];

		rightEnemy1Attacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		leftEnemy1Attacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		upEnemy1Attacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];
		downEnemy1Attacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1];

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1; imagemAtual++) {
			downEnemy1[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(0 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			downEnemy1Attacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (0 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1; imagemAtual++) {
			upEnemy1[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(1 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			upEnemy1Attacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (0 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

		}

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1; imagemAtual++) {
			rightEnemy1[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(2 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			rightEnemy1Attacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (0 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_ENEMY_1; imagemAtual++) {
			leftEnemy1[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(3 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			leftEnemy1Attacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (0 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}

		// ENEMY ATTACK
		rightAttack = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		leftAttack = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		upAttack = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		downAttack = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		rightAttackAndAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		leftAttackAndAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		upAttackAndAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];
		downAttackAndAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1];

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1; imagemAtual++) {
			downAttack[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(4 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			downAttackAndAttacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(98 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (4 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1; imagemAtual++) {
			upAttack[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(5 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			upAttackAndAttacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(98 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (5 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1; imagemAtual++) {
			rightAttack[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(6 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			rightAttackAndAttacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(98 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (6 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_ATTACK_ENEMY_1; imagemAtual++) {
			leftAttack[imagemAtual] = Game.inimigos.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_ENEMY_1)),
					(7 * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

			leftAttackAndAttacked[imagemAtual] = Game.inimigos.getSpritesheet(
					(98 + (imagemAtual * Constantes.LARGURA_ENEMY_1)), (7 * Constantes.ALTURA_ENEMY_1),
					Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);
		}
	}

	@Override
	public void render(Graphics graficos) {
		desenharMovimentacaoEnemy1(graficos);
//		mostrarMascaraInimigo1(graficos);
	}

	@Override
	public void update() {

//		switch (revezarMovimentacaoInimiga()) {
//
//		case 1:

		verificarStatus();
		if (!getStatus().equals(StatusPersonagemEnum.MORTO)) {

		}

		if (retardarMovimentacaoInimiga()) {
			movimentarInimigoEmAmbasDirecoes();
			checkIsAttacked();
		}

//			break;
//		case 2:
//
//			if (retardarMovimentacaoInimiga()) {
//				movimentarInimigoEmDirecaoUnica();
//			}
//
//			break;
//		default:
//			break;
//		}

	}

	private void checkIsAttacked() {
		if (isAttacked()) {
			attackedFrames++;
			if (attackedFrames == 5) {
				this.attackedFrames = 0;
			}
		}
		setAttacked(false);
	}

	private void desenharMovimentacaoEnemy1(Graphics graficos) {
	
		switch (this.getStatus()) {
		case VIVO:
			desenharEnemy1(graficos);
			break;
		case FERIDO:
			desenharEnemy1(graficos);
			break;
		default:
			morrer();
			break;
		}

	}

	private void desenharEnemy1(Graphics graficos) {

		if (!isMoved()) {
			if (!isAttacking()) {
				if (!isAttacked()) {
					desenharInimigoParado(graficos);
				} else {
					desenharInimigoParadoRecebendoDano(graficos);
				}
			} else {
				if (!isAttacked()) {
					desenharInimigoAtacandoPlayer(graficos);
				} else {
					desenharInimigoAtacandoRecebendoDano(graficos);
				}
			}
		} else {
			if (!isAttacked()) {
				desenharInimigoCaminhando(graficos);
			} else {
				desenharInimigoCaminhandoRecebendoDano(graficos);
			}

		}

	}

	private void desenharInimigoParadoRecebendoDano(Graphics graficos) {
		if (dir == rightDir) {
			graficos.drawImage(rightEnemy1Attacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftEnemy1Attacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upEnemy1Attacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downEnemy1Attacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharInimigoAtacandoRecebendoDano(Graphics graficos) {
		if (dir == rightDir) {
			graficos.drawImage(rightAttackAndAttacked[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftAttackAndAttacked[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upAttackAndAttacked[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downAttackAndAttacked[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharInimigoCaminhandoRecebendoDano(Graphics graficos) {
		if (dir == rightDir) {
			graficos.drawImage(rightEnemy1Attacked[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftEnemy1Attacked[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upEnemy1Attacked[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downEnemy1Attacked[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharInimigoAtacandoPlayer(Graphics graficos) {
		// QUANDO ESTIVER COLIDINDO COM O PLAYER... DESENHE O INIMIGO ATACANDO.
		if (dir == rightDir) {
			graficos.drawImage(rightAttack[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftAttack[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upAttack[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downAttack[imagemAtualAttackEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharInimigoParado(Graphics graficos) {
		// QUANDO ESTIVER PARADO SEM COLIDIR COM O PLAYER... DESENHE O INIMIGO PARADO.
		if (dir == rightDir) {
			graficos.drawImage(rightEnemy1[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftEnemy1[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == upDir) {
			graficos.drawImage(upEnemy1[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == downDir) {
			graficos.drawImage(downEnemy1[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		}
	}

	private void desenharInimigoCaminhando(Graphics graficos) {
		// QUANDO ESTIVER SE MOVENDO... DESENHE O INIMIGO SE LOCOMOVENDO.
		if (dir == rightDir) {
			graficos.drawImage(rightEnemy1[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftEnemy1[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upEnemy1[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downEnemy1[imagemAtualWalkEnemy1], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void movimentarInimigoEmAmbasDirecoes() {

		if (!mediador.notify(this, EntityActionEnum.CHECAR_COLISAO)) {

			// EIXO 'X'
			if ((getIntegerX() + getMaskX()) < (Game.player.getIntegerX() + Game.player.getMaskX())
					&& Mapa.isFree((int) (getDoubleX() + getDoubleSpeed()), getIntegerY())
					&& !isCollidingWithEnemies((getIntegerX() + getIntegerSpeed()), getIntegerY())) {
				setMoved(true);
				dir = rightDir;
				setDoubleX(getDoubleX() + getDoubleSpeed());
			} else if ((getIntegerX() + getMaskX()) > (Game.player.getIntegerX() + Game.player.getMaskX())
					&& Mapa.isFree((int) (getDoubleX() - getDoubleSpeed()), getIntegerY())
					&& !isCollidingWithEnemies((getIntegerX() - getIntegerSpeed()), getIntegerY())) {
				setMoved(true);
				dir = leftDir;
				setDoubleX(getDoubleX() - getDoubleSpeed());
			}

			// EIXO 'Y'
			if ((getIntegerY() + getMaskY()) < (Game.player.getIntegerY() + Game.player.getMaskY())
					&& Mapa.isFree(getIntegerX(), (int) (getDoubleY() + getDoubleSpeed()))
					&& !isCollidingWithEnemies(getIntegerX(), (getIntegerY() + getIntegerSpeed()))) {
				setMoved(true);
				dir = downDir;
				setDoubleY(getDoubleY() + getDoubleSpeed());

			} else if ((getIntegerY() + getMaskY()) > (Game.player.getIntegerY() + Game.player.getMaskY())
					&& Mapa.isFree(getIntegerX(), (int) (getDoubleY() - getDoubleSpeed()))
					&& !isCollidingWithEnemies(getIntegerX(), (getIntegerY() - getIntegerSpeed()))) {
				setMoved(true);
				dir = upDir;
				setDoubleY(getDoubleY() - getDoubleSpeed());
			}

			if (isMoved()) {
				framesWalkEnemy1++;
				if (framesWalkEnemy1 == maxFramesWalkEnemy1) {
					framesWalkEnemy1 = 0;
					imagemAtualWalkEnemy1++;
					if (imagemAtualWalkEnemy1 > imagemMaximaWalkEnemy1) {
						imagemAtualWalkEnemy1 = 0;
					}
				}
			}

		} else {
			setMoved(false);
			if (!isMoved()) {
				framesAttackEnemy1++;
				if (framesAttackEnemy1 == maxFramesAttackEnemy1) {
					framesAttackEnemy1 = 0;
					imagemAtualAttackEnemy1++;
					if (imagemAtualAttackEnemy1 > imagemMaximaAttackEnemy1) {
						imagemAtualAttackEnemy1 = 0;
					}
				}
			}

//			entityMB.combater(this, Game.player);

			if (mediador.notify(this, EntityActionEnum.ATACAR)) {
				atacar(this, Game.player);
			}
		}
	}

	@SuppressWarnings(value = "unused")
	private void movimentarInimigoEmDirecaoUnica() {

		if (!entityMB.isCollidingWithAnotherEntity(this, Game.player)) {

			// EIXO 'X' OU 'Y' (NESSE METODO O INIMIGO ANDA EM UM UNICA DIRECAO POR VEZ)
			if (getIntegerX() < Game.player.getIntegerX()
					&& Mapa.isFree((int) (getDoubleX() + getDoubleSpeed()), getIntegerY())
					&& !isCollidingWithEnemies((getIntegerX() + getIntegerSpeed()), getIntegerY())) {
				setMoved(true);
				dir = rightDir;
				setDoubleX(getDoubleX() + getDoubleSpeed());
			} else if (getIntegerX() > Game.player.getIntegerX()
					&& Mapa.isFree((int) (getDoubleX() - getDoubleSpeed()), getIntegerY())
					&& !isCollidingWithEnemies((getIntegerX() - getIntegerSpeed()), getIntegerY())) {
				setMoved(true);
				dir = leftDir;
				setDoubleX(getDoubleX() - getDoubleSpeed());

			} else if (getIntegerY() < Game.player.getIntegerY()
					&& Mapa.isFree(getIntegerX(), (int) (getDoubleY() + getDoubleSpeed()))
					&& !isCollidingWithEnemies(getIntegerX(), (getIntegerY() + getIntegerSpeed()))) {
				setMoved(true);
				dir = downDir;
				setDoubleY(getDoubleY() + getDoubleSpeed());

			} else if (getIntegerY() > Game.player.getIntegerY()
					&& Mapa.isFree(getIntegerX(), (int) (getDoubleY() - getDoubleSpeed()))
					&& !isCollidingWithEnemies(getIntegerX(), (getIntegerY() - getIntegerSpeed()))) {
				setMoved(true);
				dir = upDir;
				setDoubleY(getDoubleY() - getDoubleSpeed());
			}

			if (isMoved()) {
				framesWalkEnemy1++;
				if (framesWalkEnemy1 == maxFramesWalkEnemy1) {
					framesWalkEnemy1 = 0;
					imagemAtualWalkEnemy1++;
					if (imagemAtualWalkEnemy1 > imagemMaximaWalkEnemy1) {
						imagemAtualWalkEnemy1 = 0;
					}
				}

			}

		} else {
			setMoved(false);
			if (!isMoved() && mediador.notify(this, EntityActionEnum.CHECAR_COLISAO)) {
				framesAttackEnemy1++;
				if (framesAttackEnemy1 == maxFramesAttackEnemy1) {
					framesAttackEnemy1 = 0;
					imagemAtualAttackEnemy1++;
					if (imagemAtualAttackEnemy1 > imagemMaximaAttackEnemy1) {
						imagemAtualAttackEnemy1 = 0;
					}
				}
			}

			if ( mediador.notify(this, EntityActionEnum.ATACAR)) {
				atacar(this, getOtherEntity());
				System.out.println("ATACOU");
				setOtherEntity(null);
			}
	
		}

	}

	@Override
	public void realizarAcoes() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return boolean
	 * @see
	 *      <p>
	 *      This method is a way to try solve the problem of collision between
	 *      enemies themselves. Recommended when there are many enemies walking at
	 *      the same time. Because, in fact, the game will not be checking for
	 *      collision between enemies.
	 *      </p>
	 *      <p>
	 *      However, this method will not fully solve the problem of collisions
	 *      between enemies if used alone.
	 *      </p>
	 */
	private boolean retardarMovimentacaoInimiga() {
		/*
		 * DE MANEIRA RANDOMICA, QUANTO MAIOR FOR O NUMERO DA CONDICAO DO "IF", MAIOR A
		 * PROBABILIDADE DO INIMIGO SE MOVIMENTAR, CONSEQUENTEMENTE IRA SE MOVER DE
		 * FORMA MAIS RAPIDA TAMBEM.
		 */
		if (Game.random.nextInt(100) <= 40) {
			return true;
		}
		return false;
	}

	/** @return byte */
	@SuppressWarnings(value = "unused")
	private byte revezarMovimentacaoInimiga() {
		if (Game.random.nextInt(100) <= 65) {
			return 1;
		}
		return 2;
	}

	public boolean isCollidingWithEnemies(int xNext, int yNext) {

		Rectangle nearEnemyRectangle = new Rectangle(xNext + Constantes.MASCARA_ENEMY_1_X,
				yNext + Constantes.MASCARA_ENEMY_1_Y, Constantes.MASCARA_ENEMY_1_WIDTH,
				Constantes.MASCARA_ENEMY_1_HEIGHT);

		for (int inimigoAtual = 0; inimigoAtual < Game.enemies.size(); inimigoAtual++) {

			Enemy inimigoAnalisado = Game.enemies.get(inimigoAtual);
			Rectangle currentEnemyRectangle = new Rectangle(
					inimigoAnalisado.getIntegerX() + Constantes.MASCARA_ENEMY_1_X,
					inimigoAnalisado.getIntegerY() + Constantes.MASCARA_ENEMY_1_Y, Constantes.MASCARA_ENEMY_1_WIDTH,
					Constantes.MASCARA_ENEMY_1_HEIGHT);

			/*
			 * SE O INIMIGO ANALISADO FOR IGUAL A ELE MESMO, APENAS IGNORE E CONTINUE O
			 * LOOPING.
			 */
			if (inimigoAnalisado == this) {
				continue;
			}

			if (currentEnemyRectangle.intersects(nearEnemyRectangle)) {
				return true;
			}

		}

		return false;
	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraInimigo1(Graphics graficos) {
		graficos.setColor(Color.RED);
		graficos.fillRect(getIntegerX() + getMaskX() - Camera.getX(), getIntegerY() + getMaskY() - Camera.getY(),
				getMaskWidth(), getMaskHeight());
	}

	@Override
	public void atacarPlayer() {

	}

	@Override
	public void desviarPlayer() {

	}

	@Override
	public void atacar(Entity atacante, Entity vitima) {
		entityMB.combater(atacante, vitima);

	}

	@Override
	public void diminuirVida(Integer danoRecebido) {
		try {

			if (danoRecebido < 0.0) {
				throw new ValorNegativoException(danoRecebido);
			}

			setVida(getVida() - danoRecebido);

		} catch (ValorNegativoException e) {
			JOptionPane.showMessageDialog(null, "O valor da vida passado pelo parâmetro não deve ser negativo",
					"ValorNegativoException", JOptionPane.WARNING_MESSAGE, null);
			e.printStackTrace();
			System.exit(0);
		}

	}

	@Override
	public void verificarStatus() {
		 super.verificarStatus();
	}

	@Override
	public boolean desviar(Integer chanceAcertoAtacante, Integer chanceEsquivaVitima) {
		if (Game.random.nextInt(chanceAcertoAtacante) <= chanceEsquivaVitima) {
			return true;
		}
		System.out.println(getNome() + " desviou do ataque!");
		return false;
	}

	@Override
	public int dissiparDano(Integer ataqueAtacante, Integer defesaVitima) {
		int danoDissipado = ataqueAtacante - defesaVitima;

		return danoDissipado;
	}

	@Override
	public void bloquearDano() {
		System.out.println(getNome() + " bloqueou o ataque!");
		
	}

	

	

}
