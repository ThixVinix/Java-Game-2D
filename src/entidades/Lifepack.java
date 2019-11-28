package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abstracts.Entity;
import ferramentas.Constantes;
import world.Camera;

public class Lifepack extends Entity {

	public Lifepack(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		this.setMask(Constantes.MASCARA_LIFEPACK_X, Constantes.MASCARA_LIFEPACK_Y, Constantes.MASCARA_LIFEPACK_WIDTH,
				Constantes.MASCARA_LIFEPACK_HEIGHT);
	}

	@Override
	public void render(Graphics graficos) {
		super.render(graficos);
//		mostrarMascaraLifePack(graficos);
	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraLifePack(Graphics graficos) {
		graficos.setColor(Color.PINK);
		graficos.fillRect(this.getIntegerX() + this.getMaskX() - Camera.getX(),
				this.getIntegerY() + this.getMaskY() - Camera.getY(), this.getMaskWidth(), this.getMaskHeight());
	}

	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
}
