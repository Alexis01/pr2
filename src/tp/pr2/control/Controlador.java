package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Partida;
import tp.pr2.utils.Constants;
/**
 * Clase controlador que se encarga de manejar la partida.
 * @author Planet Media
 *
 */
public class Controlador {
	private Partida partida;
	private Scanner scanner;
	/**
	 * Default constructor
	 * @param p Current game
	 * @param in We use this to get user information
	 */
	public Controlador(Partida p, Scanner in){
		partida=p;
		scanner=in;
	}
	/**
	 * Execute the game
	 * @throws IOException 
	 */
	public void run() {
		String opc;
		do{
			System.out.print(partida.getTablero().toString());
			System.out.println("\n"+Constants.PLAYING + Ficha.valueOfFichaPrint( partida.getTurno())); 
            System.out.print(Constants.WDOYOUWNTODO); 
            opc = scanner.nextLine();
            
            opc= parseCommand(opc);
            
		}while(parseInstruction(opc) && !partida.isTerminada() );
		if(partida.isTerminada() && partida.getTurno()!= Ficha.VACIA){
			System.out.print(partida.getTablero().toString());
			System.out.print("\n");
			System.out.println("Ganan las " + Ficha.valueOfFichaPrint( partida.getTurno()));
		}
		
	}
	/**
	 * Parse the current user instruction and executes it.
	 * @param instruction
	 */
	private boolean parseInstruction(String ins){
		boolean forward=true;
		//whitespace omitted trim
		String instruction=ins.trim();
		
		if(instruction.equalsIgnoreCase(Constants.PONER)){
			forward=true;
			System.out.print(Constants.GET_COLUMN);
			
			if(!partida.ejecutaMovimiento(partida.getTurno(), Integer.parseInt(scanner.nextLine()))){
				showMessageToUserError(Constants.ERROR_COLUMN);
			}
		}
		else{
			if(instruction.equalsIgnoreCase(Constants.REINICIAR)){
				forward=true;
				partida.reset();
				showMessageToUser(Constants.REST_GAME);
			}
			else{
				if(instruction.equalsIgnoreCase(Constants.DESHACER)){
					forward=true;
					if(!partida.undo()){
						showMessageToUserError(Constants.ERROR_UNDO);
					}
					
				}
				else{
					if(instruction.equalsIgnoreCase(Constants.SALIR)){
						forward=false;
					}
					else{
						showMessageToUserError(Constants.DONT_UNDESTAND);
					}
				}
			}
		}
		return forward;
	}
	/**
	 * Display a message to user
	 * @param mString
	 */
	private void showMessageToUser(String mString){
		System.out.println(mString);
	}
	/**
	 * Display an error message to user
	 * @param mString
	 */
	private void showMessageToUserError(String mString){
		 System.err.println(mString);
	}
	/**
	 * Only accept one word by command.
	 * @param line
	 * @return
	 */
	private String parseCommand(String line){
		String[] words = line.split("\\s+");
		return words[0];
	}

}
