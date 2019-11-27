package ferramentas;

/**
 * @author ThixVinix
 * @since 27/06/2019
 * @version 1.0 beta
 */

public class Constantes {

	// CONSTANTES DOS FRAMES

	public static final Double FRAMES_60 = 60.0;

	public static final Double FRAMES_144 = 144.0;

	public static final Double FRAMES_240 = 240.0;

	public static final Double FRAMES_ILIMITADOS = Double.MAX_VALUE;

	// CONSTANTES DE TEMPO:

	// (10^9ns = 1000000000 = 1 second).
	public static final Integer UM_SEGUNDO_EM_NANOSEGUNDO = 1000000000;

	// (1^3ms = 1000 = 1 second).
	public static final Integer UM_SEGUNDO_EM_MILLISEGUNDO = 1000;

	// CONSTANTES DA TELA:

	public static final Integer WIDTH_SCREEN = 240;

	public static final Integer HEIGHT_SCREEN = 160;

	public static final Integer CENTRO_TELA_X = (WIDTH_SCREEN / 2);

	public static final Integer CENTRO_TELA_Y = (HEIGHT_SCREEN / 2) + 5;

	public static final Integer SCALE = 3;

	public static final Integer DIMENSAO_LARGURA = WIDTH_SCREEN * SCALE;

	public static final Integer DIMENSAO_ALTURA = HEIGHT_SCREEN * SCALE;

	public static final Integer CENTRO_DA_DIMENSAO_X = (int) (DIMENSAO_LARGURA / 2);

	public static final Integer CENTRO_DA_DIMENSAO_Y = (int) (DIMENSAO_ALTURA / 2);

	public static final Integer SEQUENCIA_BUFFER = 3;

	// CONSTANTES DOS TILES:

	public static final Integer TILES_SIZE = 16;

	/**
	 * @see
	 *      <h5>ATENTION:</h5>
	 *      <p>
	 *      This constant is related to the "<i>TILES_SIZE</i>". The
	 *      "<i>SYNCHRONOUS_QUOTIENT</i>" is the divisor that when divided by the
	 *      dividend, the quotient has the same value as the divisor. For example,
	 *      16 (dividend) divided by 4 (divisor) is equal to quotient 4 (the divisor
	 *      itself).
	 *      </p>
	 */
	public static final Integer SYNCHRONOUS_QUOTIENT = (TILES_SIZE / 4);

	// CONSTANTES DA UI (USER INTERFACE):

	// Player's life:
	public static final Integer BARRA_DE_VIDA_PLAYER_X = 4;

	public static final Integer BARRA_DE_VIDA_PLAYER_Y = 4;

	public static final Integer BARRA_DE_VIDA_PLAYER_LARGURA = 50;

	public static final Integer BARRA_DE_VIDA_PLAYER_ALTURA = 8;

	public static final Integer TAMANHO_STRING_VIDA_PLAYER = 5 * SCALE;

	public static final Integer POSICAO_STRING_VIDA_PLAYER_X = (BARRA_DE_VIDA_PLAYER_LARGURA / 2) + 10;

	public static final Integer POSICAO_STRING_VIDA_PLAYER_Y = (BARRA_DE_VIDA_PLAYER_Y * SCALE * 2)
			+ (BARRA_DE_VIDA_PLAYER_ALTURA / 2);

	public static final String MENSAGEM_VIDA_CHEIA_PLAYER = "A SUA VIDA ESTÁ CHEIA! NÃO É POSSÍVEL COLETAR O KIT MÉDICO!";

	public static final Integer TAMANHO_MENSAGEM_VIDA_CHEIA = 3 * SCALE;

	public static final Integer POSICAO_MENSAGEM_VIDA_CHEIA_X = BARRA_DE_VIDA_PLAYER_X * SCALE;

	public static final Integer POSICAO_MENSAGEM_VIDA_CHEIA_Y = (BARRA_DE_VIDA_PLAYER_ALTURA) * SCALE * 2;

	// Munition:
	public static final Integer TAMANHO_STRING_MUNICAO_PLAYER = 4 * SCALE;

	public static final Integer POSICAO_STRING_MUNICAO_PLAYER_X = (DIMENSAO_LARGURA / 2) + 200;

	public static final Integer POSICAO_STRING_MUNICAO_PLAYER_Y = 30;

	// CONSTANTES DO PLAYER:

	public static final double VIDA_MAXIMA_PLAYER = 100.0;

	public static final double VIDA_PLAYER = VIDA_MAXIMA_PLAYER;

	public static final Integer DEFESA_PLAYER = 20;

	public static final Integer ATAQUE_PLAYER = 40;

	public static final Integer ESQUIVA_PLAYER = 30;

	public static final Integer CHANCE_DE_ACERTO_PLAYER = 90;

	public static final double VELOCIDADE_PLAYER = 1;

	public static final Integer POSICAO_IMAGEM_PLAYER_X = 0;

	public static final Integer POSICAO_IMAGEM_PLAYER_Y = 0;

	public static final Integer LARGURA_PLAYER = 16;

	public static final Integer ALTURA_PLAYER = 16;

	public static final Double PONTO_CENTRAL_PLAYER_X = (double) (LARGURA_PLAYER / 2);

	public static final Double PONTO_CENTRAL_PLAYER_Y = (double) (ALTURA_PLAYER / 2);

	public static final Integer MASCARA_PLAYER_X = 2;

	public static final Integer MASCARA_PLAYER_Y = 2;

	public static final Integer MASCARA_PLAYER_WIDTH = 10;

	public static final Integer MASCARA_PLAYER_HEIGHT = 14;

	public static final Integer QUANTIDADE_IMAGENS_WALK_PLAYER = 4;

	public static final Integer QUANTIDADE_IMAGENS_RIFLEGUN_PLAYER = 4;

	public static final Integer TAMANHO_INVENTARIO_PLAYER = 500;

	public static final Integer TAMANHO_INVENTARIO_DISPONIVEL = TAMANHO_INVENTARIO_PLAYER;

	public static final Integer TAMANHO_INVENTARIO_OCUPADO = 0;

	public static final Integer MUNICAO_INICIAL_PLAYER = 0;

	public static final Integer MUNICAO_MAXIMA_PLAYER = 3000;

	// CONSTANTES DO ENEMY (1):

	public static final Double VIDA_MAXIMA_ENEMY_1 = 500.0;

	public static final Double VIDA_ENEMY_1 = VIDA_MAXIMA_ENEMY_1;

	public static final Integer DEFESA_ENEMY_1 = 10;

	public static final Integer ATAQUE_ENEMY_1 = 21;

	public static final Integer ESQUIVA_ENEMY_1 = 20;

	public static final Integer CHANCE_DE_ACERTO_ENEMY_1 = 50;

	public static final double VELOCIDADE_ENEMY_1 = 1;

	public static final Integer POSICAO_IMAGEM_ENEMY_1_X = 0;

	public static final Integer POSICAO_IMAGEM_ENEMY_1_Y = 0;

	public static final Integer LARGURA_ENEMY_1 = 16;

	public static final Integer ALTURA_ENEMY_1 = 16;

	public static final Double PONTO_CENTRAL_ENEMY_1_X = (double) (LARGURA_PLAYER / 2);

	public static final Double PONTO_CENTRAL_ENEMY_1_Y = (double) (ALTURA_PLAYER / 2);

	public static final Integer MASCARA_ENEMY_1_X = (int) (PONTO_CENTRAL_ENEMY_1_X - 4);

	public static final Integer MASCARA_ENEMY_1_Y = (int) (PONTO_CENTRAL_ENEMY_1_Y - 6);

	public static final Integer MASCARA_ENEMY_1_WIDTH = 8;

	public static final Integer MASCARA_ENEMY_1_HEIGHT = 12;

	public static final Integer QUANTIDADE_IMAGENS_WALK_ENEMY_1 = 4;

	public static final Integer QUANTIDADE_IMAGENS_ATTACK_ENEMY_1 = 6;

	// CONSTANTES DA GRAMA / GRASS:

	public static final Integer POSICAO_IMAGEM_GRAMA_X = 0;

	public static final Integer POSICAO_IMAGEM_GRAMA_Y = 0;

	public static final Integer LARGURA_GRAMA = TILES_SIZE;

	public static final Integer ALTURA_GRAMA = TILES_SIZE;

	// CONSTANTES DA PAREDE CINZA / GREY WALL (1):

	public static final Integer POSICAO_IMAGEM_PAREDE_CINZA_X = 16;

	public static final Integer POSICAO_IMAGEM_PAREDE_CINZA_Y = 0;

	public static final Integer LARGURA_PAREDE_CINZA = TILES_SIZE;

	public static final Integer ALTURA_PAREDE_CINZA = TILES_SIZE;

	// CONSTANTES DO KIT MEDICO / LIFE PACK:

	public static final Integer POSICAO_IMAGEM_LIFEPACK_X = 0;

	public static final Integer POSICAO_IMAGEM_LIFEPACK_Y = 0;

	public static final Integer LARGURA_LIFEPACK = TILES_SIZE;

	public static final Integer ALTURA_LIFEPACK = TILES_SIZE;

	public static final Integer MASCARA_LIFEPACK_X = 4;

	public static final Integer MASCARA_LIFEPACK_Y = 4;

	public static final Integer MASCARA_LIFEPACK_WIDTH = 7;

	public static final Integer MASCARA_LIFEPACK_HEIGHT = 9;

	public static final Integer CURA_LIFEPACK = 25;

	// CONSTANTES DAS MUNICOES / BULLETS:

	public static final Integer POSICAO_IMAGEM_BULLET_X = 30;

	public static final Integer POSICAO_IMAGEM_BULLET_Y = 0;

	public static final Integer LARGURA_BULLET = TILES_SIZE;

	public static final Integer ALTURA_BULLET = TILES_SIZE;

	public static final Integer MASCARA_BULLET_X = 5;

	public static final Integer MASCARA_BULLET_Y = 6;

	public static final Integer MASCARA_BULLET_WIDTH = 7;

	public static final Integer MASCARA_BULLET_HEIGHT = 6;

	public static final Integer QUANTIDADE_MUNICAO = 1000;
	
	//CONSTANTES DOS TIROS DE RIFLE / BULLETS SHOOT:
	
	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_X_RIGHT = 45;

	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_Y_RIGHT = 0;
	
	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_X_LEFT = 61;

	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_Y_LEFT = 0;
	
	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_X_UP = 75;

	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_Y_UP = 0;
	
	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_X_DOWN = 90;

	public static final Integer POSICAO_IMAGEM_BULLET_SHOOT_Y_DOWN = 0;

	public static final Integer LARGURA_BULLET_SHOOT_RIGHT = 4;

	public static final Integer ALTURA_BULLET_SHOOT_RIGHT = 1;
	
	public static final Integer LARGURA_BULLET_SHOOT_LEFT = 3;

	public static final Integer ALTURA_BULLET_SHOOT_LEFT = 1;
	
	public static final Integer LARGURA_BULLET_SHOOT_UP_AND_DOWN = 1;

	public static final Integer ALTURA_BULLET_SHOOT_UP_AND_DOWN = 3;
	
	public static final Integer MASCARA_BULLET_SHOOT_X = 0;

	public static final Integer MASCARA_BULLET_SHOOT_Y = 0;

	public static final Integer MASCARA_BULLET_SHOOT_WIDTH_RIGHT_AND_LEFT_DIRECTION = 4;

	public static final Integer MASCARA_BULLET_SHOOT_HEIGHT_RIGHT_AND_LEFT_DIRECTION = 1;
	
	public static final Integer MASCARA_BULLET_SHOOT_WIDTH_UP_AND_DOWN_DIRECTION = 1;

	public static final Integer MASCARA_BULLET_SHOOT_HEIGHT_UP_AND_DOWN_DIRECTION = 4;
	
	public static final Integer VELOCIDADE_BULLET_SHOOT = 5;
	
	public static final double DISTANCIA_INICIAL_BULLET_SHOOT = 0.0;
	
	public static final double DISTANCIA_MAXIMA_BULLET_SHOOT = 40.0;
	
	public static final Integer DANO_BULLET_SHOOT = 75;

	// CONSTANTES DA ARMA / WEAPON (1):

	public static final Integer POSICAO_IMAGEM_WEAPON_1_X = 15;

	public static final Integer POSICAO_IMAGEM_WEAPON_1_Y = 0;

	public static final Integer LARGURA_WEAPON_1 = TILES_SIZE;

	public static final Integer ALTURA_WEAPON_1 = TILES_SIZE;

	public static final Integer MASCARA_WEAPON_1_X = 4;

	public static final Integer MASCARA_WEAPON_1_Y = 6;

	public static final Integer MASCARA_WEAPON_1_WIDTH = 9;

	public static final Integer MASCARA_WEAPON_1_HEIGHT = 4;

	// CONSTANTES DOS PORTOES / GATES (1):
	
	public static final Integer POSICAO_IMAGEM_GATE_1_X_UP = 16;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_Y_UP = 16;
	
	public static final Integer LARGURA_GATE_1_UP = TILES_SIZE;

	public static final Integer ALTURA_GATE_1_UP = TILES_SIZE;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_X_RIGHT = 47;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_Y_RIGHT = 0;
	
	public static final Integer LARGURA_GATE_1_RIGHT = TILES_SIZE;

	public static final Integer ALTURA_GATE_1_RIGHT = TILES_SIZE;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_X_LEFT = 63;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_Y_LEFT = 0;
	
	public static final Integer LARGURA_GATE_1_LEFT = TILES_SIZE;

	public static final Integer ALTURA_GATE_1_LEFT = TILES_SIZE;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_X_DOWN = 79;
	
	public static final Integer POSICAO_IMAGEM_GATE_1_Y_DOWN = 0;
	
	public static final Integer LARGURA_GATE_1_DOWN = TILES_SIZE;

	public static final Integer ALTURA_GATE_1_DOWN = TILES_SIZE;
	
	
	
	// CONSTANTES DOS NOMES:

	public static final String NOME_1 = "Konrad Kranz";

	public static final String NOME_2 = "Chetan Van Der Ven";

	public static final String NOME_3 = "Ashwin McTaggart";

	public static final String NOME_4 = "Alexandre Byrd";

	public static final String NOME_5 = "Orfeo Lesley";

	public static final String NOME_6 = "Loyd Shannon";

	public static final String NOME_7 = "Mahmud Nilsen";

	public static final String NOME_8 = "Lorenzo Stefanidis";

	public static final String NOME_9 = "Glooscap Krantz";

	public static final String NOME_10 = "Eztebe Schoorel";

	// CONSTANTES DAS GUILDAS:

	public static final String GUILDA_1 = "Legion of the Dissipated";

	public static final String GUILDA_2 = "Boon of the Betrayed";

	public static final String GUILDA_3 = "Stalkers of the Weasel";

	public static final String GUILDA_4 = "Chaos Hearts";

	public static final String GUILDA_5 = "Reckless Predators";

	public static final String GUILDA_6 = "Corrupted Sharpshooters";

	// CONSTANTES DAS ESTORIAS:

	public static final String ESTORIA_PLAYER = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin a sagittis "
			+ "mauris. Vestibulum imperdiet urna in metus pulvinar, semper congue massa volutpat. Donec et metus eu neque "
			+ "ornare egestas. Duis interdum tortor quis est vestibulum posuere. Quisque tincidunt bibendum ultrices. Aenean "
			+ "bibendum convallis nisl, at dignissim sapien rhoncus nec. Nam bibendum, mi in ultrices volutpat, dolor tellus "
			+ "tempus lorem, ut fermentum nunc mauris nec mauris. Sed id euismod sapien, eget aliquam lorem. Vestibulum ante "
			+ "ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam rutrum eget metus in porta. Etiam "
			+ "sollicitudin dolor nisi, vel auctor nisi gravida a. Proin porta, augue ac lacinia feugiat, ipsum sapien finibus"
			+ " turpis, sit amet rhoncus arcu ante sit amet lacus. Vivamus ut nibh quis risus euismod malesuada iaculis ac eros.";

	public static final String ESTORIA_ENEMY = "Aenean tempor sagittis malesuada. Nunc nisl neque, blandit vitae diam et, euismod "
			+ "lacinia ligula. Nulla aliquam augue erat, sed tristique tellus luctus quis. Ut enim sapien, finibus vel quam "
			+ "at, placerat sodales quam. Sed vitae posuere lacus. Vestibulum ornare nisl sit amet mi vulputate feugiat. "
			+ "Nullam vitae consectetur metus. Maecenas blandit dictum lorem, vitae pulvinar dolor vestibulum sit amet. "
			+ "Etiam convallis metus eu rhoncus laoreet. Integer efficitur, massa eu pretium aliquet, velit arcu ultrices lacus"
			+ ", eu tincidunt ante turpis sit amet augue. Nullam volutpat at ligula sit amet tincidunt. Morbi laoreet porttitor"
			+ " pretium.";

}
