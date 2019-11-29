package abstracts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import aplicacao.Game;
import enums.StatusPersonagemEnum;
import ferramentas.Constantes;
import ferramentas.ConverterNumbers;
import interfaces.EntityActions;
import interfaces.LoopingEntity;
import mb.EntityMB;
import mediadores.EntityMediator;
import world.Camera;

public abstract class Entity implements LoopingEntity, EntityActions {

	public static final BufferedImage LIFEPACK_ENTITY = Game.itens.getSpritesheet(Constantes.POSICAO_IMAGEM_LIFEPACK_X,
			Constantes.POSICAO_IMAGEM_LIFEPACK_Y, Constantes.LARGURA_LIFEPACK, Constantes.ALTURA_LIFEPACK);

	public static final BufferedImage WEAPON_1_ENTITY = Game.itens.getSpritesheet(Constantes.POSICAO_IMAGEM_WEAPON_1_X,
			Constantes.POSICAO_IMAGEM_WEAPON_1_Y, Constantes.LARGURA_WEAPON_1, Constantes.ALTURA_WEAPON_1);

	public static final BufferedImage BULLET_ENTITY = Game.itens.getSpritesheet(Constantes.POSICAO_IMAGEM_BULLET_X,
			Constantes.POSICAO_IMAGEM_BULLET_Y, Constantes.LARGURA_BULLET, Constantes.ALTURA_BULLET);

	public static final BufferedImage BULLETS_SHOOT_RIFLE_RIGHT = Game.itens.getSpritesheet(
			Constantes.POSICAO_IMAGEM_BULLET_SHOOT_X_RIGHT, Constantes.POSICAO_IMAGEM_BULLET_SHOOT_Y_RIGHT,
			Constantes.LARGURA_BULLET_SHOOT_RIGHT, Constantes.ALTURA_BULLET_SHOOT_RIGHT);

	public static final BufferedImage BULLETS_SHOOT_RIFLE_LEFT = Game.itens.getSpritesheet(
			Constantes.POSICAO_IMAGEM_BULLET_SHOOT_X_LEFT, Constantes.POSICAO_IMAGEM_BULLET_SHOOT_Y_LEFT,
			Constantes.LARGURA_BULLET_SHOOT_LEFT, Constantes.ALTURA_BULLET_SHOOT_LEFT);

	public static final BufferedImage BULLETS_SHOOT_RIFLE_UP = Game.itens.getSpritesheet(
			Constantes.POSICAO_IMAGEM_BULLET_SHOOT_X_UP, Constantes.POSICAO_IMAGEM_BULLET_SHOOT_Y_UP,
			Constantes.LARGURA_BULLET_SHOOT_UP_AND_DOWN, Constantes.ALTURA_BULLET_SHOOT_UP_AND_DOWN);

	public static final BufferedImage BULLETS_SHOOT_RIFLE_DOWN = Game.itens.getSpritesheet(
			Constantes.POSICAO_IMAGEM_BULLET_SHOOT_X_DOWN, Constantes.POSICAO_IMAGEM_BULLET_SHOOT_Y_DOWN,
			Constantes.LARGURA_BULLET_SHOOT_UP_AND_DOWN, Constantes.ALTURA_BULLET_SHOOT_UP_AND_DOWN);

	public static final BufferedImage ENEMY_1_ENTITY = Game.inimigos.getSpritesheet(Constantes.POSICAO_IMAGEM_ENEMY_1_X,
			Constantes.POSICAO_IMAGEM_ENEMY_1_Y, Constantes.LARGURA_ENEMY_1, Constantes.ALTURA_ENEMY_1);

	public static EntityMB entityMB = new EntityMB();
	
	public static EntityMediator mediador = new EntityMediator();

	private Long id;
	private Entity otherEntity;
	
	private String nome;
	private String guilda;
	private String estoria;

	private Double vidaMax;
	private Double vida;
	private Integer defesa;
	private Integer ataque;
	private Integer esquiva;
	private Integer chanceAcerto;
	private double speed;
	private StatusPersonagemEnum status;
	private Integer bulletsPistol;

	private boolean shooted, armed, attacked, moved, attacking;
	private double x, y;
	private Integer width, height;
	private BufferedImage sprite;
	private boolean right, left, up, down;
	private Integer maskX, maskY, maskWidth, maskHeight;

	// "x" and "y" type Integer.
	public Entity(Integer x, Integer y, Integer width, Integer height, BufferedImage sprite) {
		
		id = gerarId();
		System.out.println("ID GERADO: " + id);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.maskX = 0;
		this.maskY = 0;
		this.maskWidth = width;
		this.maskHeight = height;
	}

	// "x" and "y" type Double.
	public Entity(Double x, Double y, Integer width, Integer height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.maskX = 0;
		this.maskY = 0;
		this.maskWidth = width;
		this.maskHeight = height;
	}

	public Entity(Double vida, Integer defesa, Integer ataque, Double speed) {
		this.vida = vida;
		this.defesa = defesa;
		this.ataque = ataque;
		this.speed = speed;
	}

	@Override
	public void render(Graphics graficos) {
		graficos.drawImage(sprite, this.getIntegerX() - Camera.getX(), this.getIntegerY() - Camera.getY(), null);
//		mostrarMascarasItens(graficos);

	}

	@Override
	public void update() {

	}

	@SuppressWarnings(value = { "unused" })
	private void mostrarMascarasItens(Graphics graficos) {
		graficos.setColor(Color.PINK);
		graficos.fillRect(getIntegerX() + maskX - Camera.getX(), getIntegerY() + maskY - Camera.getY(), maskWidth,
				maskHeight);
	}

	/**
	 * <p>
	 * UTILIZE ESTE METODO CASO QUEIRA MODIFICAR A MASCARA DA ENTIDADE EM QUESTAO.
	 * </p>
	 * 
	 * POR PADRAO, OS VALORES DAS MASCARAS SAO: <br>
	 * 
	 * maskX = 0 <br>
	 * maskY = 0 <br>
	 * maskWidth = width (LARGURA DA ENTIDADE) <br>
	 * maskHeight = height (ALTURA DA ENTIDADE) <br>
	 */
	public void setMask(Integer maskX, Integer maskY, Integer maskWidth, Integer maskHeight) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskWidth = maskWidth;
		this.maskHeight = maskHeight;
	}

	private Long gerarId() {
		Long cod = ConverterNumbers.trasformarEmPositivo(Game.random.nextLong());
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity entityActual = Game.entities.get(i); 
			
			if (entityActual.getId().equals(cod)) {
				return gerarId();
			}
		}
		
		return cod;
	}
	
	public  StatusPersonagemEnum verificarStatus() {

		if (this.getVida() > 40) {
			this.setStatus(StatusPersonagemEnum.VIVO);
			return StatusPersonagemEnum.VIVO;
		} else if (this.getVida() > 0 && this.getVida() <= 40) {
			this.setStatus(StatusPersonagemEnum.FERIDO);
			return StatusPersonagemEnum.FERIDO;
		} else {
			this.setStatus(StatusPersonagemEnum.MORTO);
			return StatusPersonagemEnum.MORTO;
		}

	}
	
	public Integer getIntegerX() {
		return (int) x;
	}

	public void setIntegerX(int x) {
		this.x = x;
	}

	public Integer getIntegerY() {
		return (int) y;
	}

	public void setIntegerY(int y) {
		this.y = y;
	}

	public double getDoubleX() {
		return x;
	}

	public void setDoubleX(double x) {
		this.x = x;
	}

	public double getDoubleY() {
		return y;
	}

	public void setDoubleY(double y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public Integer getIntegerSpeed() {
		return (int) speed;
	}

	public void setIntegerSpeed(int speed) {
		this.speed = speed;
	}

	public double getDoubleSpeed() {
		return speed;
	}

	public void setDoubleSpeed(double speed) {
		this.speed = speed;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGuilda() {
		return guilda;
	}

	public void setGuilda(String guilda) {
		this.guilda = guilda;
	}

	public String getEstoria() {
		return estoria;
	}

	public void setEstoria(String estoria) {
		this.estoria = estoria;
	}

	public double getVida() {
		return vida;
	}

	public void setVida(Double vida) {
		this.vida = vida;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getEsquiva() {
		return esquiva;
	}

	public void setEsquiva(Integer esquiva) {
		this.esquiva = esquiva;
	}

	public Integer getChanceAcerto() {
		return chanceAcerto;
	}

	public void setChanceAcerto(Integer chanceAcerto) {
		this.chanceAcerto = chanceAcerto;
	}

	public StatusPersonagemEnum getStatus() {
		return status;
	}

	public void setStatus(StatusPersonagemEnum status) {
		this.status = status;
	}

	public Integer getMaskX() {
		return maskX;
	}

	public void setMaskX(Integer maskX) {
		this.maskX = maskX;
	}

	public Integer getMaskY() {
		return maskY;
	}

	public void setMaskY(Integer maskY) {
		this.maskY = maskY;
	}

	public Integer getMaskWidth() {
		return maskWidth;
	}

	public void setMaskWidth(Integer maskWidth) {
		this.maskWidth = maskWidth;
	}

	public Integer getMaskHeight() {
		return maskHeight;
	}

	public void setMaskHeight(Integer maskHeight) {
		this.maskHeight = maskHeight;
	}

	public Integer getBulletsPistol() {
		return bulletsPistol;
	}

	public void setBulletsPistol(Integer bulletsPistol) {
		this.bulletsPistol = bulletsPistol;
	}

	public boolean isShooted() {
		return shooted;
	}

	public void setShooted(boolean shooted) {
		this.shooted = shooted;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public Double getVidaMax() {
		return vidaMax;
	}

	public void setVidaMax(Double vidaMax) {
		this.vidaMax = vidaMax;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public Long getId() {
		return id;
	}

	public Entity getOtherEntity() {
		return otherEntity;
	}

	public void setOtherEntity(Entity otherEntity) {
		this.otherEntity = otherEntity;
	}
	
	

}
