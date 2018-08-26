/**
 * Clase que representa un vértice de una gráfica
 *
 * @author cderian
 *
 */
public class Vertice{

	int id;

	/**
	 * Cosntructor de Vertice
	 * @param id el id del vértice
	 */
	public Vertice(int id){
		this.id = id;
	}

	/**
	 * Regresa el id del vértice.
	 * @return el id del vértice.
	 */
	public int getId(){
		return this.id;
	}
}