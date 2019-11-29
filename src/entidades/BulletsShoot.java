package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abstracts.Entity;
import aplicacao.Game;
import enums.EntityActionEnum;
import ferramentas.Constantes;
import world.Camera;

public class BulletsShoot extends Entity {

	private Integer dx;
	private Integer dy;
	private double distanciaAtual;
	private double distanciaMax;

	// ATENCAO: ESTE CONSTRUTOR CONTEM 2 PARAMETROS A MAIS (DX E DY).
	public BulletsShoot(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;

//		setMask(Constantes.MASCARA_BULLET_SHOOT_X, Constantes.MASCARA_BULLET_SHOOT_Y,
//				Constantes.MASCARA_BULLET_SHOOT_WIDTH_RIGHT_AND_LEFT_DIRECTION, Constantes.MASCARA_BULLET_SHOOT_HEIGHT_RIGHT_AND_LEFT_DIRECTION);
		setDoubleSpeed(Constantes.VELOCIDADE_BULLET_SHOOT);
		setDistanciaDoubleAtual(Constantes.DISTANCIA_INICIAL_BULLET_SHOOT);
		setDistanciaDoubleMax(Constantes.DISTANCIA_MAXIMA_BULLET_SHOOT);
		setAtaque(Constantes.DANO_BULLET_SHOOT);
		setChanceAcerto(100);
	}

	@Override
	public void render(Graphics graficos) {
		super.render(graficos);
//		mostrarMascaraBulletsShoot(graficos);
	}

	@Override
	public void update() {

//		entityMB.checkCollisionWithAnotherEntity(this);
		if (mediador.notify(this, EntityActionEnum.ATACAR)) {
			atacar(this, getOtherEntity());
			setOtherEntity(null);
		}

		setDistanciaDoubleAtual(getDistanciaDoubleAtual() + 1.0);
		if (getDistanciaDoubleAtual() < getDistanciaDoubleMax() && !entityMB.isCollidingWithWallTiles(this)) {
			setDoubleX(getDoubleX() + getDx() * getIntegerSpeed());
			setDoubleY(getDoubleY() + getDy() * getIntegerSpeed());

		} else {
			Game.bulletsShoot.remove(this);
		}
	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraBulletsShoot(Graphics graficos) {
		graficos.setColor(Color.BLACK);
		graficos.fillRect(this.getIntegerX() + this.getMaskX() - Camera.getX(),
				this.getIntegerY() + this.getMaskY() - Camera.getY(), this.getMaskWidth(), this.getMaskHeight());
	}

	@Override
	public void atacar(Entity atacante, Entity vitima) {
		entityMB.combater(atacante, vitima);

	}

	@Override
	public void perderVida(Integer danoRecebido) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realizarAcoes() {
		// TODO Auto-generated method stub

	}

	public Integer getDx() {
		return dx;
	}

	public void setDx(Integer dx) {
		this.dx = dx;
	}

	public Integer getDy() {
		return dy;
	}

	public void setDy(Integer dy) {
		this.dy = dy;
	}

	public Integer getDistanciaAtual() {
		return (int) distanciaAtual;
	}

	public double getDistanciaDoubleAtual() {
		return distanciaAtual;
	}

	public void setDistanciaDoubleAtual(double distanciaAtual) {
		this.distanciaAtual = distanciaAtual;
	}

	public Integer getDistanciaMax() {
		return (int) distanciaMax;
	}

	public double getDistanciaDoubleMax() {
		return distanciaMax;
	}

	public void setDistanciaMax(Integer distanciaMax) {
		this.distanciaMax = (int) distanciaMax;
	}

	public void setDistanciaDoubleMax(double distanciaMax) {
		this.distanciaMax = distanciaMax;
	}

}
