#!/usr/bin/python
# -*- coding: utf-8 -*-
from random import randint
from Poblacion import *

def crearPoblacion(num_clausulas, num_variables):
	'''
	 * Crea una población inicial para el problema de MaxSat.
	 * 		num_clausulas = Número de cláusulas del ejemplar.
	 * 		num_variables = Número de variables del ejemplar.
	'''
	# Creamos las cláusulas
	clausulas = []
	for i in xrange(num_clausulas):
		clausulas.append(Clausula(i, num_variables))

	# Imprimiendo la información del problema al usuario
	info = ""
	info+= "========================\n"
	info+= "== Problema MaxSat    ==\n"
	info+= "== AG - 1a Población  ==\n"
	info+= "========================\n\n"
	info+= "Notación:\n0 = False\n1 = True\n-1 = No se encuentra en la cláusula\n"
	info+= "%d cláusulas. %d variables.\n\n"%(num_clausulas, num_variables)
	info+= "****** 1a Población. Cláusulas ******\n\n"

	for c in clausulas:
		info+= str(c)+"\n"

	print info
	return clausulas

def seleccion(poblacion):
	'''
	 * Función de Selección.
	 * Define como seleccionar a los individuos que serán padres.
	'''
	poblacion = sorted(poblacion, key=lambda Ejemplar: Ejemplar.aptitud)
	mitad = len(poblacion)/2

	# Individuos más aptos
	aptos = poblacion[mitad:]

	# Individuos menos aptos
	no_aptos = poblacion[:mitad]

	seleccionados = []

	for i in xrange(mitad):
		# 20% de probabilidad de no ser apto
		es_apto = randint(1,5)
		if es_apto == 1:
			seleccionados.append(no_aptos[i])
		else:
			seleccionados.append(aptos[i])
	return seleccionados

def PMXCrossover(padre, madre):
	'''
	 * Partially Mapped Crossover.
	 * Crea un hijo a partir de la cruza de dos individuos.
	'''
	fin = randint(0,len(padre.asignacionVerdad))
	ini = randint(0,fin)

	valores_padre = padre.asignacionVerdad.values()
	valores_madre = madre.asignacionVerdad.values()

	# Intercambio
	sub1 = valores_padre[:ini:] + valores_padre[fin::]
	sub2 = valores_madre[:ini:] + valores_madre[fin::]

	# Mapeo e intercambio
	for i in range(ini, fin):
		cambio = valores_padre[i]
	
		if cambio in sub2:
			indice = sub2.index(cambio)
			sub2[indice] = valores_madre[indice]

		cambio = valores_madre[i]

		if cambio in sub1:
			indice = sub1.index(cambio)
			sub1[indice] = valores_padre[indice]

	valores_hijo1 = sub1[:ini] + valores_madre[ini:fin]+sub1[ini:]
	valores_hijo2 = sub2[:ini] + valores_padre[ini:fin]+sub2[ini:]

	return Ejemplar(valores_hijo1), Ejemplar(valores_hijo2)
	
def OXCrossover(padre, madre):
	'''
	 * Order Crossover.
	 * Crea un hijo a partir de la cruza de dos individuos.
	'''
	fin = randint(0, len(padre.asignacionVerdad))
	ini = randint(0, fin)

	valores_padre = padre.asignacionVerdad.values()
	valores_madre = madre.asignacionVerdad.values()

	# Intercambio
	sub1 = valores_padre[fin::] + valores_padre[0:ini:]
	sub2 = valores_madre[fin::] + valores_madre[0:ini:]

	# Mapeo e intercambio
	valores_hijo1 = []
	valores_hijo2 = []

	for i in range(0,ini):
		valores_hijo1.append(sub2[i])
		valores_hijo2.append(sub1[i])

	valores_hijo1 = valores_hijo1 + valores_padre[ini:fin]
	valores_hijo2 = valores_hijo2 + valores_madre[ini:fin]

	for i in range(ini, len(sub1)):
		valores_hijo1.append(sub2[i])
		valores_hijo2.append(sub1[i])

	return Ejemplar(valores_hijo1), Ejemplar(valores_hijo2)

def DisplacementMut(individuo):
	'''
	 * Displacement Mutation.
	 * Genera mutación por desplazamiento de un individuo.
	'''
	valores_ind = individuo.asignacionVerdad.values()
	destino = randint(0, len(valores_ind)-1)
	inicio = randint(0, len(valores_ind)-1)
	valor = valores_ind[randint(0, inicio)]
	valores_ind.pop(inicio)

	if destino != inicio:
		valores_ind.insert(destino, valor)
	else:
		valores_ind.append(valor)

	return Ejemplar(valores_ind)
	
def ExchangeMut(individuo):
	'''
	 * Exchange Mutation.
	 * Genera mutación por intercambio de un individuo.
	'''
	valores_ind = individuo.asignacionVerdad.values()
	destino = randint(0, len(valores_ind)-1)
	inicio = randint(0, len(valores_ind)-1)
	valor1 = valores_ind[randint(0, inicio)]
	valor2 = valores_ind[randint(0, destino)]

	if destino != inicio:
		valores_ind[inicio] = valor2
		valores_ind[destino] = valor1

	return Ejemplar(valores_ind)
	
def reemplazo(ancestros, nuevos):
	'''
	 * Función de Reemplazo.
	 * Define como reemplazar a los individuos de la población, por aquellos
	 * obtenidos en el proceso de Reproducción.
	 * 		ancestros: Anterior generación.
	 *		nuevos: Nueva generación.
	'''
	# Ordenamos la generación anterior de peor a mejor fitness
	ancestros = sorted(ancestros, key=lambda Ejemplar: Ejemplar.aptitud)
	tamanio_poblacion = len(ancestros)
	mitad = tamanio_poblacion/2
	nueva_gen = []

	# No se mantiene el mejor individuo de la generación anterior
	# Se realizará el reemplazo con el individuo más apto.
	nueva_gen = ancestros[mitad:] + nuevos

	return nueva_gen
	
def aplicarAlgoritmo(gen_anterior, clausulas):
	'''
	 * Aplica el Algoritmo Genético.
	 * Utiliza las funciones de fitness, selección, cruce, mutación y reemplazo.
	 * 		gen_anterior = La generación de los padres.
	 *		clausulas = Conjunto de cláusulas que debe satisfacer.
	'''
	# Aplicando función de evaluación (fitness)
	for individuo in gen_anterior:
		individuo.fitness(clausulas)

	# Selección
	padres = seleccion(gen_anterior)

	# Reproducción
	# Cada dos padres.
	cont = 0
	hijos = []
	while cont < len(padres)-1:

		# Cruce
		crossover = randint(0,1)
		# Partial Mapped Crossover
		if crossover == 1:
			hijo1, hijo2 = PMXCrossover(padres[cont], padres[cont+1])
		# Order Crossover
		else:
			hijo1, hijo2 = OXCrossover(padres[cont], padres[cont+1])

		# Mutación
		# Probabilidad de mutación: 10%
		# Mutación del 1er hijo
		mutacion = randint(1,20)
		if mutacion == 1:
			hijo1 = DisplacementMut(hijo1)
		if mutacion == 2:
			hijo1 = ExchangeMut(hijo1)

		# Mutación del 2o hijo
		mutacion = randint(1,20)
		if mutacion == 1:
			hijo2 = DisplacementMut(hijo2)
		if mutacion == 2:
			hijo2 = ExchangeMut(hijo2)

		hijos.append(hijo1)
		hijos.append(hijo2)

		cont+= 2

	# Reemplazo
	# De la generación anterior con la nueva generación
	nueva_generacion = []
	nueva_generacion = reemplazo(gen_anterior, hijos)
	for ind in nueva_generacion:
		ind.fitness(clausulas)

	return nueva_generacion

if __name__ == '__main__':
	'''
	 * Método que ejecutará el programa.
	'''
	# Creamos la primera población
	num_clausulas = randint(50,60)
	num_variables = randint(100,125)
	POBLACION = crearPoblacion(num_clausulas, num_variables)

	# Creamos otra generación
	print "Creamos otra generación...\n"
	primera_generacion = []
	for i in xrange(16):
		primera_generacion.append(Ejemplar( [randint(0,1) for i in xrange(num_variables)] ) )

	# Fitness, Selección, Cruce, Mutación, Reemplazo
	print "Comenzamos el proceso de reproducción...\n"
	nueva_gen = aplicarAlgoritmo(primera_generacion, POBLACION)

	for i in xrange(50):
		nueva_gen = aplicarAlgoritmo(nueva_gen, POBLACION)
		nueva_gen = sorted(nueva_gen, key=lambda Ejemplar: Ejemplar.aptitud)
		print "Generación %d. Mejor fitness = %d"%(i+1, nueva_gen[-1].aptitud)

	# Resultado
	candidato = nueva_gen[-1]
	resultado = "\nMejor candidato:\n"

	for i in candidato.asignacionVerdad.keys():
		resultado+= "x%d=%d, "%(i, candidato.asignacionVerdad[i])

	resultado+= "\nSatisface %d cláusulas."%(candidato.aptitud)
	print resultado