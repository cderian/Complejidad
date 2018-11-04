import java.util.ArrayList;

/**
 * Representación de una mochila.
 * Cada mochila tendrá como atributos 
 * 	 - Su capacidad de almacenamiento
 * 	 - Un conjunto de elementos que tiene guardados.
 *
 * @author cderian
 */
public class Mochila{

	public int capacidad;
	public ArrayList<Elemento> elementos;

	/**
	 * Constructor de una mochila.
	 */
	public Mochila(){
		this.capacidad = 100;
		elementos = new ArrayList<Elemento>();
	}

	/**
	 * Regresa la capacidad de almacenamiento de la mochila.
	 * @return la capacidad de almacenamiento de la mochila.
	 */
	public int getCapacidad(){
		return this.capacidad;
	}

	/**
	 * Modifica la capacidad de almacenamiento de la mochila.
	 * @param capacidad la nueva capcidad de alm. de la mochila.
	 */
	public void setCapacidad(int capacidad){
		this.capacidad = capacidad;
	}

	/**
	 * Regresa el conjunto de elementos almacenados por la mochila.
	 * @return el conjunto de elementos almacenados por la mochila.
	 */
	public ArrayList<Elemento> getElementos(){
		return this.elementos;
	}

	/**
	 * Modifica el conjunto de elementos almacenados en la mochila.
	 * @param elementos los nuevos elementos almacenados en la mochila.
	 */
	public void setElementos(ArrayList<Elemento> elementos){
		this.elementos = elementos;
	}

	/**
	 * Agrega un elemento a la mochila.
	 * @param e el elemento que se va a agregar a la mochila.
	 */
	public void agregarElemento(Elemento e){
		setCapacidad(getCapacidad() - e.getPeso());
		elementos.add(e);
	}

	/**
	 * Representación en cadena de la mochila.
	 * @return la información de la mochila.
	 */
	public String toString(int id){
		String s = "Mochila " + id +" (Capacidad = " + getCapacidad() + "): ";

		for(int i = 0; i<elementos.size(); i++){
			Elemento e = elementos.get(i);

			if(i == elementos.size()-1){
				s+= e.toString() + ".";
			}else{
				s+= e.toString() + ", ";
			}
		}

		return s+"\n";
	}
}