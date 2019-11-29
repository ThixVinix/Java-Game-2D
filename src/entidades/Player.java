package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import abstracts.Entity;
import aplicacao.Game;
import enums.StatusGameEnum;
import enums.StatusPersonagemEnum;
import ferramentas.Constantes;
import interfaces.CondicoesEntity;
import interfaces.EntityActions;
import world.Camera;
import world.Mapa;

public class Player extends Entity implements CondicoesEntity, EntityActions {

	public int rightDir = 0, leftDir = 1, upDir = 2, downDir = 3;
	public int dir = 3;

	/**
	 * @see SE COLOCAR "maxFrames = 60" SIGNIFICA QUE A IMAGEM VAI ATUALIZAR A CADA
	 *      1 SEGUNDO.
	 */
	private int frames = 0, maxFrames = 10, imagemAtual = 0,
			imagemMaxima = (Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER - 1);

//	private int framesRifleGun = 0, maxFramesRifleGun = 10, imagemAtualRifleGun = 0,
//			imagemMaximaRifleGun = (Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER - 1);

	private int attackedFrames = 0;

	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	private BufferedImage[] rightPlayerAttacked;
	private BufferedImage[] leftPlayerAttacked;
	private BufferedImage[] upPlayerAttacked;
	private BufferedImage[] downPlayerAttacked;

	private BufferedImage[] rightPlayerInjured;
	private BufferedImage[] leftPlayerInjured;
	private BufferedImage[] upPlayerInjured;
	private BufferedImage[] downPlayerInjured;

	private BufferedImage[] rightPlayerWithRifleGun;
	private BufferedImage[] leftPlayerWithRifleGun;
	private BufferedImage[] upPlayerWithRifleGun;
	private BufferedImage[] downPlayerWithRifleGun;

	private BufferedImage[] rightPlayerWithRifleGunAttacked;
	private BufferedImage[] leftPlayerWithRifleGunAttacked;
	private BufferedImage[] upPlayerWithRifleGunAttacked;
	private BufferedImage[] downPlayerWithRifleGunAttacked;

	private BufferedImage[] rightPlayerWithRifleGunInjured;
	private BufferedImage[] leftPlayerWithRifleGunInjured;
	private BufferedImage[] upPlayerWithRifleGunInjured;
	private BufferedImage[] downPlayerWithRifleGunInjured;

	public Player(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		nascer();
		setMask(Constantes.MASCARA_PLAYER_X, Constantes.MASCARA_PLAYER_Y, Constantes.MASCARA_PLAYER_WIDTH,
				Constantes.MASCARA_PLAYER_HEIGHT);
		gerarDescricao();
		gerarAtributos();
		gerarInventario();
		inicializarSituacoesMomentaneas();
		definirImagensPlayer();

	}

	@Override
	public void nascer() {
		setStatus(StatusPersonagemEnum.VIVO);

	}

	@Override
	public void morrer() {
		setStatus(StatusPersonagemEnum.MORTO);

	}

	@Override
	public void gerarAtributos() {
		setAtaque(Constantes.ATAQUE_PLAYER);
		setDefesa(Constantes.DEFESA_PLAYER);
		setVidaMax(Constantes.VIDA_MAXIMA_PLAYER);
		setVida(Constantes.VIDA_PLAYER);
		setEsquiva(Constantes.ESQUIVA_PLAYER);
		setChanceAcerto(Constantes.CHANCE_DE_ACERTO_PLAYER);
		setDoubleSpeed(Constantes.VELOCIDADE_PLAYER);
	}

	@Override
	public void gerarDescricao() {
		setNome(Constantes.NOME_1);
		setGuilda(Constantes.GUILDA_1);
		setEstoria(Constantes.ESTORIA_PLAYER);

	}

	@Override
	public void gerarInventario() {
		setBulletsPistol(Constantes.MUNICAO_INICIAL_PLAYER);

	}

	private void inicializarSituacoesMomentaneas() {
		setArmed(false);
		setShooted(false);
		setAttacked(false);
		setAttacking(false);
	}

	private void definirImagensPlayer() {
		obterCaminhadaSemItem();
		obterCaminhadaComRifle();

	}

	@Override
	public void render(Graphics graficos) {

		desenharMovimentacaoJogador(graficos);
//		mostrarMascaraJogador(graficos);
	}

	@Override
	public void update() {
		moverJogador();
		atualizarComClamp();
		checkIsAttacked();
		checkIsShooted();
		entityMB.checkCollisionGates(this);
		entityMB.checkCollisionLifePack(this);
		entityMB.checkCollisionBullets(this);
		entityMB.checkCollisionRifle(this);
	}

	
	private void checkIsAttacked() {
		if (isAttacked()) {
			attackedFrames++;
			if (attackedFrames == 8) {
				this.attackedFrames = 0;
				setAttacked(false);
			}
		}
	}

	private void checkIsShooted() {

		if (isShooted()) {
			setShooted(false);

			if (isArmed() && getBulletsPistol() > 0) {

				setBulletsPistol(getBulletsPistol() - 1);

				BulletsShoot shootRight;
				BulletsShoot shootLeft;
				BulletsShoot shootUp;
				BulletsShoot shootDown;

				int dx = 0;
				int dy = 0;
				int shootX = 0;
				int shootY = 0;
//				double rotacao = Math.toRadians(0);

				if (dir == rightDir) {
					dx = 1;
					shootX = 13;
					shootY = 8;
					shootRight = new BulletsShoot(getIntegerX() + shootX, getIntegerY() + shootY,
							Constantes.LARGURA_BULLET_SHOOT_RIGHT, Constantes.ALTURA_BULLET_SHOOT_RIGHT,
							Entity.BULLETS_SHOOT_RIFLE_RIGHT, dx, dy);
					shootRight.setMask(Constantes.MASCARA_BULLET_SHOOT_X, Constantes.MASCARA_BULLET_SHOOT_Y,
							Constantes.MASCARA_BULLET_SHOOT_WIDTH_RIGHT_AND_LEFT_DIRECTION,
							Constantes.MASCARA_BULLET_SHOOT_HEIGHT_RIGHT_AND_LEFT_DIRECTION);
					shootRight.setRight(true);
					Game.bulletsShoot.add(shootRight);
//					rotacao = Math.toRadians(0);

				} else if (dir == leftDir) {
					dx = -1;
					shootX = -1;
					shootY = 8;
					shootLeft = new BulletsShoot(getIntegerX() + shootX, getIntegerY() + shootY,
							Constantes.LARGURA_BULLET_SHOOT_LEFT, Constantes.ALTURA_BULLET_SHOOT_LEFT,
							Entity.BULLETS_SHOOT_RIFLE_LEFT, dx, dy);
					shootLeft.setMask(Constantes.MASCARA_BULLET_SHOOT_X, Constantes.MASCARA_BULLET_SHOOT_Y,
							Constantes.MASCARA_BULLET_SHOOT_WIDTH_RIGHT_AND_LEFT_DIRECTION,
							Constantes.MASCARA_BULLET_SHOOT_HEIGHT_RIGHT_AND_LEFT_DIRECTION);
					shootLeft.setLeft(true);
					Game.bulletsShoot.add(shootLeft);
//					rotacao = Math.toRadians(180);
				} else if (dir == upDir) {
					dy = -1;
					shootX = 7;
					shootY = -2;
					shootUp = new BulletsShoot(getIntegerX() + shootX, getIntegerY() + shootY,
							Constantes.LARGURA_BULLET_SHOOT_UP_AND_DOWN, Constantes.ALTURA_BULLET_SHOOT_UP_AND_DOWN,
							Entity.BULLETS_SHOOT_RIFLE_UP, dx, dy);
					shootUp.setMask(Constantes.MASCARA_BULLET_SHOOT_X, Constantes.MASCARA_BULLET_SHOOT_Y,
							Constantes.MASCARA_BULLET_SHOOT_WIDTH_UP_AND_DOWN_DIRECTION,
							Constantes.MASCARA_BULLET_SHOOT_HEIGHT_UP_AND_DOWN_DIRECTION);
					shootUp.setUp(true);
					Game.bulletsShoot.add(shootUp);
//					rotacao = Math.toRadians(270);
				} else if (dir == downDir) {
					dy = 1;
					shootX = 6;
					shootY = 11;
					shootDown = new BulletsShoot(getIntegerX() + shootX, getIntegerY() + shootY,
							Constantes.LARGURA_BULLET_SHOOT_UP_AND_DOWN, Constantes.ALTURA_BULLET_SHOOT_UP_AND_DOWN,
							Entity.BULLETS_SHOOT_RIFLE_DOWN, dx, dy);
					shootDown.setMask(Constantes.MASCARA_BULLET_SHOOT_X, Constantes.MASCARA_BULLET_SHOOT_Y,
							Constantes.MASCARA_BULLET_SHOOT_WIDTH_UP_AND_DOWN_DIRECTION,
							Constantes.MASCARA_BULLET_SHOOT_HEIGHT_UP_AND_DOWN_DIRECTION);
					shootDown.setDown(true);
					Game.bulletsShoot.add(shootDown);
//					rotacao = Math.toRadians(90);

//					Game.graficos2D.rotate(rotacao, Game.player.getDoubleX(), Game.player.getDoubleY());
//					Game.graficos2D.drawImage(img, xform, obs)
				}

//				BulletsShoot shoot = new BulletsShoot(getIntegerX() + shootX, getIntegerY() + shootY,
//						Constantes.LARGURA_BULLET_SHOOT_RIGHT, Constantes.ALTURA_BULLET_SHOOT_RIGHT,
//						Entity.BULLETS_SHOOT_RIFLE_RIGHT, dx, dy);
//				Game.bulletsShoot.add(shoot);

			}
		}

	}

	@SuppressWarnings(value = { "unused" })
	private Double rotacionarPlayer(Graphics2D graficos2D) {
		double rotacao = Math.toRadians(45);
		graficos2D.rotate(rotacao, (90 + Constantes.PONTO_CENTRAL_PLAYER_X), (90 + Constantes.PONTO_CENTRAL_PLAYER_Y));
		return rotacao;
	}

	@SuppressWarnings(value = { "unused" })
	private void removerRotacao(Graphics2D graficos2D, Double rotacao) {
		if (rotacao == null) {
			rotacao = 0.0;
		}
		graficos2D.rotate(-(rotacao), (90 + Constantes.PONTO_CENTRAL_PLAYER_X),
				(90 + Constantes.PONTO_CENTRAL_PLAYER_Y));
	}

	private void obterCaminhadaSemItem() {

		rightPlayer = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		leftPlayer = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		upPlayer = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		downPlayer = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];

		rightPlayerAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		leftPlayerAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		upPlayerAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		downPlayerAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];

		rightPlayerInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		leftPlayerInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		upPlayerInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];
		downPlayerInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER];

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER; imagemAtual++) {

			rightPlayer[imagemAtual] = Game.spritesheet.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_PLAYER)),
					(0 * Constantes.ALTURA_PLAYER), Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			rightPlayerAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (0 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			rightPlayerInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (0 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER; imagemAtual++) {
			leftPlayer[imagemAtual] = Game.spritesheet.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_PLAYER)),
					(1 * Constantes.ALTURA_PLAYER), Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			leftPlayerAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (1 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			leftPlayerInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (1 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER; imagemAtual++) {
			upPlayer[imagemAtual] = Game.spritesheet.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_PLAYER)),
					(2 * Constantes.ALTURA_PLAYER), Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			upPlayerAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (2 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			upPlayerInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (2 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_WALK_PLAYER; imagemAtual++) {
			downPlayer[imagemAtual] = Game.spritesheet.getSpritesheet((0 + (imagemAtual * Constantes.LARGURA_PLAYER)),
					(3 * Constantes.ALTURA_PLAYER), Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			downPlayerAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (3 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			downPlayerInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(0 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (3 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);
		}
	}

	private void obterCaminhadaComRifle() {
		rightPlayerWithRifleGun = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		leftPlayerWithRifleGun = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		upPlayerWithRifleGun = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		downPlayerWithRifleGun = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];

		rightPlayerWithRifleGunAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		leftPlayerWithRifleGunAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		upPlayerWithRifleGunAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		downPlayerWithRifleGunAttacked = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];

		rightPlayerWithRifleGunInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		leftPlayerWithRifleGunInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		upPlayerWithRifleGunInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];
		downPlayerWithRifleGunInjured = new BufferedImage[Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER];

		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER; imagemAtual++) {
			rightPlayerWithRifleGun[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), (0 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			rightPlayerWithRifleGunAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (0 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			rightPlayerWithRifleGunInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (0 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);
		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER; imagemAtual++) {
			leftPlayerWithRifleGun[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), (1 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			leftPlayerWithRifleGunAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (1 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			leftPlayerWithRifleGunInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (1 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);
		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER; imagemAtual++) {
			upPlayerWithRifleGun[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), (2 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			upPlayerWithRifleGunAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (2 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			upPlayerWithRifleGunInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (2 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

		}
		for (int imagemAtual = 0; imagemAtual < Constantes.QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER; imagemAtual++) {
			downPlayerWithRifleGun[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), (3 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			downPlayerWithRifleGunAttacked[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 64 + (3 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);

			downPlayerWithRifleGunInjured[imagemAtual] = Game.spritesheet.getSpritesheet(
					(64 + (imagemAtual * Constantes.LARGURA_PLAYER)), 128 + (3 * Constantes.ALTURA_PLAYER),
					Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER);
		}
	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraJogador(Graphics graficos) {
		graficos.setColor(Color.BLUE);
		graficos.fillRect(this.getIntegerX() + this.getMaskX() - Camera.getX(),
				this.getIntegerY() + this.getMaskY() - Camera.getY(), this.getMaskWidth(), this.getMaskHeight());
	}

	private void desenharMovimentacaoJogador(Graphics graficos) {

		switch (Game.player.getStatus()) {
		case VIVO:
			desenharJogador(graficos);
			break;
		case FERIDO:
			desenharJogadorFerido(graficos);
			break;
		default:
			// JOGADOR MORTO
			Game.gameState = StatusGameEnum.GAME_OVER;
//			Game.reiniciarJogo();
			break;
		}

	}

	private void desenharJogador(Graphics graficos) {
		if (!isMoved()) {
			if (!isArmed()) {
				if (!isAttacked()) {
					desenharJogadorParado(graficos);
				} else {
					desenharJogadorParadoRecebendoDano(graficos);
				}
			} else {
				if (!isAttacked()) {
					desenharJogadorParadoComRifle(graficos);
				} else {
					desenharJogadorParadoComRifleRecebendoDano(graficos);
				}
			}
		} else {
			if (!isArmed()) {
				if (!isAttacked()) {
					desenharJogadorCaminhando(graficos);
				} else {
					desenharJogadorCaminhandoRecebendoDano(graficos);
				}
			} else {
				if (!isAttacked()) {
					desenharJogadorCaminhandoComRifle(graficos);
				} else {
					desenharJogadorCaminhandoComRifleRecebendoDano(graficos);
				}
			}
		}
	}

	private void desenharJogadorFerido(Graphics graficos) {
		if (!isMoved()) {
			if (!isArmed()) {
				if (!isAttacked()) {
					desenharJogadorParadoFerido(graficos);
				} else {
					desenharJogadorParadoRecebendoDano(graficos);
				}
			} else {
				if (!isAttacked()) {
					desenharJogadorParadoComRifleFerido(graficos);
				} else {
					desenharJogadorParadoComRifleRecebendoDano(graficos);
				}
			}
		} else {
			if (!isArmed()) {
				if (!isAttacked()) {
					desenharJogadorCaminhandoFerido(graficos);
				} else {
					desenharJogadorCaminhandoRecebendoDano(graficos);
				}
			} else {
				if (!isAttacked()) {
					desenharJogadorCaminhandoComRifleFerido(graficos);
				} else {
					desenharJogadorCaminhandoComRifleRecebendoDano(graficos);
				}
			}
		}
	}

	private void desenharJogadorParado(Graphics graficos) {
		// PARADO SEM ITEM/ARMA
		if (dir == rightDir) {
			graficos.drawImage(rightPlayer[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayer[3], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayer[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayer[0], this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(),
					null);
		}
	}

	private void desenharJogadorParadoFerido(Graphics graficos) {
		// PARADO SEM ITEM/ARMA FERIDO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerInjured[3], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorParadoRecebendoDano(Graphics graficos) {
		// PARADO SEM ITEM/ARMA RECEBENDO DANO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerAttacked[3], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorParadoComRifle(Graphics graficos) {
		// PARADO COM RIFLE
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGun[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGun[3], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGun[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGun[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorParadoComRifleFerido(Graphics graficos) {
		// PARADO COM RIFLE FERIDO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGunInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGunInjured[3], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGunInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGunInjured[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorParadoComRifleRecebendoDano(Graphics graficos) {
		// PARADO COM RIFLE RECEBENDO DANO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGunAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGunAttacked[3], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGunAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGunAttacked[0], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhando(Graphics graficos) {
		// CAMINHANDO SEM ITEM/ARMA
		if (dir == rightDir) {
			graficos.drawImage(rightPlayer[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayer[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayer[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayer[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhandoFerido(Graphics graficos) {
		// CAMINHANDO SEM ITEM/ARMA FERIDO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhandoRecebendoDano(Graphics graficos) {
		// CAMINHANDO SEM ITEM/ARMA RECEBENDO DANO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhandoComRifle(Graphics graficos) {
		// CAMINHANDO COM RIFLE
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGun[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGun[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGun[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGun[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhandoComRifleFerido(Graphics graficos) {
		// CAMINHANDO COM RIFLE FERIDO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGunInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGunInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGunInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGunInjured[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void desenharJogadorCaminhandoComRifleRecebendoDano(Graphics graficos) {
		// CAMINHANDO COM RIFLE RECEBENDO DANO
		if (dir == rightDir) {
			graficos.drawImage(rightPlayerWithRifleGunAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == leftDir) {
			graficos.drawImage(leftPlayerWithRifleGunAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == upDir) {
			graficos.drawImage(upPlayerWithRifleGunAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		} else if (dir == downDir) {
			graficos.drawImage(downPlayerWithRifleGunAttacked[imagemAtual], this.getIntegerX() - Camera.getX(),
					this.getIntegerY() - Camera.getY(), null);
		}
	}

	private void moverJogador() {
		setMoved(false);

		if (isRight() && Mapa.isFree((int) (getDoubleX() + getDoubleSpeed()), getIntegerY()) && !entityMB.isCollidingWithGatesClosed(this)) {
			setMoved(true);
			dir = rightDir;
			setDoubleX(getDoubleX() + getDoubleSpeed());
//			Camera.setX(Camera.getX() + getSpeed());
		} else if (isLeft() && Mapa.isFree((int) (getDoubleX() - getDoubleSpeed()), getIntegerY()) && !entityMB.isCollidingWithGatesClosed(this)) {
			setMoved(true);
			dir = leftDir;
			setDoubleX(getDoubleX() - getDoubleSpeed());
		}
		if (isUp() && Mapa.isFree(getIntegerX(), (int) (getDoubleY() - getDoubleSpeed())) && !entityMB.isCollidingWithGatesClosed(this)) {
			setMoved(true);
			dir = upDir;
			setDoubleY(getDoubleY() - getDoubleSpeed());
		} else if (isDown() && Mapa.isFree(getIntegerX(), (int) (getDoubleY() + getDoubleSpeed())) && !entityMB.isCollidingWithGatesClosed(this)) {
			setMoved(true);
			dir = downDir;
			setDoubleY(getDoubleY() + getDoubleSpeed());
		}

		if (isMoved()) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				imagemAtual++;
				if (imagemAtual > imagemMaxima) {
					imagemAtual = 0;
				}
			}

		}
	}

	private void atualizarComClamp() {
		/*
		 * OBS: A LARGURA(Mapa.larguraMapa) E ALTURA(Mapa.alturaMapa) DO MAPA ESTAO NO
		 * FORMATO DE TILES. POR ISSO DEVEM SER MULTIPLICADOS PELO NUMERO DE
		 * PIXELS(TILES_SIZE).
		 */
		Camera.setX(Camera.clamp((this.getIntegerX() - Constantes.CENTRO_TELA_X), 0,
				(Mapa.larguraMapa * Constantes.TILES_SIZE - Constantes.WIDTH_SCREEN)));
		Camera.setY(Camera.clamp((this.getIntegerY() - Constantes.CENTRO_TELA_Y), 0,
				(Mapa.alturaMapa * Constantes.TILES_SIZE - Constantes.HEIGHT_SCREEN)));
	}

	@SuppressWarnings(value = { "unused" })
	private void atualizarSemClamp() {
		/*
		 * DA FORMA ABAIXO O JOGO NAO IRA USAR O METODO "clamp()" DA CLASSE "Camera" E A
		 * CAMERA IRA MOSTRAR AS PARTES QUE NAO SAO RENDERIZADAS NO MAPA. (O FUNDO
		 * PRETO, POR EXEMPLO).
		 */
		Camera.setX(this.getIntegerX() - Constantes.CENTRO_TELA_X);
		Camera.setY(this.getIntegerY() - Constantes.CENTRO_TELA_Y);
	}

	@Override
	public void realizarAcoes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacar(Entity atacante, Entity vitima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perderVida(Integer danoRecebido) {
		// TODO Auto-generated method stub
		
	}

	

//	@Override
//	public void atacarInimigo(Enemy enemy) {
//
//		Integer danoDissipado = this.getAtaque() - enemy.getDefesa();
//
//		if (danoDissipado > 0) {
//			enemy.setVida(enemy.getVida() - fdanoDissipado);
//			System.out.println("O jogador " + this.getNome() + " atacou o inimigo " + enemy.getNome() + "!\n"
//					+ "Entretanto" + " o inimigo conseguiu bloquear parte do dano. \n" + enemy.getNome() + " ficou com "
//					+ enemy.getVida() + " pontos de vida!");
//		} else {
//			System.out.println("O ataque do jogador " + this.getNome() + " não causou efeito na vida do inimigo "
//					+ enemy.getNome());
//		}
//
//	}
//
//	@Override
//	public void desviarInimigo() {
//		System.out.println("O jogador " + getNome() + " desviou!");
//
//	}

}
