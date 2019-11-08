package exceptions;

public class ValorNegativoException extends Exception {

	private static final long serialVersionUID = 7939498720245072879L;
	
	protected double valor;

	public ValorNegativoException(double valor) {
		super();
		this.valor = valor;

	}

	@Override
	public String toString() {
		return "ValorNegativoException [valor=" + valor
				+ "] \n Foi passado um valor negativo pelo parâmetro, era esperado somente valores positivos";
	}

}
