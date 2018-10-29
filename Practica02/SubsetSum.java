import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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

	static ArrayList<Set<Integer>> conjuntoL = new ArrayList<Set<Integer>>();

	/**
	 * Algoritmo Aprox-Subset-Sum
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
	public static int aproxSubsetSum(int[] s, int t, double epsilon){
		int n = s.length;

		Set<Integer> l0 = new HashSet<Integer>();
		l0.add(0);
		conjuntoL.add(l0);

		for (int i = 0; i < n; i++) {

			//Obtenemos el último conjunto li-1 de nuestro conjunto L
			Set<Integer> li = conjuntoL.get(i);
			Object[] liArray = li.toArray();

			//Obtenemos xi
			int xi = s[i];

			//Sumamos xi a nuestro conjunto li-1
			Set<Integer> lix = sumarEnLista(liArray, xi);

			//Mezclamos los conjuntos li-1 y (li-1)+xi
			li.addAll(lix);

			//trim
			Set<Integer> lTrim = trim(li, epsilon/(2*n));

			//Eliminamos de li cada elemento que sea mayor a t
			Set<Integer> lit = removeGreater(lTrim, t);

			//Agregamos li al conjunto L
			if(i == (n-1)) conjuntoL.remove(li);
			conjuntoL.add(lTrim);
		}

		//Obteniendo el valor máximo en Ln
		Set<Integer> ln = conjuntoL.get(conjuntoL.size()-1);
		Object[] array_ln = ln.toArray();
		int ss = (int)array_ln[array_ln.length-1];
		return ss;
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

	public static Set<Integer> trim(Set<Integer> list, double delta){
		Object[] l = list.toArray();
		Arrays.sort(l);
		int m = l.length;

		Set<Integer> li = new HashSet<Integer>();
		int yi = (int) l[0];
		li.add(yi);
		int last = yi;

		for (int i=1; i<m; i++) {
			yi = (int) l[i];
			int k = (int)(last*(1+delta));
			if( yi > k){
				li.add(yi);
				last = yi;
			}
		}

		return li;
	}

	public static void main(String[] args) {
		int numeros[] = {104, 102, 201, 101};
		System.out.println(aproxSubsetSum(numeros, 308, 0.4));
	}
	
}