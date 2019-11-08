package aplicacao;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

import abstracts.Entity;
import entidades.Bullets;
import entidades.BulletsShoot;
import entidades.Enemy;
import entidades.Lifepack;
import entidades.Player;
import entidades.Weapon;
import enums.StatusGameEnum;
import enums.StatusSimNaoEnum;
import ferramentas.Constantes;
import ferramentas.Variaveis;
import graficos.Spritesheet;
import graficos.UI;
import interfaces.CriacaoTela;
import interfaces.LoopingGameBase;
import movimentacao.MovimentacaoPlayer;
import world.Gate;
import world.Mapa;
import world.WallTile;

public class Game extends Canvas implements Runnable, LoopingGameBase, CriacaoTela {

	private static final long serialVersionUID = 1L;

	private boolean isRunning;
	protected static JFrame frame;
	private Thread thread;
	private BufferedImage image;
	public static Graphics2D graficos2D;

	public static Spritesheet spritesheet;
	public static Spritesheet cenario;
	public static Spritesheet itens;
	public static Spritesheet inimigos;

	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<Lifepack> lifePacks;
	public static List<Bullets> bullets;
	public static List<Weapon> rifles;
	public static List<BulletsShoot> bulletsShoot;
	public static List<WallTile> wallTiles;
	public static List<Gate> gates;

	public static Mapa mapa;
	public static Map<Integer, Mapa> mapas;

	public static Player player;

	public static Random random;
	public static StatusGameEnum gameState = StatusGameEnum.NORMAL;
	public static StatusSimNaoEnum statusSimNaoEnum;
	public static int numeracaoAtual = 1;
	private static final int numeracaoMax = 2;
//	private static String actualMap = "/mapa"+levelAtual+".png";
	public UI ui;

	public Game() {
		random = new Random();
		addKeyListener(new MovimentacaoPlayer());
		criarDimensoesTela();
		criarTela();
		criarMemoriaTemporaria();
		ui = new UI();
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		lifePacks = new ArrayList<Lifepack>();
		bullets = new ArrayList<Bullets>();
		rifles = new ArrayList<Weapon>();
		bulletsShoot = new ArrayList<BulletsShoot>();
		wallTiles = new ArrayList<WallTile>();
		gates = new ArrayList<Gate>();
		cenario = new Spritesheet("/cenario.png");
		itens = new Spritesheet("/itens.png");
		inimigos = new Spritesheet("/inimigofull.png");
//		spritesheet = new Spritesheet("/jogador-16x16.png");
		spritesheet = new Spritesheet("/jogadorfull.png");
		mapas = new HashMap<Integer, Mapa>();
		player = new Player(0, 0, Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER,
				spritesheet.getSpritesheet(Constantes.POSICAO_IMAGEM_PLAYER_X, Constantes.POSICAO_IMAGEM_PLAYER_Y,
						Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER));
		entities.add(player);
		String actualMap = "/mapa" + numeracaoAtual + ".png";
		mapa = new Mapa(actualMap);
//		instanciarPrimeiroCenario();
		mapas.put(numeracaoAtual, mapa);
	}

//	private void instanciarPrimeiroCenario() {
//		System.out.println("PASSEI PELO MÉTODO 'instanciarPrimeiroCenario'");
//		gate.setCenarioAnterior(mapas.get(numeracaoAtual));
//		gate.setVisited(true);
//		numeracaoAtual = gate.getId();
//		String actualMap = "/mapa" + numeracaoAtual + ".png";
//		mapa = new Mapa(actualMap);
//		mapas.put(numeracaoAtual, mapa);
//		
//	}

	public static void main(String[] args) {
		Game game = new Game();
		game.iniciarJogo();
	}

	@Override
	public void criarDimensoesTela() {
		this.setPreferredSize(new Dimension(Constantes.DIMENSAO_LARGURA, Constantes.DIMENSAO_ALTURA));
	}

	@Override
	public void criarTela() {
		frame = new JFrame("Meu jogo");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void criarMemoriaTemporaria() {
		image = new BufferedImage(Constantes.WIDTH_SCREEN, Constantes.HEIGHT_SCREEN, BufferedImage.TYPE_INT_RGB);
	}

	public synchronized void iniciarJogo() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	@Override
	public void run() {
		requestFocus(); // <- ASSIM QUE INICIAR O GAME, NAO PRECISA CLICAR NA TELA PARA COMECAR A JOGAR.
		long lastTimeInNanoseconds = Variaveis.getTempoAtualEmNanosegundos();
		double lastTimeInMilliseconds = Variaveis.getTempoAtualEmMillisegundos();
		double framesPorSegundo = (Constantes.UM_SEGUNDO_EM_NANOSEGUNDO / Constantes.FRAMES_60);
		double delta = 0;
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTimeInNanoseconds) / framesPorSegundo;
			lastTimeInNanoseconds = now;
			if (delta >= 1) {
				update();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - lastTimeInMilliseconds >= Constantes.UM_SEGUNDO_EM_MILLISEGUNDO) {
				System.out.println("FPS: " + frames);
				frames = 0;
				lastTimeInMilliseconds += Constantes.UM_SEGUNDO_EM_MILLISEGUNDO;
			}
		}
		stop();
	}

	@Override
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		switch (gameState) {
		case NORMAL:
			atualizarEntidades();
			atualizarBulletsShoot();
			break;
		case PAUSED:
			break;
		case GAME_OVER:
			
			break;
		default:
			break;
		}
	}

	@Override
	public void render() {
//		double rotacao = 0;
		BufferStrategy bufferStrategy = criarBufferStrategy();
		Graphics graficos = image.getGraphics();
//		Graphics2D graficos2D = (Graphics2D) graficos;
		graficos2D = (Graphics2D) graficos;
		criarPlanoDeFundo(graficos);
		mapa.render(graficos);
		renderizarEntidades(graficos);
		renderizarBulletsShoot(graficos);
//		renderizarGates(graficos);
//		escurecerCenario(graficos2D, rotacao);
		ui.renderizarDesignInterfacePlayer(graficos);
		otimizarJogo(graficos);
		graficos = desenharGraficos(bufferStrategy, graficos);
		desenharAreaJogavel(graficos);
		ui.renderizarStringInterfacePlayer(graficos);
		mostrarGraficos(bufferStrategy);
	}

	private BufferStrategy criarBufferStrategy() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(Constantes.SEQUENCIA_BUFFER);
			return criarBufferStrategy();
		}
		return bufferStrategy;
	}

	public void criarPlanoDeFundo(Graphics graficos) {
		graficos.setColor(new Color(0, 0, 0));
		graficos.fillRect(0, 0, Constantes.WIDTH_SCREEN, Constantes.HEIGHT_SCREEN); // (x, y, width, heightMap)
	}

	public void atualizarEntidades() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
//			if (e instanceof Player) {
			// ...
//			}
			e.update();
		}
	}

	public void renderizarEntidades(Graphics graficos) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(graficos);
		}
	}

	public void atualizarBulletsShoot() {
		for (int i = 0; i < bulletsShoot.size(); i++) {
			BulletsShoot shoot = bulletsShoot.get(i);
			shoot.update();
		}
	}

	public void renderizarBulletsShoot(Graphics graficos) {
		for (int i = 0; i < bulletsShoot.size(); i++) {
			bulletsShoot.get(i).render(graficos);
//			BulletsShoot shoot = bulletsShoot.get(i);
//			shoot.render(graficos);
		}
	}

	private void renderizarGates(Graphics graficos) {
		for (int i = 0; i < gates.size(); i++) {
			gates.get(i).render(graficos);
		}
	}

	public static void instanciarNovoCenario(Gate gate) {
		System.out.println("PASSEI PELO MÉTODO 'instanciarNovoCenario'");
		gate.setCenarioAnterior(mapas.get(numeracaoAtual));
		gate.setVisited(true);
		numeracaoAtual = gate.getId();
		String actualMap = "/mapa" + numeracaoAtual + ".png";
		mapa = new Mapa(actualMap);
		mapas.put(numeracaoAtual, mapa);
	}

	public static Mapa mudarCenario(Gate gate) {
		System.out.println("PASSEI PELO MÉTODO 'mudarCenario'");

		if (gate.isVisited()) {
			System.out.println("FOI VISITADO \n " + mapas.get(gate.getId() - 1));
			gate.setVisited(false);
			mapa = gate.getCenarioAnterior();
//			player.setDoubleX(gate.getX());
//			player.setDoubleY(gate.getY() + Constantes.TILES_SIZE);
			return gate.getCenarioAnterior();
		}

		gate.setVisited(true);
		return mapas.get(gate.getId());
	}

	public static void reiniciarJogo() {
		entities.clear();
		enemies.clear();
		lifePacks.clear();
		bullets.clear();
		bulletsShoot.clear();
		rifles.clear();
		wallTiles.clear();
		gates.clear();
		// TODO VERIFICAR SE O CODIGO ABAIXO COMENTADO IRA OCASIONAR ALGUM CONFLITO!
//		entities = new ArrayList<Entity>();
//		enemies = new ArrayList<Enemy>();
//		lifePacks = new ArrayList<Lifepack>();
//		bullets = new ArrayList<Bullets>();
//		bulletsShoot = new ArrayList<BulletsShoot>();
//		wallTiles = new ArrayList<WallTile>();
//		gates = new ArrayList<Gate>();
//		rifles = new ArrayList<Weapon>();
//		cenario = new Spritesheet("/cenario.png");
//		itens = new Spritesheet("/itens.png");
//		inimigos = new Spritesheet("/inimigos.png");
//		spritesheet = new Spritesheet("/jogador-16x16.png");
		player = new Player(0, 0, Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER,
				spritesheet.getSpritesheet(Constantes.POSICAO_IMAGEM_PLAYER_X, Constantes.POSICAO_IMAGEM_PLAYER_Y,
						Constantes.LARGURA_PLAYER, Constantes.ALTURA_PLAYER));
		entities.add(player);
//		String actualMap = "/mapa" + numeracaoAtual + ".png";
		String actualMap = "/mapa1.png";
		mapa = new Mapa(actualMap);
		return;
	}

	@SuppressWarnings(value = { "unused" })
	private void criarRetanguloTerra(Graphics graficos) {
		graficos.setColor(new Color(139, 69, 19));
		graficos.fillRect(0, 115, Constantes.WIDTH_SCREEN, Constantes.HEIGHT_SCREEN); // (x, y, width, heightMap)
	}

	@SuppressWarnings(value = { "unused" })
	private void criarRetanguloGrama(Graphics graficos) {
		graficos.setColor(new Color(0, 128, 0));
		graficos.fillRect(0, 115, Constantes.WIDTH_SCREEN, 3); // (x, y, width, heightMap)
	}

	@SuppressWarnings(value = { "unused" })
	private void criarCirculoSol(Graphics graficos) {
		graficos.setColor(Color.YELLOW);
		graficos.fillOval(-40, -40, 80, 80);
	}

	@SuppressWarnings(value = { "unused" })
	private void criarLinha(Graphics graficos) {
		graficos.setColor(new Color(0, 128, 0));
		graficos.drawLine(0, 115, Double.MAX_EXPONENT, 115);
	}

	@SuppressWarnings(value = { "unused" })
	private void criarFonte(Graphics graficos) {
		graficos.setFont(new Font("Arial", Font.BOLD, 16));
		graficos.setColor(Color.WHITE);
		graficos.drawString("Olá", Constantes.CENTRO_DA_DIMENSAO_X, Constantes.CENTRO_DA_DIMENSAO_Y);
	}

	@SuppressWarnings(value = { "unused" })
	private void criarPlayer(Graphics2D graficos2D) {
	}

	@SuppressWarnings(value = { "unused" })
	private Double rotacionar(Graphics2D graficos2D) {
		double rotacao = Math.toRadians(45);
		graficos2D.rotate(rotacao, (90 + Constantes.PONTO_CENTRAL_PLAYER_X), (90 + Constantes.PONTO_CENTRAL_PLAYER_Y));
		return rotacao;
	}

	@SuppressWarnings(value = { "unused" })
	private void removerRotacao(Graphics2D graficos2D, Double rotacao) {
		if (rotacao == null) {
			rotacao = Math.toRadians(0);
		}
		graficos2D.rotate(-(rotacao), (90 + Constantes.PONTO_CENTRAL_PLAYER_X),
				(90 + Constantes.PONTO_CENTRAL_PLAYER_Y));
	}

	@SuppressWarnings(value = { "unused" })
	private void escurecerCenario(Graphics2D graficos2D, Double rotacao) {
		graficos2D.setColor(new Color(0, 0, 0, 130));
		graficos2D.fillRect(0, 0, Constantes.WIDTH_SCREEN, Constantes.HEIGHT_SCREEN);
	}

	@Override
	public void otimizarJogo(Graphics graficos) {
		graficos.dispose();
	}

	@Override
	public Graphics desenharGraficos(BufferStrategy bufferStrategy, Graphics graficos) {
		graficos = bufferStrategy.getDrawGraphics();
		return graficos;
	}

	@Override
	public void desenharAreaJogavel(Graphics graficos) {
		graficos.drawImage(image, 0, 0, (Constantes.DIMENSAO_LARGURA + 10), (Constantes.DIMENSAO_ALTURA + 10), null);
	}

	@Override
	public void mostrarGraficos(BufferStrategy bufferStrategy) {
		bufferStrategy.show();
	}

}
