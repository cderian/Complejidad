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
	ArrayList<Node> grafica2 = new ArrayList<Node>();

	//Vértices que forman parte del Conjunto Independiente
	//ArrayList<Integer> vertices = new ArrayList<Integer>();
	//ArrayList<Node> vertices = new ArrayList<Node>();
	ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	ArrayList<Vertice> vertices_rojos = new ArrayList<Vertice>();

	/**
	 * Constructor de la gprafica
	 */
	public Grafica(){

		//El número 3 indica el grado promedio de la gráfica
		RandomGenerator random_gen = new RandomGenerator();
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
			vertices.add(new Vertice(i));
			
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

		/*int size = this.grafica2.size();

		while(size > 0){

			//Escogemos un vértice al azar para que sea parte del CI
			int v = random.nextInt(size);
			Node escogido = grafica2.get(v);

			int r = random.nextInt(2);

			if(r==1){
				this.vertices.add(escogido);
				//El vértice escogido al azar lo coloreamos de rojo
				this.grafica.getNode(v).setAttribute("ui.class", "rojo");
			}

			this.grafica2.remove(escogido);
			
			size = this.grafica2.size();
		}*/

		int tam = vertices.size();

		while(tam > 0){
			//Escogemos un vértice al azar para que sea parte del CI
			int n = random.nextInt(tam);
			Vertice v = vertices.get(n);

			int r = random.nextInt(2);

			if(r==1){
				vertices_rojos.add(v);
				grafica.getNode(v.getId()).setAttribute("ui.class", "rojo");
			}

			vertices.remove(v);
			tam = vertices.size();
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

		/*for(Node v : vertices){
			for(Node u : vertices){
				boolean b = v.hasEdgeBetween(u);
				if(!b){
					ind =b;
				}
			}
		}*/


		/*for (Vertice v : vertices_rojos) {
			for (Vertice u : vertices_rojos) {
				Node n = grafica.getNode(v.getId());
				Node m = grafica.getNode(u.getId());
				boolean b = n.hasEdgeBetween(m);

				if(!b){
					ind = b;
					break;
				}
			}
		}*/

		for (int i=0; i<vertices_rojos.size()-1; i++) {
			for(int j=1; j<vertices_rojos.size(); j++){
				Node n = grafica.getNode(vertices_rojos.get(i).getId());
				Node m = grafica.getNode(vertices_rojos.get(j).getId());

				boolean b = n.hasEdgeBetween(m);

				if(b){
					ind = !b;
					break;
				}
			}			
		}

		for(Vertice v : vertices_rojos){
			resultado+=" " + v.getId();
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