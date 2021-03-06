import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * ==========================================================================
 * == Programa que resuelve:                                               ==
 * == El Problema de Bin Packing                                           ==
 * ==========================================================================
 *
 * Problema de optimización de Bin Packing
 * Sean n elementos con tamaños s1, s2, ..., sn donde 0 < si <= 1
 *
 * Y supongamos que debemos empacar los artículos en contenedores, donde cada
 * contenedor tiene una capacidad de 1.
 * El problema es determinar el mínimo número de contenedores necesarios para
 * empaquetar todos los elementos.
 *
 * @author cderian
 *
 */
public class BinPacking{

	ArrayList<Elemento> elementos = new ArrayList<Elemento>();
	ArrayList<Mochila> mochilas = new ArrayList<Mochila>();

	/**
	 * La estrategia FirstFit coloca un elemento en la primera mochila en el que quepa.
	 * Si no cabe en una mochila, se crea una nueva mochila.
	 * @param items los elementos que se guardarán en la mochilas.
	 */
	public void firstFit(Elemento[] items){
		sort(items);
		Mochila m1 = new Mochila();
		mochilas.add(m1);

		for (int i=0; i<items.length; i++) {
			elementos.add(items[i]);
		}

		for (Elemento e : elementos) {
			for(Mochila m : mochilas){

				if( (!e.getEmpaquetado()) &&
					(m.getCapacidad()-e.getPeso()) >= 0){
					e.setEmpaquetado(true);
					m.agregarElemento(e);
				}
			}

			if(!e.getEmpaquetado()){
				Mochila m = new Mochila();
				e.setEmpaquetado(true);
				m.agregarElemento(e);
				mochilas.add(m);
			}
		}
	}

	/**
     * Ordena en forma descendente los elementos.
     * Los ordena de acuerdo a su peso.
     * 
     * @param arreglo el arreglo que será ordenado.
     */
    private void sort(Elemento[] items) {

        // Iteramos sobre los elementos del arreglo
        for (int i = 0; i < items.length-1; i++) {
            int max = i;
 
            // Buscamos el elemento con mayor peso
            for (int j = i+1; j < items.length; j++) {
                if (items[j].getPeso() > items[max].getPeso()) {
                    max = j;    //encontramos el mayor número
                }
            }
 
            if (i != max) {
                // Permutamos los elementos
                Elemento aux = items[i];
                items[i] = items[max];
                items[max] = aux;
            }
        }
    }

    /**
     * Representación del resultado en forma de cadena.
     * @return la asignación final de los elementos en las mochilas.
     */
    public String toString(){
    	String s = "Resultado final:\n";
    	int id = 1;

    	for (Mochila m : mochilas) {
    		s+= m.toString(id);
			id++;
		}

    	return s;
    }

	/**
	 * Método principal
	 */
	public static void main(String[] args) {

		//Presentación
		System.out.println("\n=======================================================================");
		System.out.println("== Programa que resuelve:                                            ==");
		System.out.println("== El Problema de Bin Packing                                        ==");
		System.out.println("=======================================================================");
		System.out.println("Te ayudaré a guardar elementos en el menor número de mochilas posibles.");
		System.out.println("Recuerda que cada mochila tiene peso de 100.");

		//Pidiendo datos al usuario
		System.out.println("\nIngresa tus datos");
		BinPacking bp = new BinPacking();
		Scanner sc = new Scanner(System.in);
		Elemento[] elems = new Elemento[0];

		try{
			System.out.print("¿Cuántos elementos quieres guardar?: ");
			int n = sc.nextInt();
			elems = new Elemento[n];

			for (int i = 0; i < n; i++) {
				System.out.print("Peso del elemento "+ (i+1) +": ");
				int peso = sc.nextInt();
				Elemento e = new Elemento((i+1), peso);
				elems[i] = e;
			}

			// Procesando información
			bp.firstFit(elems);

			//Imprimiendo resultado al usuario
			System.out.println("\n"+bp.toString());

		}catch(InputMismatchException ex){
			System.err.println("\n*****ERROR. ENTRADA NO ACEPTADA*****");
			System.err.println("Ingresaste un valor indebido.\n");
		}
	}
}