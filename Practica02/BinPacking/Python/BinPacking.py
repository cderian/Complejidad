#! /usr/bin/env python
#-*- coding: utf-8 -*-
import Mochila

'''
 * Title       : BinPacking
 * Language    : Python
 * By          : cderian
 * Description : Programa que resuelve el Problema de BinPacking
 *
 * 				Problema de optimización de Bin Packing
 * 				Sean n elementos con tamaños s1, s2, ..., sn donde 0 < si <= 1
 *
 * 				Y supongamos que debemos empacar los artículos en contenedores,
 * 				donde cada mochila tiene una capacidad de 1.
 * 				El problema es determinar el mínimo número de mochilas necesarias
 * 				para empaquetar todos los elementos.
'''

mochilas = []

'''
 * Ordena en forma descendente los elementos.
 * Los ordena de acuerdo a su peso.
'''
def sort(lst):

	# Iteramos sobre los elementos del arreglo
	for i in range(0, len(lst)):
		pos_max = i

		# Buscamos el elemento con mayor peso
		for j in range(i+1, len(lst)):
			if lst[j].getPeso() > lst[pos_max].getPeso():
				pos_max = j

		if(i != pos_max):
			# Permutamos los elementos
			temp = lst[i]
			lst[i] = lst[pos_max]
			lst[pos_max] = temp

	return lst

'''
 * La estrategia FirstFit coloca un elemento en la primera mochila en el que quepa.
 * Si no cabe en una mochila, se crea una nueva mochila.
'''
def firstFit(elementos):
	elems = sort(elementos)
	m1 = Mochila.Mochila(1)
	mochilas.append(m1)

	for i in range(0, len(elems)):
		e = elems[i]
		for j in range (0, len(mochilas)):
			mi = mochilas[j]

			if not (e.getEmpaquetado()) and ((mi.getCapacidad() - e.getPeso())>=0):
				e.setEmpaquetado(True)
				mi.agregar(e)

		if not (e.getEmpaquetado()):
			m = Mochila.Mochila(len(mochilas)+1)
			e.setEmpaquetado(True)
			m.agregar(e)
			mochilas.append(m)

'''
 * Representación del resultado de empaquetar elementos en mochilas.
'''
def toString():
	s = "Resultado final:\n"
	for i in range(0,len(mochilas)):
		s = s + mochilas[i].toString()
	return s

'''
 * Método principal.
 * Interectuará con el usuario.
'''
def main():
	print("\n=======================================================================");
	print("== Programa que resuelve:                                            ==");
	print("== El Problema de Bin Packing                                        ==");
	print("=======================================================================");
	print("Te ayudaré a guardar elementos en el menor número de mochilas posibles.");
	print("Recuerda que cada mochila tiene peso de 100.");
	print("\nIngresa tus datos");
	
	elems = []

	try:
		n = input ("¿Cuántos elementos quieres guardar?: ")
	
		for i in range(0, n):
			p = input("Peso del elemento " + str(i+1) + ": ")
			e = Mochila.Elemento(i+1, p)
			elems.append(e)

		firstFit(elems)
		print("\n"+toString())
	except NameError:
		print("\n*****ERROR. ENTRADA NO ACEPTADA*****")
		print("Ingresaste un valor indebido.\n")

main()