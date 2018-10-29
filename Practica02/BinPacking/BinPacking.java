import java.util.ArrayList;
import java.util.Iterator;

/**
 * ==========================================================================
 * == Programa que resuelve:                                               ==
 * == El Problema de Bin Packing
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
	Iterator<Elemento> itElementos = elementos.iterator();
	Iterator<Mochila> itMochilas = mochilas.iterator();

	/**
	 *
	 */
	public void firstFit(Elemento[] items){
		selectionSort(items);

		for (int i=0; i<items.length; i++) {
			elementos.add(items[i]);
		}

		while( itElementos.hasNext() ){
			Elemento e = itElementos.next();

			while(! e.getEmpaquetado() && itMochilas.hasNext() ){
				Mochila m = itMochilas.next();

				if( (m.getCapacidad()-e.getPeso()) >= 0){
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
     * Ordena en forma descendente un arreglo usando SelectionSort
     * 
     * @param arreglo el arreglo que será ordenado.
     */
    private void selectionSort(Elemento[] items) {

        //iteramos sobre los elementos del arreglo
        for (int i = 0; i < items.length-1; i++) {
            int max = i;
 
            //buscamos el mayor número
            for (int j = i+1; j < items.length; j++) {
                if (items[j].getPeso() > items[max].getPeso()) {
                    max = j;    //encontramos el mayor número
                }
            }
 
            if (i != max) {
                //permutamos los valores
                Elemento aux = items[i];
                items[i] = items[max];
                items[max] = aux;
            }
        }
    }

	/**
	 * Método principal
	 */
	public static void main(String[] args) {
		System.out.println("Hola mundo!");
		//int[] numeros = {3,5,1,2,10,9,8,13,2,2,7};

		/*Elemento m1 = new Elemento(1);
		Elemento m2 = new Elemento(3);
		Elemento m3 = new Elemento(5);
		Elemento m4 = new Elemento(6);
		Elemento m5 = new Elemento(10);

		Elemento[] items = {m1, m2, m3, m4, m5};
		selectionSort(items);

		for (int i=0; i<items.length; i++) {
			System.out.println("Elemento e"+ (i+1) + ": " + items[i].getPeso());
		}*/
	}
}