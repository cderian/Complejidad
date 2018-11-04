#! /usr/bin/env python
#-*- coding: utf-8 -*-

'''
 * Title       : Elemento
 * Language    : Python
 * By          : cderian
 * Description : Representación de un elemento.
 *               Cada elemento tendrá como atributos:
 *					- Un id.
 *					- Un peso.
 *					- Un boolean que indica si el elemento
 *					  ya fue guardado en una mochila o no.
'''
class Elemento:

	'''
	 * Constructor de Elemento.
	'''
	def __init__(self, ide, peso):
		self.ide = ide
		self.peso = peso
		self.empaquetado = False

	'''
	 * Regresa el id del elemento
	'''
	def getId(self):
		return self.ide

	'''
	 * Regresa el peso del elemento
	'''
	def getPeso(self):
		return self.peso

	'''
	 * Informa si el elemento ya fue guardado en una mochila o no.	
	'''
	def getEmpaquetado(self):
		return self.empaquetado

	'''
	 * Modifica la información del elemento con respecto a si ya
	 * fue guardado en una mochila o no.
	'''
	def setEmpaquetado(self, empaquetado):
		self.empaquetado = empaquetado

	'''
	 * Representación en cadena de la mochila.
	'''
	def toString(self):
		s = "Elem " + str(self.getId()) + "(P = " + str(self.getPeso()) + ")"
		return s

'''
 * Title       : Mochila
 * Language    : Python
 * By          : cderian
 * Description : Representación de una mochila.
 *               Cada mochila tendrá como atributos:
 *					- Un id.
 *					- Su capacidad de almacenamiento
 *					- Un conjunto de elementos que tiene guardados.
'''
class Mochila:
	
	'''
	 * Constructor de Mochila
	'''
	def __init__(self, idm):
		self.idm = idm
		self.capacidad = 100
		self.elementos = []

	'''
	 * Regresa el id de la mochila.
	'''
	def getId(self):
		return self.idm

	'''
	 * Regresa la capacidad de almacenamiento de la mochila.
	'''
	def getCapacidad(self):
		return self.capacidad

	'''
	 * Modifica la capacidad de almacenamiento de la mochila.
	'''
	def setCapacidad(self, capacidad):
		self.capacidad = capacidad

	'''
	 * Regresa el conjunto de elementos almacenados por la mochila.
	'''
	def getElementos(self):
		return self.elementos

	'''
	 * Agrega un elemento a la mochila.
	'''
	def agregar(self, e):
		self.setCapacidad(self.getCapacidad()-e.getPeso())
		self.elementos.append(e)

	'''
	 * Representación en cadena de la mochila.
	'''
	def toString(self):
		s = "Mochila " + str(self.getId()) + " (Capacidad = " + str(self.getCapacidad()) + "): "
		
		for i in xrange(len(self.elementos)):
			e = self.elementos[i]

			if(i == len(self.elementos)-1):
				s = s + e.toString() + ".\n"
			else:
				s = s + e.toString() + ", "

		return s