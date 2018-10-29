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
		Mochila m1 = new Mochila();
		mochilas.add(m1);

		for (int i=0; i<items.length; i++) {
			elementos.add(items[i]);
		}

		for (Elemento e : elementos) {
			Elemento ei = e;

			for(Mochila m : mochilas){
				Mochila mi = m;

				if( (!e.getEmpaquetado()) && (m.getCapacidad()-e.getPeso()) >= 0){
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

    public String toString(){
    	String s = "Resultado final:\n";
    	
    	int ids = 1;

    	for (Mochila m : mochilas) {
    		s+= m.toString(ids);
			ids++;
		}

    	return s;
    }

	/**
	 * Método principal
	 */
	public static void main(String[] args) {

		Elemento e1 = new Elemento(1, 10);
		Elemento e2 = new Elemento(2, 30);
		Elemento e3 = new Elemento(3, 50);
		Elemento e4 = new Elemento(4, 60);
		Elemento e5 = new Elemento(5,100);
		Elemento[] items = {e1, e2, e3, e4, e5};

		BinPacking bp = new BinPacking();
		bp.firstFit(items);
		System.out.println(bp.toString());
	}
}