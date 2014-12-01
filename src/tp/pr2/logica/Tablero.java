package tp.pr2.logica;


/**
 * Representa el tablero de una partida
 * @author Planet Media
 *
 */
public class Tablero {
	private int tamColX;
	private int tamFilY;
	private Ficha[][] tablero;

	/**
	 * Default constructor
	 * @param tx
	 * @param ty
	 */
	public Tablero(int tx, int ty) {
		super();
		if(tx<1 && ty<1){
			tamColX = 1;
			tamFilY = 1;
		}
		else{
			tamColX = tx;
			tamFilY = ty;
		}
		tablero=new Ficha[tamColX][tamFilY];
		resetTable();
		
	}
	/**
	 * Reinicia el tablero
	 */
	public void resetTable(){
		for(int fil=0;fil<tamFilY;fil++){
			for (int col = 0; col < tamColX; col++){
				tablero[col][fil]=Ficha.VACIA;
			}	
		}
	}
	
	/**
	 * M�todo para obtener el alto del tablero.
	 * @return N�mero de filas del tablero
	 */
	public int getAlto(){
		return  tamFilY;
	}
	/**
	 * M�todo para obtener el ancho del tablero.
	 * @return N�mero de columnas del tablero.
	 */
	public int getAncho(){
		return tamColX;
	}
	/**
	 * M�todo para acceder a la informaci�n de una casilla o celda concreta
	 * @param x N�mero de columna (1..ancho)
	 * @param y N�mero de fila (1..alto)
	 * @return
	 */
	public Ficha getCasilla(int x,int y){
		Ficha f = Ficha.VACIA;
		if(x-1>=0 && x-1<getAncho() && y-1>=0 && y-1<getAlto() ){ //&& x!=0 && y!=0
			f=tablero[x-1 ][  y-1];
		}
		return f;
	}
	/**
	 * 
	 * @param x N�mero de columna (1..ancho)
	 * @param y N�mero de fila (1..alto)
	 * @param color Color de la casilla. Si se indica Ficha.VACIA, la celda quedar� sin ficha
	 */
	public void setCasilla(int x,int y,Ficha color){
		if(x-1>=0 && x-1<tamColX && y-1>=0 && y-1<tamFilY ){
			tablero[x-1][y-1]=color;
		}
	}
	/**
	 * Prints the current table
	 */
	public void toStringTable() {
		for(int fil=0;fil<getAlto();fil++){
			System.out.print("|");
			for (int col = 0; col < getAncho(); col++){
				System.out.print(Ficha.valueOfFicha(tablero[col][fil]));
			}
			System.out.print("|\n");
		}
		for(int i=0;i<=getAncho()+1;i++){
			if(i==0 || i==getAncho()+1){
				System.out.print("+");
			}
			else{
				System.out.print("-");
			}
		}
		System.out.print("\n");
		for(int i=0;i<=getAncho()+1;i++){
			if(i==0 || i==getAncho()+1){
				System.out.print(" ");
			}
			else{
				System.out.print(i);
			}
		}
		System.out.print("\n");
	}
	
	@Override
	public String toString() {
		String result="";
		for(int fil=0;fil<getAlto();fil++){
			//System.out.print("|");
			result+="|";
			for (int col = 0; col < getAncho(); col++){
				//System.out.print(Ficha.valueOfFicha(tablero[col][fil]));
				result+=Ficha.valueOfFicha(tablero[col][fil]);
			}
			//System.out.print("|\n");
			result+="|\n";
		}
		for(int i=0;i<=getAncho()+1;i++){
			if(i==0 || i==getAncho()+1){
				//System.out.print("+");
				result+="+";
			}
			else{
				//System.out.print("-");
				result+="-";
			}
		}
		//System.out.print("\n");
		result+="\n";
		for(int i=0;i<=getAncho()+1;i++){
			if(i==0 || i==getAncho()+1){
				//System.out.print(" ");
				result+=" ";
			}
			else{
				//System.out.print(i);
				result+=i;
			}
		}
		//System.out.print("\n");
		result+="\n";
		return result;
	}
	/**
	 * Inserta una ficha en la columna, si procede
	 * @param f ficha a meter
	 * @param c columna 
	 * @return ok si se ha podido insertar
	 */
	public int insertInColumn(Ficha f, int c){
		boolean ok=false;
		int i = getAlto()-1;
		int fila=-1;
		while(i>=0 && !ok){
			if(tablero[c][i].toString().equalsIgnoreCase(Ficha.VACIA.toString())){
				ok=true;
				tablero[c][i]=f;
				fila=i;
			}
			i--;
		}
		if(hasEndedInADraw()){
			fila=-2;
		}
		return fila;
	}
	/**
	 * Verify if the match has ended
	 * @return
	 */
	private boolean hasEndedInADraw(){
		boolean ok=true;
		for(int fil=0;fil<getAlto();fil++){				
			for (int col = 0; col < getAncho(); col++) {
					if(tablero[col][fil].toString().equalsIgnoreCase(Ficha.VACIA.toString())){
						ok=false;
					}
			}			
		}
		return ok;
	}
	/**
	 * Delete a Piece from the table, used by undo 
	 * @param col
	 * @return true if success
	 */
	public boolean deletePiece(int col){
		boolean ok=false;
		int i=0;
		while(i<=getAlto()-1 && !ok){
			if(!tablero[col][i].toString().equalsIgnoreCase(Ficha.VACIA.toString())){
				tablero[col][i]=Ficha.VACIA;
				
				ok=true;
			}
			i++;
		}
		return ok;
	}
	
	/**
	 * Verify if the pieces repeats 4 times in a row
	 * @param fila
	 * @param column
	 * @param f
	 * @return
	 */
	private boolean checkRow(int fila, int column, String ficha){
		int fil=0,col=0, cont=0;
		
		boolean ok=false;
		while(fil<getAlto() && !ok){
			col=0;
			cont=0;
			while(col <getAncho() && !ok) {
				if(!Ficha.valueOfFicha(tablero[col][fil]).equals(ficha)){
					cont=0;
				}
				else{
					cont++;
				}
				if(cont==4){
					ok=true;
				}
				col++;	
			}
			fil++;
		}
		
		
		return ok;
	}
	/**
	 * Verify if the piece repeats 4 times in a column
	 * @param fila
	 * @param column
	 * @param f
	 * @return
	 */
	private boolean checkColumn( int column, String ficha){
		boolean ok=false;
		int i=0,cont=0;
		while(i<=getAlto()-1 && cont<4){
			if(!Ficha.valueOfFicha(tablero[column][i]).equals(ficha)){
				cont=0;
			}
			else{
				cont++;
				
			}
			i++;
		}
		if(cont==4){
			ok=true;
		}
		return ok;
		
	}
	/**
	 * Verify if the piece repeats 4 times in a diagonal
	 * @param fila 
	 * @param column
	 * @param ficha
	 * @return
	 */
	public boolean checkAboveDiagonal(int fila, int column, String ficha){
		int fil=0,col=0, cont=0, indexC=0, indexF=0;
		boolean ok=false;
		while(col <getAncho() && !ok){
			fil=0;
			cont=0;
			while(fil<getAlto() && !ok ){
				boolean ok2=false;
				cont=0;
				indexC=col;
				indexF=fil;
				while( indexC<getAncho() && indexF<getAlto() && indexF>=0 && indexC>=0 &&  !ok2){
					if(Ficha.valueOfFicha(tablero[indexC][indexF]).equals(ficha)){
						cont++;
						indexC++;
						indexF++;
					}
					else{
						ok2=true;						
					}
					if(cont==4){
						ok=true;
					}
				}
				fil++;	
				
			}
			col++;
		}
		return ok;
	}
	/**
	 * Verify if the piece repeats 4 times in a diagonal
	 * @param fila
	 * @param column
	 * @param ficha
	 * @return
	 */
	public boolean checkBelowDiagonal(int fila,int column,String ficha){
		int fil=getAlto()-1,col=0, cont=0, indexC=0, indexF=0;
		boolean ok=false;
		while(col<getAncho() && !ok){
			fil=getAlto()-1;
			cont=0;
			while(fil>=0 && !ok ){
				indexC=col;
				cont=0;
				indexF=fil;
				boolean ok2=false;
				while(indexC<getAncho() && indexF<getAlto() && indexF>=0 && indexC>=0 && !ok2){
					if(Ficha.valueOfFicha(tablero[indexC][indexF]).equals(ficha)){
						cont++;
						indexC++;
						indexF--;
					}
					else{
						ok2=true;						
					}
					if(cont==4){
						ok=true;
					}
				}
				fil--;	
			}
			col++;
		}
		return ok;
	}
	/**
	 * Use diferents functions to Verify if the piece repeats 4 times
	 * @param fila
	 * @param column
	 * @param f
	 * @return
	 */
	public boolean theUserhasWon(int fila, int column, Ficha f){
		//comprueba fila
		boolean ok=false;
		String valuePiece=Ficha.valueOfFicha(f);
		if(checkRow(fila, column, valuePiece)){
			ok=true;
		}
//		//comprueba columna
		if(checkColumn( column, valuePiece)){
			ok=true;
		}
//		//diagonal hacia arriba
		if(checkAboveDiagonal(fila, column, valuePiece)){
			ok=true;
		}
//		//diagonal hacia abajo
		if(checkBelowDiagonal(fila, column, valuePiece)){
			ok=true;
		}
		return ok;
	}
	
}
