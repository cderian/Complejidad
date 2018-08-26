import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.stream.SourceBase;

import org.graphstream.algorithm.generator.BaseGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;

import java.util.ArrayList;
import java.util.Random;
/**
 * Programa que resuelve el problema del Conjunto Independiente.
 *
 * El problema consiste en, dada una gráfica G=(V,A).
 * Un subconjunto I de V se dice que es Conjunto Independiente de
 * vértices de G si para cualquier par de vértices de I no existe
 * una arista que los conecte.
 *
 * @author cderian
 *
 */
public class Grafica{

	Random random = new Random();

	//Gráfica original
	Graph grafica = new SingleGraph("Gráfica");

	//Copia de los vértices de la gráfica
	ArrayList<Vertice> vertices = new ArrayList<Vertice>();

	//Los vértices del Conjunto Independiente
	ArrayList<Vertice> vertices_rojos = new ArrayList<Vertice>();

	/**
	 * Constructor de la gráfica aleatoria
	 */
	public Grafica(){

		RandomGenerator random_gen = new RandomGenerator();
		random_gen.addSink(grafica);
		random_gen.begin();

		//Producimos un número aleatorio entre 5 y 10 para
		//indicar el número de vértices de nuestra gráfica.
		int r = random.nextInt(6)+5;

		//Creamos los vértices
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
	 *
	 * Verifica si el Conjunto Independiente obtenido es,
	 * efectivamente, un Conjunto Independiente.
	 *
	 * Imprime los vértices que componen el Comjunto Independiente.
	 * Y, también, el resultado que dé de la verificación
	 */
	private void esCI(){

		boolean ind = true;

		//Busca si existe una arista entre los vértices del CI
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

		//Imprime el resultado
		String resultado = "\nVértices (Rojos) del Conjunto Independiente:";

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