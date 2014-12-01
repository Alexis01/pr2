package tp.pr2.logica;

public class Pila {
	private int[ ] undoStack;
	private int currentPointerInit;
	private int currentPointerEnd;
	private int maxElemns;
	/**
	 * Default constructor 4 stack
	 * @param maxMovements
	 */
	public Pila(int maxMovements){
		undoStack= new int[maxMovements];
		currentPointerInit=-1;
		currentPointerEnd=-1;
		maxElemns=maxMovements;
	}
	/**
	 * Checks if the stack is full
	 * @return
	 */
	public boolean full(){
		return (currentPointerEnd==maxElemns-1 || currentPointerEnd+1==currentPointerInit);
	}
	/**
	 * Checks if the stack is empty
	 * @return
	 */
	public boolean empty(){
		return (currentPointerEnd==-1 && currentPointerInit==0 || currentPointerEnd==-1 && currentPointerInit==-1);
	}
	
	/**
	 * Inserts an element into the stack
	 * @param column
	 */
	public void insertToStack(int column){
		if(full()){
			
			int i=0;
			while(i<maxElemns-1){
				int aux=undoStack[i+1];
				undoStack[i]=aux;
				i++;
			}
			undoStack[maxElemns-1]=column;
			
		}else{
				currentPointerEnd++;
				undoStack[currentPointerEnd]=column;
				currentPointerInit=0;
		}
		
	}
	/**
	 * Returns an element from the stack
	 * @return
	 */
	public int getFromStack(){
		int value=-1;
		if(!empty()){
			value=undoStack[currentPointerEnd];
			//undoStack[currentPointerInit]=value;
			if(currentPointerInit==currentPointerEnd){
				currentPointerInit=-1;
				currentPointerEnd=-1;
			}
			else{
				currentPointerEnd--;
			}
		}
		
		return value;
	}
}
