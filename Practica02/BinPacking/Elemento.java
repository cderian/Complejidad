/**
 * Representación de un elemento.
 * Cada elemento tendrá como atributos 
 * 	 - Un id.
 * 	 - Un peso.
 * 	 - Un boolean que indica si el elemento
 *     ya fue guardado en una mochila o no.
 *
 * @author cderian
 */
public class Elemento{

	public int id;
	public int peso;
	public boolean empaquetado;

	/**
	 * Constructor de Elemento
	 * @param id el id del elemento.
	 * @param peso el peso del elemento.
	 */
	public Elemento(int id, int peso){
		this.id = id;
		this.peso = peso;
		this.empaquetado = false;
	}

	/**
	 * Regresa el id del elemento.
	 * @return el id del elemento.
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Modifica el id del elemento.
	 * @param id el nuevo id del elemento.
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Regresa el peso del elemento.
	 * @return el peso del elemento.
	 */
	public int getPeso(){
		return this.peso;
	}

	/**
	 * Modifica el peso del elemento.
	 * @param peso el nuevo peso del elemento.
	 */
	public void setPeso(int peso){
		this.peso = peso;
	}

	/**
	 * Informa si el elemento ya fue guardado en una mochila o no.
	 * @return true si el elemento ya fue guardado en una mochila.
	 *		   false en otro caso.
	 */
	public boolean getEmpaquetado(){
		return this.empaquetado;
	}

	/**
	 * Modifica la información del elemento con respecto a si ya
	 * fue guardado en una mochila o no.
	 * @param empaquetado el nuevo boolean del elemento.
	 */
	public void setEmpaquetado(boolean empaquetado){
		this.empaquetado = empaquetado;
	}

	/**
	 * Representación en cadena del elemento.
	 * @return la información del elemento.
	 */
	public String toString(){
		String s = "Elem " + getId() + " (P = " + getPeso() + ")";
		return s;
	}
}