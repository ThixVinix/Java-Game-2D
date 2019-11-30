package interfaces;

import abstracts.Entity;

public interface EntityActions {

	
	public void realizarAcoes();
	
	public void atacar(Entity atacante, Entity vitima);
	
	public boolean desviar(Integer chanceAcertoAtacante, Integer chanceEsquivaVitima);
	
	public int dissiparDano(Integer ataqueAtacante, Integer defesaVitima);
	
	public void bloquearDano();
	
//	public void defender();
//	
//	public void ganharVida();
//	
	public void diminuirVida(Integer danoRecebido);
//	
//	public void alterarVidaMaxima();
//	
//	public void ganharAtaque();
//	
//	public void perderAtaque();
//	
//	public void ganharDefesa();
//	
//	public void perderDefesa();
//	
//	public void ganharVelocidadeMovimento();
//	
//	public void perderVelocidadeMovimento();
//	
//	public void ganharChanceAcerto();
//	
//	public void perderChanceAcerto();
		
	}
