public class Elemento{

	public int id;
	public int peso;
	public boolean empaquetado;

	public Elemento(int id, int peso){
		this.id = id;
		this.peso = peso;
		this.empaquetado = false;
	}

	public int getId(){
		return this.id;
	}

	public void setId(){
		this.id = id;
	}

	public int getPeso(){
		return this.peso;
	}

	public void setPeso(int peso){
		this.peso = peso;
	}

	public boolean getEmpaquetado(){
		return this.empaquetado;
	}

	public void setEmpaquetado(boolean empaquetado){
		this.empaquetado = empaquetado;
	}

	public String toString(){
		return "Elem " + "(" + getId() + ", " + getPeso() + ", " + getEmpaquetado() + ")";
	}
}