package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abstracts.Entity;
import ferramentas.Constantes;
import world.Camera;

public class Weapon extends Entity {

	public Weapon(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		setMask(Constantes.MASCARA_WEAPON_1_X, Constantes.MASCARA_WEAPON_1_Y, Constantes.MASCARA_WEAPON_1_WIDTH,
				Constantes.MASCARA_WEAPON_1_HEIGHT);
	}

	@Override
	public void render(Graphics graficos) {
		super.render(graficos);
//		mostrarMascaraWeapon1(graficos);
	}

	/**
	 * This method is only used to verify how the entity mask
	 */
	@SuppressWarnings(value = { "unused" })
	private void mostrarMascaraWeapon1(Graphics graficos) {
		graficos.setColor(Color.ORANGE);
		graficos.fillRect(this.getIntegerX() + this.getMaskX() - Camera.getX(),
				this.getIntegerY() + this.getMaskY() - Camera.getY(), this.getMaskWidth(), this.getMaskHeight());
	}

}
