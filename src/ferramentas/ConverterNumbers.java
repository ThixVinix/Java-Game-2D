package ferramentas;

public class ConverterNumbers {

	public static Long converterParaPositivoLong(Long num) {
		if (num < 0) {
			num = -(num);
		}

		return num;

	}

}
