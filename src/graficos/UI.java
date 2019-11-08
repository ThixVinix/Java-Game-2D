package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import aplicacao.Game;
import entidades.Lifepack;
import ferramentas.Constantes;
import mb.EntityMB;

public class UI {

	private EntityMB entityMB = new EntityMB();

	/**
	 * TODOS OS METODOS DE RENDERIZACAO CONTIDOS NESTE METODO SERAO ESTICADOS PELO
	 * "SCALE" DO GAME. *
	 */
	public void renderizarDesignInterfacePlayer(Graphics graficos) {
		renderizarBarraDeVidaPlayerUI(graficos);
	}

	/**
	 * ESTE METODO RENDERIZA AS STRINGS QUE NAO ESTAO SENDO ESTICADAS PELO "SCALE".
	 * POR ISSO, DEVEM RECEBER UM TRATAMENTO PARA SE ADAPTAREM DE ACORDO COM O
	 * "SCALE".
	 */
	public void renderizarStringInterfacePlayer(Graphics graficos) {
		renderizarStringVidaPlayerUI(graficos);
		notificarVidaMaximaUI(graficos);
		renderizarQuantidadeMunicaoPlayerUI(graficos);
	}

	private void renderizarBarraDeVidaPlayerUI(Graphics graficos) {
		graficos.setColor(Color.RED);
		graficos.fill3DRect(Constantes.BARRA_DE_VIDA_PLAYER_X, Constantes.BARRA_DE_VIDA_PLAYER_Y,
				Constantes.BARRA_DE_VIDA_PLAYER_LARGURA, Constantes.BARRA_DE_VIDA_PLAYER_ALTURA, true);

		if (Game.player.getVida() > 40) {
			graficos.setColor(Color.GREEN);
			graficos.fill3DRect(Constantes.BARRA_DE_VIDA_PLAYER_X, Constantes.BARRA_DE_VIDA_PLAYER_Y,
					(int) ((Game.player.getVida() / Constantes.VIDA_MAXIMA_PLAYER)
							* Constantes.BARRA_DE_VIDA_PLAYER_LARGURA),
					Constantes.BARRA_DE_VIDA_PLAYER_ALTURA, true);
		} else if (Game.player.getVida() > 0) {
			graficos.setColor(Color.ORANGE);
			graficos.fill3DRect(Constantes.BARRA_DE_VIDA_PLAYER_X, Constantes.BARRA_DE_VIDA_PLAYER_Y,
					(int) ((Game.player.getVida() / Constantes.VIDA_MAXIMA_PLAYER)
							* Constantes.BARRA_DE_VIDA_PLAYER_LARGURA),
					Constantes.BARRA_DE_VIDA_PLAYER_ALTURA, true);
		}

	}

	private void renderizarStringVidaPlayerUI(Graphics graficos) {
		graficos.setColor(Color.BLACK);
		graficos.setFont(new Font("arial", Font.BOLD, Constantes.TAMANHO_STRING_VIDA_PLAYER));
		graficos.drawString((int) Game.player.getVida() + " / " + (int) Constantes.VIDA_MAXIMA_PLAYER,
				Constantes.POSICAO_STRING_VIDA_PLAYER_X, Constantes.POSICAO_STRING_VIDA_PLAYER_Y);
	}

	private void notificarVidaMaximaUI(Graphics graficos) {

		for (int i = 0; i < Game.lifePacks.size(); i++) {
			Lifepack atual = Game.lifePacks.get(i);

			if (entityMB.isCollidingWithAnotherEntity(Game.player, atual)) {
				if (Game.player.getVida() >= Constantes.VIDA_MAXIMA_PLAYER) {
					graficos.setColor(Color.RED);
					graficos.setFont(new Font("Arial Bold", Font.BOLD, Constantes.TAMANHO_MENSAGEM_VIDA_CHEIA));
					graficos.drawString(Constantes.MENSAGEM_VIDA_CHEIA_PLAYER, Constantes.POSICAO_MENSAGEM_VIDA_CHEIA_X,
							Constantes.POSICAO_MENSAGEM_VIDA_CHEIA_Y);
				}
			}

		}

	}

	private void renderizarQuantidadeMunicaoPlayerUI(Graphics graficos) {
		graficos.setColor(Color.WHITE);
		graficos.setFont(new Font("arial", Font.BOLD, Constantes.TAMANHO_STRING_MUNICAO_PLAYER));
		graficos.drawString("Munição de Rifle: " + Game.player.getBulletsPistol(),
				Constantes.POSICAO_STRING_MUNICAO_PLAYER_X, Constantes.POSICAO_STRING_MUNICAO_PLAYER_Y);

	}

}
