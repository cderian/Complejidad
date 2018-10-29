import java.util.ArrayList;

public class Mochila{

	public int capacidad;
	public ArrayList<Elemento> elementos;

	public Mochila(){
		this.capacidad = 1;
		elementos = new ArrayList<Elemento>();
	}

	public int getCapacidad(){
		return this.capacidad;
	}

	public void setCapacidad(int capacidad){
		this.capacidad = capacidad;
	}

	public ArrayList<Elemento> getElementos(){
		return this.elementos;
	}

	public void setElementos(ArrayList<Elemento> elementos){
		this.elementos = elementos;
	}

	public void agregarElemento(Elemento e){
		setCapacidad(getCapacidad() - e.getPeso());
		elementos.add(e);
	}

	public String toString(int id){
		String s = "Mochila " + id +": ";

		for (Elemento e : elementos) {
			s+= e.toString() + ", ";
		}

		return s;
	}
}