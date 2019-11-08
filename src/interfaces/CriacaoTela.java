package interfaces;

public interface CriacaoTela {

	// this.setPreferredSize(new Dimension(WIDTH_SCREEN*SCALE, HEIGHT_SCREEN*SCALE));
	public abstract void criarDimensoesTela();

	// frame = new JFrame("Meu jogo");
	// frame.add(this);
	// frame.pack();
	// frame.setResizable(false);
	// frame.setLocationRelativeTo(null);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setVisible(true);
	public abstract void criarTela();

	// image = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_RGB);
	public abstract void criarMemoriaTemporaria();

}
