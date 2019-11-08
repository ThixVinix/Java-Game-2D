package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage spritesheet;

	//CONSTRUTOR DA CLASSE: IRA TENTAR PEGAR O ARQUIVO DA IMAGEM DESEJADA.
	public Spritesheet(String path) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*	METODO GET. APOS ESCOLHER UMA IMAGEM EXISTENTE, HABILITARA ESTE METODO
	 * PARA POSSA ATRIBUIR A POSICAO DA IMAGEM DENTRO DA TELA. */
	public BufferedImage getSpritesheet(int x, int y, int width, int height) {

		return spritesheet.getSubimage(x, y, width, height);
	}

}
