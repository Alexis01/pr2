package tp.pr2.logica;

import tp.pr2.utils.Constants;


/**
 * Enumerado que muestra el tipo de ficha que tenemos
 * @author Planet Media
 *
 */
public enum Ficha{
	BLANCA, NEGRA, VACIA;
	
	/**
	 * Returns string representation of a piece v1 
	 * @param ficha la ficha del tablero
	 * @return x,o,. Dependiendo de la representaci�n
	 */
	public static String valueOfFicha(Ficha ficha){
		
		if(ficha==BLANCA){
			return Constants.VBLANCAS;
		}
		else{
			if(ficha==NEGRA){
				return Constants.VNEGRAS;
			}
			else{
				if(ficha==VACIA){
					return Constants.VVACIAS;
				}
				
			}
		}
		return null;
	}
	/**
	 * Returns string representation of a piece v2
	 * @param ficha la ficha del tablero
	 * @return x,o,. Dependiendo de la representaci�n
	 */
	public static String valueOfFichaPrint(Ficha ficha){
		
		if(ficha==BLANCA){
			return Constants.SYSO_BLANCAS;
		}
		else{
			if(ficha==NEGRA){
				return Constants.SYSO_NEGRAS;
			}
			else{
				if(ficha==VACIA){
					return Constants.SYSO_EMPATE;
				}
				
			}
		}
		return null;
	}
	
	
}
