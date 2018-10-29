import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;

/**
 * ==========================================================================
 * == Programa que resuelve:                                               ==
 * == El Problema de Optimización de Subset Sum                            ==
 * ==========================================================================
 *
 * Problema de optimización de Subset Sum
 * Dado un par (S, t) donde:
 * 		S = {x1, x2, ..., xn}
 *		t es un entero positivo
 *
 * Deseamos encontrar un subconjunto de S cuya suma sea tan grande como sea
 * posible pero no mayor a t.
 *
 * @author cderian
 *
 */
public class SubsetSum{

	static ArrayList<Set<Integer>> conjuntoL = new ArrayList<Set<Integer>>();

	/**
	 * Esquema de aproximación en tiempo polinómico para el Problema de SubsetSum.
	 *
	 * Calcula las sumas de todos los subconjuntos de {x1, ..., xi}, utilizando como
	 * punto de partida las sumas de todos los subconjuntos de {x1, ..., xi-1}.
	 * Despúes de crear cada subconjunto, lo "recorta", es decir, si dos valores
	 * están cerca uno del otro, entonces, dado que sólo se requiere una solución
	 * aproximada, no se necesita mantener ambos explícitamente.
	 *
	 * @param s un conjunto de n enteros S = {x1, x2, ..., xn} en orden arbitrario.
	 * @param t el valor objetivo.
	 * @param e un parámetro de aproximación.
	 *
	 * @return z valor que está dentro de un factor 1+e de la solución óptima.
	 */
	public static int aproxSubsetSum(int[] s, int t, double e){
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
			Set<Integer> lTrim = trim(li, e/(2*n));

			//Eliminamos de li cada elemento que sea mayor a t
			Set<Integer> lit = removeGreater(lTrim, t);

			//Agregamos li al conjunto L
			if(i == (n-1)) conjuntoL.remove(li);
			conjuntoL.add(lit);
		}

		//Obteniendo el valor máximo en Ln
		Set<Integer> ln = conjuntoL.get(conjuntoL.size()-1);
		Object[] array_ln = ln.toArray();
		int z = (int)array_ln[array_ln.length-1];
		return z;
	}

	/**
	 * A cada elemento de un conjunto de enteros le suma un número entero.
	 * S + x = {s + x : x en S}
	 *
	 * @param listai un conjunto de n enteros S = {s1, s2, ..., sn}
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
	 * Recorta una lista Li (Li en L) por d. Elimina tantos elementos de Li como sea posible.
	 * Por cada elemento y que se elimina de L, existe un elemento z en Li que se aproxima
	 * a una solución óptima.
	 *
	 * @param list un conjunto de m enteros L = {y1, y2, ..., ym} en orden creciente.
	 * @param d parámetro de recorte con 0 < d < 1
	 * @return un conjunto recortado ordenado.
	 */
	public static Set<Integer> trim(Set<Integer> list, double d){
		Object[] l = list.toArray();
		Arrays.sort(l);
		int m = l.length;

		Set<Integer> li = new HashSet<Integer>();
		int yi = (int) l[0];
		li.add(yi);
		int last = yi;

		for (int i=1; i<m; i++) {
			yi = (int) l[i];
			int k = (int)(last*(1+d));
			if( yi > k){
				li.add(yi);
				last = yi;
			}
		}

		return li;
	}

	/**
	 * Dado un conjunto de enteros, elimina cada elemento del conjunto que sea
	 * mayor a un número entero t.
	 * @param li el conjunto de enteros.
	 * @param t el número entero mayor.
	 * @param un conjunto de enteros donde todos sus elementos son menores a t.
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
	 * Método principal
	 */
	public static void main(String[] args) {
		//Presentación
		System.out.println("\n===================================================");
		System.out.println("== Programa que resuelve:                        ==");
		System.out.println("== El Problema de Optimización de Subset Sum     ==");
		System.out.println("===================================================");

		//Pidiendo datos al usuario
		System.out.println("\nIngresa tus datos");
		Scanner sc = new Scanner(System.in);
		int[] numeros = new int[0];
		int t = 0;
		double e = 0.0;

		try{
			System.out.print("Tamaño de tu conjunto: ");
			int n = sc.nextInt();
			numeros = new int[n];

			for (int i = 0; i < n; i++) {
				System.out.print("Elemento "+ (i+1) +": ");
				int elem = sc.nextInt();
				numeros[i] = elem;
			}

			System.out.print("Valor objetivo t: ");
			t = sc.nextInt();
			System.out.print("Parámetro de aproximación e: ");
			e = sc.nextDouble();

			//Procesando información
			int solucion = aproxSubsetSum(numeros, t, e);

			//Imprimiendo resultado al usuario
			String s = "\nTu conjunto: {";
			
			for (int i = 0; i<numeros.length; i++) {
				if(i == (numeros.length-1)) s+= numeros[i]+"}";
				else s+= numeros[i]+", ";
			}
			
			System.out.println(s);
			System.out.println("Solución: "+ solucion+"\n");

		}catch(InputMismatchException ex){
			System.err.println("\n*****ERROR. ENTRADA NO ACEPTADA*****");
			System.err.println("Ingresaste un valor indebido.\n");
		}
	}
	
}