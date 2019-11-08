package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abstracts.Tile;
import aplicacao.Game;
import ferramentas.Constantes;

public class Gate extends Tile {

	private Integer id;
	private Integer idGateAnterior;
	private boolean visited;
	private Mapa cenarioAnterior;
	private boolean close;
	private boolean backGate;

	private static int geradorId = 2;
	private static boolean contInicial = false;
	
	private BufferedImage[] imagesGate;
	

	//CRIANDO PORTA DE IDA
	public Gate(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);

		if (!contInicial) {
			contInicial = true;
			this.id = 1;
		}
		
		this.id = gerarId();
		setVisited(false);
		setClose(false);
		setBackGate(false);
	}

	//CRIANDO PORTA DE VOLTA
	public Gate(int x, int y, BufferedImage sprite, int id) {
		super(x, y, sprite);
		this.id = id;
		setVisited(true);
		setClose(true);
		setBackGate(true);
//		definirImagensGate();
	}

	private void definirImagensGate() {
		imagesGate = new BufferedImage[1];
		
		for (int imagemAtual = 0; imagemAtual < 2; imagemAtual++) {
			imagesGate[imagemAtual] = Game.cenario.getSpritesheet(
					(16 + (imagemAtual * Constantes.LARGURA_GATE_1_UP)), 16 + (0 * Constantes.ALTURA_GATE_1_UP),
					Constantes.LARGURA_GATE_1_UP, Constantes.ALTURA_GATE_1_UP);

		}
		
	}


	
//	private void desenharImagensGate(Graphics graficos) {
//		if (this.isClose()) {
//			graficos.drawImage(imagesGate[0], this.getX() - Camera.getX(), this.getY() - Camera.getY(),
//					null);
//		} else if (!this.isClose()) {
//			graficos.drawImage(imagesGate[1], this.getX() - Camera.getX(), this.getY() - Camera.getY(),
//					null);
//		}
//		
//	}

	private static Integer gerarId() {
		return geradorId++;
	}

	public Integer getId() {
		return id;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Mapa getCenarioAnterior() {
		return cenarioAnterior;
	}

	public void setCenarioAnterior(Mapa cenarioAnterior) {
		this.cenarioAnterior = cenarioAnterior;
	}

	public Integer getIdGateAnterior() {
		return idGateAnterior;
	}

	public void setIdGateAnterior(Integer idGateAnterior) {
		this.idGateAnterior = idGateAnterior;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean isBackGate() {
		return backGate;
	}

	public void setBackGate(boolean backGate) {
		this.backGate = backGate;
	}

}
