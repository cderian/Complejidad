#!/usr/bin/python
# -*- coding: utf-8 -*-
from random import randint
from random import randrange

'''
 * Clase que representa una Cláusula
'''
class Clausula:

	def __init__(self, id_clausula, num_variables):
		'''
		 * Constructor de una Clausula.
		'''
		tamanio = randint(3,5)
		self.variables = {}

		for i in xrange(num_variables):
			self.variables[i] = -1

		cont = 0
		while( cont < tamanio ):
			posicion = randrange(0, num_variables-1)

			if self.variables[posicion] == -1:
				self.variables[posicion] = randint(0, 1)
				cont+= 1

		self.id = id_clausula
		self.verdadero = False

	def __str__ (self):
		'''
		 * Representación en cadena de la Cláusula.
		'''
		clausula = "C%d ("%self.id

		for i in xrange( len(self.variables) ):
			if not (self.variables[i] == -1):
				clausula+="x%d "%i

		clausula+= ") = ("

		for i in self.variables.keys():
			clausula+= "x%d=%d "%(i, self.variables[i])

		return clausula + ")\n"

'''
 * Clase que representa un ejemplar solución con un conjunto de variables
 * con valores aleatorios: 0 y 1.
'''
class Ejemplar:
	def __init__ (self, variables):
		'''
		 * Constructor de un Ejemplar Solución.
		'''
		self.asignacionVerdad = {}

		for i in xrange(len(variables)):
			self.asignacionVerdad[i] = variables[i]

		self.aptitud = 0

	def fitness(self, clausulas):
		'''
		 * Función fitness.
		 * A partir de un conjunto de clausulas de un ejemplar solución,
		 * calcula y evalua cuantas clausulas satisface.
		'''
		aptitud = 0

		for c in clausulas:
			aceptado = 0

			for i in c.variables.keys():

				if self.asignacionVerdad[i] == -1 and c.variables[i] == 0:
					n = 1
				elif self.asignacionVerdad[i] == 1 and c.variables[i] == 1:
					n = 1
				else:
					n = 0

				aceptado = aceptado or n

			if (aceptado != 0):
				aptitud+= 1

		self.aptitud = aptitud