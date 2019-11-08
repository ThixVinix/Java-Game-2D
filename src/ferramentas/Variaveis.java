package ferramentas;

public class Variaveis {

	// ATRIBUTOS DE TEMPO:

	// Gets the current system time in nanoseconds (10^9ns = 1000000000 = 1 second).
	private static Long tempoAtualEmNanosegundos = System.nanoTime();

	// Gets the current system time in milliseconds (1^3ms = 1000 = 1 second).
	private static Long tempoAtualEmMillisegundos = System.currentTimeMillis();
	
	public static Long getTempoAtualEmNanosegundos() {
		return tempoAtualEmNanosegundos;
	}

	public static Long getTempoAtualEmMillisegundos() {
		return tempoAtualEmMillisegundos;
	}

}
