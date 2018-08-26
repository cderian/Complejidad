Complejidad Computacional 2019-1
Práctica 01

312224464 Estrada Gómez César Derian

*** HERRAMIENTA: GRAPHSTREAM ***

Para realizar esta práctica utilicé la blibioteca que nos proporciona Java:
GraphStream. Esta biblioteca nos ayuda a analizar y modelar gráficas dinámicas.

En esta práctica, utilicé esta herramiente para dar una mejor visualización
de la gráfica que presentó como resultado.

Por lo tanto, es necesario descargar los siguientes JAR:
		
		gs-algo-1.3.jar
		gs-core-1.3.jar

	Se pueden descargar desde el sitio oficial:
		http://graphstream-project.org/doc/

	O bien, desde mi Drive:
		https://drive.google.com/open?id=16sdqOjE0QTGZAwY0bnK3qJQM3f_hjV4v

Una vez descargados, se tienen que colocar en una carpeta de nombre "lib"

	Complejidad
		
		lib
			- gs-algo-1.3.jar
			- gs-core-1.3.jar
		
		Practica01
			- Grafica.java
			- Vertice.java
		
		readme.txt

***** EJECUCIÓN *****
Para compilar el programa es necesario escribir en consola:

	javac -cp .:../lib/gs-core-1.3.jar:../lib/gs-algo-1.3.jar *.java

Para ejecutar el programa es necesario escribir en consola:

	java -cp .:../lib/gs-core-1.3.jar:../lib/gs-algo-1.3.jar Grafica
