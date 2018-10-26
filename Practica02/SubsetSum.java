import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

/**
 * ===================================================
 * == Programa que resuelve:                        ==
 * == El Problema de Optimización de Subset Sum     ==
 * ===================================================
 *
 * Problema de optimización de Subset Sum
 * Dado un par (S, t) donde:
 * 		S = {x1, x2, ..., xn}
 *		t es un entero positivo
 *
 * Deseamos encontrar un subconjunto de S cuya suma
 * sea tan grande como sea posible pero no mayor a t.
 *
 */
public class SubsetSum{

	/**
	 * Algoritmo Exact-Subset-Sum
	 *
	 * Algoritmo de tiempo exponencial que calcula el valor
	 * óptimo para el problema de optimización de Subset Sum.
	 *
	 * @param s un conjunto de enteros S = {x1, x2, ..., xn}.
	 * @param t el valor objetivo.
	 *
	 * El algoritmo calcula Li, la lista de sumas de todos los
	 * subconjuntos de {x1, x2, ..., xn} que no excedan t.
	 *
	 * @return el valor máximo en Ln.
	 */
	public static int exactSubsetSum(int[] s, int t){
		int n = s.length;
		ArrayList<Set<Integer>> conjuntoL = new ArrayList<Set<Integer>>();

		Set<Integer> l0 = new HashSet<Integer>();
		l0.add(0);
		conjuntoL.add(l0);

		for (int i = 0; i < n; i++) {

			//Obtenemos el último conjunto li-1 de nuestro conjunto L
			Set<Integer> li = conjuntoL.get(i);
			Object[] li_aux = li.toArray();

			//Obtenemos xi
			int xi = s[i];

			//Sumamos xi a nuestro conjunto li-1
			Set<Integer> lix = sumarEnLista(li_aux, xi);

			//Mezclamos los conjuntos li-1 y (li-1)+xi
			li.addAll(lix);

			//Eliminamos de li cada elemento que sea mayor a t
			Set<Integer> lit = removeGreater(li, t);

			//Agregamos li al conjunto L
			conjuntoL.add(lit);
		}

		//Parche
		conjuntoL.remove(conjuntoL.size()-2);

		//Obteniendo el valor máximo en Ln
		Set<Integer> ln = conjuntoL.get(conjuntoL.size()-1);
		Object[] array_ln = ln.toArray();
		int ss = (int)array_ln[array_ln.length-1];
		return ss;
	}

	/**
	 * Dado un conjunto de enteros, elimina cada elemento del conjunto
	 * que sea mayor a un número entero t.
	 * @param li el conjunto de enteros.
	 * @param t el número entero mayor.
	 * @param un conjunto de enteros donde todos sus elementos son
	 *		  menores a t.
	 */
	public static Set<Integer> removeGreater(Set<Integer> li, int t){
		Set<Integer> elems = new HashSet<Integer>();
		Object[] l = li.toArray();

		for (int i=0; i < l.length; i++) {
			int e = (int)l[i];

			if(e < t){
				elems.add(e);
			}
		}

		return elems;
	}

	/**
	 * A cada elemento de un conjunto de enteros le suma un número entero.
	 * S + x = {s + x : x en S}
	 *
	 * @param listai el conjunto de números enteros.
	 * @param xi el número entero que se sumará a cada elemento del conjunto.
	 * @return el conjunto listai con la suma ya aplicada.
	 */
	public static Set<Integer> sumarEnLista(Object[] listai, int xi){
		Set<Integer> elems = new HashSet<Integer>();
		for (int i = 0; i < listai.length; i++) {
			int e = (int)listai[i];
			e+= xi;
			elems.add(e);
		}

		return elems;
	}
	
	/**
	 * Método principal
	 */
	public static void main(String[] args) {
		int numeros[] = {1, 4, 5};
		System.out.println(exactSubsetSum(numeros, 8));
	}
}