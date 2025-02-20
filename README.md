## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

---

## Integrantes: Joan S. Acevedo Aguilar - Cesar A. Borray Suarez

---

# Componentes y conectores - Parte I.

Antes de realizar este ejercicio, se nos pidio realizar un ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones el cual se encuentra ya resulto en el siguiente repositorio externo [*Aqui*](https://github.com/AndresSu2342/ARSW-TallerIntroductorioSpring) o en el siguiente direccionamiento:

https://github.com/AndresSu2342/ARSW-TallerIntroductorioSpring

---

#### Middleware- gestión de planos.

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.

	Dentro de nuestro archivo pom.xml, tenemos agregadas las dependencias necesarias de Spring

	![Image](https://github.com/user-attachments/assets/471676a7-d27d-4654-81f7-26bbca1f345d)

	* Agregar la configuración de Spring.

	Para habilitar la configuración de Spring, debemos crear un archivo de configuración applicationContext.xml donde habilitando component scanning (<context:component-scan>) haremos que Spring buscará automáticamente en el paquete "edu.eci.arsw.blueprints" las clases anotadas con @Component, @Service, @Repository y @Controller para gestionarlas como beans.

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

   ![Image](https://github.com/user-attachments/assets/d1e5c1d1-2c71-4b25-b6c0-1083bdce8ae4)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.
	
	Creamos una clase principal Main en la raiz del proyecto, al mismo nivel que model, persistence y services, el cual rectifica la funcionalidad de nuestra aplicacion.
	
	![Image](https://github.com/user-attachments/assets/7c9c856f-0c41-4a91-9e78-cb818cc7c3a6)

	Se configuró Spring para inyectar las dependencias y realizar las funcionalidades principales que son el registro, consulta de un plano y la consulta por autor.
	Verificamos la impresion del resultado

   	![Image](https://github.com/user-attachments/assets/611452a0-7127-45cf-a290-b1e4f1d84b45)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.

        Para eliminar puntos repetidos consecutivos en un plano, se creó la clase RedundancyFilter, la cual implementa la interfaz BlueprintFilter.

        Primero, se añadió el método filter() en la capa de servicio (BlueprintServices), para que aplique el filtro antes de devolver los resultados.

        ![Image](https://github.com/user-attachments/assets/57dfc4b1-7d69-4b63-b4b7-5937b5524755)

        Luego, se definió el método en la interfaz BlueprintFilter, permitiendo que diferentes filtros puedan ser utilizados en la aplicación.

        ![Image](https://github.com/user-attachments/assets/956f002d-b46b-4a82-9a89-b3a02c36435a)

        Por último, se implementó el filtro en RedundancyFilter, eliminando puntos consecutivos duplicados al procesar los Blueprints

        ![Image](https://github.com/user-attachments/assets/959b4a09-fb35-4f25-b517-322c6de477d5)

    * (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

        Se creó la clase SubsamplingFilter, que selecciona solo la mitad de los puntos de un plano para simplificar la representación.

	    Se modificó BlueprintServices para integrar este nuevo filtro en la lógica de la aplicación.

        ![Image](https://github.com/user-attachments/assets/adc96376-890c-4b5b-a104-6ad086ec72aa)

        Después, se agregó la implementación en SubsamplingFilter, eliminando la mitad de los puntos de cada blueprint.

        ![Image](https://github.com/user-attachments/assets/264cbbea-4ba4-4a89-b828-92775648acb4)

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

	Se agregaron pruebas en BlueprintFilterTest para validar que el filtro de redundancia elimine correctamente los puntos redundantes y que el filtro de submuestreo elimine correctamente los puntos.

	![Image](https://github.com/user-attachments/assets/6efabdd7-713b-46c5-8946-43926e45d9df)

	![Image](https://github.com/user-attachments/assets/cf004831-d04e-4a38-92ed-40b8a2f07f92)

	La ejecucion de ambas pruebas nos sale como correctas

	![Image](https://github.com/user-attachments/assets/5b0d43c6-92fc-4742-808a-c68d8d203934)