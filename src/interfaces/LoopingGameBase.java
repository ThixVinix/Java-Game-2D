package interfaces;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public interface LoopingGameBase {

	public abstract void update();

	//	BufferStrategy bufferStrategy = criarBufferStrategy();
	//	Graphics graficos = image.getGraphics();
	//	Graphics2D graficos2D = (Graphics2D) graficos;
	//	criarPlanoDeFundo(graficos);
	//	otimizarJogo(graficos);
	//	graficos = desenharGraficos(bufferStrategy, graficos);
	//	desenharAreaJogavel(graficos);
	//	mostrarGraficos(bufferStrategy);
	public abstract void render();

	/*
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
	 * */
	public abstract void run();
	
	//	isRunning = false;
	//	try {
	//		thread.join();
	//	} catch (InterruptedException e) {
	//		e.printStackTrace();
	//	}
	public abstract void stop();

	//	graficos.setColor(new Color(0, 0, 0));
	//	graficos.fillRect(0, 0, Constantes.WIDTH_SCREEN, Constantes.HEIGHT_SCREEN);
	public abstract void criarPlanoDeFundo(Graphics graficos);
		
	//graficos.dispose();
	public abstract void otimizarJogo(Graphics graficos);

	//graficos = bufferStrategy.getDrawGraphics();
	//return graficos;
	public abstract Graphics desenharGraficos(BufferStrategy bufferStrategy, Graphics graficos) ;

	//graficos.drawImage(image, 0, 0, (Constantes.DIMENSAO_LARGURA + 10), (Constantes.DIMENSAO_ALTURA + 10), null);
	public abstract void desenharAreaJogavel(Graphics graficos); 

	//bufferStrategy.show();
	public abstract void mostrarGraficos(BufferStrategy bufferStrategy);
	
	
}
