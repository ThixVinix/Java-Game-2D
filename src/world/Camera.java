package world;

import abstracts.Entity;
import abstracts.Tile;
import entidades.Player;

/**
 * @see
 *      <p>
 *      ESTA CLASSE, PARA FAZER EFEITO, PRECISOU SER UTILIZADA NAS SEGUINTES
 *      CLASSES:
 *      </p>
 *      <table>
 *      <tr>
 *      <th>{@link Entity},</th>
 *      <th>{@link Player},</th>
 *      <th>{@link Tile},</th>
 *      <th>{@link Mapa},</th>
 *      <th>{@link Enemy}</th>
 *      </tr>
 *      </table>
 */
public class Camera {

	private static int x = 0;
	private static int y = 0;

	/**
	 * @see <p>This method does not allow the camera to exceed the limits of the map.</p>
	 */
	public static int clamp(int posicaoAtualCamera, int limiteMinimoMapa, int limiteMaximoMapa) {

		if (posicaoAtualCamera < limiteMinimoMapa) {
			posicaoAtualCamera = limiteMinimoMapa;
		}

		if (posicaoAtualCamera > limiteMaximoMapa) {
			posicaoAtualCamera = limiteMaximoMapa;
		}

		return posicaoAtualCamera;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Camera.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Camera.y = y;
	}

}
