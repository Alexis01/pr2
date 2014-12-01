package tp.pr2.logica;

public abstract class Movimiento {

	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. (Puede hacerse abstracto)
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public abstract Ficha getJugador();
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab-Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab);
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; en caso contrario,
	 *  el comportamiento es indeterminado. 
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab-Tablero de donde deshacer el movimiento.
	 */
	public abstract void undo(Tablero tab);
	
}
