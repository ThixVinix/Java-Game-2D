package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import abstracts.Entity;
import abstracts.Tile;
import aplicacao.Game;
import entidades.Bullets;
import entidades.Enemy;
import entidades.Lifepack;
import entidades.Weapon;
import ferramentas.Constantes;

public class Mapa {

	protected int mapaAtual = Game.numeracaoAtual;
	protected int contadorPixelsPretos = 0;
	protected int contadorPixelsBrancos = 0;
	protected int contadorPixelsVermelhos = 0;
	protected int contadorPixelsAzuis = 0;
	protected int contadorPixelsAmarelos = 0;
	protected int contadorPixelsLaranjas = 0;
	protected int contadorPixelsRosas = 0;
	protected int contadorPixelsAzuisClaros = 0;

	private static Tile[] tiles;
	public static int larguraMapa, alturaMapa;
	public static int contGate = 0;

	public Mapa(String path) {
		try {

			BufferedImage map = ImageIO.read(getClass().getResource(path));
			// pixels = (width * height) = (20 * 20) = 400
			int[] pixels = new int[map.getWidth() * map.getHeight()];

			larguraMapa = map.getWidth();
			alturaMapa = map.getHeight();

			tiles = new Tile[larguraMapa * alturaMapa];

			map.getRGB(0, 0, larguraMapa, alturaMapa, pixels, 0, larguraMapa);

			for (int larguraAtual = 0; larguraAtual < larguraMapa; larguraAtual++) {
				for (int alturaAtual = 0; alturaAtual < alturaMapa; alturaAtual++) {

					int pixelAtual = pixels[larguraAtual + (alturaAtual * larguraMapa)];

					// Floor...
					tiles[larguraAtual + (alturaAtual * larguraMapa)] = new FloorTile(
							(larguraAtual * Constantes.LARGURA_GRAMA), (alturaAtual * Constantes.ALTURA_GRAMA),
							Tile.TILE_FLOOR_GRASS);

					switch (pixelAtual) {
					// Example: "0xFF" + "000000".
					case 0xFF000000:
						// Black - Floor... *
						tiles[larguraAtual + (alturaAtual * larguraMapa)] = new FloorTile(
								larguraAtual * Constantes.LARGURA_GRAMA, alturaAtual * Constantes.ALTURA_GRAMA,
								Tile.TILE_FLOOR_GRASS);
						contadorPixelsPretos++;
						break;
					case 0xFFFFFFFF:
						// White - Wall... *
						Game.wallTiles.add((WallTile) (tiles[larguraAtual + (alturaAtual * larguraMapa)] = new WallTile(
								larguraAtual * Constantes.LARGURA_PAREDE_CINZA,
								alturaAtual * Constantes.ALTURA_PAREDE_CINZA, Tile.TILE_WALL_GREY)));

						contadorPixelsBrancos++;
						break;
					case 0xFFFF0000:
						// Red - Enemy... *
						Enemy inimigo = new Enemy((larguraAtual * Constantes.LARGURA_ENEMY_1),
								(alturaAtual * Constantes.ALTURA_ENEMY_1), Constantes.LARGURA_ENEMY_1,
								Constantes.ALTURA_ENEMY_1, Entity.ENEMY_1_ENTITY);
						Game.entities.add(inimigo);
						Game.enemies.add(inimigo);
						contadorPixelsVermelhos++;
						break;
					case 0xFF0026FF:
						// Blue - Player... *
						Game.player.setIntegerX(larguraAtual * Constantes.LARGURA_PLAYER);
						Game.player.setIntegerY(alturaAtual * Constantes.ALTURA_PLAYER);
						contadorPixelsAzuis++;
						break;
					case 0xFFFF7F7F:
						// Pink - Life Pack... *
						Lifepack kitMedico = new Lifepack((larguraAtual * Constantes.LARGURA_LIFEPACK),
								(alturaAtual * Constantes.ALTURA_LIFEPACK), Constantes.LARGURA_LIFEPACK,
								Constantes.ALTURA_LIFEPACK, Entity.LIFEPACK_ENTITY);
						Game.lifePacks.add(kitMedico);
						Game.entities.add(kitMedico);
						contadorPixelsRosas++;
						break;
					case 0xFFFF6A00:
						// Orange - Weapon... *
						Weapon rifle = new Weapon((larguraAtual * Constantes.LARGURA_WEAPON_1),
								(alturaAtual * Constantes.ALTURA_WEAPON_1), Constantes.LARGURA_WEAPON_1,
								Constantes.ALTURA_WEAPON_1, Entity.WEAPON_1_ENTITY);
						Game.rifles.add(rifle);
						Game.entities.add(rifle);
						contadorPixelsLaranjas++;
						break;
					case 0xFFFFD800:
						// Yellow - Bullets... *
						Bullets municao = new Bullets((larguraAtual * Constantes.LARGURA_BULLET),
								(alturaAtual * Constantes.ALTURA_BULLET), Constantes.LARGURA_BULLET,
								Constantes.ALTURA_BULLET, Entity.BULLET_ENTITY);
						Game.bullets.add(municao);
						Game.entities.add(municao);
						contadorPixelsAmarelos++;
						break;
					case 0xFF00FFFF:
						//Light Blue - Gate Go - Gate up...*
						Game.gates.add((Gate)(tiles[larguraAtual + (alturaAtual * larguraMapa)] = new Gate(
								larguraAtual * Constantes.LARGURA_GATE_1_UP,
								alturaAtual * Constantes.ALTURA_GATE_1_UP, Tile.TILE_GATE_GREY_UP)));
						contadorPixelsAzuisClaros++;
						break;
					case 0xFF4CFF00:
						//Green - Gate Back  - Gate up...*
					
						Gate gateBack = Game.gates.get(contGate);
						System.out.println("ID DA PORTA DE VOLTA = " + gateBack.getId());
						System.out.println("ESTADO VISITADO DA PORTA DE VOLTA = " + gateBack.isVisited());
						System.out.println("CENARIO ANTERIOR DA PORTA DE VOLTA = " + gateBack.getCenarioAnterior());
						Game.gates.add((Gate)(tiles[larguraAtual + (alturaAtual * larguraMapa)] = new Gate(larguraAtual * Game.gates.get(contGate).getWidth(), alturaAtual * Game.gates.get(contGate).getHeight(), Tile.TILE_GATE_GREY_UP, Game.gates.get(contGate).getId())));
						contGate++;
						contadorPixelsAzuisClaros++;
						break;
					default:
						// Floor...
						tiles[larguraAtual + (alturaAtual * larguraMapa)] = new FloorTile(
								(larguraAtual * Constantes.LARGURA_GRAMA), (alturaAtual * Constantes.ALTURA_GRAMA),
								Tile.TILE_FLOOR_GRASS);
						break;
					}
				}
			}

			System.out.println("Cenário atual: " + mapaAtual);
			System.out.println("Total de 'pisos' renderizados: " + contadorPixelsPretos + ";");
			System.out.println("Total de 'paredes' renderizadas: " + contadorPixelsBrancos + ";");
			System.out.println("Total de 'inimigos' renderizados: " + contadorPixelsVermelhos + ";");
			System.out.println("Total de 'jogadores' renderizados: " + contadorPixelsAzuis + ";");
			System.out.println("Total de 'armas' renderizadas: " + contadorPixelsLaranjas + ";");
			System.out.println("Total de 'kit médicos' renderizados: " + contadorPixelsRosas + ";");
			System.out.println("Total de 'munições' renderizadas: " + contadorPixelsAmarelos + ";");
			System.out.println("Total de 'portas' renderizadas: " + contadorPixelsAzuisClaros + ".");
			System.out.println("A soma de todos os objetos renderizados é o total de pixels do mapa, que são: "
					+ (contadorPixelsPretos + contadorPixelsBrancos + contadorPixelsVermelhos + contadorPixelsAzuis
							+ contadorPixelsLaranjas + contadorPixelsRosas + contadorPixelsAmarelos)
					+ " pixels.");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see
	 *      <p>
	 *      The signal ">>" is equals to "/", but needs to use the
	 *      <code>Constantes.SYNCHRONOUS_QUOTIENT</code>. This signal divides faster
	 *      than "/".
	 *      </p>
	 *      <p>
	 *      To test the rendering from the player's view, simply decrease the
	 *      initial position of camera X and Y by -2, then increase the camera's
	 *      final position X and Y by +4.
	 *      </p>
	 */
	public void render(Graphics graficos) {

		int posicaoInicialCameraX = (Camera.getX() >> Constantes.SYNCHRONOUS_QUOTIENT);
		int posicaoInicialCameraY = (Camera.getY() >> Constantes.SYNCHRONOUS_QUOTIENT);

		int posicaoFinalCameraX = posicaoInicialCameraX + (Constantes.WIDTH_SCREEN >> Constantes.SYNCHRONOUS_QUOTIENT);
		int posicaoFinalCameraY = posicaoInicialCameraY + (Constantes.HEIGHT_SCREEN >> Constantes.SYNCHRONOUS_QUOTIENT);

		for (int larguraAtual = posicaoInicialCameraX; larguraAtual <= posicaoFinalCameraX; larguraAtual++) {
			for (int alturaAtual = posicaoInicialCameraY; alturaAtual <= posicaoFinalCameraY; alturaAtual++) {

				if (larguraAtual < 0 || alturaAtual < 0 || larguraAtual >= larguraMapa || alturaAtual >= alturaMapa) {
					/*
					 * SE ENTRAR NESTA CONDICAO, SIGNIFICA QUE A CAMERA ULTRAPASSOU OS LIMITES DO
					 * MAPA E NAO IRA RENDERIZAR.
					 */
					continue;
				}
				Tile tile = tiles[larguraAtual + (alturaAtual * larguraMapa)];
				tile.render(graficos);
			}
		}

	}

	@SuppressWarnings(value = { "unused" })
	private void renderizarMapaInteiro(Graphics graficos) {
//	 	DA FORMA ABAIXO, O MAPA DO JOGO SERIA RENDERIZADO POR COMPLETO, POREM PERDENDO PERFOMANCE:
		for (int larguraAtual = 0; larguraAtual < larguraMapa; larguraAtual++) {
			for (int alturaAtual = 0; alturaAtual < alturaMapa; alturaAtual++) {
				Tile tile = tiles[larguraAtual + (alturaAtual * larguraMapa)];
				tile.render(graficos);
			}
		}
	}

	/**
	 * @see
	 *      <p>
	 *      This method checks if the player collides with the wall.
	 *      </p>
	 *      <p>
	 *      If it returns false, it means that the next pixel contains a wall. Not
	 *      allowing the player to continue walking.
	 *      </p>
	 *      <p>
	 *      For this to occur, it is necessary to verify that the next pixel is an
	 *      instance of the desired object.
	 *      </p>
	 */
	public static boolean isFree(Integer xNext, Integer yNext) {

		int x1 = xNext / Constantes.TILES_SIZE;
		int y1 = yNext / Constantes.TILES_SIZE;

		int x2 = (xNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;
		int y2 = yNext / Constantes.TILES_SIZE;

		int x3 = xNext / Constantes.TILES_SIZE;
		int y3 = (yNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;

		int x4 = (xNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;
		int y4 = (yNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;

		return !((tiles[x1 + (y1 * Mapa.larguraMapa)] instanceof WallTile)
				|| (tiles[x2 + (y2 * Mapa.larguraMapa)] instanceof WallTile)
				|| (tiles[x3 + (y3 * Mapa.larguraMapa)] instanceof WallTile)
				|| (tiles[x4 + (y4 * Mapa.larguraMapa)] instanceof WallTile));
	}
	
	public static boolean isFreeGate(Integer xNext, Integer yNext) {

		int x1 = xNext / Constantes.TILES_SIZE;
		int y1 = yNext / Constantes.TILES_SIZE;

		int x2 = (xNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;
		int y2 = yNext / Constantes.TILES_SIZE;

		int x3 = xNext / Constantes.TILES_SIZE;
		int y3 = (yNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;

		int x4 = (xNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;
		int y4 = (yNext + Constantes.TILES_SIZE - 1) / Constantes.TILES_SIZE;

		return !((tiles[x1 + (y1 * Mapa.larguraMapa)] instanceof Gate)
				|| (tiles[x2 + (y2 * Mapa.larguraMapa)] instanceof Gate)
				|| (tiles[x3 + (y3 * Mapa.larguraMapa)] instanceof Gate)
				|| (tiles[x4 + (y4 * Mapa.larguraMapa)] instanceof Gate));
	}
}
