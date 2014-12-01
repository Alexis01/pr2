package tp.pr2.logica;

public class MovimientoComplica extends Movimiento {
	private int posicion;
	private Ficha jugador;
	@Override
	public Ficha getJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo(Tablero tab) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Constructor del objeto.
	 * @param donde-Columna en la que se colocará la ficha
	 * @param color-Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoComplica(int donde,Ficha color){
		posicion=donde;
		jugador=color;
	}

}
