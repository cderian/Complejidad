import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.stream.SourceBase;

import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Grafica{

	Random random = new Random();

	//Gráfica original
	Graph grafica = new SingleGraph("Gráfica");

	//Copia de los vértices de la gráfica
	ArrayList<Integer> grafica2 = new ArrayList<Integer>();

	//Vértices que forman parte del Conjunto Independiente
	ArrayList<Integer> vertices = new ArrayList<Integer>();

	/**
	 * Constructor de la gprafica
	 */
	public Grafica(){

		//El número 3 indica el grado promedio de la gráfica
		RandomGenerator random_gen = new RandomGenerator(3);
		random_gen.addSink(grafica);
		random_gen.begin();

		//Producimos un número aleatorio entre 5 y 10.
		//Este número indicará el número de nodos de nuestra gráfica.
		int r = random.nextInt(6)+5;

		//Creamos los nodos
		for(int i=0; i<r; i++){
			random_gen.nextEvents();
		}

		random_gen.end();

		for(int i=0; i < grafica.getNodeCount(); i++){
			//Hacemos una copia de los vértices de la gráfica
			this.grafica2.add(i);
			//Los vértices de la gráfica los coloreamos de color azul
			grafica.getNode(i).addAttribute("ui.style", "fill-color: rgb(7,3,61);");
		}
	}

	/**
	 * Fase Adivinadora
	 * 
	 * Obtendrá vértices al azar para formar un
	 * Conjunto Independiente de la gráfica.
	 */
	private void obtenerCI(){

		int size = this.grafica2.size();

		while(size > 0){

			//Escogemos un vértice al azar para que sea parte del CI
			int v = random.nextInt(size);
			int r = random.nextInt(2);

			if(r==1){
				this.vertices.add(v);
				//El vértice escogido al azar lo coloreamos de rojo
				this.grafica.getNode(v).setAttribute("ui.class", "rojo");
			}

			this.grafica2.remove(v);
			
			size = this.grafica2.size();
		}
	}

	/**
	 * Fase Verificadora
	 * Dará como resultado si el Conjunto Independiente obtenido es,
	 * efectivamente, un Conjunto Independiente.
	 */
	private void esCI(){
		boolean ind = true;
		
		//Imprime los vértices del conjunto independiente
		//Y también, el resultado de la verificación
		String resultado = "\nVértices (Rojos) del Conjunto Independiente:";

		for(int v : vertices){
			for(int u : vertices){
				boolean b = grafica.getNode(v).hasEdgeBetween(u);
				if(!b){
					ind =b;
				}
			}
		}

		for(int v : vertices){
			resultado+=" " + v;
		}

		resultado+="\nEs Conjunto Independiente: "+ind+"\n";

		System.out.println(resultado);
	}

	/**
	 * Mostrará la gráfica en pantalla
	 * Los vértices rojos son los que forman parte del Conjunto Independiente
	 * El resto son de color azul.
	 */
	public void mostrarGrafica(){
		String css = "node.rojo{fill-color:red;}" + "node.azul{fill-color:blue;}" + "node.verde{fill-color:green;}" + "node.amarillo{fill-color:yellow;}" ;
		grafica.addAttribute("ui.stylesheet", css);
		grafica.display();
	}

	public static void main(String[] args) {
		Grafica g = new Grafica();
		g.obtenerCI();
		g.esCI();
		g.mostrarGrafica();
	}
}