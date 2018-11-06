#! /usr/bin/env python
#-*- coding: utf-8 -*-
'''
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
 * Deseamos encontrar un subconjunto de S cuya sumasea tan grande como sea
 * posible pero no mayor a t.
'''
conjuntoL = []

'''
 * Mezcla dos listas sin repetir elementos
'''
def mergeLists(l1, l2):
	li = []
	
	for i in xrange(len(l1)):
		if l1[i] in l2:
			li.append(l1[i])
			l2.remove(l1[i])
		else:
			li.append(l1[i])

	for i in xrange(len(l2)):
		li.append(l2[i])

	return li

'''
 * Recorta una lista Li (Li en L) por d. Elimina tantos elementos de Li como sea posible.
 * Por cada elemento y que se elimina de L, existe un elemento z en Li que se aproxima
 * a una solución óptima.
 *
 * @param list un conjunto de m enteros L = {y1, y2, ..., ym} en orden creciente.
 * @param d parámetro de recorte con 0 < d < 1
 * @return un conjunto recortado ordenado.
'''
def trim(lst, d):
	ls = sorted(lst)
	m = len(ls)
	li = []
	print (ls)
	yi = ls[0]
	li.append(yi)
	last = yi

	for i in range(1, m):
		yi = ls[i]
		k = last*(1+d)

		if(yi > k):
			li.append(yi)
			last = yi

	return li

'''
 * Dado un conjunto de enteros, elimina cada elemento del conjunto que sea
 * mayor a un número entero t.
'''
def removeGreater(li, t):
	elems = []

	for i in range(0, len(li)):
		e = li[i]

		if(e < t):
			elems.append(e)

	return elems

'''
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
'''
def aproxSubsetSum(s, t, e):
	n = len(s)
	delta = e/(2.0*n)
	l0 = [0]
	conjuntoL.append(l0)

	for i in range(0, n):

		# Obtenemos el último conjunto li-1 de nuestro conjunto L
		li = conjuntoL[i]

		# Obtenemos xi
		xi = s[i]

		# Sumamos xi a nuestro conjunto li-1
		lix = []
		for i in range(0, len(li)):
			n = li[i]
			lix.append(n+xi)
		
		# Mezclamos los conjuntos li-1 y (li-1)+xi
		li_merged = mergeLists(li, lix)

		# trim
		li_trim = trim(li_merged, delta)

		# Eliminamos de li cada elemento que sea mayor a t
		lit = removeGreater(li_trim, t)

		#Agregamos li al conjunto L
		conjuntoL.append(lit)

	# Obteniendo el valor máximo en Ln
	ln = conjuntoL[len(conjuntoL)-1]
	z = ln[len(ln)-1]

	return z

'''
 * Método principal.
 * Interectuará con el usuario.
'''
def main():
	print("\n===================================================");
	print("== Programa que resuelve:                        ==");
	print("== El Problema de Optimización de Subset Sum     ==");
	print("===================================================");
	print("\nIngresa tus datos");

	numeros = []

	n = input ("Tamaño de tu conjunto: ")
	
	for i in range(0, n):
		elem = input("Elemento " + str(i+1) + ": ")
		numeros.append(elem)

	t = input("Valor objetivo t: ")
	e = input("Parámetro de aproximación e: ")

	# Procesando información
	solucion = aproxSubsetSum(numeros, t, e)

	# Imprimiendo resultados al usuario
	s = "\nTu conjunto: {"
	for i in xrange (len(numeros)):
		if(i == len(numeros)-1):
			s = s + str(numeros[i])+"}"
		else:
			s = s + str(numeros[i])+", "

	print s
	print "Solución: " + str(solucion)
	
main()
