package tp.pr2.logica;

public class MovimientoConecta4 extends Movimiento{
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
	public MovimientoConecta4(int donde,Ficha color){
		posicion=donde;
		jugador=color;
	}

}
