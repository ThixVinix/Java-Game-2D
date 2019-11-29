package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abstracts.Entity;
import ferramentas.Constantes;
import world.Camera;

public class Bullets extends Entity {

	public Bullets(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		setMask(Constantes.MASCARA_BULLET_X, Constantes.MASCARA_BULLET_Y, Constantes.MASCARA_BULLET_WIDTH,
				Constantes.MASCARA_BULLET_HEIGHT);
	}

	@Override
	public void render(Graphics graficos) {
		super.render(graficos);
//		mostrarMascaraBullets(graficos);
	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraBullets(Graphics graficos) {
		graficos.setColor(Color.YELLOW);
		graficos.fillRect(this.getIntegerX() + this.getMaskX() - Camera.getX(),
				this.getIntegerY() + this.getMaskY() - Camera.getY(), this.getMaskWidth(), this.getMaskHeight());
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

}
