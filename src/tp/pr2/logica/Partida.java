package tp.pr2.logica;

import tp.pr2.utils.Constants;
/**
 * Clase partida, encargada de manejar una partida con un tablero
 * @author Planet Media
 *
 */
public class Partida {
	private Ficha ganador;
	private Pila undoStack;
	private Tablero tablero;
	private Ficha turno; 
	private boolean terminada; 

	
	/**
	 * Default constructor
	 */
	public Partida(){
		ganador=Ficha.VACIA;
		undoStack= new Pila(Constants.MAX_UNDO);
		turno=Ficha.BLANCA;
		tablero=new Tablero(Constants.MAX_COLS ,  Constants.MAX_FIL);
		terminada=false;
	}
	/**
	 *  Ejecuta el movimiento indicado.
	 * @param color Color del jugador que pone
	 * @param col Columna donde colocar la ficha (1..7)
	 * @return true si se puede efectuar el movimiento.
	 *  Es un error intentar colocar una ficha del jugador que no tiene el turno, cuando la partida est� terminada, columna llena, ...
	 */
	public boolean ejecutaMovimiento(Ficha color, int col){
		boolean ok=false;
		int userToProgrammer=col-1;
		if(!terminada){
			if(userToProgrammer>=0 && userToProgrammer<Constants.MAX_COLS && color==turno){
				int result=tablero.insertInColumn(color, userToProgrammer);
				if(result!=-1 && result!=-2){
					ok=true;
					undoStack.insertToStack(userToProgrammer);
					if(tablero.theUserhasWon(result, userToProgrammer,turno)){
						terminada=true;
						ganador=turno;
					}
					else{
						setNextTurn();						
					}		
				}
				if(result==-2){
					ok=true;
					ganador=Ficha.VACIA;
					terminada=true;
					System.out.println(Constants.DRAW);
				}	
			}
		}
		
		return ok;
	}
	/**
	 * Setea el siguiente turno despues de un movimiento o un undo
	 */
	private void setNextTurn(){
		if(turno==Ficha.BLANCA){
			turno=Ficha.NEGRA;
		}
		else{
			turno=Ficha.BLANCA;
		}
	}
	/**
	 * Restart the current game
	 */
	public void reset(){
		ganador=Ficha.VACIA;
		undoStack= new Pila(Constants.MAX_UNDO);
		turno=Ficha.BLANCA;
		tablero.resetTable();
		terminada=false;

	}
	/**
	 * Undoes the last movement executed
	 * @return true si se pudo deshacer
	 */
	public boolean undo(){
		boolean ok=false;
		if(!undoStack.empty()){
			int column=undoStack.getFromStack();
			if(column!=-1){
				tablero.deletePiece(column);
				ok=true;
				setNextTurn();	
			}
		}
		return ok;
	}
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno(){	
		return turno;
	}
	/**
	 * 
	 * @return Color del ganador. Si la partida termin� en tablas, Ficha.VACIA. Si la partida no ha terminado a�n,
	 * el resultado es indeterminado.
	 */
	public Ficha getGanador(){
		return ganador;
	}
	/**
	 * M�todo para saber si la partida ha conlu�do ya o no.
	 * @return true si la partida ha acabado
	 */
	public boolean isTerminada(){
		return terminada;
	}
	/**
	 * M�todo de acceso al tablero. Dependiendo de c�mo se haga la implementaci�n del resto de clases (principalmente de la clase Controlador),
	 * es posible que no se utilice para nada en la pr�ctica. 
	 * Sin embargo, es necesario implementarlo para poder ejecutar los test de unidad.
	 * @return Estado del tablero actual
	 */
	public Tablero getTablero(){
		return tablero;
	}

	/**
	 * Return the current turn
	 * @return
	 */
	public String printTurn(){
		return getTurno().toString();
	}
	

	
}
