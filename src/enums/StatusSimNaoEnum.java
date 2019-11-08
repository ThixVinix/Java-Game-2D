package enums;

public enum StatusSimNaoEnum {

	SIM('S'), 
	NAO('N');
	
	private char descricaoSimNao;

	private StatusSimNaoEnum(char descricaoSimNao) {
		this.descricaoSimNao = descricaoSimNao;
	}
	
	public char getDescricaoSimNao() {
		return descricaoSimNao;
	}


}
