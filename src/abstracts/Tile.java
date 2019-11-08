package abstracts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aplicacao.Game;
import ferramentas.Constantes;
import world.Camera;

public abstract class Tile {

	public static final BufferedImage TILE_FLOOR_GRASS = Game.cenario.getSpritesheet(Constantes.POSICAO_IMAGEM_GRAMA_X,
			Constantes.POSICAO_IMAGEM_GRAMA_Y, Constantes.LARGURA_GRAMA, Constantes.ALTURA_GRAMA);

	public static final BufferedImage TILE_FLOOR_EARTH = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_PAREDE_CINZA_X, Constantes.POSICAO_IMAGEM_PAREDE_CINZA_Y,
			Constantes.LARGURA_PAREDE_CINZA, Constantes.ALTURA_PAREDE_CINZA);
	
	public static final BufferedImage TILE_WALL_GREY = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_PAREDE_CINZA_X, Constantes.POSICAO_IMAGEM_PAREDE_CINZA_Y,
			Constantes.LARGURA_PAREDE_CINZA, Constantes.ALTURA_PAREDE_CINZA);
	
	public static final BufferedImage TILE_GATE_GREY_UP = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_GATE_1_X_UP, Constantes.POSICAO_IMAGEM_GATE_1_Y_UP,
			Constantes.LARGURA_GATE_1_UP, Constantes.ALTURA_GATE_1_UP);
	
	public static final BufferedImage TILE_GATE_GREY_RIGHT = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_GATE_1_X_RIGHT, Constantes.POSICAO_IMAGEM_GATE_1_Y_RIGHT,
			Constantes.LARGURA_GATE_1_RIGHT, Constantes.ALTURA_GATE_1_RIGHT);
	
	public static final BufferedImage TILE_GATE_GREY_LEFT = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_GATE_1_X_LEFT, Constantes.POSICAO_IMAGEM_GATE_1_Y_LEFT,
			Constantes.LARGURA_GATE_1_LEFT, Constantes.ALTURA_GATE_1_LEFT);
	
	public static final BufferedImage TILE_GATE_GREY_DOWN = Game.cenario.getSpritesheet(
			Constantes.POSICAO_IMAGEM_GATE_1_X_DOWN, Constantes.POSICAO_IMAGEM_GATE_1_Y_DOWN,
			Constantes.LARGURA_GATE_1_DOWN, Constantes.ALTURA_GATE_1_DOWN);
	
	
	
	
	private BufferedImage sprite;
	private int x, y;
	private int width, height;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.width = Constantes.TILES_SIZE;
		this.height = Constantes.TILES_SIZE;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.x - Camera.getX(), this.y - Camera.getY(), null);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
