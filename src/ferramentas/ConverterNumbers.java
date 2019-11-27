package ferramentas;

public class ConverterNumbers {

	public static Long trasformarEmPositivo(Long num) {

		if (num < 0) {
			num = -(num);
		}

		return num;

	}

}
