package exceptions;

public class VidaMaximaException extends Exception {

	private static final long serialVersionUID = -6841437096469076271L;

	protected double vidaMax;

	public VidaMaximaException(double vidaMax) {
		super();
		this.vidaMax = vidaMax;

	}

	@Override
	public String toString() {
		return "VidaMaximaException [vidaMax=" + vidaMax + "] \n A 'vidaMax' da entidade não deve ser igual à '0'";
	}

}
