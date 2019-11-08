package movimentacao;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import aplicacao.Game;

public class MovimentacaoPlayer implements KeyListener {

	// QUANDO A TECLA FOR PRESSIONADA:
	@Override
	public void keyPressed(KeyEvent eventoTeclado) {

		//MOVIMENTACAO DO JOGADOR:
		if (eventoTeclado.getKeyCode() == KeyEvent.VK_RIGHT || eventoTeclado.getKeyCode() == KeyEvent.VK_D) {
			Game.player.setRight(true);

		} else if (eventoTeclado.getKeyCode() == KeyEvent.VK_LEFT || eventoTeclado.getKeyCode() == KeyEvent.VK_A) {
			Game.player.setLeft(true);
		}

		if (eventoTeclado.getKeyCode() == KeyEvent.VK_UP || eventoTeclado.getKeyCode() == KeyEvent.VK_W) {
			Game.player.setUp(true);

		} else if (eventoTeclado.getKeyCode() == KeyEvent.VK_DOWN || eventoTeclado.getKeyCode() == KeyEvent.VK_S) {
			Game.player.setDown(true);

		}

		//ATIRAR
		if (eventoTeclado.getKeyCode() == KeyEvent.VK_E) {
			Game.player.setShooted(true);
		}
		
	}
	
	

	// QUANDO A TECLA FOR SOLTADA:
	@Override
	public void keyReleased(KeyEvent eventoTeclado) {

		if (eventoTeclado.getKeyCode() == KeyEvent.VK_RIGHT || eventoTeclado.getKeyCode() == KeyEvent.VK_D) {
			Game.player.setRight(false);
		} else if (eventoTeclado.getKeyCode() == KeyEvent.VK_LEFT || eventoTeclado.getKeyCode() == KeyEvent.VK_A) {
			Game.player.setLeft(false);
		}

		if (eventoTeclado.getKeyCode() == KeyEvent.VK_UP || eventoTeclado.getKeyCode() == KeyEvent.VK_W) {
			Game.player.setUp(false);
		} else if (eventoTeclado.getKeyCode() == KeyEvent.VK_DOWN || eventoTeclado.getKeyCode() == KeyEvent.VK_S) {
			Game.player.setDown(false);
		}

		//ATIRAR
				if (eventoTeclado.getKeyCode() == KeyEvent.VK_E) {
					Game.player.setShooted(false);
				}
	}

	@Override
	public void keyTyped(KeyEvent eventoTeclado) {
		// TODO Auto-generated method stub

	}

}
