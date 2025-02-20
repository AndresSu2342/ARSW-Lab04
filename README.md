## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.

	Dentro de nuestro archivo pom.xml, tenemos agregadas las dependencias necesarias de Spring

	![Image](https://github.com/user-attachments/assets/471676a7-d27d-4654-81f7-26bbca1f345d)

	* Agregar la configuración de Spring.

	Para habilitar la configuración de Spring, debemos crear un archivo de configuración applicationContext.xml donde habilitando component scanning (<context:component-scan>) haremos que Spring buscará automáticamente en el paquete "edu.eci.arsw" las clases anotadas con @Component, @Service, @Repository y @Controller para gestionarlas como beans.

	![Image](https://github.com/user-attachments/assets/5e0f1076-6ac3-4085-97e6-4cea64157ac8)
	
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

	Definimos las anotaciones dentro de las clases para inyectar el esquema de persistencia, segun como se nos muestra en el diagrama

	![Image](https://github.com/user-attachments/assets/38531cb6-6e13-4db3-a2a2-d73eb1cdf61a)

	![Image](https://github.com/user-attachments/assets/037c110e-5dde-4673-81ea-e93542bd9ed1)


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

	Para implementar las nuevas funciones empezamos desde la capa superior que es la clase BlueprintServices, se agrego la funcionalidad al metodo getBluePrint() y se agrego un nuevo metodo para la operacion getBlueprintsByAuthor() del mismo nombre.

	![Image](https://github.com/user-attachments/assets/fd035e5a-2a4f-46d7-8819-2474fc6182f3)

   Ahora bajamos a la siguiente capa que seria la interfaz BlueprintsPersistence donde ya tendriamos el metodo getBluePrint() solo nos faltaria agregar el metodo getBlueprintsByAuthor().

	![Image](https://github.com/user-attachments/assets/0393c00d-2f11-4882-8fbb-bbfb9993181e)
	
	Por ultimo para terminar las operaciones, tendriamos que implementar la funcionalidad sobreescribiendo los metodos definidos en la interfaz en la ultima capa que seria en InMemoryBlueprintPersistence.

	![Image](https://github.com/user-attachments/assets/e5492dc0-6aea-4dde-a57e-245b6b53f3a1)

	De esto ultimo, solo faltaria realizar las pruebas correspondientes de exito y fallo para la operacion getBlueprintsByAuthor().
	
	![Image](https://github.com/user-attachments/assets/fbb29d7e-56d1-4b42-8b32-2c5338225c89)

4. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.
	
	Creamos una clase principal Main en la raiz del proyecto, al mismo nivel que model, persistence y services, el cual rectifica la funcionalidad de nuestra aplicacion.
	
	![Image](https://github.com/user-attachments/assets/7c9c856f-0c41-4a91-9e78-cb818cc7c3a6)

	Se configuró Spring para inyectar las dependencias y realizar las funcionalidades principales que son el registro, consulta de un plano y la consulta por autor.
	Verificamos la impresion del resultado

   	![Image](https://github.com/user-attachments/assets/611452a0-7127-45cf-a290-b1e4f1d84b45)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
